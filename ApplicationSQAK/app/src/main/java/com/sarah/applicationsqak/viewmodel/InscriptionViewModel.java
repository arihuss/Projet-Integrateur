package com.sarah.applicationsqak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Dao.InscriptionDao;

import static com.sarah.applicationsqak.modele.Dao.InscriptionDao.InscriptionVisiteurResultat;
import static com.sarah.applicationsqak.modele.Dao.InscriptionDao.InscriptionAppliquantResultat;

import androidx.lifecycle.LiveData;
import com.sarah.applicationsqak.modele.Inscription;
import java.util.List;

public class InscriptionViewModel extends AndroidViewModel {

    private final MutableLiveData<String> message = new MutableLiveData<>();
    private final MutableLiveData<List<Inscription>> inscriptions = new MutableLiveData<>();
    private InscriptionDao dao;

    public InscriptionViewModel(@NonNull Application application) {
        super(application);
        this.dao = new InscriptionDao(application.getApplicationContext());
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public LiveData<List<Inscription>> getInscriptions() {
        return inscriptions;
    }

    public void chargerInscriptionsPourUtilisateur(long idUtilisateur) {
        new Thread(() -> {
            List<Inscription> liste = dao.getInscriptionsParUtilisateur(idUtilisateur);
            inscriptions.postValue(liste);
        }).start();
    }

    public void inscrireVisiteur(long idUtilisateur, int idEvenement) {
        new Thread(() -> {
            if (!dao.estInscrit(idUtilisateur, idEvenement)) {
                InscriptionVisiteurResultat resultat = dao.inscrireVisiteur(idUtilisateur, idEvenement);

                switch (resultat) {
                    case SUCCES:
                        message.postValue("Inscription réussie !");
                        break;
                    case LIMITE_ATTEINTE:
                        message.postValue("Nombre maximum de participants atteint.");
                        break;
                    case EVENEMENT_INTROUVABLE:
                        message.postValue("Événement introuvable.");
                        break;
                    case ERREUR_INSERTION:
                        message.postValue("Erreur lors de l'insertion.");
                        break;
                }
            } else {
                message.postValue("Vous avez déjà envoyer une inscription pour cet événement.");
            }
        }).start();

    }

    public void inscrireAppliquant(long idUtilisateur, int idEvenement) {
        new Thread(() -> {
            if (!dao.estInscrit(idUtilisateur, idEvenement)) {
                InscriptionAppliquantResultat resultat = dao.inscrireAppliquant(idUtilisateur, idEvenement);

                switch (resultat) {
                    case SUCCES:
                        message.postValue("Application envoyée !");
                        break;
                    case LISTE_ATTENTE:
                        message.postValue("Application envoyée dans la liste d’attente.");
                        break;
                    case EVENEMENT_INTROUVABLE:
                        message.postValue("Événement introuvable.");
                        break;
                    case INSERTION_ECHEC:
                        message.postValue("Erreur lors de l'envoi de l'application.");
                        break;
                }
            } else {
                message.postValue("Vous avez déjà envoyer une inscription pour cet événement.");
            }
        }).start();

    }

    public void annulerInscription(long idUtilisateur, int idEvenement) {
        new Thread(() -> {
            dao.annulerInscription((int) idUtilisateur, idEvenement);
            message.postValue("Inscription annulée !");
        }).start();
    }



}
