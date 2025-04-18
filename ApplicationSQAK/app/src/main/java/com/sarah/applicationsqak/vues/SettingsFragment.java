package com.sarah.applicationsqak.vues;

import static java.security.AccessController.getContext;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Switch;
import android.widget.Toast;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.viewmodel.UtilisateurViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String PREF_USER_ID = "utilisateur_id";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnModifier, btnPlus, btnSupp, btnSuppPop, btnRePop;
    Button btnDeconnecter;
    Dialog dialog;


    //pour dark mode
    private Switch switchDarkMode;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String DARK_MODE_KEY = "dark_mode";

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private long getUserId() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(PREF_USER_ID, -1);
    }

    private boolean isUserLoggedIn() {
        return getUserId() != -1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnModifier = view.findViewById(R.id.btnModifProfil);
        btnPlus = view.findViewById(R.id.btnPlus);
        btnSupp = view.findViewById(R.id.btnSupprimerCompte);

        // btn de deconnexion
        btnDeconnecter = view.findViewById(R.id.btnDeconnecter);
        btnDeconnecter.setOnClickListener(this);


        //Pop Up Supprimer Compte
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pop_up_supprimer);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);

        btnSuppPop = dialog.findViewById(R.id.btnSupprimerPop);
        btnRePop = dialog.findViewById(R.id.btnRevenirPop);

        btnModifier.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnSupp.setOnClickListener(this);
        btnSuppPop.setOnClickListener(this);
        btnRePop.setOnClickListener(this);


        // section DarkMode
        AppCompatButton btnTheme = view.findViewById(R.id.btnTheme);

        // section DarkMode : Récupérer les préférences pour DM
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(DARK_MODE_KEY, false);

        // section DarkMode : Mettre à jour le texte du bouton
        updateThemeButtonText(btnTheme, isDarkModeEnabled);

        // section DarkMode : Gestion du clic bouton
        btnTheme.setOnClickListener(v -> {
            boolean newDarkModeState = !isDarkModeEnabled;

            // Appliquer mode sombre ou clair
            if (newDarkModeState) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Sauvegarder la préférence utilisateur
            sharedPreferences.edit().putBoolean(DARK_MODE_KEY, newDarkModeState).apply();

            // Recharger l'activité pour appliquer le changement
            requireActivity().recreate();
        });

        return view;

    }

    //changer ecriture du bouton
    private void updateThemeButtonText(AppCompatButton btnTheme, boolean isDarkModeEnabled) {
        if (isDarkModeEnabled) {
            btnTheme.setText("Passer en mode clair");
        } else {
            btnTheme.setText("Passer en mode sombre");
        }
    }

    @Override
    public void onClick(View v) {

        //Click sur le bouton MODIFIER PROFIL qui redirige vers modifier profil
        if (v == btnModifier) {

            // Vérifier l'identité de l'utilisateur afin d'accéder à modifier profil
            if (!isUserLoggedIn()) {
                startActivity(new Intent(getContext(), ConnexionActivity.class));
                requireActivity().finish();
                return ;
            } else {
                Intent intent = new Intent(getActivity(), ModifierProfilActivity.class);
                startActivity(intent);
            }



        //Click sur le bouton PLUS sur SQAK qui emmène à la page about us
        } else if (v ==btnPlus){

            Intent intention2;
            intention2 = new Intent(getActivity(), AboutUsActivity.class);
            startActivity(intention2);

        //Click sur le bouton SUPPRIMER MON COMPTE qui affiche un pop up certifiant la suppression du compte de l'utilisateur
        } else if (v == btnSupp){

            // Vérifier l'identité de l'utilisateur afin d'accéder
            SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
            long userId = prefs.getLong(PREF_USER_ID, -1);

            if (userId == -1) {

                startActivity(new Intent(getContext(), ConnexionActivity.class));
                requireActivity().finish();

            } else {
                dialog.show();
            }

        } else if (v == btnDeconnecter) {
        SharedPreferences prefs = requireActivity().getSharedPreferences("AppPrefs", getContext().MODE_PRIVATE);
        prefs.edit().remove("utilisateur_id").apply();

        Intent intent = new Intent(getActivity(), ConnexionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Efface l'historique d'activité
        startActivity(intent);
        }



        // Les boutons Supprimer et Retour du pop up
        //Supprime le compte + reviens à la page d'accueil
        if(v == btnSuppPop){

            long id = getUserId();

            UtilisateurViewModel utilisateurViewModel = new ViewModelProvider(requireActivity()).get(UtilisateurViewModel.class);
            utilisateurViewModel.supprimerUtilisateur((id));

            SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            prefs.edit().remove(PREF_USER_ID).apply();

            Toast.makeText(getActivity(), "Compte supprimé avec succès.", Toast.LENGTH_LONG).show();

            Intent intention3 = new Intent(getActivity(), AccueilActivity.class);
            intention3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intention3.putExtra("Suppression_compte", true);
            startActivity(intention3);
            requireActivity().finish();

            dialog.dismiss();

        //retour à la page des paramètres
        } else if(v == btnRePop){
            dialog.dismiss();
        }

    }
}







