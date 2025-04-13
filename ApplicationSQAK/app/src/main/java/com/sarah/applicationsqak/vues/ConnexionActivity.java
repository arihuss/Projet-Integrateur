package com.sarah.applicationsqak.vues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

public class ConnexionActivity extends AppCompatActivity {

    private EditText edtCourriel, edtMotDePasse;
    private Button btnSeConnecter;
    private ImageView btnRetour;
    private TextView txtMdpOublie;

    private UtilisateurViewModel utilisateurViewModel;

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PREF_USER_ID = "utilisateur_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        edtCourriel = findViewById(R.id.edtCourriel);
        edtMotDePasse = findViewById(R.id.edtMotDePasse);
        btnSeConnecter = findViewById(R.id.btnSeConnecter);
        btnRetour = findViewById(R.id.imageView4);
        txtMdpOublie = findViewById(R.id.txtMdpOublie);

        // Bouton retour accueil
        btnRetour.setOnClickListener(v -> {
            startActivity(new Intent(this, AccueilActivity.class));
            finish();
        });

        // Connexion
        btnSeConnecter.setOnClickListener(v -> {
            String email = edtCourriel.getText().toString().trim();
            String password = edtMotDePasse.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            Utilisateur utilisateur = utilisateurViewModel.authentifier(email, password);

            if (utilisateur != null) {
                // Sauvegarder l'ID utilisateur
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                prefs.edit().putLong(PREF_USER_ID, utilisateur.getId()).apply();

                Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, PrincipaleActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
            }
        });

        txtMdpOublie.setOnClickListener(v -> {
            Toast.makeText(this, "Fonctionnalité à implémenter", Toast.LENGTH_SHORT).show();
        });
    }
}
