package com.sheroozdrive.SheroozDrive.service;

import com.sheroozdrive.SheroozDrive.exception.UserDuplicateException;
import com.sheroozdrive.SheroozDrive.exception.UserNotFoundException;
import com.sheroozdrive.SheroozDrive.model.Folder;
import com.sheroozdrive.SheroozDrive.model.User;
import com.sheroozdrive.SheroozDrive.model.dto.UserDto;
import com.sheroozdrive.SheroozDrive.model.mapper.UserMapper;
import com.sheroozdrive.SheroozDrive.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserDto> findAll(){
        List<User> users=userRepository.findAll();
        return users.stream().map(userMapper::convertToDto).toList();
    }

    public UserDto save(UserDto userDto) {
        User user;
        if(userDto.id()!=null) {
            user=userRepository.findById(userDto.id()).orElseThrow(() -> new UserNotFoundException(userDto.id()));
            BeanUtils.copyProperties(userDto, user);
        }else {
            if (userRepository.existsByEmail(userDto.email()))
                throw new UserDuplicateException(userDto.email());
            user=userMapper.convertToModel(userDto);
        }

        return userMapper.convertToDto(userRepository.save(user));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}