package com.eatdontyeet.recipebackend.repository;

import com.eatdontyeet.recipebackend.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findByRecipeId(Long recipeId);


}
