package com.eatdontyeet.recipebackend.web;

import com.eatdontyeet.recipebackend.entity.Profile;
import com.eatdontyeet.recipebackend.service.ProfileService;
import com.eatdontyeet.recipebackend.shared.ProfileDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    ProfileService profileService;

    @PostMapping("/new")
    public ResponseEntity<Profile> saveProfile(@Valid @RequestBody Profile profile) {
        Profile savedProfile = profileService.saveProfile(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileDto> findByUserId(@PathVariable String userId) {
        ProfileDto profileDto = profileService.getUserByUserId(userId);
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

}
