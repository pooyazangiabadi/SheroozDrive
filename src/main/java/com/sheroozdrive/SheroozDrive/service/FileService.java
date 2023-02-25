package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.exception.FolderNotFoundException;
import com.sheroozdrive.SheroozDrive.model.File;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.repository.FileRepository;
import com.sheroozdrive.SheroozDrive.repository.FolderRepository;
import com.sheroozdrive.SheroozDrive.util.ThumbnailGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final ThumbnailGenerator thumbnailGenerator;


    @Value("${setting.upload.dir}")
    private String uploadDir;

    @Value("${setting.upload.maxsize}")
    private int maxUploadSize;

    public ResponseEntity<?> uploadFile(String folderId,MultipartFile file) throws IOException {

        if (!Arrays.asList("application/pdf", "video/mp4", "image/jpeg", "image/png").contains(file.getContentType())) {
            return new ResponseEntity<>("Unsupported media type.", HttpStatus.BAD_REQUEST);
        }

        if (file.getSize() > maxUploadSize) {
            return new ResponseEntity<>("File size exceeds the limit.", HttpStatus.BAD_REQUEST);
        }

        File fileEntity = new File();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setSize(file.getSize());
        if(folderId!=null) {
            fileEntity.setFolder(new Folder(folderId));
        }

        fileEntity = fileRepository.save(fileEntity);

        if(fileEntity.getFolder()!=null){
            Folder folder=folderRepository.findById(fileEntity.getFolder().getId()).orElseThrow(() -> new FolderNotFoundException(folderId));
            if(folder.getFiles()==null){
                folder.setFiles(new ArrayList<>());
            }
            folder.getFiles().add(fileEntity);
            folderRepository.save(folder);
        }

        String fileName = fileEntity.getId() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filePath = System.getProperty("user.dir") + uploadDir + fileName;
        java.io.File destFile = new java.io.File(filePath);
        file.transferTo(destFile);

        thumbnailGenerator.generateThumbnail(destFile);

        return new ResponseEntity<>(fileEntity, HttpStatus.OK);
    }

    public ResponseEntity<Resource> getFile(String id) throws IOException {

        Optional<File> fileOptional = fileRepository.findById(id);
        if (!fileOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File fileEntity = fileOptional.get();
        String fileName = fileEntity.getId() + "." + StringUtils.getFilenameExtension(fileEntity.getName());
        String filePath = System.getProperty("user.dir") + "/uploads/" + fileName;
        java.io.File file = new java.io.File(filePath);
        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] fileContent = Files.readAllBytes(file.toPath());
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileEntity.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileContent.length)
                .contentType(MediaType.parseMediaType(fileEntity.getType()))
                .body(resource);
    }

    public FileService(FileRepository fileRepository, FolderRepository folderRepository, ThumbnailGenerator thumbnailGenerator) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.thumbnailGenerator = thumbnailGenerator;
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
