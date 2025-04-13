package com.sarah.applicationsqak.vues;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sarah.applicationsqak.R;

public class ModifierProfilActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton ImgbtnModifBack;
    Button SaveModif, btnChoisirPhoto;
    EditText edtprenom, edtnom, edtmail, edtnum, edtbio, edtmdp, edtmdp2;
    Uri imageUpload;
    ActivityResultLauncher<Intent> launcherGalerie;



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

        launcherGalerie = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        imageUpload = result.getData().getData();
                    }
                }
        );




        ImgbtnModifBack = findViewById(R.id.btnRetourSettings);
        ImgbtnModifBack.setOnClickListener(this);
        SaveModif.setOnClickListener(this);
        btnChoisirPhoto.setOnClickListener(this);

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

            Intent intent2 = new Intent(Intent.ACTION_PICK);
            intent2.setType("image/*");
            launcherGalerie.launch(intent2);

        }


    }
}