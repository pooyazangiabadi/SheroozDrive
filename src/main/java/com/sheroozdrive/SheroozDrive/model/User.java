package com.sheroozdrive.SheroozDrive.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    @DBRef
    private List<File> files;

    @DBRef
    private List<Folder> folders;
}
