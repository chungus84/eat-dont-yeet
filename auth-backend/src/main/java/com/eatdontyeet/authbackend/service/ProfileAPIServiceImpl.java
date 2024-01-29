package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.ProfileDto;
import com.eatdontyeet.authbackend.security.SecurityContants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.*;


import java.util.List;

public class ProfileAPIServiceImpl implements ProfileAPIService {

    @Override
    public Response saveProfile(String userId, String userName) throws Exception {
        AsyncHttpClient client = Dsl.asyncHttpClient();
        Response response;
        ProfileDto profileDto = new ProfileDto(userName, userId);
        try {
            response = client.preparePost(SecurityContants.NEW_PROFILE_URI)
                    .setBody(new ObjectMapper().writeValueAsString(profileDto))
                    .setHeader("Content-Type", "application/json")
                    .execute().get();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        client.close();
        return response;
    }

    @Override
    public Response findProfileByUserId(String userId) throws Exception {
        AsyncHttpClient client = Dsl.asyncHttpClient();
        Response response;
        try {
            response = client.prepareGet(SecurityContants.FIND_PROFILE_URI + userId)
                    .execute().get();
            client.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return response;
    }
}
