package com.sarah.applicationsqak;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    Button btnModifier, btnPlus, btnSupp, btnSuppPop, btnRePop;
    Dialog dialog;

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
        btnSupp = view.findViewById(R.id.btnSupprimerCompte);

        //Pop Up Supprimer Compte
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pop_up_supprimer);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        btnSuppPop = dialog.findViewById(R.id.btnSupprimerPop);
        btnRePop = dialog.findViewById(R.id.btnRevenirPop);

        btnModifier.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnSupp.setOnClickListener(this);
        btnSuppPop.setOnClickListener(this);
        btnRePop.setOnClickListener(this);


        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onClick(View v) {

        //Click sur le bouton MODIFIER PROFIL qui redirige vers modifier profil
        if (v == btnModifier) {

            Intent intention1;
            intention1 = new Intent(getActivity(), ModifierProfil.class);
            startActivity(intention1);

        //Click sur le bouton PLUS sur SQAK qui emmène à la page about us
        } else if (v ==btnPlus){

            Intent intention2;
            intention2 = new Intent(getActivity(), AboutUs.class);
            startActivity(intention2);

        //Click sur le bouton SUPPRIMER MON COMPTE qui affiche un pop up certifiant la suppression du compte de l'utilisateur
        } else if (v == btnSupp){
            dialog.show();
        }

        // Les boutons Supprimer et Retour du pop up
        //Supprime le compte + reviens à la page d'accueil
        if(v == btnSuppPop){
            //compléter le futur afin de pouvoir supprimer le compte
            Intent intention3;
            intention3 = new Intent(getActivity(), Accueil.class);
            intention3.putExtra("Suppression_compte", true);
            startActivity(intention3);
            dialog.dismiss();

        //retour à la page des paramètres
        } else if(v == btnRePop){
            dialog.dismiss();
        }

    }
}