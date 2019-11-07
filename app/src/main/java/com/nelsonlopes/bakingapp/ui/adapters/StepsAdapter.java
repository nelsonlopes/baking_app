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
    private List<Step> mSteps;
    private OnItemClickListener mOnItemClickListener;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public MyViewHolder(View v) {
            super(v);
            view = v;
        }

        public void bind(final Step step, final List<Step> steps, final int position,
                         final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(step, steps, position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Step step, List<Step> steps, int position);
    }

    public StepsAdapter(Context context, List<Step> steps, OnItemClickListener onItemClickListener) {
        mContext = context;
        mSteps = steps;
        mOnItemClickListener = onItemClickListener;
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

        stepTv.setText(mSteps.get(position).getShortDescription());

        holder.bind(mSteps.get(position), mSteps, position, mOnItemClickListener);
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mSteps != null) {
            return mSteps.size();
        } else {
            return 0;
        }
    }

    public Step getItem(int position) {
        if (mSteps == null || mSteps.size() == 0) {
            return null;
        }

        return mSteps.get(position);
    }

    public void setSteps(List<Step> steps) {
        this.mSteps = steps;
        notifyDataSetChanged();
    }
}