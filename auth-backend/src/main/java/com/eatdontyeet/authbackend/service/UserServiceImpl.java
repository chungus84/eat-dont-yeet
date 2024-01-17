package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) throw new IllegalArgumentException("UserName is empty");
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) throw new IllegalArgumentException("First Name is empty");
        if (user.getLastName() == null || user.getLastName().isEmpty()) throw new IllegalArgumentException("Last Name is empty");
        if (user.getEmail() == null || user.getEmail().isEmpty()) throw new IllegalArgumentException("Email is empty");
        if (user.getUserId() == null || user.getPassword().isEmpty()) throw new IllegalArgumentException("Password is empty");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
