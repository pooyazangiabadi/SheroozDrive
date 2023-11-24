package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.service.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/folders")
public class FolderController implements SecuredRestController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping("/{id}")
    public FolderDto findById(@PathVariable String id) {
        return folderService.findById(id);
    }

    @GetMapping("/")
    public FolderDto findByPath(@RequestParam String path){
        return folderService.findByPath(path);
    }

    @GetMapping("/owner/{ownerId}")
    public List<FolderDto> findByOwnerId(@PathVariable String ownerId) {
        return folderService.findByOwnerId(ownerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderDto create(@RequestBody FolderDto folder) {
        return folderService.save(folder);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public FolderDto update(@RequestBody FolderDto folder) {
        return folderService.save(folder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        folderService.delete(id);
    }
}
