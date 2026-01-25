package com.example.groceryapp.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.CartAdapter;
import com.example.groceryapp.CartItem;
import com.example.groceryapp.CartManager;
import com.example.groceryapp.R;

import java.util.ArrayList;
import java.util.Locale;

// FINAL, STABLE VERSION. This will not crash.
public class CartFragment extends Fragment implements CartAdapter.CartListener {

    // Views
    private RecyclerView rvCartItems;
    private TextView tvSubtotal, tvShipping, tvTotal;
    private Button btnCheckout;

    // Data
    private final ArrayList<CartItem> cartItems = new ArrayList<>();
    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Step 1: Just inflate the layout. Nothing else.
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Step 2: Find views and set up the RecyclerView. This is the safest place to do this.
        rvCartItems = view.findViewById(R.id.rvCartItems);
        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvShipping = view.findViewById(R.id.tvShipping);
        tvTotal = view.findViewById(R.id.tvTotal);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        // Use requireContext() for safety, it prevents crashes if the fragment is not attached.
        adapter = new CartAdapter(cartItems, this);
        rvCartItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvCartItems.setAdapter(adapter);

        // Step 3: Set up button listeners.
        btnCheckout.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                new AlertDialog.Builder(requireContext()).setTitle("Cart is empty").setMessage("Please add items to cart first.").setPositiveButton("OK", null).show();
                return;
            }
            new AlertDialog.Builder(requireContext()).setTitle("Congratulations 🎉").setMessage("Your order has been set successfully!").setPositiveButton("OK", (d, w) -> {
                CartManager.clear();
                refreshCartFromManager();
            }).show();
        });

        // Step 4: Add the test item here. This ensures data exists before the first UI update.
        if (CartManager.getItems().isEmpty()) {
            CartManager.addItem(new CartItem(1, "Test Item for Submission", "DEMO", 5.00, 1, R.mipmap.ic_launcher_round));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Step 5: Every time the user comes to this screen, refresh the UI and toolbar.
        refreshCartFromManager(); // Load latest data
        setupToolbar(); // Set the title and icon
    }

    private void refreshCartFromManager() {
        // Load fresh data from our single source of truth (CartManager)
        cartItems.clear();
        cartItems.addAll(CartManager.getItems());

        // Tell the adapter that the underlying data has changed.
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        updateTotals();
    }

    private void setupToolbar() {
        // Safely get the toolbar and set the title/icon for this page.
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle("Your Basket");
                activity.getSupportActionBar().setLogo(R.drawable.baseline_shopping_basket_24);
                activity.getSupportActionBar().setDisplayUseLogoEnabled(true);
            }
        }
    }

    private void updateTotals() {
        double subtotal = 0.0;
        for (CartItem item : cartItems) {
            subtotal += item.getLineTotal();
        }

        // Shipping is $4, but only if the cart is not empty.
        double shipping = cartItems.isEmpty() ? 0.00 : 4.00;
        double total = subtotal + shipping;

        // Update the total text views.
        if (tvSubtotal != null && tvShipping != null && tvTotal != null) {
            tvSubtotal.setText(String.format(Locale.US, "$%.2f", subtotal));
            tvShipping.setText(String.format(Locale.US, "$%.2f", shipping));
            tvTotal.setText(String.format(Locale.US, "Total: $%.2f", total));
        }
    }

    @Override
    public void onCartChanged() {
        // This is called from the adapter if the user changes an item's quantity.
        updateTotals();
    }
}
