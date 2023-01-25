package com.sheroozdrive.SheroozDrive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserDuplicateExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userDuplicateExceptionAdvice(UserDuplicateException e) {
        return e.getMessage();
    }
}
