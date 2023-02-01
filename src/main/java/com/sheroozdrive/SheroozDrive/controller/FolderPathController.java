package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.service.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_")
public class FolderPathController {

    private final FolderService folderService;

    public FolderPathController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/**")
    public FolderDto findByPath(HttpServletRequest request){
        String uri = request.getRequestURI();
        String path = uri.substring(3);
        return folderService.findByPath(path);
    }
}