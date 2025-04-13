package com.sarah.applicationsqak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.modele.Dao.UtilisateurDao;

public class UtilisateurViewModel extends AndroidViewModel {

    private final UtilisateurDao utilisateurDao;

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
}
