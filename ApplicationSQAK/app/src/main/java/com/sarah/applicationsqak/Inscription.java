package com.sarah.applicationsqak;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class Inscription extends AppCompatActivity {

    //TODO reparer les intents entre confirmation et inscription pour le bouton retour
    //TODO verif si le code a 5 hiffres marche bien

    private EditText edtPrenom, edtNom, edtCourriel, edtTel, edtMotDePasse, edtConfMDP;
    private Button btnSignUp;
    private ImageView btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Elements du layout
        edtPrenom = findViewById(R.id.edtPrenom);
        edtNom = findViewById(R.id.edtNom);
        edtCourriel = findViewById(R.id.edtCourriel);
        edtTel = findViewById(R.id.edtTel);
        edtMotDePasse = findViewById(R.id.edtMotDePasse);
        edtConfMDP = findViewById(R.id.edtConfMDP);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnRetour = findViewById(R.id.imageView4);

        // Bouton retour
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inscription.this, Accueil.class);
                startActivity(intent);
                finish();
            }
        });

        // recup des donnees de la page de confirmation si btn retour
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("PRENOM")) {
                edtPrenom.setText(intent.getStringExtra("PRENOM"));
            }
            if (intent.hasExtra("NOM")) {
                edtNom.setText(intent.getStringExtra("NOM"));
            }
            if (intent.hasExtra("COURRIEL")) {
                edtCourriel.setText(intent.getStringExtra("COURRIEL"));
            }
            if (intent.hasExtra("TEL")) {
                edtTel.setText(intent.getStringExtra("TEL"));
            }
            if (intent.hasExtra("MOT_DE_PASSE")) {
                edtMotDePasse.setText(intent.getStringExtra("MOT_DE_PASSE"));
                edtConfMDP.setText(intent.getStringExtra("MOT_DE_PASSE"));
            }
        }

        // Bouton SignUp
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFields()) return;

                // Générer un code de confirmation à 5 chiffres
                int codeConfirmation = new Random().nextInt(90000) + 10000;

                // TODO: Envoyer code par mail
                Toast.makeText(Inscription.this, "Code envoyé par mail: " + codeConfirmation, Toast.LENGTH_SHORT).show();

                // Rediriger vers Confirmation
                Intent intent = new Intent(Inscription.this, Confirmation.class);
                intent.putExtra("email", edtCourriel.getText().toString().trim());
                intent.putExtra("code", codeConfirmation);
                startActivity(intent);
                finish();
            }
        });
    }


    // Vérification des champs
    private boolean validateFields() {
        boolean valid = true;

        if (edtPrenom.getText().toString().trim().isEmpty()) {
            edtPrenom.setError("Champ requis");
            valid = false;
        }
        if (edtNom.getText().toString().trim().isEmpty()) {
            edtNom.setError("Champ requis");
            valid = false;
        }
        if (edtCourriel.getText().toString().trim().isEmpty()) {
            edtCourriel.setError("Champ requis");
            valid = false;
        }
        if (edtTel.getText().toString().trim().isEmpty()) {
            edtTel.setError("Champ requis");
            valid = false;
        }
        if (edtMotDePasse.getText().toString().trim().isEmpty()) {
            edtMotDePasse.setError("Champ requis");
            valid = false;
        } else if (edtMotDePasse.getText().toString().length() < 8) {
            edtMotDePasse.setError("Min. 8 caractères");
            valid = false;
        }

        if (edtConfMDP.getText().toString().trim().isEmpty()) {
            edtConfMDP.setError("Champ requis");
            valid = false;
        } else if (!edtMotDePasse.getText().toString().equals(edtConfMDP.getText().toString())) {
            edtConfMDP.setError("Les mots de passe ne correspondent pas");
            valid = false;
        }

        return valid;
    }
}
