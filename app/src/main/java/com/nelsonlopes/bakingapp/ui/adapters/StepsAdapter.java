package com.nelsonlopes.bakingapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Step> steps;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public StepsAdapter(Context context, List<Step> mSteps) {
        mContext = context;
        steps = mSteps;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StepsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_item, parent, false);

        StepsAdapter.MyViewHolder vh = new StepsAdapter.MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from the dataset at this position
        // - replace the contents of the view with that
        TextView stepTv = holder.view.findViewById(R.id.tv_step);

        stepTv.setText(steps.get(position).getShortDescription());

        /*holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe recipe = getItem(holder.getAdapterPosition());

                ///Intent intent = new Intent(mContext, DetailsActivity.class);
                //intent.putExtra(mContext.getResources().getString(R.string.parcel_movie), movie);

                //mContext.startActivity(intent);
            }
        });*/
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (steps != null) {
            return steps.size();
        } else {
            return 0;
        }
    }

    public Step getItem(int position) {
        if (steps == null || steps.size() == 0) {
            return null;
        }

        return steps.get(position);
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }
}