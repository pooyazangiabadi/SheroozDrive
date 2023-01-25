package com.sheroozdrive.SheroozDrive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FolderDuplicateExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(FolderDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String folderDuplicateExceptionAdvice(FolderDuplicateException e) {
        return e.getMessage();
    }
}
