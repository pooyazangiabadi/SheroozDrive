package com.sheroozdrive.SheroozDrive.model.dto;

import java.util.List;

public record FolderDto(String id,
                        String name,
                        String ownerId,
                        String parentId,
                        List<String> fileIds) {

}
