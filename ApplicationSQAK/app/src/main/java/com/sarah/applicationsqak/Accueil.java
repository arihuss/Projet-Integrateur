package com.sarah.applicationsqak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Accueil extends AppCompatActivity {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String DARK_MODE_KEY = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Lire la préférence du mode sombre avant d'afficher l'interface
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(DARK_MODE_KEY, false);

        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Boutons
        Button btnSeConnecter = findViewById(R.id.btnSeConnecter);
        Button btnSinscrire = findViewById(R.id.btnSinscrire);
        Button btnInvite = findViewById(R.id.btnInvite);

        // Bouton Se connecter
        btnSeConnecter.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this, Connexion.class);
            startActivity(intent);
        });

        // Bouton S'inscrire
        btnSinscrire.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this, Inscription.class);
            startActivity(intent);
        });

        // Bouton En invité
        btnInvite.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this, Principale.class);
            startActivity(intent);
        });
    }
}
