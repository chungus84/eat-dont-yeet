package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.exception.EntityNotFoundException;
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
    public User saveUser(User user) throws IllegalArgumentException {
       if (!checkUserDetails(user)) throw new IllegalArgumentException("User details are incomplete");
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) throws IllegalArgumentException {
        if (userId == null || userId.trim().isEmpty()) throw new IllegalArgumentException("Please provide a valid UserId");
        Optional<User> user = userRepository.findByUserId(userId);
        return unwrapUser(user, userId);
    }

    public static boolean checkUserDetails(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) return false;
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) return false;
        if (user.getLastName() == null || user.getLastName().isEmpty()) return false;
        if (user.getEmail() == null || user.getEmail().isEmpty()) return false;
        if (user.getPassword() == null || user.getPassword().isEmpty()) return false;
        return true;
    }

    public static User unwrapUser(Optional<User> entity, String userId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(userId, User.class);
    }
}
