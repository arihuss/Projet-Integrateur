package com.sarah.applicationsqak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Accueil extends AppCompatActivity {

    private Switch switchDarkMode;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String DARK_MODE_KEY = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // preferences systeme sil y a lieu
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(DARK_MODE_KEY, false);

        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // mettre la switch active si dark mode pref systeme
        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchDarkMode.setChecked(isDarkModeEnabled);

        // Switch changement de mode light/dark
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            sharedPreferences.edit().putBoolean(DARK_MODE_KEY, isChecked).apply();

            restartActivity();
        });

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

    // Fonction pour redémarrer l'activité et mettre DM
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
