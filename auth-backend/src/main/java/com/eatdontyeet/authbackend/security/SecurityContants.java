package com.eatdontyeet.authbackend.security;

public class SecurityContants {

    public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/user/register"; // Pu

    public static final String SECRET_KEY = "b990273183f73fe1d38f8c488b48a2df1d4431e5b37bce99f6b2d62e7f846f82c6b871525a45a718b5b306674baefccf2f92643ba33e231681d9149eb2ef5b61";
}
