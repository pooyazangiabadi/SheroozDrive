package com.sheroozdrive.SheroozDrive.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class File extends Base{
    private String name;
    private long size;
    private User owner;
    private Folder folder;
    private String type;

    public File(String id, String name, long size, User owner, Folder folder, String type) {
        super(id);
        this.name = name;
        this.size = size;
        this.owner = owner;
        this.folder = folder;
        this.type = type;
    }
}
