package com.sheroozdrive.SheroozDrive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = {"/",""})
    public String Main(){
        return "Hello, SheroozDrive! :)";
    }
}
