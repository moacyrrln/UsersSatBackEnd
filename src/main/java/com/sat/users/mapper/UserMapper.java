// E:/dev/SAT/users/src\main\java\com\sat/users\mapper\UserMapper.java
package com.sat.users.mapper;

import com.sat.users.dto.UserDTO;
import com.sat.users.model.Users;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {
  public UserDTO mapToDTO(Users user) {
    return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getPhone());
  }

  public Users mapToEntity(UserDTO userDTO) {
    return new Users(userDTO.getName(), userDTO.getEmail(), userDTO.getCpf(),
        userDTO.getPhone());
  }
}
