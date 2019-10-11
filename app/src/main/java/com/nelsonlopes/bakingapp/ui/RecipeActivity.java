package com.nelsonlopes.bakingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Recipe;
import com.nelsonlopes.bakingapp.ui.adapters.IngredientsAdapter;
import com.nelsonlopes.bakingapp.ui.adapters.StepsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.rv_ingredients)
    RecyclerView ingredientsRv;
    @BindView(R.id.rv_steps)
    RecyclerView stepsRv;
    // Create a variable to store a reference to the error message TextView
    @BindView(R.id.tv_error_message_display)
    TextView mErrorMessageDisplay;
    // Create a ProgressBar variable to store a reference to the ProgressBar
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    // Recipe
    private Recipe recipe;
    // Ingredients
    private RecyclerView.Adapter mAdapterIngredients;
    private RecyclerView.LayoutManager layoutManagerIngredients;
    // Steps
    private RecyclerView.Adapter mAdapterSteps;
    private RecyclerView.LayoutManager layoutManagerSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Bind the view using Butter Knife
        ButterKnife.bind(this);

        // Get Recipe
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        recipe = intent.getParcelableExtra(getString(R.string.parcel_recipe));

        // Ingredients
        // Use this setting to improve performance as we know that changes in content do not change
        // the layout size of the RecyclerView
        ingredientsRv.setHasFixedSize(true);

        // Use a grid layout manager
        layoutManagerIngredients = new LinearLayoutManager(this);
        ingredientsRv.setLayoutManager(layoutManagerIngredients);

        // RecyclerView item divider
        ingredientsRv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        // Specify an adapter
        mAdapterIngredients = new IngredientsAdapter(this, recipe.getIngredients());

        ingredientsRv.setAdapter(mAdapterIngredients);

        // Steps
        // Use this setting to improve performance as we know that changes in content do not change
        // the layout size of the RecyclerView
        stepsRv.setHasFixedSize(true);

        // Use a grid layout manager
        layoutManagerSteps = new LinearLayoutManager(this);
        stepsRv.setLayoutManager(layoutManagerSteps);

        // RecyclerView item divider
        stepsRv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        // Specify an adapter
        mAdapterSteps = new StepsAdapter(this, recipe.getSteps());

        stepsRv.setAdapter(mAdapterSteps);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }
}
