package com.sheroozdrive.SheroozDrive.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record UserDto(String id,
                      String name,
                      String email,
                      String password) {
}
