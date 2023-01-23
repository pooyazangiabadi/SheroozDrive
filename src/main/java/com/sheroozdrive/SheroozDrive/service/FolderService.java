package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public List<Folder> findByOwnerId(String ownerId) {
        return folderRepository.findByOwnerId(ownerId);
    }

    public Folder findById(String id) {
        return folderRepository.findById(id).orElse(null);
    }

    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    public void delete(String id) {
        folderRepository.deleteById(id);
    }
}
