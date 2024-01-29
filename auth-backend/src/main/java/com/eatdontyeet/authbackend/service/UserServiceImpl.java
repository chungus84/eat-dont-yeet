package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.AuthRequest;
import com.eatdontyeet.authbackend.entity.AuthResponse;
import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.exception.BadCredentialsException;
import com.eatdontyeet.authbackend.exception.EntityNotFoundException;
import com.eatdontyeet.authbackend.repository.UserRepository;
import com.eatdontyeet.authbackend.shared.UserDto;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtGenerator jwtGenerator;
    private ProfileAPIService profileAPIService;



    @Override
    public User saveUser(User user) throws Exception {
       if (!checkUserDetails(user)) throw new IllegalArgumentException("User details are incomplete");
       user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(8)));
       User savedUser = userRepository.save(user);
       profileAPIService.saveProfile(user.getUserId(), user.getUserName());
       return savedUser;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws IllegalArgumentException {
        if (userId == null || userId.trim().isEmpty()) throw new IllegalArgumentException("Please provide a valid UserId");
        return unwrapUser(userRepository.findByUserId(userId), userId);
    }

    @Override
    public UserDto getUser(String userName) throws IllegalArgumentException {
        if (userName == null || userName.trim().isEmpty()) throw new IllegalArgumentException("Please provide a userName");
        return unwrapUser(userRepository.findByUserName(userName), "404");

    }

    @Override
    public AuthResponse loginUser(AuthRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) throw new IllegalArgumentException("Please provide valid credentials");
        UserDto userDto = unwrapUser(userRepository.findByEmail(request.getEmail()), "404");
        if (!BCrypt.checkpw(request.getPassword(), userDto.getPassword())) throw new BadCredentialsException();
        return  new AuthResponse(userDto.getUserId(), userDto.getUserName(), jwtGenerator.generateToken(userDto).get("token"));

    }

    @Override
    public UserDto getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Please provide a valid email");
        return unwrapUser(userRepository.findByEmail(email), "404");
    }

    public static boolean checkUserDetails(User user) {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) return false;
        return true;
    }

    public static UserDto unwrapUser(Optional<User> entity, String userId) {
        if (entity.isPresent()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(entity.get(), userDto);
            return userDto;
        } else throw new EntityNotFoundException(userId, User.class);
    }
}
