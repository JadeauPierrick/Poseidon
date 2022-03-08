package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeAll
    private void setUp() {
        user = new User();
        user.setUsername("AREA21");
        user.setPassword("Amsterdam");
        user.setFullname("Martin");
        user.setRole("USER");
    }

    @Test
    @Order(1)
    public void addUserTest() throws Exception {
        userService.addUser(user);
        assertNotNull(user.getId());
        assertThat(user.getUsername()).isEqualTo("AREA21");
    }

    @Test
    @Order(2)
    public void getUsersTest() {
        List<User> users = userService.getUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    @Order(3)
    public void getUserByIdTest() throws Exception {
        User u = userService.getUserById(user.getId());
        assertThat(u.getId()).isEqualTo(user.getId());
    }

    @Test
    @Order(4)
    public void getUserByNullIdTest() {
        try {
            userService.getUserById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The user was not found");
        }
    }

    @Test
    @Order(5)
    public void getUserByUserNameTest() throws Exception {
        User userTest = userService.getUserByUserName(user.getUsername());
        assertThat(userTest.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @Order(6)
    public void getUserByNullUserNameTest() {
        try {
            userService.getUserByUserName("AAAAAA");
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("The user was not found");
        }
    }

    @Test
    @Order(7)
    public void deleteUserByIdTest() throws Exception {
        userService.deleteUserById(user.getId());
        List<User> users = userService.getUsers();
        assertThat(users.size()).isEqualTo(3);
    }

    @Test
    @Order(8)
    public void deleteUserByNullIdTest() {
        try {
            userService.deleteUserById(10);
        } catch (Exception exception) {
            assertThat(exception.getMessage()).contains("Invalid user Id : " + 10);
        }
    }
}
