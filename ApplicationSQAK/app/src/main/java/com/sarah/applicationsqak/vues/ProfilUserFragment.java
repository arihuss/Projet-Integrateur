package com.sarah.applicationsqak.vues;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sarah.applicationsqak.R;

import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.Utilisateur;

import java.util.List;

public class ProfilUserFragment extends Fragment {

    private ImageView imgUserProfil;
    private TextView txtPrenom, txtNom, txtBio;
    private RecyclerView rvExpUser;

    private UtilisateurViewModel utilisateurViewModel;
    private EvenementViewModel evenementViewModel;

    private static final String PREFS_NAME = "AppPrefs";
    private static final String PREF_USER_ID = "utilisateur_id";

    public ProfilUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil_user, container, false);

        // Références UI
        imgUserProfil = view.findViewById(R.id.imgUserProfil);
        txtPrenom = view.findViewById(R.id.txtPrenomUser);
        txtNom = view.findViewById(R.id.txtNomUser);
        txtBio = view.findViewById(R.id.txtBioUser);
        rvExpUser = view.findViewById(R.id.rvExpUser);

        // Vérifier l'identité de l'utilisateur
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        long userId = prefs.getLong(PREF_USER_ID, -1);

        if (userId == -1) {
            startActivity(new Intent(getContext(), ConnexionActivity.class));
            requireActivity().finish();
            return view;
        }

        // Initialiser les ViewModels
        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);
        evenementViewModel = new ViewModelProvider(this).get(EvenementViewModel.class);



        // Charger les infos de l'utilisateur
        Utilisateur utilisateur = utilisateurViewModel.getUtilisateurParId(userId);
        if (utilisateur != null) {
            txtPrenom.setText(utilisateur.getPrenom());
            txtNom.setText(utilisateur.getNom());
            txtBio.setText(utilisateur.getBio());

            if (utilisateur.getImageUrl() != null && !utilisateur.getImageUrl().isEmpty()) {
                Glide.with(this)
                        .load(utilisateur.getImageUrl())
                        .placeholder(R.drawable.placeholder)
                        .into(imgUserProfil);
            }
        }

        // Charger les événements
        rvExpUser.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Evenement> evenements = evenementViewModel.getEvenementsParUtilisateur(userId);
        rvExpUser.setAdapter(new ExperienceAdapter(evenements));

        return view;
    }
}
