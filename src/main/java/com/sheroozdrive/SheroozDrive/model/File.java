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
    private String ownerId;
    private Folder folder;

    public File(String id, String name, long size, String ownerId) {
        super(id);
        this.name = name;
        this.size = size;
        this.ownerId = ownerId;
    }
}
