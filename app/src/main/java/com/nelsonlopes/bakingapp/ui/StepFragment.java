package com.nelsonlopes.bakingapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Step;

public class StepFragment extends Fragment {

    private Step mStep;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public StepFragment() {
    }

    /**
     * Inflates the fragment layout file
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Load the saved state if there is one
        if(savedInstanceState != null) {
            // Loads image/video and step description
        }

        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);

        // Get a reference to the objects in the fragment layout
        final TextView textView = rootView.findViewById(R.id.tv_fragment_step);

        if (mStep == null) {
            textView.setText("Select a step in the list");
        } else {
            textView.setText(mStep.getDescription());
        }

        // Return the rootView
        return rootView;
    }

    /**
     * Save the current state of this fragment
     */
    @Override
    public void onSaveInstanceState(Bundle currentState) {
    }

    // Setter method
    public void setStep(Step step) {
        mStep = step;
    }
}
