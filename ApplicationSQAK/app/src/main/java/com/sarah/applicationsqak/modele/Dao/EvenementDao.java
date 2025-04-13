package com.sarah.applicationsqak.modele.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        values.put()
    }
}
