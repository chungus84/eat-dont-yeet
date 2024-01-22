package com.eatdontyeet.recipebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @NotEmpty(message = "UserId must not be empty")
    @Column(name = "userId", unique = true)
    private String userId;

    @NonNull
    @NotEmpty(message = "UserName must not be empty")
    @Column(name = "userName", unique = true, length = 50)
    private String userName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "profile_recipe",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    )
    private Set<Recipe> recipes;


}
