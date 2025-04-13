package com.sarah.applicationsqak.vues;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sarah.applicationsqak.R;

import java.util.Random;

public class InscriptionActivity extends AppCompatActivity {

    private EditText edtPrenom, edtNom, edtCourriel, edtTel, edtMotDePasse, edtConfMDP;
    private Button btnSignUp;
    private ImageView btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Liens avec le layout
        edtPrenom = findViewById(R.id.edtPrenom);
        edtNom = findViewById(R.id.edtNom);
        edtCourriel = findViewById(R.id.edtCourriel);
        edtTel = findViewById(R.id.edtTel);
        edtMotDePasse = findViewById(R.id.edtMotDePasse);
        edtConfMDP = findViewById(R.id.edtConfMDP);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnRetour = findViewById(R.id.imageView4);

        // Reprise des données si retour depuis ConfirmationActivity
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("PRENOM")) edtPrenom.setText(intent.getStringExtra("PRENOM"));
            if (intent.hasExtra("NOM")) edtNom.setText(intent.getStringExtra("NOM"));
            if (intent.hasExtra("COURRIEL")) edtCourriel.setText(intent.getStringExtra("COURRIEL"));
            if (intent.hasExtra("TEL")) edtTel.setText(intent.getStringExtra("TEL"));
            if (intent.hasExtra("MOT_DE_PASSE")) {
                String mdp = intent.getStringExtra("MOT_DE_PASSE");
                edtMotDePasse.setText(mdp);
                edtConfMDP.setText(mdp);
            }
        }

        // Retour à l'accueil
        btnRetour.setOnClickListener(v -> {
            startActivity(new Intent(this, AccueilActivity.class));
            finish();
        });

        // Inscription - aller à ConfirmationActivity
        btnSignUp.setOnClickListener(v -> {
            if (!validateFields()) return;

            // Générer un code à 5 chiffres
            int codeConfirmation = new Random().nextInt(90000) + 10000;

            // Toast temporaire (à remplacer par envoi mail)
            Toast.makeText(this, "Code envoyé par mail : " + codeConfirmation, Toast.LENGTH_SHORT).show();

            // Intent vers confirmation avec toutes les infos
            Intent intentConf = new Intent(this, ConfirmationActivity.class);
            intentConf.putExtra("PRENOM", edtPrenom.getText().toString().trim());
            intentConf.putExtra("NOM", edtNom.getText().toString().trim());
            intentConf.putExtra("COURRIEL", edtCourriel.getText().toString().trim());
            intentConf.putExtra("TEL", edtTel.getText().toString().trim());
            intentConf.putExtra("MOT_DE_PASSE", edtMotDePasse.getText().toString().trim());
            intentConf.putExtra("CODE_CONFIRMATION", String.valueOf(codeConfirmation));

            startActivity(intentConf); // pas de finish() pour permettre le retour
        });
    }

    // Validation des champs
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
