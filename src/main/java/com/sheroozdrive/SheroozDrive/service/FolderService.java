package com.sheroozdrive.SheroozDrive.service;

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

    public List<Folder> findByOwnerId(String ownerId) {
        return folderRepository.findByOwnerId(ownerId);
    }

    public Folder findById(String id) {
        return folderRepository.findById(id).orElse(null);
    }

    public FolderDto save(FolderDto folderDto) {
        Folder folder=new Folder();
        folder=folderMapper.convertToModel(folderDto);
        return folderMapper.convertToDto(folderRepository.save(folder));
    }

    public void delete(String id) {
        folderRepository.deleteById(id);
    }
}
