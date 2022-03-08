package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(Integer id) throws Exception;

    User getUserByUserName(String userName) throws Exception;

    User addUser(User user) throws Exception;

    void deleteUserById(Integer id) throws Exception;
}
