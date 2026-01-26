package com.example.groceryapp.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.groceryapp.databinding.FragmentCartBinding;
import com.example.groceryapp.helper.ManagmentCart;
import com.example.groceryapp.ui.CartAdapter;
import com.example.groceryapp.ui.home.ProductAdapter;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private ManagmentCart managmentCart;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        managmentCart = new ManagmentCart(getContext());

        initList();
        calculateCart();

        return binding.getRoot();
    }

    private void initList() {
        ArrayList<ProductAdapter.Product> items = managmentCart.getListCart();
        if (binding.cartView != null) {
            binding.cartView.setLayoutManager(new LinearLayoutManager(getContext()));
            CartAdapter adapter = new CartAdapter(items, this::calculateCart);
            binding.cartView.setAdapter(adapter);
        }
    }

    public void calculateCart() {
        double total = managmentCart.getTotalFee();
        double delivery = 15.0;
        double finalTotal = total + delivery;

        if (binding.totalFeeTxt != null) {
            binding.totalFeeTxt.setText("$" + String.format("%.2f", total));
            binding.deliveryTxt.setText("$" + String.format("%.2f", delivery));
            binding.totalTxt.setText("$" + String.format("%.2f", finalTotal));
        }
    }
}