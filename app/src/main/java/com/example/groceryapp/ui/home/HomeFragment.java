package com.example.groceryapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.databinding.FragmentHomeBinding;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;
import com.example.groceryapp.R;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MaterialButton bannerButton = root.findViewById(R.id.banner_button);
        bannerButton.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Exploring Weekly Selection!", Toast.LENGTH_SHORT).show();
        });

        RecyclerView recyclerView = binding.recyclerViewHome;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        ProductAdapter adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setProducts(getSampleProducts());

        return root;
    }

    private List<ProductAdapter.Product> getSampleProducts() {
        List<ProductAdapter.Product> products = new ArrayList<>();
        products.add(new ProductAdapter.Product("1", "Fresh Avocados", "From Mexico", "Creamy and delicious, perfect for toast or guacamole.", 2.99, "NEW", R.drawable.avocado));
        products.add(new ProductAdapter.Product("2", "Artisanal Sourdough", "Baked Fresh Daily", "Crusty, chewy, and full of flavor. The perfect bread for any meal.", 5.99, "LOCAL", R.drawable.artisan_sourdough));
        products.add(new ProductAdapter.Product("3", "Organic Blueberries", "From Local Farms", "Sweet and juicy, packed with antioxidants. A healthy and delicious snack.", 4.99, "ORGANIC", R.drawable.blueberry));
        return products;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}