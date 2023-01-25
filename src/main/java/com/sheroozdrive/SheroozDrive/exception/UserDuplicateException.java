package com.sheroozdrive.SheroozDrive.exception;

public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException(String id) {
        super("User exist by " + id);
    }
}
