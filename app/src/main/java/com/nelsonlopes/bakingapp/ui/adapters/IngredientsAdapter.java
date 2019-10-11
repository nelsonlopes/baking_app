package com.nelsonlopes.bakingapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nelsonlopes.bakingapp.R;
import com.nelsonlopes.bakingapp.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Ingredient> ingredients;

    // Provide a reference to the views for each data item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public IngredientsAdapter(Context context, List<Ingredient> mIngredients) {
        mContext = context;
        ingredients = mIngredients;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item, parent, false);

        IngredientsAdapter.MyViewHolder vh = new IngredientsAdapter.MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from the dataset at this position
        // - replace the contents of the view with that
        TextView ingredientTv = holder.view.findViewById(R.id.tv_ingredient);

        ingredientTv.setText(ingredients.get(position).getQuantity() + " " +
                ingredients.get(position).getMeasure() + " " +
                ingredients.get(position).getIngredient());
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (ingredients != null) {
            return ingredients.size();
        } else {
            return 0;
        }
    }

    public Ingredient getItem(int position) {
        if (ingredients == null || ingredients.size() == 0) {
            return null;
        }

        return ingredients.get(position);
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }
}