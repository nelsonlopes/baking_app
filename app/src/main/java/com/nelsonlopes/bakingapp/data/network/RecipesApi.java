package com.nelsonlopes.bakingapp.data.network;

import com.nelsonlopes.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesApi {
    interface RecipesInterface {
        @GET("/topher/2017/May/59121517_baking/baking.json")
        Call<List<Recipe>> getRecipes();
    }
}