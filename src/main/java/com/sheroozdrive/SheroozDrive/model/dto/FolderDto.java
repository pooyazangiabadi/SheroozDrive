package com.sheroozdrive.SheroozDrive.model.dto;

import java.util.List;

public record FolderDto(String id,
                        String name,
                        String ownerId,
                        String parentId,
                        List<FolderDto> folders,
                        List<FileDto> files) {
    public FolderDto(String id, String name, String ownerId, String parentId, List<FolderDto> folders, List<FileDto> files) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.parentId = parentId;
        this.folders = folders;
        this.files = files;
    }

    public FolderDto(String id, String name, String ownerId, String parentId) {
        this(id, name, ownerId, parentId, null, null);
    }
}
