package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.User;
import com.springapp.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }else {
            throw new Exception("The user was not found");
        }
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            return user.get();
        }else {
            throw new Exception("The user was not found");
        }
    }

    @Override
    public User addUser(User user) throws Exception {
        Optional<User> currentUser = userRepository.findByUserName(user.getUsername());
        if (currentUser.isPresent()) {
            throw new Exception("This user name is already used");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }else {
            throw new Exception("Invalid user Id : " + id);
        }
    }
}
