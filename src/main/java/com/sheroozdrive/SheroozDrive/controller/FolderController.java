package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/{id}")
    public Folder findById(@PathVariable String id) {
        return folderService.findById(id);
    }

    @GetMapping("/owner/{ownerId}")
    public List<Folder> findByOwnerId(@PathVariable String ownerId) {
        return folderService.findByOwnerId(ownerId);
    }

    @PostMapping
    public Folder create(@RequestBody Folder folder) {
        return folderService.save(folder);
    }

    @PutMapping
    public Folder update(@RequestBody Folder folder) {
        return folderService.save(folder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        folderService.delete(id);
    }
}
