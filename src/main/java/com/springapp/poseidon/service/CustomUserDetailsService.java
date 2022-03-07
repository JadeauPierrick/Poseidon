package com.springapp.poseidon.service;

import com.springapp.poseidon.domain.User;
import com.springapp.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isPresent()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(user.get().getRole());
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), Arrays.asList(authority));
        }else {
            throw new UsernameNotFoundException("User with " + userName + ", not found !");
        }
    }
}
