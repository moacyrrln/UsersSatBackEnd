// UserServiceTest.java
package com.sat.users.service;

import com.sat.users.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;

  @Test
  @DirtiesContext
  public void testGetAllUsers() {
    var users = userService.getAllUsers();
    Assertions.assertEquals(0, users.size());

    UserDTO newUser1 = new UserDTO("User1", "user1@email.com", 1, 1);
    userService.createUser(newUser1);
    UserDTO newUser2 = new UserDTO("User2", "user2@email.com", 2, 2);
    userService.createUser(newUser2);

    users = userService.getAllUsers();

    Assertions.assertEquals(2, users.size());

    Assertions.assertEquals(1L, users.get(0).getId() );
    Assertions.assertEquals("User1", users.get(0).getName() );
    Assertions.assertEquals("user1@email.com", users.get(0).getEmail() );
    Assertions.assertEquals(1, users.get(0).getCpf() );
    Assertions.assertEquals(1, users.get(0).getPhone() );

    Assertions.assertEquals(2L, users.get(1).getId() );
    Assertions.assertEquals("User2", users.get(1).getName() );
    Assertions.assertEquals("user2@email.com", users.get(1).getEmail() );
    Assertions.assertEquals(2, users.get(1).getCpf() );
    Assertions.assertEquals(2, users.get(1).getPhone() );
  }

  @Test
  @DirtiesContext
  public void testGetUserById() {
    UserDTO newUser = new UserDTO("Moacyr Ribeiro Leal Neto", "moacyr@email.com", 1, 1);

    UserDTO createdUser = userService.createUser(newUser);
    var user = userService.getUserById(1L);

    Assertions.assertEquals(1, user.getId());
    Assertions.assertEquals("Moacyr Ribeiro Leal Neto", user.getName() );
    Assertions.assertEquals("moacyr@email.com", user.getEmail() );
    Assertions.assertEquals(1, user.getCpf() );
    Assertions.assertEquals(1, user.getPhone() );

    user = userService.getUserById(2L);
    Assertions.assertNull(user);
  }

  @Test
  @DirtiesContext
  public void testCreateUser() {
    UserDTO newUser = new UserDTO("Moacyr Ribeiro Leal Neto", "moacyr@email.com",1,1);

    UserDTO createdUser = userService.createUser(newUser);
    var users = userService.getAllUsers();

    Assertions.assertEquals(1, users.size());
    Assertions.assertEquals(1L, users.get(0).getId() );
    Assertions.assertEquals("Moacyr Ribeiro Leal Neto", users.get(0).getName() );
    Assertions.assertEquals("moacyr@email.com", users.get(0).getEmail() );
    Assertions.assertEquals(1, users.get(0).getCpf() );
    Assertions.assertEquals(1, users.get(0).getPhone() );
  }

  @Test
  @DirtiesContext
  public void testUpdateUser() {
    UserDTO user = new UserDTO("User1", "user1@email.com",1,1);
    userService.createUser(user);

    user = new UserDTO("User2", "user2@email.com",2,2);
    userService.updateUser(1L, user);
    userService.updateUser(3L, user);

    var users = userService.getUserById(1L);

    Assertions.assertEquals(1, users.getId());
    Assertions.assertEquals("User2", users.getName() );
    Assertions.assertEquals("user2@email.com", users.getEmail() );
    Assertions.assertEquals(2, users.getCpf() );
    Assertions.assertEquals(2, users.getPhone() );
  }

  @Test
  @DirtiesContext
  public void testDeleteUser() {
    UserDTO newUser1 = new UserDTO("User1", "user1@email.com",1,1);
    userService.createUser(newUser1);
    UserDTO newUser2 = new UserDTO("User2", "user2@email.com",2,2);
    userService.createUser(newUser2);

    userService.deleteUser(1L);
    userService.deleteUser(3L);

    var users = userService.getAllUsers();

    Assertions.assertEquals(1, users.size());

    Assertions.assertEquals(2L, users.get(0).getId() );
    Assertions.assertEquals("User2", users.get(0).getName() );
    Assertions.assertEquals("user2@email.com", users.get(0).getEmail() );
    Assertions.assertEquals(2, users.get(0).getCpf() );
    Assertions.assertEquals(2, users.get(0).getPhone() );
  }
}

