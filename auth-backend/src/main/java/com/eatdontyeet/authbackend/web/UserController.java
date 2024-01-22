package com.eatdontyeet.authbackend.web;

import com.eatdontyeet.authbackend.entity.AuthRequest;
import com.eatdontyeet.authbackend.entity.AuthResponse;
import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.security.SecurityContants;
import com.eatdontyeet.authbackend.service.UserService;
import com.eatdontyeet.authbackend.shared.UserDto;
import com.eatdontyeet.authbackend.web.response.RestUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<RestUser> findByUserId(@PathVariable String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        return new ResponseEntity<>(new ModelMapper().map(userDto, RestUser.class), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("userName", savedUser.getUserName());
        responseHeaders.set("userId", savedUser.getUserId());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = userService.loginUser(request);
        HttpHeaders headers = new HttpHeaders();
        headers.set("userName", response.getUserName());
        headers.set("userId", response.getUserId());
        headers.set("Authorization", SecurityContants.BEARER + response.getAccessToken());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
