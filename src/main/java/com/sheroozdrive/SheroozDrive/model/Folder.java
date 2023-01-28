package com.sheroozdrive.SheroozDrive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Folder extends Base{
    private String name;
    private String ownerId;
    private String parentId;

    @DBRef
    private List<File> files;

    @DBRef
    private List<Folder> childFolders;

    public Folder(String id, String name, String ownerId, String parentId) {
        super(id);
        this.name = name;
        this.ownerId = ownerId;
        this.parentId = parentId;
    }
}
