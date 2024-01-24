package com.eatdontyeet.recipebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.w3c.dom.Text;

@Entity
@Table(name = "recipeDetails")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RecipeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "recipeId")
    private Long recipeId;

    @NonNull
    @Column(name = "vegetarian")
    private boolean vegetarian;

    @NonNull
    @Column(name = "vegan")
    private boolean vegan;

    @NonNull
    @Column(name = "glutenFree")
    private boolean glutenFree;

    @NonNull
    @Column(name = "dairyFree")
    private boolean dairyFree;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "readyInMinutes")
    private int readyInMinutes;

    @NonNull
    @Column(name = "summary", length = 3000)
    private String summary;

    @NonNull
    @Column(name = "instructions", length = 3000)
    private String instructions = "none";

    @NonNull
    @Column(name = "image")
    private String image;

    @NonNull
    @Column(name = "sourceUrl")
    private String sourceUrl;

    public RecipeDetail(Long id, boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, String title, int readyInMinutes, String summary, String instructions, String image, String sourceUrl) {
        this.recipeId = id;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.summary = summary;
        this.instructions = instructions;
        this.image = image;
        this.sourceUrl = sourceUrl;
    }


}
