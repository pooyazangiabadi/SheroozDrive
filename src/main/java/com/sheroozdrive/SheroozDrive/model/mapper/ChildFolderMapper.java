package com.sheroozdrive.SheroozDrive.model.mapper;

import com.google.common.base.Strings;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChildFolderMapper implements BaseMapper<Folder, FolderDto> {
    @Override
    public FolderDto convertToDto(Folder model) {
        return new FolderDto(model.getId(),
                model.getName(),
                Optional.ofNullable(model.getOwner()).orElse(new User()).getId(),
                Optional.ofNullable(model.getParent()).orElse(new Folder()).getId());
    }

    @Override
    public Folder convertToModel(FolderDto dto) {
        return new Folder(dto.id(),
                dto.name(),
                Strings.isNullOrEmpty(dto.ownerId())?null:new User(dto.ownerId()),
                Strings.isNullOrEmpty(dto.parentId())?null:new Folder(dto.parentId()));
    }
}
