package com.example.groceryapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.R;
import com.example.groceryapp.helper.ManagmentCart;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private ManagmentCart managmentCart;

    public ProductAdapter(ManagmentCart managmentCart) {
        this.managmentCart = managmentCart;
    }

    public void setProducts(List<Product> products) {
        this.productList = products;
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

        holder.productImage.setImageResource(product.getImageResId());

        // FIXED: Now we pass the WHOLE product object to the cart
        holder.addButton.setOnClickListener(v -> {
            managmentCart.insertItem(product);
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
        private String id, name, origin, description, badge;
        private double price;
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

        public String getName() { return name; }
        public String getOrigin() { return origin; }
        public String getDescription() { return description; }
        public double getPrice() { return price; }
        public String getBadge() { return badge; }
        public int getImageResId() { return imageResId; }
    }
}