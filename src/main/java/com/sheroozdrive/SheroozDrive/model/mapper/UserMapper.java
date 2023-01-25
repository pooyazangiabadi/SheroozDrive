package com.sheroozdrive.SheroozDrive.model.mapper;

import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDto> {
    @Override
    public UserDto convertToDto(User model) {
        return new UserDto(model.getId(),
                model.getName(),
                model.getEmail(),
                model.getPassword());
    }

    @Override
    public User convertToModel(UserDto dto) {
        return new User(dto.id(),
                dto.name(),
                dto.email(),
                dto.password());
    }
}
