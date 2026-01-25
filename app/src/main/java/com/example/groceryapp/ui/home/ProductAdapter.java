package com.example.groceryapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.R;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList = new ArrayList<>();

    public void setProducts(List<Product> products) {
        productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product_card_modern, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getPrice());
        holder.productOrigin.setText(product.getOrigin());
        holder.productDescription.setText(product.getDescription());

        if (product.getBadge() != null && !product.getBadge().isEmpty()) {
            holder.productBadge.setText(product.getBadge().toUpperCase());
            holder.productBadge.setVisibility(View.VISIBLE);
        } else {
            holder.productBadge.setVisibility(View.GONE);
        }

        if (product.getImageResId() != 0) {
            holder.productImage.setImageResource(product.getImageResId());
        } else {
            holder.productImage.setImageResource(R.drawable.ic_placeholder);
        }

        holder.addButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),
                    "Added " + product.getName() + " to basket!",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productBadge, productName, productPrice, productOrigin, productDescription;
        android.widget.Button addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productBadge = itemView.findViewById(R.id.product_badge);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productOrigin = itemView.findViewById(R.id.product_origin);
            productDescription = itemView.findViewById(R.id.product_description);
            addButton = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }

    public static class Product {
        private String id;
        private String name;
        private String origin;
        private String description;
        private double price;
        private String badge;
        private int imageResId;

        public Product(String id, String name, String origin, String description, double price, String badge, int imageResId) {
            this.id = id;
            this.name = name;
            this.origin = origin;
            this.description = description;
            this.price = price;
            this.badge = badge;
            this.imageResId = imageResId;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getOrigin() { return origin; }
        public String getDescription() { return description; }
        public double getPrice() { return price; }
        public String getBadge() { return badge; }
        public int getImageResId() { return imageResId; }
    }
}