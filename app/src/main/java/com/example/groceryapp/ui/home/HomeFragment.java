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
        
        products.add(new ProductAdapter.Product(
                "1", 
                "Tuscan Extra Virgin Olive Oil", 
                "CHIANTI, ITALY", 
                "Cold-pressed from century-old groves in the hills of Chianti. Notes of artichoke and fresh grass with a peppery finish.", 
                42, 
                "LIMITED HARVEST", 
                R.drawable.photo_home)); // Using available image as placeholder

        products.add(new ProductAdapter.Product(
                "2", 
                "Aged Parmigiano Reggiano", 
                "EMILIA-ROMAGNA, ITALY", 
                "36-month aged wheels from a family dairy. Crystallized texture with intense nutty and savory flavors.", 
                28, 
                "DOP CERTIFIED", 
                R.drawable.ic_placeholder));

        products.add(new ProductAdapter.Product(
                "3", 
                "Wild Alaskan Salmon", 
                "COPPER RIVER, ALASKA", 
                "Line-caught in pristine waters and flash-frozen within hours to preserve its vibrant color and rich omega-3 oils.", 
                38, 
                "SUSTAINABLE", 
                R.drawable.ic_placeholder));

        products.add(new ProductAdapter.Product(
                "4", 
                "Heirloom Tomatoes", 
                "SICILY, ITALY", 
                "Grown in volcanic soil on a small farm in Sicily. Bursting with sweetness and complex acidity.", 
                12, 
                "ORGANIC", 
                R.drawable.ic_placeholder));

        products.add(new ProductAdapter.Product(
                "5", 
                "Artisan Sourdough", 
                "SAN FRANCISCO, USA", 
                "Naturally leavened with a 100-year-old starter. Thick, caramelized crust with a chewy, open crumb.", 
                18, 
                "FRESH BAKED", 
                R.drawable.artisan_sourdough));

        products.add(new ProductAdapter.Product(
                "6", 
                "French Sea Salt", 
                "GUÉRANDE, FRANCE", 
                "Hand-harvested from the salt marshes of Guérande. Delicate crystals that add a perfect finishing crunch.", 
                16, 
                null, 
                R.drawable.ic_placeholder));

        return products;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}