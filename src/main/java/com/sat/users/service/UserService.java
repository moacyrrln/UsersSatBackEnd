// E:\dev\SAT/users\src\main\java\com\sat/users\service\UserService.java
package com.sat.users.service;

import com.sat.users.dto.UserDTO;
import com.sat.users.mapper.UserMapper;
import com.sat.users.model.Users;
import com.sat.users.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  public List<UserDTO> getAllUsers() {
    List<Users> users = userRepository.findAll();

    if (users.isEmpty()) {
      return Collections.emptyList();
    } else {
      return users.stream()
          .map(userMapper::mapToDTO)
          .collect(Collectors.toList());
    }
  }

  public UserDTO getUserById(Long id) {
    Users user = userRepository.findById(id).orElse(null);
    return (user != null) ? userMapper.mapToDTO(user) : null;
  }

  public UserDTO createUser(UserDTO userDTO) {
    validateUserDTO(userDTO);
    Users user = userMapper.mapToEntity(userDTO);
    userRepository.save(user);
    return userMapper.mapToDTO(user);
  }

  private void validateUserDTO(UserDTO userDTO) {
    if (userDTO.getName() == null || userDTO.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }
    if (userDTO.getCpf() == 0) {
      throw new IllegalArgumentException("CPF cannot be null or empty");
    }
    if (userDTO.getPhone() == 0) {
      throw new IllegalArgumentException("Phone number cannot be null or empty");
    }
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {
    Users existingUser = userRepository.findById(id).orElse(null);

    if (existingUser != null) {
      if (!(userDTO.getName() == null || userDTO.getName().trim().isEmpty())) existingUser.setName(userDTO.getName());
      if (!(userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty())) existingUser.setEmail(userDTO.getEmail());
      existingUser.setCpf(userDTO.getCpf());
      existingUser.setPhone(userDTO.getPhone());
      userRepository.save(existingUser);
      return userMapper.mapToDTO(existingUser);
    }

    return null;
  }

  public boolean deleteUser(Long userId) {
    Users existingUser = userRepository.findById(userId).orElse(null);

    if (existingUser != null) {
      userRepository.delete(existingUser);
      return true;
    }

    return false;
  }
}

