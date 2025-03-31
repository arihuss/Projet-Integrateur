package com.sarah.applicationsqak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnModifier, btnPlus;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnModifier = view.findViewById(R.id.btnModifProfil);
        btnPlus = view.findViewById(R.id.btnPlus);
        AppCompatButton btnTheme = view.findViewById(R.id.btnTheme); // Ajout du bouton

        btnModifier.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

        // Récupérer les préférences pour le mode sombre
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean(DARK_MODE_KEY, false);

        // Mettre à jour le texte du bouton selon le mode actuel
        updateThemeButtonText(btnTheme, isDarkModeEnabled);

        // Gestion du clic sur le bouton
        btnTheme.setOnClickListener(v -> {
            boolean newDarkModeState = !isDarkModeEnabled;

            // Appliquer le mode sombre ou clair
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

    /**
     * Met à jour le texte du bouton en fonction du mode actuel
     */
    private void updateThemeButtonText(AppCompatButton btnTheme, boolean isDarkModeEnabled) {
        if (isDarkModeEnabled) {
            btnTheme.setText("Passer en mode clair");
        } else {
            btnTheme.setText("Passer en mode sombre");
        }
    }



    @Override
    public void onClick(View v) {

        if (v == btnModifier) {

            Intent intention1;
            intention1 = new Intent(getActivity(), ModifierProfil.class);
            startActivity(intention1);

        } else if (v ==btnPlus){

            Intent intention2;
            intention2 = new Intent(getActivity(), AboutUs.class);
            startActivity(intention2);
        }

    }
}