package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;

public interface UserService {
    User saveUser(User user);

    User getUserByUserId(String userId);

    User getUser(String userName);


}
