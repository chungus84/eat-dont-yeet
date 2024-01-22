package com.eatdontyeet.recipebackend.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    @PostMapping("/new")
    public void findNewRecipesByIngredients(@RequestBody String[] ingredients) {
        for (String ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }
}
