package com.nelsonlopes.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Recipe;
import com.nelsonlopes.bakingapp.model.Step;
import com.nelsonlopes.bakingapp.ui.adapters.IngredientsAdapter;
import com.nelsonlopes.bakingapp.ui.adapters.StepsAdapter;
import com.nelsonlopes.bakingapp.ui.widget.WidgetUpdateService;

import java.util.List;

import static com.nelsonlopes.bakingapp.ui.RecipeActivity.mSteps;
import static com.nelsonlopes.bakingapp.ui.RecipeActivity.mPosition;

// This fragment displays all of the recipe steps in one large list
// The list appears as a LinearLayout RecyclerView
public class MasterListFragment extends Fragment {

    // Recipe
    private Recipe recipe;

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Inflates the RecyclerViews of Recipe Ingredients and Steps
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get Recipe from Intent
        Intent intent = getActivity().getIntent();
        if (intent == null) {
            closeOnError();
        }

        recipe = intent.getParcelableExtra(getString(R.string.parcel_recipe));

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Related to Ingredients
        RecyclerView.Adapter mAdapterIngredients;
        RecyclerView.LayoutManager layoutManagerIngredients;
        // Get a reference to the Ingredients RecyclerView in the fragment_master_list xml layout file
        RecyclerView ingredientsRv = rootView.findViewById(R.id.rv_ingredients);
        layoutManagerIngredients = new LinearLayoutManager(getContext());
        ingredientsRv.setLayoutManager(layoutManagerIngredients);
        mAdapterIngredients = new IngredientsAdapter(getContext(), recipe.getIngredients());
        ingredientsRv.setAdapter(mAdapterIngredients);

        // Related to Steps
        RecyclerView.Adapter mAdapterSteps;
        RecyclerView.LayoutManager layoutManagerSteps;
        // Get a reference to the Steps RecyclerView in the fragment_master_list xml layout file
        RecyclerView stepsRv = rootView.findViewById(R.id.rv_steps);
        layoutManagerSteps = new LinearLayoutManager(getContext());
        stepsRv.setLayoutManager(layoutManagerSteps);
        stepsRv.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mAdapterSteps = new StepsAdapter(getContext(), recipe.getSteps(), new StepsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Step step, List<Step> steps, int position) {
                if (RecipeActivity.mTwoPane) {
                    // Create two-pane interaction

                    StepFragment newFragment = new StepFragment();

                    // Give the step resource to the new fragment
                    newFragment.setStep(step);

                    // Replace the old fragment with a new one
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.step_container, newFragment)
                            .commit();
                } else {
                    // Handle the single-pane phone case by passing information in a Bundle attached to an Intent
                    mSteps = steps;
                    mPosition = position;

                    // Put this information in an Intent that will launch an StepActivity
                    final Intent intent = new Intent(getContext(), StepActivity.class);
                    intent.putExtra(getActivity().getApplicationContext().getResources().getString(R.string.parcel_step), step);

                    getActivity().startActivity(intent);
                }
            }
        });
        stepsRv.setAdapter(mAdapterSteps);

        // Start Widget's Service, which is going to update the widget's list with the
        // ingredients data
        startWidgetService();

        // Return the root view
        return rootView;
    }

    private void closeOnError() {
        getActivity().finish();
        Toast.makeText(getContext(), R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This will trigger WidgetUpdateService to update the Widget
     * to the last recipe that the user has seen
     */
    void startWidgetService()
    {
        Intent intent = new Intent(getContext(), WidgetUpdateService.class);
        intent.putExtra(getContext().getResources().getString(R.string.parcel_recipe), recipe);
        intent.setAction(WidgetUpdateService.WIDGET_UPDATE_ACTION);
        getContext().startService(intent);
    }
}
