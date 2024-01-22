package com.eatdontyeet.recipebackend.service;

import com.eatdontyeet.recipebackend.entity.Recipe;
import com.eatdontyeet.recipebackend.security.SecurityConstants;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;

import java.util.List;

public class ExternalApiServiceImpl implements ExternalApiService {


    @Override
    public void searchNewRecipesByIngredients(String[] ingredients) throws Exception {
        StringBuilder paramString = new StringBuilder();
        AsyncHttpClient client = Dsl.asyncHttpClient();
        for(String ingredient : ingredients) {
            paramString.append("%2C%20").append(ingredient);
        }
        try {

            client.prepareGet(SecurityConstants.INGREDIENTS_END_POINT + paramString + "&ignorePantry=true&ranking=1")
                    .setHeader(SecurityConstants.RAPID_API_KEY_HEADER, SecurityConstants.RAPID_API_KEY)
                    .setHeader(SecurityConstants.RAPID_API_HOST_HEADER, SecurityConstants.RAPID_API_HOST)
                    .execute()
                    .toCompletableFuture()
                    .thenAccept(System.out::println)
                    .join();
            client.close();
        } catch (Exception ex) {
            throw new Exception();
        }
        System.out.println(client.toString());
    }
}
