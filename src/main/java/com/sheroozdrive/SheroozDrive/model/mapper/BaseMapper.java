package com.sheroozdrive.SheroozDrive.model.mapper;

public interface BaseMapper<M,D> {
    D convertToDto(M model);
    M convertToModel(D dto);
}
