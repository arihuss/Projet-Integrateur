package com.sarah.applicationsqak.vues;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

public class ModifierProfilActivity extends AppCompatActivity {

    private ImageButton ImgbtnModifBack;
    private Button SaveModif, btnChoisirPhoto;
    private EditText edtprenom, edtnom, edtmail, edtnum, edtbio, edtmdp, edtmdp2;
    private Uri imageUpload;
    private ActivityResultLauncher<Intent> launcherGalerie;

    private Utilisateur utilisateurActuel;
    private UtilisateurViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifier_profil);

        // Initialiser les vues
        edtprenom = findViewById(R.id.edtModifPrenom);
        edtnom = findViewById(R.id.edtModifNom);
        edtmail = findViewById(R.id.edtModifCourriel);
        edtnum = findViewById(R.id.edtModifNum);
        edtbio = findViewById(R.id.edtModifBio);
        edtmdp = findViewById(R.id.edtModifMdp);
        edtmdp2 = findViewById(R.id.edtModifConfirm);
        SaveModif = findViewById(R.id.btnModifSave);
        btnChoisirPhoto = findViewById(R.id.btnChoisirPhoto);
        ImgbtnModifBack = findViewById(R.id.btnRetourSettings);

        viewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        // Récupérer l'utilisateur courant
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        long userId = prefs.getLong("utilisateur_id", -1);
        utilisateurActuel = viewModel.getUtilisateurParId(userId);

        if (utilisateurActuel != null) {
            edtprenom.setText(utilisateurActuel.getPrenom());
            edtnom.setText(utilisateurActuel.getNom());
            edtmail.setText(utilisateurActuel.getCourriel());
            edtnum.setText(utilisateurActuel.getNumTel());
            edtbio.setText(utilisateurActuel.getBio());
        }

        // Gestion de la galerie
        launcherGalerie = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imageUpload = result.getData().getData();
                    }
                }
        );

        // Listeners
        ImgbtnModifBack.setOnClickListener(v -> finish());

        btnChoisirPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            launcherGalerie.launch(intent);
        });

        SaveModif.setOnClickListener(v -> enregistrerModifications());

        // Inset padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void enregistrerModifications() {
        boolean aChange = false;
        boolean erreur = false;

        String nouveauPrenom = edtprenom.getText().toString().trim();
        String nouveauNom = edtnom.getText().toString().trim();
        String nouveauCourriel = edtmail.getText().toString().trim();
        String nouveauNum = edtnum.getText().toString().trim();
        String nouvelleBio = edtbio.getText().toString().trim();
        String nouveauMdp = edtmdp.getText().toString();
        String nouveauMdpConfirm = edtmdp2.getText().toString();

        // Mot de passe modifié ?
        if (!nouveauMdp.isEmpty()) {
            if (nouveauMdp.length() < 8) {
                edtmdp.setError("Le mot de passe doit contenir au moins 8 caractères.");
                erreur = true;
            } else if (!nouveauMdp.equals(nouveauMdpConfirm)) {
                edtmdp2.setError("Les mots de passe ne correspondent pas.");
                erreur = true;
            } else {
                utilisateurActuel.setMotDePasse(nouveauMdp);
                aChange = true;
            }
        }

        // Comparer les champs
        if (!nouveauPrenom.equals(utilisateurActuel.getPrenom())) {
            utilisateurActuel.setPrenom(nouveauPrenom);
            aChange = true;
        }

        if (!nouveauNom.equals(utilisateurActuel.getNom())) {
            utilisateurActuel.setNom(nouveauNom);
            aChange = true;
        }

        if (!nouveauCourriel.equals(utilisateurActuel.getCourriel())) {
            utilisateurActuel.setCourriel(nouveauCourriel);
            aChange = true;
        }

        if (!nouveauNum.equals(utilisateurActuel.getNumTel())) {
            utilisateurActuel.setNumTel(nouveauNum);
            aChange = true;
        }

        if (!nouvelleBio.equals(utilisateurActuel.getBio())) {
            utilisateurActuel.setBio(nouvelleBio);
            aChange = true;
        }

        if (imageUpload != null) {
            utilisateurActuel.setImageUrl(imageUpload.toString());
            aChange = true;
        }

        // Mise à jour si OK
        if (!erreur && aChange) {
            viewModel.mettreAJourUtilisateur(utilisateurActuel);
            Toast.makeText(this, "Profil mis à jour", Toast.LENGTH_SHORT).show();
            finish();
        } else if (!erreur) {
            Toast.makeText(this, "Aucun changement détecté", Toast.LENGTH_SHORT).show();
        }
    }
}
