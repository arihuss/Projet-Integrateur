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

    public long inscrireUtilisateur(long idUser, int idEvent, String role) {
        //// **** Les log sont à modifiés = pas encore implémenté
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        // Lire les infos de l'événement
        Cursor cursor = db.query(
                BaseContrat.EvenementTable.TABLE_NAME,
                null,
                BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idEvent)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int nbParticipantsMax = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX));
            int nbBenevolesMax = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_MAX));
            int nbInscriptions = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_INSCRIPTIONS));
            int nbBenevolesAcceptes = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES));

            Log.d("INSCRIPTION", "Événement " + idEvent + " : " + nbInscriptions + "/" + nbParticipantsMax + " participants, " + nbBenevolesAcceptes + "/" + nbBenevolesMax + " bénévoles");

            // Vérifier si on a atteint les max
            if (nbBenevolesAcceptes >= nbBenevolesMax) {
                Log.d("INSCRIPTION", "Limite de bénévoles atteinte pour l'événement " + idEvent);
            }

            if (nbInscriptions >= nbParticipantsMax) {
                Log.d("INSCRIPTION", "Limite de visiteurs atteinte pour l'événement " + idEvent);
            }

            // Si c’est un visiteur
            if (role.equalsIgnoreCase("visiteur")) {
                if (nbInscriptions < nbParticipantsMax) {
                    // Incrémenter nb_inscriptions
                    ContentValues updateValues = new ContentValues();
                    updateValues.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, nbInscriptions + 1);

                    if (nbInscriptions + 1 == nbParticipantsMax) {
                        updateValues.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, 1);
                        Log.d("INSCRIPTION", "COMPLET_VISITEUR mis à 1 pour l'événement " + idEvent);
                    }

                    db.update(
                            BaseContrat.EvenementTable.TABLE_NAME,
                            updateValues,
                            BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                            new String[]{String.valueOf(idEvent)}
                    );
                } else {
                    Log.d("INSCRIPTION", "Inscription refusée : nombre max de participants atteint.");
                    cursor.close();
                    return -1;
                }
            }

            cursor.close();
        } else {
            Log.d("INSCRIPTION", "Événement non trouvé.");
            return -1;
        }

        // Insérer l'inscription
        ContentValues values = new ContentValues();
        values.put(BaseContrat.InscriptionTable.ID_UTILISATEUR, idUser);
        values.put(BaseContrat.InscriptionTable.ID_EVENEMENT, idEvent);
        values.put(BaseContrat.InscriptionTable.ROLE, role);

        SimpleDateFormat formatDate = new SimpleDateFormat("d MMM yyyy hha", Locale.CANADA);
        String dateCourrante = formatDate.format(new Date());
        values.put(BaseContrat.InscriptionTable.DATE_INSCRIPTION, dateCourrante);

        return db.insert(BaseContrat.InscriptionTable.TABLE_NAME, null, values);
    }


    public boolean estInscrit(long idUser, int idEvent) {
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
