package com.sarah.applicationsqak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Boutons
        Button btnSeConnecter = findViewById(R.id.btnSeConnecter);
        Button btnSinscrire = findViewById(R.id.btnSinscrire);
        Button btnInvite = findViewById(R.id.btnInvite);

        // Bouton Se connecter
        btnSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, Connexion.class);
                startActivity(intent);
            }
        });

        // Bouton S'inscrire
        btnSinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, Inscription.class);
                startActivity(intent);
            }
        });

        // Bouton En invité
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, Principale.class);
                startActivity(intent);
            }
        });
    }
}
