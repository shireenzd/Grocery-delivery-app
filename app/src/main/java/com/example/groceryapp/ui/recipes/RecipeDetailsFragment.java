package com.example.groceryapp.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.groceryapp.databinding.FragmentRecipeDetailsBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeDetailsFragment extends BottomSheetDialogFragment {

    private FragmentRecipeDetailsBinding binding;
    private static final String ARG_RECIPE = "arg_recipe";
    private Recipe recipe;
    private IngredientAdapter adapter;

    // factory method to create a new instance, passing the recipe
    public static RecipeDetailsFragment newInstance(Recipe recipe) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipe = (Recipe) getArguments().getSerializable(ARG_RECIPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (recipe == null) {
            dismiss(); // Close if there's no data
            return;
        }

        // Populate the views
        binding.tvRecipeAuthor.setText("Recipe by " + recipe.author);
        binding.tvRecipeTitlePopup.setText(recipe.title);
        binding.tvRecipeSteps.setText(recipe.recipeSteps);

        // Set up RecyclerView for ingredients
        adapter = new IngredientAdapter(recipe.ingredients);
        binding.recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewIngredients.setAdapter(adapter);

        // Set up the button click listener
        binding.btnAddToCart.setOnClickListener(v -> {
            List<Ingredient> itemsToAdd = adapter.getUncheckedIngredients();

            if (itemsToAdd.isEmpty()) {
                Toast.makeText(getContext(), "All ingredients are checked!", Toast.LENGTH_SHORT).show();
            } else {
                // For now, just show a Toast. Later, add cart logic here.
                String names = itemsToAdd.stream().map(i -> i.name).collect(Collectors.joining(", "));
                Toast.makeText(getContext(), "Adding to cart: " + names, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}