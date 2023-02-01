package com.sheroozdrive.SheroozDrive.model.mapper;

import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.dto.FolderDto;
import com.sheroozdrive.SheroozDrive.model.dto.FolderMapperTypeEnum;
import org.springframework.stereotype.Component;

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
                model.getOwnerId(),
                model.getParentId(),
                type==FolderMapperTypeEnum.ALL
                        ?emptyIfNull(model.getChildFolders()).stream().map(this::convertToDto).toList()
                        :emptyIfNull(model.getChildFolders()).stream().map(childFolderMapper::convertToDto).toList(),
                emptyIfNull(model.getFiles()).stream().map(fileMapper::convertToDto).toList());
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
