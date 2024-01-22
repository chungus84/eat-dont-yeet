package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.shared.UserDto;

import java.util.Map;

public interface JwtGenerator {
    Map<String, String> generateToken(UserDto user);
}
