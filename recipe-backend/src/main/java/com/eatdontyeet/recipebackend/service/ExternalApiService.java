package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;
import org.asynchttpclient.Response;

import java.util.List;

public interface ExternalApiService {

    Response searchNewRecipesByIngredients(String[] ingredients) throws Exception;

    Response findRecipeDetail(Long[] recipeId) throws Exception;
}
