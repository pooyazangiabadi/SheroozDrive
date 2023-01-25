package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.UserDto;
import com.sheroozdrive.SheroozDrive.model.mapper.UserMapper;
import com.sheroozdrive.SheroozDrive.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDto save(UserDto userDto) {
        User user=new User();
        user=userMapper.convertToModel(userDto);
        return userMapper.convertToDto(userRepository.save(user));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}