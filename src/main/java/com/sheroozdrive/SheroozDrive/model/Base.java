package com.sheroozdrive.SheroozDrive.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Base {
    @Id
    private String id;

    public Base(String id) {
        this.id = id;
    }
}
