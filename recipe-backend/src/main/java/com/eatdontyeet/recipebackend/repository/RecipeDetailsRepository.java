package com.eatdontyeet.recipebackend.repository;

import com.eatdontyeet.recipebackend.entity.RecipeDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RecipeDetailsRepository extends CrudRepository<RecipeDetail, Long> {

    Optional<RecipeDetail> findByRecipeId(Long recipeId);

    boolean existsByRecipeId(Long recipeId);
}
