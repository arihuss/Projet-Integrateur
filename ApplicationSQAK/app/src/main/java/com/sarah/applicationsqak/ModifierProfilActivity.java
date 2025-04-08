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
    Button SaveModif, btnChoisirPhoto;
    EditText edtprenom, edtnom, edtmail, edtnum, edtbio, edtmdp, edtmdp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifier_profil);

        edtprenom = findViewById(R.id.edtModifPrenom);
        edtnom = findViewById(R.id.edtModifNom);
        edtmail = findViewById(R.id.edtModifCourriel);
        edtnum = findViewById(R.id.edtModifNum);
        edtbio = findViewById(R.id.edtModifBio);
        edtmdp = findViewById(R.id.edtModifMdp);
        edtmdp2 = findViewById(R.id.edtModifConfirm);
        SaveModif = findViewById(R.id.btnModifSave);
        btnChoisirPhoto = findViewById(R.id.btnChoisirPhoto);

        Intent intent = getIntent();




        ImgbtnModifBack = findViewById(R.id.btnRetourSettings);
        ImgbtnModifBack.setOnClickListener(this);
        SaveModif.setOnClickListener(this);

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

            Intent intent1 = new Intent();
            finish();

        }

        if(v == btnChoisirPhoto){



        }


    }
}