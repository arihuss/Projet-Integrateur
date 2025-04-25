package com.sarah.applicationsqak.vues;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.Inscription;
import com.sarah.applicationsqak.viewmodel.EvenementViewModel;
import com.sarah.applicationsqak.viewmodel.InscriptionViewModel;
import com.sarah.applicationsqak.viewmodel.OrganisateurViewModel;

import java.util.ArrayList;
import java.util.List;

public class InscriptionsFragment extends Fragment {

    private ListView lvInscriptions;
    private EventFilterView filtreView;
    private InscriptionAdapter adapter;
    private EvenementViewModel evenementViewModel;
    private InscriptionViewModel inscriptionViewModel;
    private OrganisateurViewModel organisateurViewModel;

    private long clientId;

    public InscriptionsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inscriptions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filtreView = view.findViewById(R.id.eventFilterView);
        lvInscriptions = view.findViewById(R.id.lvMesInscriptions);

        SharedPreferences prefs = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        clientId = prefs.getLong("utilisateur_id", -1);

        // Initialisation ViewModels
        evenementViewModel = new ViewModelProvider(this).get(EvenementViewModel.class);
        inscriptionViewModel = new ViewModelProvider(this).get(InscriptionViewModel.class);
        organisateurViewModel = new ViewModelProvider(this).get(OrganisateurViewModel.class);

        // Initialisation de l’adapter avec le ViewModel organisateur
        adapter = new InscriptionAdapter(requireContext(), R.layout.item_inscriptions_user, organisateurViewModel);
        lvInscriptions.setAdapter(adapter);

        // Branche le bouton X (désinscription)
        adapter.setOnDesinscriptionClickListener(inscription -> {
            inscriptionViewModel.annulerInscription(clientId, inscription.getId_evenement());
        });

        // Observer les messages
        inscriptionViewModel.getMessage().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null && !msg.isEmpty()) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
                inscriptionViewModel.chargerInscriptionsPourUtilisateur(clientId); // recharge après suppression
            }
        });

        inscriptionViewModel.getInscriptions().observe(getViewLifecycleOwner(), inscriptions -> {
            List<Evenement> evenements = evenementViewModel.getEvenementsActifsParUtilisateur(clientId);
            List<InscriptionAdapter.InscriptionAffichage> affichages = new ArrayList<>();

            for (Inscription ins : inscriptions) {
                for (Evenement ev : evenements) {
                    if (ev.getId() == ins.getId_evenement()) {
                        affichages.add(new InscriptionAdapter.InscriptionAffichage(ev, ins));
                        break;
                    }
                }
            }

            adapter.clear();
            adapter.addAll(affichages);
        });

        // Charger les données initiales
        inscriptionViewModel.chargerInscriptionsPourUtilisateur(clientId);

        // Gérer les changements de filtre
        filtreView.setOnFilterChangeListener((lieu, etat, role, date, recherche) -> {
            inscriptionViewModel.chargerInscriptionsPourUtilisateur(clientId);
        });
    }
}