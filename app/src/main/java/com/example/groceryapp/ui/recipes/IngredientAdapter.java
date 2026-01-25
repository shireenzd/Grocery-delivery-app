package com.example.groceryapp.ui.recipes;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private final List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    // Helper method to get the ingredients that are NOT checked
    public List<Ingredient> getUncheckedIngredients() {
        List<Ingredient> unchecked = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.isChecked) {
                unchecked.add(ingredient);
            }
        }
        return unchecked;
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_ingredient);
        }

        public void bind(final Ingredient ingredient) {
            checkBox.setText(ingredient.name);
            checkBox.setChecked(ingredient.isChecked);
            applyStrikeThrough(ingredient.isChecked);

            // Update the model when the checkbox state changes
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                ingredient.isChecked = isChecked;
                applyStrikeThrough(isChecked);
            });
        }

        private void applyStrikeThrough(boolean isChecked) {
            if (isChecked) {
                checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                checkBox.setPaintFlags(checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }
    }
}