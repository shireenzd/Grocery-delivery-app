package com.example.groceryapp.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.R;
import com.example.groceryapp.ui.home.ProductAdapter;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<ProductAdapter.Product> cartList;
    private Runnable updatePriceTask;

    public CartAdapter(ArrayList<ProductAdapter.Product> cartList, Runnable updatePriceTask) {
        this.cartList = cartList;
        this.updatePriceTask = updatePriceTask;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductAdapter.Product item = cartList.get(position);

        holder.title.setText(item.getName());
        holder.pic.setImageResource(item.getImageResId());
        holder.priceEach.setText("$" + String.format("%.2f", item.getPrice()));

        // --- PLUS BUTTON ---
        holder.plusItem.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.num.getText().toString());
            int newCount = count + 1;
            holder.num.setText(String.valueOf(newCount));
            holder.priceEach.setText("$" + String.format("%.2f", item.getPrice() * newCount));
            updatePriceTask.run(); // Updates total price at bottom
        });

        // --- MINUS BUTTON ---
        holder.minusItem.setOnClickListener(v -> {
            int count = Integer.parseInt(holder.num.getText().toString());
            if (count > 1) {
                int newCount = count - 1;
                holder.num.setText(String.valueOf(newCount));
                holder.priceEach.setText("$" + String.format("%.2f", item.getPrice() * newCount));
                updatePriceTask.run();
            } else {
                // If quantity is 1 and user presses minus, remove it
                cartList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                updatePriceTask.run();
            }
        });

        // --- TRASH BUTTON (GARBAGE ICON) ---
        holder.trashBtn.setOnClickListener(v -> {
            // 1. Remove the item from the data list
            int currentPosition = holder.getAdapterPosition();
            cartList.remove(currentPosition);

            // 2. Refresh the list with an animation
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition, cartList.size());

            // 3. Update the total price at the bottom
            updatePriceTask.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, plusItem, minusItem, num, priceEach;
        ImageView pic, trashBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCartTxt);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
            priceEach = itemView.findViewById(R.id.totalEachItem);
            trashBtn = itemView.findViewById(R.id.trashBtn);
        }
    }
}