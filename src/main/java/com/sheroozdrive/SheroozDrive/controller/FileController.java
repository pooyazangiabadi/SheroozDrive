package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.File;
import com.sheroozdrive.SheroozDrive.service.FileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController implements SecuredRestController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public File findById(@PathVariable String id) {
        return fileService.findById(id);
    }

    @GetMapping("/owner/{ownerId}")
    public List<File> findByOwnerId(@PathVariable String ownerId) {
        return fileService.findByOwnerId(ownerId);
    }

    @PostMapping
    public File create(@RequestBody File file) {
        return fileService.save(file);
    }

    @PutMapping
    public File update(@RequestBody File file) {
        return fileService.save(file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        fileService.delete(id);
    }
}
