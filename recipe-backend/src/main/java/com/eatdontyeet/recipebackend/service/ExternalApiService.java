package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;

import java.util.List;

public interface ExternalApiService {

    void searchNewRecipesByIngredients(String[] ingredients) throws Exception;

}
