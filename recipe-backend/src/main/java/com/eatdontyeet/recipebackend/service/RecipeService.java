package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;
import com.eatdontyeet.recipebackend.entity.RecipeDetail;

import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    RecipeDetail saveRecipeDetail(RecipeDetail recipeDetail);

    Recipe getRecipe(Long recipeId);

    List<Recipe> getRecipes();

    List<Recipe> searchNewRecipe(String[] ingredients) throws Exception;

    List<RecipeDetail> getRecipeDetails(List<Long> recipeIds) throws Exception;
}
