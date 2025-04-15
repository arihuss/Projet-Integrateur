package com.sarah.applicationsqak.vues;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Evenement;

public class EvenementActivity extends AppCompatActivity {

    private TextView tvDescEvent, tvDateEvent, tvLieuEvent, tvNomEvent, tvNomOrganisateur, tvNbLikes, tvComment;
    private ImageView imgProfileOrg, imgEvent;
    private Button btnInscInvite, btnInscBenevole, btnPublier;


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
        tvComment = findViewById(R.id.tvNouvComment);
        imgEvent = findViewById(R.id.imgAfficheEvent);

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
            EvenementDao dao = new EvenementDao(this);
            Evenement evenement = dao.getEvenementParId(idEvent);

            // Affichage
            tvDescEvent.setText(evenement.getDescription());
            tvDateEvent.setText(evenement.getDateDebut() + " " + evenement.getDateFin());
            tvLieuEvent.setText(evenement.getLieu());
            tvNomEvent.setText(evenement.getNomEvent());
            tvNomOrganisateur.setText(dao.getNomOrganisateurParId(evenement.getId_organisateur()));
            tvNbLikes.setText(dao.getNbLikesParIdStatistique(evenement.getId_statistique()));

            // Images -- à changer
            imgProfileOrg.setImageResource(R.drawable.placeholder);
            imgEvent.setImageResource(R.drawable.placeholder);

        }

        btnInscInvite.setOnClickListener(v -> {
            Toast.makeText(this, "Inscription envoyée!", Toast.LENGTH_SHORT).show();

        });

        btnInscBenevole.setOnClickListener(v -> {
            Toast.makeText(this, "Application bénévolat envoyée!", Toast.LENGTH_SHORT).show();
        });

        btnPublier.setOnClickListener(v -> {
            if(!tvComment.toString().trim().isEmpty()) {
                String commentaire = tvComment.toString().trim();
                Toast.makeText(this, "Commentaire publié!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Commentaire invalide", Toast.LENGTH_SHORT).show();
            }
        });





    }
}