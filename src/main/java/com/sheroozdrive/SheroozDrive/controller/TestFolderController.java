package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.service.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/folders")
public class TestFolderController {
    private final FolderService folderService;

    public TestFolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderDto create(@RequestBody FolderDto folder) {
        return folderService.testSave(folder);
    }
}
