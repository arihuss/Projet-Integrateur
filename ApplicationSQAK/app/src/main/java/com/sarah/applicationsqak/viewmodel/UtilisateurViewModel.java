package com.sarah.applicationsqak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.modele.Dao.UtilisateurDao;

public class UtilisateurViewModel extends AndroidViewModel {

    private final UtilisateurDao utilisateurDao;
    private final MutableLiveData<String> courrielUtilisateur = new MutableLiveData<>();

    public UtilisateurViewModel(@NonNull Application application) {
        super(application);
        utilisateurDao = new UtilisateurDao(application.getApplicationContext());
    }

    // Insérer un nouvel utilisateur
    public long insererUtilisateur(Utilisateur utilisateur) {
        return utilisateurDao.insererUtilisateur(utilisateur);
    }

    // Authentifier un utilisateur (retourne l’objet Utilisateur ou null)
    public Utilisateur authentifier(String courriel, String motDePasse) {
        return utilisateurDao.getUtilisateurParIdentifiants(courriel, motDePasse);
    }

    // Récupérer un utilisateur uniquement par courriel (ex: mot de passe oublié)
    public Utilisateur getUtilisateurParCourriel(String courriel) {
        return utilisateurDao.getUtilisateurParCourriel(courriel);
    }

    // Mettre à jour un utilisateur (ex: mot de passe)
    public void mettreAJourUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.mettreAJourUtilisateur(utilisateur);
    }

    // Supprimer utilisateur
    public void supprimerUtilisateur(long id) {
        utilisateurDao.supprimerUtilisateur(id);
    }

    // Récupérer utilisateur complet
    public Utilisateur getUtilisateurParId(long id) {
        return utilisateurDao.getUtilisateurParId(id);
    }

    // Nouvelle méthode MVVM : expose le courriel via LiveData
    public LiveData<String> getCourrielUtilisateur() {
        return courrielUtilisateur;
    }

    public void chargerCourrielParId(long id) {
        new Thread(() -> {
            Utilisateur utilisateur = utilisateurDao.getUtilisateurParId(id);
            if (utilisateur != null) {
                courrielUtilisateur.postValue(utilisateur.getCourriel());
            } else {
                courrielUtilisateur.postValue(null);
            }
        }).start();
    }
}
