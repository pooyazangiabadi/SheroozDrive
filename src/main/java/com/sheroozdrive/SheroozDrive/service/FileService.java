package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.model.File;
import com.sheroozdrive.SheroozDrive.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> findByOwnerId(String ownerId) {
        return fileRepository.findByOwnerId(ownerId);
    }

    public File findById(String id) {
        return fileRepository.findById(id).orElse(null);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public void delete(String id) {
        fileRepository.deleteById(id);
    }
}
