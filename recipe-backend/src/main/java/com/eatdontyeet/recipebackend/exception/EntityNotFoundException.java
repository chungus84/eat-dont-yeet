package com.eatdontyeet.recipebackend.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String userId, Class<?> entity) {
        super("The " + entity.getSimpleName().toLowerCase() + " with id " + userId + " does not exist in our records");
    }
}
