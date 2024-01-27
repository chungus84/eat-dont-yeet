package com.eatdontyeet.authbackend.service;

import com.eatdontyeet.authbackend.entity.ProfileDto;
import com.eatdontyeet.authbackend.security.SecurityContants;
import org.asynchttpclient.*;

import java.util.List;

public class ProfileAPIServiceImpl implements ProfileAPIService {

    @Override
    public Response saveProfile(String userName, String userId) throws Exception {
        AsyncHttpClient client = Dsl.asyncHttpClient();
        BoundRequestBuilder response;
        ProfileDto profileDto = new ProfileDto(userName, userId);
        try {
            response = (BoundRequestBuilder) client.preparePost(SecurityContants.NEW_PROFILE_URI)
                    .setBody(String.valueOf(profileDto))
                    .execute().get();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return (Response) response;
    }
}
