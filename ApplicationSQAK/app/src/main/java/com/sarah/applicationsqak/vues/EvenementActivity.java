package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Dao.InscriptionDao;
import com.sarah.applicationsqak.modele.Dao.UtilisateurDao;
import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.viewmodel.EvenementViewModel;
import com.sarah.applicationsqak.viewmodel.InscriptionViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EvenementActivity extends AppCompatActivity {

    private TextView tvDescEvent, tvDateEvent, tvLieuEvent, tvNomEvent, tvNomOrganisateur, tvNbLikes;
    private EditText txtComment;
    private ImageView imgProfileOrg, imgEvent, imgRetour;
    private Button btnInscInvite, btnInscBenevole, btnPublier;
    LinearLayout layoutCommentaires;
    private Evenement evenement;
    EvenementDao dao;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String PREF_USER_ID = "utilisateur_id";
    private InscriptionViewModel modelInscription;
    private EvenementViewModel modelEvent;
    private UtilisateurDao daoUser;
    long idUser; // id de l'utilisateur connecté


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "EvenementActivity onCreate() appelé");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_evenement);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liaison avec la vue
        tvDescEvent = findViewById(R.id.tvDescrEvent);
        tvDateEvent = findViewById(R.id.tvDateEvent);
        tvLieuEvent = findViewById(R.id.tvLieuEvent);
        imgProfileOrg = findViewById(R.id.imgProfileEvents);
        btnInscInvite = findViewById(R.id.btnInscInvite);
        btnInscBenevole = findViewById(R.id.btnInscBenevole);
        btnPublier = findViewById(R.id.btnPublier);
        tvNomEvent = findViewById(R.id.tvNomEvent);
        tvNomOrganisateur = findViewById(R.id.tvNomOrganisateur);
        tvNbLikes = findViewById(R.id.tvNbLikes);
        txtComment = findViewById(R.id.etNouvComment);
        imgEvent = findViewById(R.id.imgAfficheEvent);
        imgRetour = findViewById(R.id.imgRetour);
        layoutCommentaires = findViewById(R.id.layoutCommentaires);


        // Récupérer l'id de l'événement passer en intent
        int idEvent = getIntent().getIntExtra("ID_EVENEMENT", -1);

        Log.d("DEBUG", "ID de l'événement recu: "+ idEvent);
        if (idEvent == -1) {
            Log.e("DEBUG", "ID invalide, retour à l'accueil ou connexion.");
            Toast.makeText(this, "Erreur : aucun événement sélectionné", Toast.LENGTH_SHORT).show();
            finish(); // ou redirige vers une autre activité si tu veux
            return;
        }


        if(idEvent != -1) {
            // Chercher l'événement via le Dao
            dao = new EvenementDao(this);
            evenement = dao.getEvenementParId(idEvent);

            // Affichage
            tvDescEvent.setText(evenement.getDescription());
            tvDateEvent.setText(convertirDatePourAffichage(evenement.getDateDebut()) + "  AU  " + convertirDatePourAffichage(evenement.getDateFin()));
            tvLieuEvent.setText(evenement.getLieu());
            tvNomEvent.setText(evenement.getNomEvent());
            tvNomOrganisateur.setText(dao.getNomOrganisateurParId(evenement.getId_organisateur()));
            tvNbLikes.setText(String.valueOf(dao.getNbLikesParIdStatistique(evenement.getId_statistique())));

            // Changement couleur bouton si inscription max atteint
            if(evenement.getCompletVisiteur() == 1) {
                btnInscInvite.setBackgroundTintList(
                        ContextCompat.getColorStateList(this, R.color.btndesactive));
                btnInscInvite.setTextColor(Color.WHITE);
            }
            // Image header de l'événement
            Glide.with(this)
                    .load(evenement.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imgEvent);

            // Image de l'organisateur
            String imgOrgUrl = dao.getImageOrganisateurParId(evenement.getId_organisateur());
            Glide.with(this)
                    .load(imgOrgUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imgProfileOrg);

        }

        // Initialiser le dao
        modelInscription = new ViewModelProvider(this).get(InscriptionViewModel.class);

        // Observer les messages
        modelInscription.getMessage().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        // Chercher le id de l'utilisateur connecté
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        idUser = prefs.getLong(PREF_USER_ID, -1);

        // Bouton pour s'inscire en tant que invité
        btnInscInvite.setOnClickListener(v -> {
            if(idUser != -1) {
                modelInscription.inscrireVisiteur(idUser, evenement.getId());
            }
            else {
                Toast.makeText(this, "Aucun utilisateur connecté", Toast.LENGTH_SHORT).show();
            }
        });

        // Bouton pour envoyer une application
        btnInscBenevole.setOnClickListener(v -> {
            if(idUser != -1) {
                modelInscription.inscrireAppliquant(idUser, evenement.getId());
            }
            else {
                Toast.makeText(this, "Aucun utilisateur connecté", Toast.LENGTH_SHORT).show();
            }
        });



        //Quand on clique le nom de l'organisateur (ou l'image de l'organisateur), ça nous redirige vers la page de l'organisateur
        tvNomOrganisateur.setOnClickListener(v -> {
            Intent intentOrg = new Intent(EvenementActivity.this, ProfilOrganisateurActivity.class);
            intentOrg.putExtra("ID_ORGANISATEUR",(long) evenement.getId_organisateur());
            startActivity(intentOrg);
        });

        imgProfileOrg.setOnClickListener(v -> {
            Intent intentOrg = new Intent(EvenementActivity.this, ProfilOrganisateurActivity.class);
            intentOrg.putExtra("ID_ORGANISATEUR", (long) evenement.getId_organisateur());
            startActivity(intentOrg);
        });


        imgRetour.setOnClickListener(v -> {
            finish();
        });


        // Publication de commentaire
        btnPublier.setOnClickListener(v -> {
            String commentaire = txtComment.getText().toString().trim();
            if(commentaire.isEmpty()) {
                Toast.makeText(this, "Commentaire invalide", Toast.LENGTH_SHORT).show();
            }
            else if(idUser == -1) {
                Toast.makeText(this, "Aucun utilisateur connecté", Toast.LENGTH_SHORT).show();
            }
            else {
                modelEvent = new ViewModelProvider(this).get(EvenementViewModel.class);
                modelEvent.ajouterCommentaire(idUser, idEvent, commentaire,() -> runOnUiThread(() -> {
                    // Création d'un TextView avec le commentaire
                    ajouterCommentaireUI(commentaire);
                    Toast.makeText(this, "Commentaire publié!", Toast.LENGTH_SHORT).show();
                    txtComment.setText("");  // Effacer le champ après avoir publier la commentaire
                }));
            }
        });
    }

    private String convertirDatePourAffichage(String dateBrute) {
        try {
            // Format actuel : "dd/MM/yyyy"
            SimpleDateFormat formatEntree = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
            Date date = formatEntree.parse(dateBrute);

            // Format final souhaité : "d MMM yyyy hha"
            SimpleDateFormat formatFinal = new SimpleDateFormat("d MMM yyyy hha", Locale.FRENCH);
            return formatFinal.format(date);
        } catch (ParseException e) {
            return dateBrute;  // Retourne la date originale si échec
        }
    }

    private void ajouterCommentaireUI(String commentaire) {
        View commentaireView = LayoutInflater.from(this).inflate(R.layout.comment_list_item, layoutCommentaires, false);

        // Liaison des composantes avec le view
        TextView tvNomUser = commentaireView.findViewById(R.id.tvNomComment);
        TextView tvMessage = commentaireView.findViewById(R.id.tvComment);
        TextView tvDate = commentaireView.findViewById(R.id.tvDateComment);
        ImageView imgProfile = commentaireView.findViewById(R.id.imgProfileComment);

        // Retrouver le nom de l'utilisateur connecté
        daoUser = new UtilisateurDao(this);
        Utilisateur utilisateur = daoUser.getUtilisateurParId(idUser);
        String nomComplet = utilisateur.getPrenom() + " " + utilisateur.getNom();

        tvNomUser.setText(nomComplet);
        tvMessage.setText(commentaire);

        // Charger image avec Glide
        Glide.with(this)
                .load(utilisateur.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(imgProfile);

        // Mettre la date courrante
        String dateCourrante = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).format(new Date());
        tvDate.setText(dateCourrante);

        layoutCommentaires.addView(commentaireView);

    }


}