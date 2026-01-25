package com.example.groceryapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.groceryapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean showLogout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        BottomNavigationView navView = binding.navView;

        // Top-level destinations (bottom nav screens)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_recipes,
                R.id.navigation_cart
        ).build();

        androidx.navigation.fragment.NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_activity_main);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Handle BottomNav + Logout visibility based on destination
        navController.addOnDestinationChangedListener(
                (controller, destination, arguments) -> {

                    if (destination.getId() == R.id.signInFragment) {
                        navView.setVisibility(View.GONE);
                        showLogout = false;
                    } else {
                        navView.setVisibility(View.VISIBLE);
                        showLogout = true;
                    }

                    invalidateOptionsMenu(); // refresh toolbar menu
                }
        );
    }

    // Inflate toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    // Show / hide logout icon dynamically
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logoutItem = menu.findItem(R.id.action_logout);
        if (logoutItem != null) {
            logoutItem.setVisible(showLogout);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    // Handle logout click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {

            androidx.navigation.fragment.NavHostFragment navHostFragment =
                    (NavHostFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.nav_host_fragment_activity_main);

            NavController navController = navHostFragment.getNavController();

            navController.navigate(R.id.signInFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
