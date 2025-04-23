package com.sarah.applicationsqak.modele.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InscriptionDao {
    private DbUtil dbUtil;

    public InscriptionDao(Context context) {
        dbUtil = new DbUtil(context);
    }

    public long inscrireVisiteur(long idUser, int idEvent) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        Cursor cursor = db.query(
                BaseContrat.EvenementTable.TABLE_NAME,
                null,
                BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idEvent)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int nbParticipantsMax = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX));
            int nbInscriptions = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_INSCRIPTIONS));

            if (nbInscriptions < nbParticipantsMax) {
                // Mise à jour de l'événement
                ContentValues updateValues = new ContentValues();
                updateValues.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, nbInscriptions + 1);

                // Changer le boolean complet si event rempli
                if (nbInscriptions + 1 == nbParticipantsMax) {
                    updateValues.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, 1);
                }

                db.update(
                        BaseContrat.EvenementTable.TABLE_NAME,
                        updateValues,
                        BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                        new String[]{String.valueOf(idEvent)}
                );

                // Insertion dans la table Inscription
                ContentValues values = new ContentValues();
                values.put(BaseContrat.InscriptionTable.ID_UTILISATEUR, idUser);
                values.put(BaseContrat.InscriptionTable.ID_EVENEMENT, idEvent);
                values.put(BaseContrat.InscriptionTable.ROLE, "visiteur");

                SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
                String dateCourrante = formatDate.format(new Date());
                values.put(BaseContrat.InscriptionTable.DATE_INSCRIPTION, dateCourrante);

                cursor.close();

                return db.insert(BaseContrat.InscriptionTable.TABLE_NAME, null, values);
            } else {
                // Nombre max d'inscriptions atteint
                cursor.close();
                return -1;
            }

        } else {
            // Evenement non trouvé
            if (cursor != null) cursor.close();
            return -2;
        }
    }


    public int annulerInscription(int idUser, int idEvent) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        ContentValues values = new ContentValues();

        SimpleDateFormat formatDate = new SimpleDateFormat("d MMM yyyy hha", Locale.CANADA);
        String dateCourrante = formatDate.format(new Date());
        values.put(BaseContrat.InscriptionTable.DATE_ANNULATION, dateCourrante);

        int requete = db.update(BaseContrat.InscriptionTable.TABLE_NAME,
                values,
                BaseContrat.InscriptionTable.ID_UTILISATEUR + " = ? AND" +
                BaseContrat.InscriptionTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idUser), String.valueOf(idEvent)});

        return requete;
    }

//    public List<Inscription> getInscriptionsParUtilisateur(long idUtilisateur) {
//        List<Inscription> inscriptions = new ArrayList<>();
//        SQLiteDatabase db = dbUtil.getReadableDatabase();
//
//        Cursor cursor = db.query(BaseContrat.InscriptionTable.TABLE_NAME,
//                null,
//                BaseContrat.InscriptionTable.ID_UTILISATEUR + "=?",
//                new String[]{String.valueOf(idUtilisateur)},
//                null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                // Créer un objet Inscription et le remplir
//                // (à condition que tu aies une classe Inscription)
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        return inscriptions;
//    }







}
