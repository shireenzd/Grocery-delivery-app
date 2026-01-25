package com.example.groceryapp.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.databinding.FragmentRecipesBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Recipe> recipeList = new ArrayList<>();

        // --- 1. One-Pot Cheesy Pasta ---
        List<Ingredient> pastaIngredients = new ArrayList<>();
        pastaIngredients.add(new Ingredient("250g Pasta Shells"));
        pastaIngredients.add(new Ingredient("1 cup Cheddar Cheese"));
        pastaIngredients.add(new Ingredient("1/2 cup Milk"));
        pastaIngredients.add(new Ingredient("2 tsp Butter"));
        pastaIngredients.add(new Ingredient("Salt & Pepper"));

        String pastaSteps = "Step 1: In a medium saucepan, melt butter over medium heat.\n" +
                "Step 2: Stir in milk and bring to a simmer.\n" +
                "Step 3: Add pasta shells and cook until al dente, about 8-10 minutes.\n" +
                "Step 4: Remove from heat, stir in cheese until melted. Season with salt and pepper.";

        recipeList.add(new Recipe(
                "u914-K4_T_k",
                "One-Pot Cheesy Pasta",
                "A quick and delicious one-pot meal perfect for a weeknight dinner.",
                "20",
                "Easy",
                "Chef John",
                pastaIngredients,
                pastaSteps
        ));

        // --- 2. Classic Avocado Toast (NOW UPDATED) ---
        List<Ingredient> avocadoIngredients = new ArrayList<>();
        avocadoIngredients.add(new Ingredient("1 slice of bread"));
        avocadoIngredients.add(new Ingredient("1/2 ripe avocado"));
        avocadoIngredients.add(new Ingredient("1 pinch of salt"));
        avocadoIngredients.add(new Ingredient("1 pinch of red pepper flakes"));

        String avocadoSteps = "Step 1: Toast your slice of bread to your desired crispiness.\n" +
                "Step 2: While the bread is toasting, mash the avocado in a small bowl.\n" +
                "Step 3: Spread the mashed avocado evenly on the toast.\n" +
                "Step 4: Sprinkle with salt and red pepper flakes. Serve immediately.";

        recipeList.add(new Recipe(
                "4sR1eY_b_qM",
                "Classic Avocado Toast",
                "The perfect healthy breakfast or snack.",
                "5",
                "Easy",
                "Erin Lives Whole",
                avocadoIngredients,
                avocadoSteps
        ));

        // --- 3. Spicy Chicken Stir-Fry (NOW UPDATED) ---
        List<Ingredient> stirFryIngredients = new ArrayList<>();
        stirFryIngredients.add(new Ingredient("1 lb boneless chicken breast, cubed"));
        stirFryIngredients.add(new Ingredient("2 cups mixed vegetables (broccoli, carrots)"));
        stirFryIngredients.add(new Ingredient("1/4 cup soy sauce"));
        stirFryIngredients.add(new Ingredient("1 tbsp sesame oil"));
        stirFryIngredients.add(new Ingredient("2 cloves garlic, minced"));

        String stirFrySteps = "Step 1: In a large skillet or wok, heat sesame oil over medium-high heat.\n" +
                "Step 2: Add chicken and cook until browned and cooked through.\n" +
                "Step 3: Add garlic and mixed vegetables. Stir-fry for 3-5 minutes until tender-crisp.\n" +
                "Step 4: Pour in soy sauce and stir to combine. Serve over rice.";

        recipeList.add(new Recipe(
                "3htg-333s4A",
                "Spicy Chicken Stir-Fry",
                "A vibrant stir-fry that is better than takeout.",
                "30",
                "Medium",
                "Ali Martin",
                stirFryIngredients,
                stirFrySteps
        ));

        RecyclerView recyclerView = binding.recyclerViewRecipes;
        RecipeAdapter adapter = new RecipeAdapter(recipeList, getViewLifecycleOwner().getLifecycle());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}