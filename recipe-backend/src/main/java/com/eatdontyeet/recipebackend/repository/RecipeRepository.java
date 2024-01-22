package com.eatdontyeet.recipebackend.repository;

import com.eatdontyeet.recipebackend.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

    Optional<Recipe> findByRecipeId(Long recipeId);


}
