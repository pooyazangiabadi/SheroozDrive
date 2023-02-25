package com.sheroozdrive.SheroozDrive.model.mapper;

import com.google.common.base.Strings;
import com.sheroozdrive.SheroozDrive.model.File;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.FileDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FileMapper implements BaseMapper<File, FileDto> {
    @Override
    public FileDto convertToDto(File model) {
        return new FileDto(model.getId(),
                model.getName(),
                model.getSize(),
                Optional.ofNullable(model.getOwner()).orElse(new User()).getId(),
                Optional.ofNullable(model.getFolder()).orElse(new Folder()).getId(),
                model.getType(),
                model.getThumbnail()
        );
    }

    @Override
    public File convertToModel(FileDto dto) {
        return new File(dto.id(),
                dto.name(),
                dto.size(),
                Strings.isNullOrEmpty(dto.ownerId())?null:new User(dto.ownerId()),
                Strings.isNullOrEmpty(dto.folderId())?null:new Folder(dto.folderId()),
                dto.type(),
                dto.thumbnail()
        );
    }
}
