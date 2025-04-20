package com.sarah.applicationsqak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sarah.applicationsqak.modele.Dao.OrganisateurDao;
import com.sarah.applicationsqak.modele.Organisateur;

public class OrganisateurViewModel extends AndroidViewModel {

    private final OrganisateurDao organisateurDao;


    public OrganisateurViewModel(@NonNull Application application) {
        super(application);
        organisateurDao = new OrganisateurDao(application.getApplicationContext());
    }

    public Organisateur getOrganisateurParId(long idOrg) {
        return organisateurDao.getOrganisateurParId(idOrg);
    }
}
