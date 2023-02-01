package com.sheroozdrive.SheroozDrive.model.mapper;

import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import org.springframework.stereotype.Component;

@Component
public class ChildFolderMapper implements BaseMapper<Folder, FolderDto> {
    @Override
    public FolderDto convertToDto(Folder model) {
        return new FolderDto(model.getId(),
                model.getName(),
                model.getOwnerId(),
                model.getParentId());
    }

    @Override
    public Folder convertToModel(FolderDto dto) {
        return new Folder(dto.id(),
                dto.name(),
                dto.ownerId(),
                dto.parentId());
    }
}
