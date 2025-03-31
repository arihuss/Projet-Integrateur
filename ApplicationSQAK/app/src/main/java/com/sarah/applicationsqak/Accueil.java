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
        // Charger les préférences avant d'afficher l'UI
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(DARK_MODE_KEY, false);

        // Appliquer le mode sombre si activé
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Récupérer le Switch
        switchDarkMode = findViewById(R.id.switchDarkMode);

        // Mettre à jour l'état du Switch
        switchDarkMode.setChecked(isDarkModeEnabled);

        // Gérer le changement de mode avec le Switch
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Sauvegarder l'état du mode sombre
            sharedPreferences.edit().putBoolean(DARK_MODE_KEY, isChecked).apply();

            // Redémarrer l'activité pour appliquer le changement
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

    // Fonction pour redémarrer l'activité et appliquer le changement de mode
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
