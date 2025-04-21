package com.sarah.applicationsqak.vues;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.Organisateur;
import com.sarah.applicationsqak.viewmodel.EvenementViewModel;
import com.sarah.applicationsqak.viewmodel.OrganisateurViewModel;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

import java.util.List;

public class ProfilOrganisateurActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nomOrg,  bioOrg;
    private ImageView imgOrg;
    private RecyclerView rvEventsOrg;
    private ImageView btnBack;
    private OrganisateurViewModel organisateurViewModel;
    private EvenementViewModel evenementViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil_organisateur);

        nomOrg = findViewById(R.id.tvNomOrgProfile);
        bioOrg = findViewById(R.id.tvDescOrg);
        imgOrg = findViewById(R.id.imgOrgProfile);
        btnBack = findViewById(R.id.imageView6);
        rvEventsOrg = findViewById(R.id.rvMesEventsOrg);

        long idOrg = getIntent().getLongExtra("ID_ORGANISATEUR", -1);

        if(idOrg == -1) {
            finish();
            return;
        }

        organisateurViewModel = new ViewModelProvider(this).get(OrganisateurViewModel.class);
        evenementViewModel = new ViewModelProvider(this).get(EvenementViewModel.class);

        Organisateur organisateur = organisateurViewModel.getOrganisateurParId(idOrg);

        btnBack.setOnClickListener(this);


        if (organisateur != null) {
            String nomAffiche;

            if (organisateur.getNomOrg().isEmpty() == false) {
                nomAffiche = organisateur.getNomOrg();
            } else {
                nomAffiche = organisateur.getPrenomPOrg() + " " + organisateur.getNomPOrg();
            }

            nomOrg.setText(nomAffiche);
            bioOrg.setText(organisateur.getBioOrg());

            if (organisateur.getImageUrlOrg() != null && !organisateur.getImageUrlOrg().isEmpty()) {
                Glide.with(this)
                        .load(organisateur.getImageUrlOrg())
                        .placeholder(R.drawable.placeholder)
                        .into(imgOrg);
            }

            //Charger les évènements de l'organisateur

            List<Evenement> evenements = evenementViewModel.getEvenementsParOrganisateur(idOrg);
            rvEventsOrg.setLayoutManager(new LinearLayoutManager(this));
            rvEventsOrg.setAdapter(new ExperienceAdapter(evenements));

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btnBack) {
            finish();
        }



    }
}
