package com.sarah.applicationsqak.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Evenement;

import java.util.List;

public class EvenementViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Evenement>> evenements = new MutableLiveData<>();
    private EvenementDao dao;

    public EvenementViewModel(@NonNull Application application) {
        super(application);
        dao = new EvenementDao(application.getApplicationContext());
        chargerEvenements();
    }

    public LiveData<List<Evenement>> getEvenements() {
        return evenements;
    }


    public void chargerEvenements() {
        evenements.setValue(dao.getEvenements());
    }

    public void ajouterEvenement(Evenement e) {
        dao.ajouterEvenement(e);
        chargerEvenements();
    }

    public void filtrerEvenements(String lieu, String etat, String date, String role) {
        new Thread(() -> {
            List<Evenement> resultats = dao.getEvenementsFiltres(lieu, etat. role, date, recherche);
            evenements.postValue(resultats);
        }).start();
    }




}
