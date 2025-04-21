package com.sarah.applicationsqak.vues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

public class ConfirmationActivity extends AppCompatActivity {

    private EditText edtCodeConfirmation;
    private String codeEnvoye;
    private String prenom, nom, courriel, tel, motDePasse;

    private UtilisateurViewModel utilisateurViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        // Récupération des données
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

        // Retour vers inscription
        btnRetour.setOnClickListener(v -> {
            Intent retourIntent = new Intent(ConfirmationActivity.this, InscriptionActivity.class);
            retourIntent.putExtra("PRENOM", prenom);
            retourIntent.putExtra("NOM", nom);
            retourIntent.putExtra("COURRIEL", courriel);
            retourIntent.putExtra("TEL", tel);
            retourIntent.putExtra("MOT_DE_PASSE", motDePasse);
            startActivity(retourIntent);
            finish();
        });

        // Confirmation
        btnConfirmer.setOnClickListener(v -> {
            String codeEntre = edtCodeConfirmation.getText().toString().trim();

            if (codeEntre.isEmpty() || codeEntre.length() != 5 || !codeEntre.matches("\\d{5}")) {
                edtCodeConfirmation.setError("Veuillez entrer un code à 5 chiffres");
            } else if (!codeEntre.equals(codeEnvoye)) {
                edtCodeConfirmation.setError("Code incorrect !");
            } else {
                // Création du nouvel utilisateur
                Utilisateur nouvelUtilisateur = new Utilisateur(
                        prenom, nom, courriel, tel, motDePasse
                );

                long id = utilisateurViewModel.insererUtilisateur(nouvelUtilisateur);

                if (id != -1) {
                    Toast.makeText(this, "Compte créé avec succès !", Toast.LENGTH_LONG).show();
                    Intent intentConnexion = new Intent(ConfirmationActivity.this, ConnexionActivity.class);
                    startActivity(intentConnexion);
                    finish();
                } else {
                    Toast.makeText(this, "Erreur lors de la création du compte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
