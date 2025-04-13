package com.sarah.applicationsqak.vues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.sarah.applicationsqak.R;

public class AccueilActivity extends AppCompatActivity {

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

        if (getIntent().getBooleanExtra("Suppression_compte", false)) {
            Toast.makeText(this, "Compte supprimé avec succès", Toast.LENGTH_SHORT).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Boutons
        Button btnSeConnecter = findViewById(R.id.btnSeConnecter);
        Button btnSinscrire = findViewById(R.id.btnSinscrire);
        Button btnInvite = findViewById(R.id.btnInvite);

        // Bouton Se connecter
        btnSeConnecter.setOnClickListener(v -> {
            Intent intent = new Intent(AccueilActivity.this, ConnexionActivity.class);
            startActivity(intent);
        });

        // Bouton S'inscrire
        btnSinscrire.setOnClickListener(v -> {
            Intent intent = new Intent(AccueilActivity.this, InscriptionActivity.class);
            startActivity(intent);
        });

        // Bouton En invité
        btnInvite.setOnClickListener(v -> {
            Intent intent = new Intent(AccueilActivity.this, PrincipaleActivity.class);
            startActivity(intent);
        });
    }
}
