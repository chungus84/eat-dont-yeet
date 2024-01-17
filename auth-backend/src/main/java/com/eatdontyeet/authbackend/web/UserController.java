package com.eatdontyeet.authbackend.web;

import com.eatdontyeet.authbackend.entity.User;
import com.eatdontyeet.authbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<String> findByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getUserByUserId(userId).getUserName(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("userName", savedUser.getUserName());
        responseHeaders.set("userId", savedUser.getUserId());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }
}
