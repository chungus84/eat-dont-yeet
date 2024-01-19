package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Profile;
import com.eatdontyeet.recipebackend.shared.ProfileDto;

public interface ProfileService {

    ProfileDto saveProfile(Profile profile);

    ProfileDto getUserByUserId(String userId);

    ProfileDto getUserByUserName(String userName);


}
