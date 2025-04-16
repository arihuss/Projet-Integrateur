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

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.viewmodel.EvenementViewModel;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

public class ProfilOrganisateurActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nomOrg,  bioOrg;
    private ImageView photoOrg;
    private ListView listEvent;
    private ImageButton btnBack;
    private EvenementViewModel EvenementViewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        nomOrg = findViewById(R.id.tvNomOrgProfile);
        bioOrg = findViewById(R.id.tvDescOrg);
        photoOrg = findViewById(R.id.imgOrgProfile);
        btnBack = findViewById(R.id.imageView6);





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
