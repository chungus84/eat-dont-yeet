package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Profile;
import com.eatdontyeet.recipebackend.exception.EntityNotFoundException;
import com.eatdontyeet.recipebackend.repository.ProfileRepository;
import com.eatdontyeet.recipebackend.shared.ProfileDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private ProfileRepository profileRepository;

    @Override
    public ProfileDto getUserByUserId(String userId) throws IllegalArgumentException {
        if (userId == null || userId.trim().isEmpty()) throw new IllegalArgumentException("Please provide a valid userId");
        Profile profile = unwrapProfile(profileRepository.findByUserId(userId), userId);
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto);
        return profileDto;
    }

    @Override
    public ProfileDto getUserByUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) throw new IllegalArgumentException("please provide a valid userNane");
        Profile profile = unwrapProfile(profileRepository.findByUserName(userName), userName);
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto);
        return profileDto;
    }

    @Override
    public Profile saveProfile(Profile profile) throws IllegalArgumentException {
        if (!checkProfileDetails(profile)) throw  new IllegalArgumentException("Profile details are incomplete");
        ProfileDto profileDto = new ProfileDto(profile.getUserId(), profile.getUserName());
        return profileRepository.save(profile);

    }

    public static boolean checkProfileDetails(Profile profile) {
        if (profile.getUserId() == null || profile.getUserId().trim().isEmpty()) return false;
        if (profile.getUserName() == null || profile.getUserName().trim().isEmpty()) return false;
        return true;

    }
    public static Profile unwrapProfile(Optional<Profile> entity, String value) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(value, Profile.class);
    }
}
