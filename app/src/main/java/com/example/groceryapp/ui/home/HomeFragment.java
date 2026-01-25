package com.example.groceryapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.databinding.FragmentHomeBinding;
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


        products.add(new ProductAdapter.Product("1", "Avocado", "CHIANTI, ITALY",
                "Cold-pressed from century-old groves...", 42, "LIMITED HARVEST", R.drawable.avocado));

        products.add(new ProductAdapter.Product("2", "Blueberry", "MAINE, USA",
                "Wild blueberries from the coastal regions of Maine.", 28, "ORGANIC", R.drawable.blueberry));

        products.add(new ProductAdapter.Product("3", "Artisan Sourdough", "SAN FRANCISCO, USA",
                "Traditional 48-hour fermented sourdough bread.", 18, "FRESH BAKED", R.drawable.artisan_sourdough));


        return products;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}