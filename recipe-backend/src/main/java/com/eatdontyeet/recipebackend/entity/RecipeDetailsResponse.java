package com.eatdontyeet.recipebackend.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDetailsResponse {

    private Long id;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private String title;
    private int readyInMinutes;
    private String summary;
    private String instructions;
    private String image;
    private String sourceUrl;
}
