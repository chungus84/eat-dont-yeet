package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.*;
import com.eatdontyeet.recipebackend.exception.EntityNotFoundException;
import com.eatdontyeet.recipebackend.repository.RecipeDetailsRepository;
import com.eatdontyeet.recipebackend.repository.RecipeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private ExternalApiService externalApiService;
    private RecipeDetailsRepository recipeDetailsRepository;

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        if (!recipeRepository.existsByRecipeId(recipe.getRecipeId())) {
            return recipeRepository.save(recipe);
        }
        throw new EntityExistsException("This Recipe already exists");
    }

    @Override
    public RecipeDetail saveRecipeDetail(RecipeDetail recipeDetail) {
        if(!recipeDetailsRepository.existsByRecipeId(recipeDetail.getRecipeId())) return recipeDetailsRepository.save(recipeDetail);
        throw new EntityExistsException("This recipe already exists");
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
        Response res = externalApiService.searchNewRecipesByIngredients(ingredients);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<RecipeResponse> recipeList = mapper.readValue(res.getResponseBody(), new TypeReference<List<RecipeResponse>>(){});
        List<Recipe> recipes = convertResponseToRecipes(recipeList);
        List<Long> recipeIds = extractRecipeIds(recipes);

        for(Recipe rec : recipes) {
            try {
                saveRecipe(rec);
            } catch (EntityExistsException ex) {
                System.out.println(ex.getMessage());
            }
        }
        getRecipeDetails(recipeIds);
        return recipes;
    }

    public List<RecipeDetail> getRecipeDetails(List<Long> recipeIds) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Response res = externalApiService.findRecipeDetail(recipeIds);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<RecipeDetailsResponse> recipeDetailsList = mapper.readValue(res.getResponseBody(), new TypeReference<List<RecipeDetailsResponse>>() {});
        List<RecipeDetail> recipeDetails = convertResponseToRecipeDetails(recipeDetailsList);

        for (RecipeDetail rec : recipeDetails) {
            try {
                saveRecipeDetail(rec);
            } catch (EntityExistsException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return recipeDetails;

    }



    public static Recipe unwrapRecipe(Optional<Recipe> entity, Long recipeId) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(recipeId.toString(), Recipe.class);
    }

    public static List<String> getIngredientsList(List<Map<String, Object>> ingredients) {
        List<String> ingredientNameList = new ArrayList<>();
        for (Map<String, Object> ingredient : ingredients) {
            Ingredient ingredientObj = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .convertValue(ingredient, Ingredient.class);
            ingredientNameList.add(ingredientObj.getAmount() + " " + ingredientObj.getUnitShort() + " " + ingredientObj.getName());
        }
        return ingredientNameList;
    }

    public static List<Recipe> convertResponseToRecipes(List<RecipeResponse> recipeResponseList) {
        List<Recipe> recipes = new ArrayList<>();
        for (RecipeResponse recipeResponse : recipeResponseList) {
            List<String> missed = getIngredientsList(recipeResponse.getMissedIngredients());
            List<String> used = getIngredientsList(recipeResponse.getUsedIngredients());
            used.addAll(missed);
            Recipe recipe = new Recipe(recipeResponse.getId(), recipeResponse.getTitle(), recipeResponse.getImage(), used);
            recipes.add(recipe);
        }
        return recipes;
    }

    public static List<RecipeDetail> convertResponseToRecipeDetails(List<RecipeDetailsResponse> recipeDetailsResponseList) {
        List<RecipeDetail> recipeDetailsList = new ArrayList<>();
        for (RecipeDetailsResponse recipe : recipeDetailsResponseList) {
            RecipeDetail recipeDetails = new RecipeDetail(recipe.getId(),
                    recipe.isVegetarian(),
                    recipe.isVegan(),
                    recipe.isGlutenFree(),
                    recipe.isDairyFree(),
                    recipe.getTitle(),
                    recipe.getReadyInMinutes(),
                    recipe.getSummary(),
                    recipe.getInstructions(),
                    recipe.getImage(),
                    recipe.getSourceUrl());
            recipeDetailsList.add(recipeDetails);
        }
        return recipeDetailsList;
    }

    public static List<Long> extractRecipeIds(List<Recipe> recipes) {
        List<Long> idList = new ArrayList<>();
        for (Recipe recipe: recipes) {
            idList.add(recipe.getRecipeId());
        }
        return idList;
    }

}
