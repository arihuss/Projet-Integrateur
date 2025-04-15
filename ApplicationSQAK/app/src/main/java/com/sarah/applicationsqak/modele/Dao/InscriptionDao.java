package com.sarah.applicationsqak.modele.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

import java.util.Date;

public class InscriptionDao {
    private DbUtil dbUtil;

    public InscriptionDao(Context context) {
        dbUtil = new DbUtil(context);
    }

    public long inscrireUtilisateur(int idUser, int idEvent, String role) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContrat.InscriptionTable.ID_UTILISATEUR, idUser);
        values.put(BaseContrat.InscriptionTable.ID_EVENEMENT, idEvent);
        values.put(BaseContrat.InscriptionTable.ROLE, role);
        values.put(BaseContrat.InscriptionTable.DATE_INSCRIPTION, new Date().toString());

        return db.insert(BaseContrat.InscriptionTable.TABLE_NAME, null, values);
    }

    public boolean estInscrit(int idUser, int idEvent) {
        SQLiteDatabase db = dbUtil.getReadableDatabase();

        Cursor cursor = db.query(BaseContrat.InscriptionTable.TABLE_NAME,
                null,
                BaseContrat.InscriptionTable.ID_UTILISATEUR + " = ? AND " +
                BaseContrat.InscriptionTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idUser), String.valueOf(idEvent)},
                null, null, null);

        boolean estInscrit = (cursor != null && cursor.moveToFirst());

        if(cursor != null) {
            cursor.close();
        }

        return estInscrit;
    }


    public int annulerInscription(int idUser, int idEvent) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseContrat.InscriptionTable.DATE_ANNULATION, new Date().toString());

        int requete = db.update(BaseContrat.InscriptionTable.TABLE_NAME,
                values,
                BaseContrat.InscriptionTable.ID_UTILISATEUR + " = ? AND" +
                BaseContrat.InscriptionTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idUser), String.valueOf(idEvent)});

        return requete;
    }

    public List<Inscription> getInscriptionsParUtilisateur(long idUtilisateur) {
        List<Inscription> inscriptions = new ArrayList<>();
        SQLiteDatabase db = dbUtil.getReadableDatabase();

        Cursor cursor = db.query(BaseContrat.InscriptionTable.TABLE_NAME,
                null,
                BaseContrat.InscriptionTable.ID_UTILISATEUR + "=?",
                new String[]{String.valueOf(idUtilisateur)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // Créer un objet Inscription et le remplir
                // (à condition que tu aies une classe Inscription)
            } while (cursor.moveToNext());
        }

        cursor.close();
        return inscriptions;
    }







}
