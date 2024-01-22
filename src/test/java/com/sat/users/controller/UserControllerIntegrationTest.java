package com.sat.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sat.users.dto.UserDTO;
import com.sat.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  private ObjectMapper objectMapper = new ObjectMapper();

  private UserDTO userDTO;

  @BeforeEach
  void setUp() {
    userDTO = new UserDTO("Test User", "test@email.com", 123456789L, 987654321L);
  }

  @Test
  void testCreateUser() throws Exception {
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists()) // Verifica se o ID existe
        .andExpect(jsonPath("$.name").value(userDTO.getName()))
        .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
        .andExpect(jsonPath("$.cpf").value(userDTO.getCpf()))
        .andExpect(jsonPath("$.phone").value(userDTO.getPhone()));
  }


  @Test
  void testGetAllUsers() throws Exception {
    UserDTO createdUser = userService.createUser(userDTO);
    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(createdUser.getId()))
        .andExpect(jsonPath("$[0].name").value(createdUser.getName()))
        .andExpect(jsonPath("$[0].email").value(createdUser.getEmail()))
        .andExpect(jsonPath("$[0].cpf").value(createdUser.getCpf()))
        .andExpect(jsonPath("$[0].phone").value(createdUser.getPhone()));
  }


  @Test
  void testCreateUpdateAndGetUser() throws Exception {
    // 1. Criar um novo usuário
    UserDTO newUser = new UserDTO("Test User", "test@email.com", 123456789L, 987654321L);
    String newUserJson = objectMapper.writeValueAsString(newUser);

    String responseJson = mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newUserJson))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    UserDTO createdUser = objectMapper.readValue(responseJson, UserDTO.class);

    // 2. Atualizar o usuário criado
    createdUser.setName("Updated Test User");
    String updatedUserJson = objectMapper.writeValueAsString(createdUser);

    mockMvc.perform(put("/users/" + createdUser.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(updatedUserJson))
        .andExpect(status().isOk());

    // 3. Recuperar o usuário atualizado e verificar se as alterações foram aplicadas
    mockMvc.perform(get("/users/" + createdUser.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Updated Test User"));
  }

  @Test
  void testCreateAndDeleteUser() throws Exception {
    // 1. Criar um novo usuário
    UserDTO newUser = new UserDTO("Test User", "test@email.com", 123456789L, 987654321L);
    String newUserJson = objectMapper.writeValueAsString(newUser);

    String responseJson = mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newUserJson))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    UserDTO createdUser = objectMapper.readValue(responseJson, UserDTO.class);

    // 2. Deletar o usuário criado
    mockMvc.perform(delete("/users/" + createdUser.getId()))
        .andExpect(status().isNoContent()); // Espera status 204 (No Content)

    // 3. Tentar recuperar o usuário deletado e verificar se ele não existe
    mockMvc.perform(get("/users/" + createdUser.getId()))
        .andExpect(status().isNotFound());
  }


}
