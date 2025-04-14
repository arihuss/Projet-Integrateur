package com.sarah.applicationsqak.viewmodel;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static java.security.AccessController.getContext;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Evenement;

import java.util.List;

public class EvenementViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Evenement>> evenements = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();
    private EvenementDao dao;

    public EvenementViewModel(@NonNull Application application) {
        super(application);
        dao = new EvenementDao(application.getApplicationContext());
        chargerEvenements();
    }

    public LiveData<List<Evenement>> getEvenements() {
        return evenements;
    }

    public LiveData<String> getMessage() {
        return message;
    }


    public void chargerEvenements() {
        evenements.setValue(dao.getEvenements());
    }

    public void ajouterEvenement(Evenement e) {
        dao.ajouterEvenement(e);
        chargerEvenements();
    }

    public void filtrerEvenements(String lieu, String etat, String role, String date, String recherche) {
        new Thread(() -> {
            List<Evenement> resultats = dao.getEvenementsFiltres(lieu, etat, role, date, recherche);
            if(resultats == null) {
                message.postValue("Catégorie invalide");
            }
            else {
                evenements.postValue(resultats);
            }
        }).start();
    }

    public List<Evenement> getEvenementsParUtilisateur(long userId) {
        return dao.getEvenementsParUtilisateur(userId);
    }





}
