package com.sarah.applicationsqak.viewmodel;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static java.security.AccessController.getContext;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarah.applicationsqak.modele.Commentaire;
import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Evenement;

import java.util.ArrayList;
import java.util.List;

public class EvenementViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Evenement>> evenements = new MutableLiveData<>();
    private final MutableLiveData<List<Commentaire>> commentaires = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();
    private EvenementDao dao;

    public EvenementViewModel(@NonNull Application application) {
        super(application);
        dao = new EvenementDao(application.getApplicationContext());

        // Charger les événements automatiquement au run
        chargerEvenements();
    }

    public LiveData<List<Evenement>> getEvenements() {
        return evenements;
    }
    public LiveData<List<Commentaire>> getCommentaires() {
        return commentaires;
    }

    public LiveData<String> getMessage() {
        return message;
    }


    public void chargerEvenements() {
        new Thread(() -> {
            try {
                List<Evenement> liste = dao.getEvenements();
                evenements.postValue(liste);
                message.postValue("Evenements chargés avec succès (" + liste.size() + ")" );
            }
            catch(Exception e) {
                message.postValue("Erreur de chargement des événements: " + e.getMessage());
            }
        }).start();

    }

    public void ajouterEvenement(Evenement event) {
        new Thread(() -> {
            try {
                dao.ajouterEvenement(event);
                chargerEvenements();
                message.postValue("Événement ajouté avec succès");
            }
            catch(Exception e) {
                message.postValue("Erreur lors de l'ajout de l'événemement: " + e.getMessage());
            }
        }).start();

    }

    public void filtrerEvenements(String lieu, String etat, String role, String date, String recherche) {
        new Thread(() -> {
            try {
                List<Evenement> resultats = dao.getEvenementsFiltres(lieu, etat, role, date, recherche);
                if(resultats == null) {
                    message.postValue("Catégorie invalide");
                }
                else {
                    evenements.postValue(resultats);
                }
            }
            catch(Exception e) {
                message.postValue("Erreur lors du filtrage: " + e.getMessage());
            }

        }).start();
    }

    public List<Evenement> getEvenementsParUtilisateur(long userId) {
        try {
            return dao.getEvenementsParUtilisateur(userId);
        }
        catch(Exception e) {
            message.postValue("Erreur lors du chargement des événements de l'utilisateur");
            return new ArrayList<>();  // retourne une liste vide
        }

    }

    public List<Evenement> getEvenementsParOrganisateur(long idOrg) {
        try {
            return dao.getEvenementsParOrganisateur(idOrg);
        } catch(Exception e) {
            message.postValue("Erreur lors du chargement des événements de l'organisateur");
            return new ArrayList<>();
        }
    }

    public void ajouterCommentaire(long idUser, int idEvent, String texte, Runnable onSuccess) {
        // Runnable onSuccess: contient le code à excéuter (ajout du commentaire dans le UI), si l'insertion fonctionne

        new Thread(() -> {
            long resultat = dao.ajouterCommentaire(idUser, idEvent, texte);
            if(resultat != -1) {
                message.postValue("Commentaire publié!");
                onSuccess.run();
            }
            else {
                message.postValue("Erreur lors de la publication");
            }
        }).start();
    }

    public void chargerCommentaires(int idEvent) {
        new Thread(() -> {
            List<Commentaire> commentairesCharges = dao.getCommentairesPourEvenement(idEvent);
            commentaires.postValue(commentairesCharges);
        }).start();
    }





}
