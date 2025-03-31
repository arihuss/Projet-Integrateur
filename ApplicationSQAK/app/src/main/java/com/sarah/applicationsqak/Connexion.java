package com.sarah.applicationsqak;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Connexion extends AppCompatActivity {

    private EditText edtCourriel, edtMotDePasse;
    private Button btnSeConnecter;
    private ImageView btnRetour;
    private TextView txtMdpOublie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        // Elements du layout
        edtCourriel = findViewById(R.id.edtCourriel);
        edtMotDePasse = findViewById(R.id.edtMotDePasse);
        btnSeConnecter = findViewById(R.id.btnSeConnecter);
        btnRetour = findViewById(R.id.imageView4);
        txtMdpOublie = findViewById(R.id.txtMdpOublie);

        // Bouton retour
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connexion.this, Accueil.class);
                startActivity(intent);
                finish();
            }
        });

        // Bouton Se connecter vers Principale
        btnSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtCourriel.getText().toString().trim();
                String password = edtMotDePasse.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Connexion.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Vérif si info correspondent avec le JSON Server
                Toast.makeText(Connexion.this, "Connexion réussie", Toast.LENGTH_SHORT).show();

                // Redirection vers principale
                Intent intent = new Intent(Connexion.this, Principale.class);
                startActivity(intent);
                finish();
            }
        });

        // TODO: faire fonctionnalité pour "Mot de passe oublié"
        txtMdpOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Connexion.this, "Fonctionnalité à implémenter", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
