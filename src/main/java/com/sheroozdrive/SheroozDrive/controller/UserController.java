package com.sheroozdrive.SheroozDrive.controller;

import com.sheroozdrive.SheroozDrive.model.dto.UserDto;
import com.sheroozdrive.SheroozDrive.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/email/{email}")
    public UserDto findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}