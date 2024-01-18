package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.exception.EntityNotFoundException;
import com.eatdontyeet.authbackend.repository.UserRepository;
import com.eatdontyeet.authbackend.shared.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    public UserDto getUserByUserId(String userId) throws IllegalArgumentException {
        if (userId == null || userId.trim().isEmpty()) throw new IllegalArgumentException("Please provide a valid UserId");
        User user = unwrapUser(userRepository.findByUserId(userId), userId);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(String userName) throws IllegalArgumentException {
        if (userName == null || userName.trim().isEmpty()) throw new IllegalArgumentException("Please provide a userName");
        User user = unwrapUser(userRepository.findByUserName(userName), "404");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;

    }

    @Override
    public UserDto getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Please provide a valid email");
        User user = unwrapUser(userRepository.findByEmail(email), "404");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static boolean checkUserDetails(User user) {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) return false;
        return true;
    }

    public static User unwrapUser(Optional<User> entity, String userId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(userId, User.class);
    }
}
