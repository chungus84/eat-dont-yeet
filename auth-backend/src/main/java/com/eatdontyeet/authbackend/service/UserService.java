package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.shared.UserDto;

public interface UserService {
    User saveUser(User user);

    UserDto getUserByUserId(String userId);

    UserDto getUser(String userName);

    UserDto getUserByEmail(String email);


}
