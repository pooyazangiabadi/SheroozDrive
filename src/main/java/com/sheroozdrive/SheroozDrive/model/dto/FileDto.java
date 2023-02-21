package com.sheroozdrive.SheroozDrive.model.dto;

public record FileDto(String id,
                      String name,
                      long size,
                      String ownerId,
                      String folderId,
                      String type) {
}
