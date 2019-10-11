package com.nelsonlopes.bakingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Recipe> recipes;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public RecipesAdapter(Context context, List<Recipe> mRecipes) {
        mContext = context;
        recipes = mRecipes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecipesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);

        RecipesAdapter.MyViewHolder vh = new RecipesAdapter.MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from the dataset at this position
        // - replace the contents of the view with that
        TextView recipeTv = holder.view.findViewById(R.id.tv_recipe);

        recipeTv.setText(recipes.get(position).getName());

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
        if (recipes != null) {
            return recipes.size();
        } else {
            return 0;
        }
    }

    public Recipe getItem(int position) {
        if (recipes == null || recipes.size() == 0) {
            return null;
        }

        return recipes.get(position);
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
}
