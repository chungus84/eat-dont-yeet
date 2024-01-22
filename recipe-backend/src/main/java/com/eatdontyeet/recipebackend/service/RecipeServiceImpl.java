package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Ingredient;
import com.eatdontyeet.recipebackend.entity.Recipe;
import com.eatdontyeet.recipebackend.entity.RecipeResponse;
import com.eatdontyeet.recipebackend.exception.EntityNotFoundException;
import com.eatdontyeet.recipebackend.repository.RecipeRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private ExternalApiService externalApiService;

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

    public List<Recipe> searchNewRecipe(String[] ingredients) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Recipe> recipes = new ArrayList<>();
        Response res = externalApiService.searchNewRecipesByIngredients(ingredients);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<RecipeResponse> recipeList = mapper.readValue(res.getResponseBody(), new TypeReference<List<RecipeResponse>>(){});

        Recipe recipe;

        for (RecipeResponse ele : recipeList) {
            recipe = new Recipe(ele.getId(), ele.getTitle());
            recipe.setImage(ele.getImage());
            List<String> missed = new ArrayList<>();
            List<String> used = new ArrayList<>();

            for (Object el : ele.getMissedIngredients()) {
                Ingredient ingredient = mapper.convertValue(el, Ingredient.class);
                missed.add(ingredient.getName());
            }
            for (Object el : ele.getUsedIngredients()) {
                Ingredient ingredient = mapper.convertValue(el, Ingredient.class);
                used.add(ingredient.getName());
            }
            used.addAll(missed);
            recipe.setIngredients(used);
            recipes.add(recipe);
        }

        for(Recipe rec : recipes) {
            if (recipeRepository.existsByRecipeId(rec.getRecipeId())) {
                continue;
            } else recipeRepository.save(rec);
        }

        return recipes;






    }



    public static Recipe unwrapRecipe(Optional<Recipe> entity, Long recipeId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(recipeId.toString(), Recipe.class);
    }
}
