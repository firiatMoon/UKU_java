package uku.java.JavaBackend.MVC.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uku.java.JavaBackend.MVC.models.User;
import uku.java.JavaBackend.MVC.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldSuccessCreateUser() throws Exception {
        User user = new User(
                null,
                "Peter",
                "peter@mail.ru",
                34,
                null
        );

        String userJson = mapper.writeValueAsString(user);

        String createdUserJson = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User createdUser = mapper.readValue(createdUserJson, User.class);
        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(user.getName(), createdUser.getName());
    }

    @Test
    void shouldNotCreateUserWhenRequestNotValid() throws Exception {
        User user = new User(
                null,
                "Peter",
                "peter@mail.ru",
                16,
                null
        );

        String userJson = mapper.writeValueAsString(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldSuccessSearchUserById() throws Exception {
        User user = new User(
                null,
                "Peter",
                "peter@mail.ru",
                23,
                null
        );

        user = userService.createUser(user);
        String foundUserJson = mockMvc.perform(get("/users/{id}", user.getId()))
               .andExpect(status().isOk())
               .andReturn()
               .getResponse()
               .getContentAsString();

        User foundUser = mapper.readValue(foundUserJson, User.class);
        org.assertj.core.api.Assertions.assertThat(user)
                .usingRecursiveComparison()
                .isEqualTo(foundUser);
    }

    @Test
    void shouldReturnNotFoundWhenUserNotPresent() throws Exception {
       mockMvc.perform(get("/users/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}