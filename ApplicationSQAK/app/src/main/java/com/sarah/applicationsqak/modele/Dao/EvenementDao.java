package com.sarah.applicationsqak.modele.Dao;

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.sarah.applicationsqak.modele.Categorie;
import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class EvenementDao {
    private DbUtil dbUtil;

    public EvenementDao(Context context) {
        dbUtil = new DbUtil(context);
    }

    public List<Evenement> getEvenements() {
        List<Evenement> evenements = new ArrayList<>();
        SQLiteDatabase db = dbUtil.getReadableDatabase();

        // SELECT * FROM Evenement
        Cursor cursor = db.query(BaseContrat.EvenementTable.TABLE_NAME, null, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();

            // Remplir la liste
            do {
                Evenement e = new Evenement();

                e.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.ID_EVENEMENT)));
                e.setId_organisateur(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.ID_ORGANISATEUR)));
                e.setId_statistique(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.ID_STATISTIQUE)));
                e.setNomEvent(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NOM_EVENT)));
                e.setLieu(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.LIEU)));
                e.setDateDebut(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.DATE_DEBUT)));
                e.setDateFin(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.DATE_FIN)));
                e.setNbBenevolesMax(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_MAX)));
                e.setNbParticipantsMax(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX)));
                e.setEtatBenevole(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.ETAT_BENEVOLE)));
                e.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.DESCRIPTION)));
                e.setEtat(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.ETAT)));
                e.setNbInscriptions(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_INSCRIPTIONS)));
                e.setNbBenevolesAcceptes(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES)));
                e.setCompletBenevole(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.COMPLET_BENEVOLE)));
                e.setCompletVisiteur(cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.COMPLET_VISITEUR)));
                e.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.IMAGE_URL)));

                // Pour la catégorie
                String nomCategorie = cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.CATEGORIE));
                Categorie categorie = Categorie.valueOf(nomCategorie);
                e.setCategorie(categorie);

                evenements.add(e);
            }while(cursor.moveToNext());
        }

        cursor.close();

        return evenements;
    }


    public long ajouterEvenement(Evenement event) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContrat.EvenementTable.ID_ORGANISATEUR, event.getId_organisateur());

        values.put(BaseContrat.EvenementTable.ID_STATISTIQUE, event.getId_statistique());
        values.put(BaseContrat.EvenementTable.NOM_EVENT, event.getNomEvent());
        values.put(BaseContrat.EvenementTable.LIEU, event.getLieu());
        values.put(BaseContrat.EvenementTable.DATE_DEBUT, event.getDateDebut());
        values.put(BaseContrat.EvenementTable.DATE_FIN, event.getDateFin());
        values.put(BaseContrat.EvenementTable.NB_BENEVOLES_MAX, event.getNbBenevolesMax());
        values.put(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX, event.getNbParticipantsMax());
        values.put(BaseContrat.EvenementTable.ETAT_BENEVOLE, event.getEtatBenevole());
        values.put(BaseContrat.EvenementTable.CATEGORIE, event.getCategorie().name());
        values.put(BaseContrat.EvenementTable.DESCRIPTION, event.getDescription());
        values.put(BaseContrat.EvenementTable.ETAT, event.getEtat());
        values.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, event.getNbInscriptions());
        values.put(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES, event.getNbBenevolesAcceptes());
        values.put(BaseContrat.EvenementTable.COMPLET_BENEVOLE, event.getCompletBenevole());
        values.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, event.getCompletVisiteur());
        values.put(BaseContrat.EvenementTable.IMAGE_URL, event.getImageUrl());

        long newRowId = db.insert(BaseContrat.EvenementTable.TABLE_NAME, null, values);

        return newRowId;
    }

    public List<Evenement> getEvenementsFiltres(String lieu, String etat, String role, String date, String categorie) {
        SQLiteDatabase db= dbUtil.getReadableDatabase();

        List<String> conditions = new ArrayList<>();  // contient tous les filtres sélectionnés
        List<String> valeurs = new ArrayList<>();

        // Si dans la Spinner des lieux, on a PAS coché 'Lieux' (tous les lieux), filtrer selon le lieu choisi
        if(!lieu.equalsIgnoreCase("Lieux")) {

        }
    }
}
