package com.sheroozdrive.SheroozDrive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FolderNotFoundExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(FolderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String folderNotFoundExceptionAdvice(FolderNotFoundException e) {
        return e.getMessage();
    }
}
