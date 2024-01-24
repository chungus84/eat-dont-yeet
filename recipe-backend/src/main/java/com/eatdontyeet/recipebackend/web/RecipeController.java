package com.eatdontyeet.recipebackend.web;

import com.eatdontyeet.recipebackend.entity.Recipe;
import com.eatdontyeet.recipebackend.entity.RecipeDetail;
import com.eatdontyeet.recipebackend.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    RecipeService recipeService;

    @PostMapping("/new")
    public ResponseEntity<List<Recipe>> findNewRecipesByIngredients(@RequestBody String[] ingredients) throws Exception {
        List<Recipe> recipes = recipeService.searchNewRecipe(ingredients);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> findAllRecipes() {
        return new ResponseEntity<>(recipeService.getRecipes(), HttpStatus.OK);
    }

//    @PostMapping("/details")
//    public ResponseEntity<List<RecipeDetail>> getRecipeDetails( recipeIds) throws Exception {
//       List<RecipeDetail> recipeDetails = recipeService.getRecipeDetails(recipeIds);
//       return new ResponseEntity<>(recipeDetails, HttpStatus.OK);
//
//    }


}
