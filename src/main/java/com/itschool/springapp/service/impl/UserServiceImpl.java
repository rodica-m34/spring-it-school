package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.User;
import com.itschool.springapp.model.UserDTO;
import com.itschool.springapp.repository.UserRepository;
import com.itschool.springapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(long id) {
        User foundUserEntity = userRepository.findById(id)
                .orElseThrow();
        return toUserDTO(foundUserEntity);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUserEntities = userRepository.findAll();

        return allUserEntities.stream()
                .map(this::toUserDTO)
                .toList();
    }

    @Override
    public UserDTO createUser(UserDTO newUserDTO) {
        User userEntity = toUserEntity(newUserDTO);
        User createdUserEntity = userRepository.save(userEntity);
        return toUserDTO(createdUserEntity);
    }

    @Override
    public UserDTO updateUser(long id, UserDTO updatedUserDTO) {
        User userEntity = toUserEntity(updatedUserDTO);
        userEntity.setId(id);
        User updatedUserEntity = userRepository.save(userEntity);
        return toUserDTO(updatedUserEntity);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getAge());
    }

    private User toUserEntity(UserDTO userDTO) {
        return new User(userDTO.name(), userDTO.email(), userDTO.age());
    }
}
