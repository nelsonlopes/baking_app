package com.nelsonlopes.bakingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Step;

// This activity is responsible for displaying the master list of all images
public class RecipeActivity extends AppCompatActivity {

    public Step step;
    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    public static boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Determine if you're creating a two-pane or single-pane display
        if (findViewById(R.id.step_container) != null) {
            // This StepFragment will only initially exist in the two-pane tablet case
            mTwoPane = true;

            if(savedInstanceState == null) {
                // In two-pane mode, add StepFragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new fragment
                StepFragment stepFragment = new StepFragment();
                stepFragment.setStep(step);
                fragmentManager.beginTransaction()
                        .add(R.id.step_container, stepFragment)
                        .commit();
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
    }
}
