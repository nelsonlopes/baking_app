package com.nelsonlopes.bakingapp.data.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesRestClient {
    private final static String RECIPES_BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    private RecipesApi.RecipesInterface recipes;
    private Retrofit retrofit;

    private static RecipesRestClient instance = null;

    private RecipesRestClient() {
        initializeRetrofit();
    }

    public static RecipesRestClient getInstance() {
        if (instance == null) {
            instance = new RecipesRestClient();
        }

        return instance;
    }

    public RecipesApi.RecipesInterface getRecipes() {
        if (recipes == null) {
            recipes = retrofit.create(RecipesApi.RecipesInterface.class);
        }

        return recipes;
    }

    private void initializeRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url()
                                .newBuilder()
                                .build();
                        Request.Builder builder = request.newBuilder()
                                .url(url)
                                .method(request.method(), request.body());
                        request = builder.build();

                        return chain.proceed(request);
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(RECIPES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
