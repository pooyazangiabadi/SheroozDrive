package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.exception.UserDuplicateException;
import com.sheroozdrive.SheroozDrive.exception.UserNotFoundException;
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

    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user==null)
            throw new UserNotFoundException(email);

        return userMapper.convertToDto(user);
    }

    public UserDto findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.convertToDto(user);
    }

    public UserDto save(UserDto userDto) {
        if(userDto.id()!=null)
            if (!userRepository.existsById(userDto.id()))
                throw new UserNotFoundException(userDto.id());

        if(userRepository.existsByEmail(userDto.email()))
            throw new UserDuplicateException(userDto.email());

        User user=userMapper.convertToModel(userDto);
        return userMapper.convertToDto(userRepository.save(user));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}