package com.sheroozdrive.SheroozDrive.exception;

public class FolderDuplicateException extends RuntimeException{
    public FolderDuplicateException(String name) {
        super("Folder exist by " + name);
    }
}
