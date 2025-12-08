package com.itschool.springapp.service;

import com.itschool.springapp.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(long id);
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO newUserDTO);
    UserDTO updateUser(long id, UserDTO updatedUserDTO);
    void deleteUser(long id);
}
