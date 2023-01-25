package com.sheroozdrive.SheroozDrive.exception;

public class FolderNotFoundException extends RuntimeException{
    public FolderNotFoundException(String id) {
        super("Could not find folder by " + id);
    }
}
