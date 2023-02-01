package com.sheroozdrive.SheroozDrive.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Folder extends Base{
    private String name;
    private User owner;
    private Folder parent;

    @DBRef
    private List<File> files;

    @DBRef
    private List<Folder> childFolders;

    public Folder(String id, String name, User owner, Folder parent) {
        super(id);
        this.name = name;
        this.owner = owner;
        this.parent = parent;
    }

    public Folder(String id) {
        super(id);
    }
}
