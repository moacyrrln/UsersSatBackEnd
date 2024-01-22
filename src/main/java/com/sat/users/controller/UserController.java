// E:\dev\SAT/users\src\main\java\com\sat/users\controller\UserController.java
package com.sat.users.controller;

import com.sat.users.dto.UserDTO;
import com.sat.users.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();

    if (users.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(users);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    UserDTO userDTO = userService.getUserById(id);
    return (userDTO != null) ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    UserDTO createdUser = userService.createUser(userDTO);
    return ResponseEntity.ok(createdUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    UserDTO updatedUser = userService.updateUser(id, userDTO);
    return (updatedUser != null) ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    boolean deleted = userService.deleteUser(id);
    return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}

