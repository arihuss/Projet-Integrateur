package com.sarah.applicationsqak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sarah.applicationsqak.modele.Dao.EvenementDao;
import com.sarah.applicationsqak.modele.Dao.InscriptionDao;

public class InscriptionViewModel extends AndroidViewModel {
    private final MutableLiveData<String> message = new MutableLiveData<>();
    private InscriptionDao dao;

    public InscriptionViewModel(@NonNull Application application) {
        super(application);
        this.dao = new InscriptionDao(application.getApplicationContext());
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void inscrireUtilisateur(long idUtilisateur, int idEvenement, String role) {
        new Thread(() -> {
            if(!dao.estInscrit(idUtilisateur, idEvenement)) {
                dao.inscrireUtilisateur(idUtilisateur, idEvenement, role);
                message.postValue("Inscription réussi");
            }
            else {
                message.postValue("Déjà inscrit à cet événement");
            }
        }).start();
    }


}
