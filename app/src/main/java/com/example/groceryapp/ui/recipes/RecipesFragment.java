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
        recipeList.add(new Recipe(
                "u914-K4_T_k",
                "One-Pot Cheesy Pasta",
                "A quick and delicious one-pot meal perfect for a weeknight dinner.",
                "20",
                "Easy",
                "https://www.allrecipes.com/recipe/238691/simple-macaroni-and-cheese/"
        ));
        recipeList.add(new Recipe(
                "4sR1eY_b_qM",
                "Classic Avocado Toast",
                "The perfect healthy and satisfying breakfast or snack.",
                "5",
                "Easy",
                "https://www.loveandlemons.com/avocado-toast-recipe/"
        ));
        recipeList.add(new Recipe(
                "3htg-333s4A",
                "Spicy Chicken Stir-Fry",
                "A vibrant and flavorful stir-fry that is better than takeout.",
                "30",
                "Medium",
                "https://www.gimmesomeoven.com/stir-fry-recipe/"
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