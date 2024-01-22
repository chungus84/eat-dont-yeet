package com.eatdontyeet.authbackend.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("The credentials provided do not match");
    }
}
