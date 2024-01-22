package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;
import com.eatdontyeet.recipebackend.exception.EntityNotFoundException;
import com.eatdontyeet.recipebackend.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe getRecipe(Long recipeId) {
        return unwrapRecipe(recipeRepository.findByRecipeId(recipeId),recipeId);
    }

    @Override
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    public static Recipe unwrapRecipe(Optional<Recipe> entity, Long recipeId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(recipeId.toString(), Recipe.class);
    }
}
