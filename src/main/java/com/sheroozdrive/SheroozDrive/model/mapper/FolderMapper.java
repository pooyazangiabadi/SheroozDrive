package com.sheroozdrive.SheroozDrive.model.mapper;

import com.google.common.base.Strings;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.model.dto.FolderMapperTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Component
public class FolderMapper implements BaseMapper<Folder, FolderDto> {
    private final FileMapper fileMapper;
    private final ChildFolderMapper childFolderMapper;

    public FolderMapper(FileMapper fileMapper, ChildFolderMapper childFolderMapper) {
        this.fileMapper = fileMapper;
        this.childFolderMapper = childFolderMapper;
    }


    public FolderDto convertToDto(Folder model, FolderMapperTypeEnum type) {
        return new FolderDto(model.getId(),
                model.getName(),
                Optional.ofNullable(model.getOwner()).orElse(new User()).getId(),
                Optional.ofNullable(model.getParent()).orElse(new Folder()).getId(),
                type==FolderMapperTypeEnum.ALL
                        ?emptyIfNull(model.getChildFolders()).stream().map(this::convertToDto).toList()
                        :emptyIfNull(model.getChildFolders()).stream().map(childFolderMapper::convertToDto).toList(),
                emptyIfNull(model.getFiles()).stream().map(fileMapper::convertToDto).toList());
    }

    @Override
    public FolderDto convertToDto(Folder model) {
        return new FolderDto(model.getId(),
                model.getName(),
                Optional.ofNullable(model.getOwner()).orElse(new User()).getId(),
                Optional.ofNullable(model.getParent()).orElse(new Folder()).getId(),
                emptyIfNull(model.getChildFolders()).stream().map(this::convertToDto).toList(),
                emptyIfNull(model.getFiles()).stream().map(fileMapper::convertToDto).toList());
    }

    @Override
    public Folder convertToModel(FolderDto dto) {
        return new Folder(dto.id(),
                dto.name(),
                Strings.isNullOrEmpty(dto.ownerId())?null:new User(dto.ownerId()),
                Strings.isNullOrEmpty(dto.parentId())?null:new Folder(dto.parentId()));
    }
}
