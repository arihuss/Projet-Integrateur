package com.sarah.applicationsqak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModifierProfilActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton ImgbtnModifBack;
    Button SaveModif;
    EditText prenom, nom, mail, num, bio, mdp, mdp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifier_profil);

        prenom = findViewById(R.id.edtModifPrenom);
        nom = findViewById(R.id.edtModifNom);
        mail = findViewById(R.id.edtModifCourriel);
        num = findViewById(R.id.edtModifNum);
        bio = findViewById(R.id.edtModifBio);
        mdp = findViewById(R.id.edtModifMdp);
        mdp2 = findViewById(R.id.edtModifConfirm);
        SaveModif = findViewById(R.id.btnModifSave);



        ImgbtnModifBack = findViewById(R.id.btnRetourSettings);
        ImgbtnModifBack.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {

        if (v == ImgbtnModifBack){
            finish();
        }

        if (v == SaveModif){

            Intent intent1;
            finish();

        }


    }
}