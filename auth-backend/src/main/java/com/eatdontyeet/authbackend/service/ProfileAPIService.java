package com.eatdontyeet.authbackend.service;

import org.asynchttpclient.Response;

public interface ProfileAPIService {
    Response saveProfile(String userName, String userId) throws Exception;

    Response findProfileByUserId(String userId) throws Exception;
}
