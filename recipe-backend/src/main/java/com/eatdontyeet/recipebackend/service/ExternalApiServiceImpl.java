package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;
import com.eatdontyeet.recipebackend.security.SecurityConstants;
import org.asynchttpclient.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class ExternalApiServiceImpl implements ExternalApiService {


    @Override
    public Response searchNewRecipesByIngredients(String[] ingredients) throws Exception {
        StringBuilder paramString = new StringBuilder();


        AsyncHttpClient client = Dsl.asyncHttpClient();
        for(String ingredient : ingredients) {
            paramString.append("%2C%20").append(ingredient);
        }

        Response response = client.prepareGet(SecurityConstants.INGREDIENTS_END_POINT + paramString + "&ignorePantry=true&ranking=1")
                .setHeader(SecurityConstants.RAPID_API_KEY_HEADER, SecurityConstants.RAPID_API_KEY)
                .setHeader(SecurityConstants.RAPID_API_HOST_HEADER, SecurityConstants.RAPID_API_HOST)
                .execute().get();

        return response;


    }
}
