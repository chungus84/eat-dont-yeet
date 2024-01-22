package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;
import org.asynchttpclient.Response;

import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    Recipe getRecipe(Long recipeId);

    List<Recipe> getRecipes();

    List<Recipe> searchNewRecipe(String[] ingredients) throws Exception;
}
