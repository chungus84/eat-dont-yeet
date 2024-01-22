package com.eatdontyeet.recipebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    private long id;
    private String title;
    private String image;
    private List<Map<String, Object>> missedIngredients;
    private List<Map<String, Object>> usedIngredients;

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", missedIngredients=" + missedIngredients +
                ", usedIngredients=" + usedIngredients +
                '}';
    }
}