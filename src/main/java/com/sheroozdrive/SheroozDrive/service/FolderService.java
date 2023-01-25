package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.exception.FolderDuplicateException;
import com.sheroozdrive.SheroozDrive.exception.FolderNotFoundException;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.model.mapper.FolderMapper;
import com.sheroozdrive.SheroozDrive.repository.FolderRepository;
import org.springframework.stereotype.Service;

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
        List<Folder> folder=folderRepository.findByOwnerId(ownerId);
        return folder.stream().map(folderMapper::convertToDto).toList();
    }

    public FolderDto findById(String id) {
        Folder folder=folderRepository.findById(id).orElseThrow(() -> new FolderNotFoundException(id));
        return folderMapper.convertToDto(folder);
    }

    public FolderDto save(FolderDto folderDto) {
        if(folderRepository.existsByNameAndParentId(folderDto.name(),folderDto.parentId()))
            throw new FolderDuplicateException(folderDto.name());

        Folder folder=folderMapper.convertToModel(folderDto);
        return folderMapper.convertToDto(folderRepository.save(folder));
    }

    public void delete(String id) {
        folderRepository.deleteById(id);
    }
}
