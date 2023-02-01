package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.exception.FolderDuplicateException;
import com.sheroozdrive.SheroozDrive.exception.FolderNotFoundException;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.model.dto.FolderMapperTypeEnum;
import com.sheroozdrive.SheroozDrive.model.mapper.FolderMapper;
import com.sheroozdrive.SheroozDrive.repository.FolderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    public FolderService(FolderRepository folderRepository, FolderMapper folderMapper) {
        this.folderRepository = folderRepository;
        this.folderMapper = folderMapper;
    }

    public List<FolderDto> findByOwnerId(String ownerId) {
        List<Folder> folder=folderRepository.findByOwnerIdAndParentIdNull(ownerId);
        return folder.stream().map(folderMapper::convertToDto).toList();
        /*List<Folder> allFolders = folderRepository.findByOwnerId(ownerId);
        Map<String, Folder> folderMap = allFolders.stream().collect(Collectors.toMap(folder -> folder.getId(), folder -> folder));

        for (Folder folder : allFolders) {
            String parentId = folder.getParentId();
            if (parentId != null && folderMap.containsKey(parentId)) {
                if(folderMap.get(parentId).getChildFolders()==null){
                    folderMap.get(parentId).setChildFolders(new ArrayList<>());
                }
                folderMap.get(parentId).getChildFolders().add(folder);
                folderMap.remove(folder.getId());
            }
        }

        allFolders=new ArrayList<Folder>(folderMap.values());
        return allFolders.stream().map(folderMapper::convertToDto).toList();*/
    }

    public FolderDto findById(String id) {
        Folder folder=folderRepository.findById(id).orElseThrow(() -> new FolderNotFoundException(id));
        return folderMapper.convertToDto(folder);
    }

    public FolderDto save(FolderDto folderDto) {
        Folder folder;
        if(folderDto.id()!=null) {
            folder=folderRepository.findById(folderDto.id()).orElseThrow(() -> new FolderDuplicateException(folderDto.name()));
            BeanUtils.copyProperties(folderDto, folder);
        }else{
            if(folderRepository.existsByNameAndParentId(folderDto.name(),folderDto.parentId()))
                throw new FolderDuplicateException(folderDto.name());
            folder=folderMapper.convertToModel(folderDto);
        }
        folder=folderRepository.save(folder);
        if(folder.getParentId()!=null){
            Folder parentFolder=folderRepository.findById(folder.getParentId()).orElseThrow(() -> new FolderDuplicateException(folderDto.name()));
            if(parentFolder.getChildFolders()==null){
                parentFolder.setChildFolders(new ArrayList<>());
            }
            parentFolder.getChildFolders().add(folder);
            folderRepository.save(parentFolder);
        }
        return folderMapper.convertToDto(folder);
    }

    public void delete(String id) {
        folderRepository.deleteById(id);
    }

    public FolderDto findByPath(String path) {
        List<String> items = Arrays.asList(path.split("/"));
        String parentId=null;
        Folder folder=null;
        for (String item:items) {
            folder=folderRepository.findByNameAndParentId(item,parentId).orElseThrow(() -> new FolderNotFoundException(item));
            parentId=folder.getId();
        }
        return folderMapper.convertToDto(folder, FolderMapperTypeEnum.FIRST_CHILD);
    }
}
