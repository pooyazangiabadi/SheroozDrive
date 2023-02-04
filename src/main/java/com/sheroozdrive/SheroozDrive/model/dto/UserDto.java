package com.sheroozdrive.SheroozDrive.model.dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(String id,
                      @NotNull(message = "Name not be null") String name,
                      @NotNull(message = "Email not be null") String email,
                      String password,
                      RoleEnum role) {
}
