package com.sheroozdrive.SheroozDrive.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class File {
    @Id
    private String id;
    private String name;
    private long size;
    private String ownerId;
}
