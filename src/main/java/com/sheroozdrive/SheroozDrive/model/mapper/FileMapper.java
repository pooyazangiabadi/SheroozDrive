package com.sheroozdrive.SheroozDrive.model.mapper;

import com.sheroozdrive.SheroozDrive.model.File;
import com.sheroozdrive.SheroozDrive.model.dto.FileDto;
import org.springframework.stereotype.Component;

@Component
public class FileMapper implements BaseMapper<File, FileDto> {
    @Override
    public FileDto convertToDto(File model) {
        return new FileDto(model.getId(),
                model.getName(),
                model.getSize(),
                model.getOwnerId());
    }

    @Override
    public File convertToModel(FileDto dto) {
        return new File(dto.id(),
                dto.name(),
                dto.size(),
                dto.ownerId());
    }
}
