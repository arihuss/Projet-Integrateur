package com.sarah.applicationsqak;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Evenement extends AppCompatActivity {

    private TextView tvDescEvent, tvDateEvent, tvLieuEvent, tvNomEvent, tvNomOrganisateur, tvNbLikes;
    private ImageView imgProfileOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        tvNomEvent = findViewById(R.id.tvNomEvent);
        tvNomOrganisateur = findViewById(R.id.tvNomOrganisateur);
        imgProfileOrg = findViewById(R.id.imgProfileEvents);
        tvNbLikes = findViewById(R.id.tvNbLikes);



    }
}