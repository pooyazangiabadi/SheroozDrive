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
public class Folder {
    @Id
    private String id;
    private String name;
    private String ownerId;
    private List<String> fileIds;

    @DBRef
    private List<File> files;
}
