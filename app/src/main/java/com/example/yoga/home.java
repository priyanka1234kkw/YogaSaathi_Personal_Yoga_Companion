package com.example.yoga;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;

public class home extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ImageView yogaImage = findViewById(R.id.yogaImage);
        TextView tvQuote = findViewById(R.id.tvQuote);

        // Load Animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Apply Animation
        yogaImage.startAnimation(fadeIn);
        tvQuote.startAnimation(fadeIn);

        // Open Drawer on Menu Icon Click
        findViewById(R.id.menu_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Handle Navigation Drawer Clicks
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_notification) {
                // Handle notification click
            } else if (id == R.id.nav_contact) {
                // Handle contact click
            } else if (id == R.id.nav_rate) {
                // Handle rate us click
            } else if (id == R.id.nav_feedback) {
                // Handle feedback click
            } else if (id == R.id.nav_policy) {
                // Handle policies click
            } else if (id == R.id.nav_signout) {
                // Handle sign out click
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Handle Bottom Navigation Clicks
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Stay on Home
            } else if (id == R.id.nav_yoga) {
                // Navigate to Yoga Plans Activity
                Intent intent = new Intent(home.this, yoga_plan.class);
                startActivity(intent);
            } else if (id == R.id.nav_insights) {
                // Load Insights Section
            } else if (id == R.id.nav_profile) {
                // Load Profile Section
            }
            return true;
        });

    }
}
