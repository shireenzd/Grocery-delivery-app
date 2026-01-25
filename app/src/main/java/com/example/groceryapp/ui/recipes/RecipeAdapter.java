package com.example.groceryapp.ui.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceryapp.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> recipes;
    private final Lifecycle lifecycle;

    public RecipeAdapter(List<Recipe> recipes, Lifecycle lifecycle) {
        this.recipes = recipes;
        this.lifecycle = lifecycle;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, lifecycle);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView playerView;
        TextView title, desc, mins, level, link;

        public RecipeViewHolder(@NonNull View itemView, Lifecycle lifecycle) {
            super(itemView);
            playerView = itemView.findViewById(R.id.youtube_player_view);
            title = itemView.findViewById(R.id.tv_recipe_title);
            desc = itemView.findViewById(R.id.tv_recipe_desc);
            mins = itemView.findViewById(R.id.tv_minutes);
            level = itemView.findViewById(R.id.tv_level);
            link = itemView.findViewById(R.id.tv_full_recipe_link);

            lifecycle.addObserver(playerView);
        }

        public void bind(Recipe recipe) {
            title.setText(recipe.title);
            desc.setText(recipe.description);
            mins.setText(recipe.minutes + " mins");
            level.setText("• " + recipe.level);

            playerView.getYouTubePlayerWhenReady(youTubePlayer -> {
                youTubePlayer.cueVideo(recipe.videoId, 0);
            });

            link.setOnClickListener(v -> {
                if (itemView.getContext() instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                    RecipeDetailsFragment bottomSheet = RecipeDetailsFragment.newInstance(recipe);
                    bottomSheet.show(activity.getSupportFragmentManager(), "RecipeDetailsFragment");
                }
            });
        }
    }
}