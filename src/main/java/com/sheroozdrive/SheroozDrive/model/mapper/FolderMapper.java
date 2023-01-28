package com.sheroozdrive.SheroozDrive.model.mapper;

import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Component
public class FolderMapper implements BaseMapper<Folder, FolderDto> {
    private final FileMapper fileMapper;

    public FolderMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public FolderDto convertToDto(Folder model) {
        return new FolderDto(model.getId(),
                model.getName(),
                model.getOwnerId(),
                model.getParentId(),
                emptyIfNull(model.getChildFolders()).stream().map(this::convertToDto).toList(),
                emptyIfNull(model.getFiles()).stream().map(fileMapper::convertToDto).toList());
    }

    @Override
    public Folder convertToModel(FolderDto dto) {
        return new Folder(dto.id(),
                dto.name(),
                dto.ownerId(),
                dto.parentId());
    }
}
