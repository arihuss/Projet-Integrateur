package com.sarah.applicationsqak;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Principale extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principale);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liaison des composantes avec la vue
        bottomNav = findViewById(R.id.bottomNavigationView);

        // Get NavHostFragment and ensure its not null
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fgContainer);
        if(navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // Set up the navigation with Bottom Navigation View
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
        else {
            Log.e("Principale", "NavHostFragment is null! Check activity_principale.xml");
        }








    }
}