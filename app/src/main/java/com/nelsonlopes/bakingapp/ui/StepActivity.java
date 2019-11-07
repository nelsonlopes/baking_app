package com.nelsonlopes.bakingapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

// This activity will display step details (video, image, description)
public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        // Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {
            // Retrieve step that was sent through an intent; use it to display the desired Step information

            // Create a new StepFragment
            StepFragment stepFragment = new StepFragment();

            // Get Step from Intent
            Intent intent = getIntent();
            if (intent == null) {
                closeOnError();
            }

            Step step = intent.getParcelableExtra(getString(R.string.parcel_step));

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Give the step resource to the new fragment
            stepFragment.setStep(step);

            fragmentManager.beginTransaction()
                    .add(R.id.step_container, stepFragment)
                    .commit();
        }
    }

    private void closeOnError() {
        this.finish();
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }
}
