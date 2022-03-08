package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomUserDetailsServiceTest {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeAll
    private void setUp() throws Exception {
        user = new User();
        user.setUsername("Romero");
        user.setPassword("Holland");
        user.setFullname("Nicky");
        user.setRole("USER");
        userService.addUser(user);
    }

    @AfterAll
    private void tearDown() throws Exception {
        userService.deleteUserById(user.getId());
    }

    @Test
    @Order(1)
    public void loadUserByUsernameTest() {
        UserDetails userTest = customUserDetailsService.loadUserByUsername("Romero");
        assertThat(userTest.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @Order(2)
    public void loadUserByNullUsernameTest() {
        try {
            customUserDetailsService.loadUserByUsername("AAAA");
        }catch (UsernameNotFoundException usernameNotFoundException) {
            assertThat(usernameNotFoundException.getMessage()).contains("User with " + "AAAA" + ", not found !");
        }
    }
}
