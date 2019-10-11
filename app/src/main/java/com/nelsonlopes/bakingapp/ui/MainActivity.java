package com.nelsonlopes.bakingapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.data.network.NetworkUtils;
import com.nelsonlopes.bakingapp.data.network.RecipesRestClient;
import com.nelsonlopes.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_recipes)
    RecyclerView recyclerView;
    // Create a variable to store a reference to the error message TextView
    @BindView(R.id.tv_error_message_display)
    TextView mErrorMessageDisplay;
    // Create a ProgressBar variable to store a reference to the ProgressBar
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    private List<Recipe> recipes = null;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind the view using Butter Knife
        ButterKnife.bind(this);

        /**
         * If something goes wrong (like there is no internet connection), the usar taps the
         * TextView and a new call to the API is made.
         */
        mErrorMessageDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecipes();
            }
        });

        // Use this setting to improve performance as we know that changes in content do not change
        //the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Use a grid layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // RecyclerView item divider
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        // Specify an adapter
        mAdapter = new RecipesAdapter(this, recipes);

        recyclerView.setAdapter(mAdapter);

        // Get Recipes - API call + JSON parse + RecyclerView populate
        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.parcel_recipe))) {
            getRecipes();
        } else {
            Parcelable[] parcelable = savedInstanceState.
                    getParcelableArray(getString(R.string.parcel_recipe));

            if (parcelable != null) {
                int numRecipeObjects = parcelable.length;
                List<Recipe> recipes = new ArrayList<>();
                for (int i = 0; i < numRecipeObjects; i++) {
                    recipes.add((Recipe) parcelable[i]);
                }

                ((RecipesAdapter) mAdapter).setRecipes(recipes);
            }
        }
    }

    private void getRecipes() {
        /**
         * If the sort method is favorites, the data is persisted locally. Else, calls the API
         */
        if (NetworkUtils.isOnline(getApplicationContext())) {
            Call<List<Recipe>> call = RecipesRestClient.getInstance().getRecipes().getRecipes();
            Callback<List<Recipe>> callback = new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    if (!response.isSuccessful()) {
                        showErrorMessage(R.string.error_message_results);
                        return;
                    }
                    recipes = response.body();
                    ((RecipesAdapter) mAdapter).setRecipes(recipes);
                    showRecipes();
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    showErrorMessage(R.string.error_message_results);
                }
            };
            call.enqueue(callback);
        } else {
            showErrorMessage(R.string.error_message_network);
        }
    }

    /**
     * This method will make the RecyclerView visible and hide the error message.
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showRecipes() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.GONE);
        // Then, make sure the JSON data is visible
        recyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the RecyclerView.
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage(int errorMessage) {
        // First, hide the currently visible data
        recyclerView.setVisibility(View.GONE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mErrorMessageDisplay.setText(getResources().getString(errorMessage));
    }
}