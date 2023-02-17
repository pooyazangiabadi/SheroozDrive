package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.auth.IAuthenticationFacade;
import com.sheroozdrive.SheroozDrive.exception.FolderDuplicateException;
import com.sheroozdrive.SheroozDrive.exception.FolderNotFoundException;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.model.dto.FolderMapperTypeEnum;
import com.sheroozdrive.SheroozDrive.model.dto.Notification;
import com.sheroozdrive.SheroozDrive.model.mapper.FolderMapper;
import com.sheroozdrive.SheroozDrive.repository.FileRepository;
import com.sheroozdrive.SheroozDrive.repository.FolderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;
    private final FileRepository fileRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    Logger logger = LoggerFactory.getLogger(FolderService.class);

    public FolderService(FolderRepository folderRepository, FolderMapper folderMapper, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.folderMapper = folderMapper;
        this.fileRepository = fileRepository;
    }

    public User getUser(){
        return (User) authenticationFacade.getAuthentication().getPrincipal();
    }

    public List<FolderDto> findByOwnerId(String ownerId) {
        List<Folder> folder=folderRepository.findByOwnerIdAndParentIdNull(ownerId);
        return folder.stream().map(folderMapper::convertToDto).toList();
    }

    public FolderDto findById(String id) {
        User user=getUser();
        Folder folder=null;
        if(user.getRole().equals("ADMIN")) {
            folder = folderRepository.findById(id).orElseThrow(() -> new FolderNotFoundException(id));
        }else {
            folder = folderRepository.findByIdAndOwnerId(id,user.getId()).orElseThrow(() -> new FolderNotFoundException(id));
        }
        return folderMapper.convertToDto(folder);
    }

    @Transactional
    public FolderDto save(FolderDto folderDto) {
        Folder folder;
        User user=getUser();
        if(folderDto.id()!=null) {
            folder=folderRepository.findById(folderDto.id()).orElseThrow(() -> new FolderDuplicateException(folderDto.name()));
            BeanUtils.copyProperties(folderDto, folder);
        }else{
            if(folderRepository.existsByNameAndParentId(folderDto.name(),user.getId()))
                throw new FolderDuplicateException(folderDto.name());
            folder=folderMapper.convertToModel(folderDto);
        }
        folder.setOwner(user);
        folder=folderRepository.save(folder);
        if(folder.getParent()!=null){
            Folder parentFolder=folderRepository.findById(folder.getParent().getId()).orElseThrow(() -> new FolderDuplicateException(folderDto.name()));
            if(parentFolder.getChildFolders()==null){
                parentFolder.setChildFolders(new ArrayList<>());
            }
            parentFolder.getChildFolders().add(folder);
            folderRepository.save(parentFolder);
        }
        return folderMapper.convertToDto(folder);
    }

    public void delete(String id) {
        User user=getUser();
        if(user.getRole().equals("ADMIN")) {
            if(!folderRepository.existsById(id)){
                throw new FolderNotFoundException(id);
            }
            folderRepository.deleteById(id);
        }else {
            if(!folderRepository.existsByIdAndOwnerId(id, user.getId())){
                throw new FolderNotFoundException(id);
            }
            folderRepository.deleteByIdAndOwnerId(id,user.getId());
        }
    }

    public FolderDto findByPath(String path) {
        String[] items = path.split("/");
        String parentId=null;
        Folder folder=null;

        String ownerId=getUser().getId();

        Notification notification = new Notification();
        notification.setType("Leave");
        notification.setUser(ownerId);
        messagingTemplate.convertAndSend("/topic/public", notification);

        if (items.length>=1 && items[0].equals("root")) {
            if(items.length==1){
                folder=new Folder(null,"root",null,null);
                folder.setChildFolders(folderRepository.findByParentIsNullAndOwnerId(ownerId).orElse(new ArrayList<>()));
                folder.setFiles(fileRepository.findByFolderIsNull().orElse(new ArrayList<>()));
            }else {
                items = Arrays.copyOfRange(items, 1, items.length);
                for (String item:items) {
                    folder=folderRepository.findByNameAndParentIdAndOwnerId(item,parentId,ownerId).orElseThrow(() -> new FolderNotFoundException(item));
                    parentId=folder.getId();
                }
            }
        }else{
            throw new FolderNotFoundException(path);
        }


        return folderMapper.convertToDto(folder, FolderMapperTypeEnum.FIRST_CHILD);
    }
}
