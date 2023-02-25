package com.sheroozdrive.SheroozDrive.model.dto;

import org.bson.types.Binary;

public record FileDto(String id,
                      String name,
                      long size,
                      String ownerId,
                      String folderId,
                      String type,
                      Binary thumbnail) {
}
