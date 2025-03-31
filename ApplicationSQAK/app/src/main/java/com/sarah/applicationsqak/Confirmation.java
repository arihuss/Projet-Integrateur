package com.sarah.applicationsqak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {

    private EditText edtCodeConfirmation;
    private String codeEnvoye; // Le code envoyé de inscription
    private String prenom, nom, courriel, tel, motDePasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Récupération des données depuis l'inscription
        Intent intent = getIntent();
        if (intent != null) {
            codeEnvoye = intent.getStringExtra("CODE_CONFIRMATION");
            prenom = intent.getStringExtra("PRENOM");
            nom = intent.getStringExtra("NOM");
            courriel = intent.getStringExtra("COURRIEL");
            tel = intent.getStringExtra("TEL");
            motDePasse = intent.getStringExtra("MOT_DE_PASSE");
        }

        edtCodeConfirmation = findViewById(R.id.edtCodeConfirmation);
        Button btnConfirmer = findViewById(R.id.btnConfirmer);
        ImageView btnRetour = findViewById(R.id.btnRetour);

        // Bouton retour avec infos
        btnRetour.setOnClickListener(v -> {
            Intent retourIntent = new Intent(Confirmation.this, Inscription.class);
            retourIntent.putExtra("PRENOM", prenom);
            retourIntent.putExtra("NOM", nom);
            retourIntent.putExtra("COURRIEL", courriel);
            retourIntent.putExtra("TEL", tel);
            retourIntent.putExtra("MOT_DE_PASSE", motDePasse);
            startActivity(retourIntent);
            finish();
        });

        // Vérif code de confirmation
        btnConfirmer.setOnClickListener(v -> {
            String codeEntre = edtCodeConfirmation.getText().toString().trim();

            if (codeEntre.isEmpty() || codeEntre.length() != 5 || !codeEntre.matches("\\d{5}")) {
                edtCodeConfirmation.setError("Veuillez entrer un code à 5 chiffres");
            } else if (!codeEntre.equals(codeEnvoye)) {
                edtCodeConfirmation.setError("Code incorrect !");
            } else {

                // TODO: faire le INSERT de lutilisateur

                // toast de Confirmation
                Toast.makeText(Confirmation.this, "Compte créé avec succès !", Toast.LENGTH_LONG).show();
                Intent intentConnexion = new Intent(Confirmation.this, Connexion.class);
                startActivity(intentConnexion);
                finish();
            }
        });
    }
}
