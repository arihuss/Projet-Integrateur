package com.sarah.applicationsqak.vues;

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

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Organisateur;
import com.sarah.applicationsqak.viewmodel.EvenementViewModel;
import com.sarah.applicationsqak.viewmodel.OrganisateurViewModel;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

public class ProfilOrganisateurActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nomOrg,  bioOrg;
    private ImageView imgOrg;
    private ListView listEvent;
    private ImageView btnBack;
    private OrganisateurViewModel organisateurViewModel;
    private EvenementViewModel evenementViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        nomOrg = findViewById(R.id.tvNomOrgProfile);
        bioOrg = findViewById(R.id.tvDescOrg);
        imgOrg = findViewById(R.id.imgOrgProfile);
        btnBack = findViewById(R.id.imageView6);

        long idOrg = getIntent().getLongExtra("ID_ORGANISATEUR", -1);

        if(idOrg == -1) {
            finish();
            return;
        }

        organisateurViewModel = new ViewModelProvider(this).get(OrganisateurViewModel.class);
        evenementViewModel = new ViewModelProvider(this).get(EvenementViewModel.class);

        Organisateur organisateur = organisateurViewModel.getOrganisateurParId(idOrg);

        if (organisateur != null) {
            String nomAffiche;

            if (organisateur.getNomOrg().isEmpty() == false) {
                nomAffiche = organisateur.getNomOrg();
            } else {
                nomAffiche = organisateur.getPrenomPOrg() + " " + organisateur.getNomPOrg();
            }

            bioOrg.setText(organisateur.getBioOrg());

            if (organisateur.getImageUrlOrg() != null && !organisateur.getImageUrlOrg().isEmpty()) {
                Glide.with(this)
                        .load(organisateur.getImageUrlOrg())
                        .placeholder(R.drawable.placeholder)
                        .into(imgOrg);
            }

            //Charger les évènements de l'organisateur

        }









        setContentView(R.layout.activity_profil_organisateur);
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
