package com.example.groceryapp.helper;

import android.content.Context;
import android.widget.Toast;
import com.example.groceryapp.ui.home.ProductAdapter; // Import the Product class
import java.util.ArrayList;

public class ManagmentCart {
    private Context context;

    public ManagmentCart(Context context) {
        this.context = context;
    }

    // Change String to Product so it remembers photos and prices
    private static ArrayList<ProductAdapter.Product> listCart = new ArrayList<>();

    public void insertItem(ProductAdapter.Product item) {
        listCart.add(item);
        Toast.makeText(context, item.getName() + " added to basket", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ProductAdapter.Product> getListCart() {
        return listCart;
    }

    public Double getTotalFee() {
        Double fee = 0.0;
        for (ProductAdapter.Product item : listCart) {
            fee = fee + item.getPrice(); // Dynamic calculation
        }
        return fee;
    }
}