package com.eatdontyeet.recipebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NonNull
    @NotEmpty(message = "UserId must not be empty")
    @Column(name = "recipeId", unique = true)
    private Long recipeId;

    @NonNull
    @NotEmpty(message = "Title cannot be empty")
    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "ingredients")
    private List<String> ingredients;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "profile_recipe",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId")
    )
    private Set<Profile> profiles;

}
