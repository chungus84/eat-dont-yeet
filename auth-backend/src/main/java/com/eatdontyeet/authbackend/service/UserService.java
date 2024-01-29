package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.AuthRequest;
import com.eatdontyeet.authbackend.entity.AuthResponse;
import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.shared.UserDto;

public interface UserService {
    User saveUser(User user) throws Exception;

    UserDto getUserByUserId(String userId);

    UserDto getUser(String userName);

    UserDto getUserByEmail(String email);

    AuthResponse loginUser(AuthRequest request);

}
