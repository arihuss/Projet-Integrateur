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

    // Pour le retour de résultats
    public enum InscriptionAppliquantResultat {
        SUCCES,
        INSERTION_ECHEC,
        LISTE_ATTENTE,
        EVENEMENT_INTROUVABLE
    }

    public enum InscriptionVisiteurResultat {
        SUCCES,
        LIMITE_ATTEINTE,
        EVENEMENT_INTROUVABLE,
        ERREUR_INSERTION
    }




    public InscriptionDao(Context context) {
        dbUtil = new DbUtil(context);
    }

    public InscriptionVisiteurResultat inscrireVisiteur(long idUser, int idEvent) {

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

                long insertId = db.insert(BaseContrat.InscriptionTable.TABLE_NAME, null, values);
                cursor.close();

                if(insertId != -1) {
                    return InscriptionVisiteurResultat.SUCCES;
                }
                else {
                    return InscriptionVisiteurResultat.ERREUR_INSERTION;
                }
            } else {
                // Nombre max d'inscriptions atteint
                cursor.close();
                return InscriptionVisiteurResultat.LIMITE_ATTEINTE;
            }

        } else {
            // Evenement non trouvé
            if (cursor != null) cursor.close();
            return InscriptionVisiteurResultat.EVENEMENT_INTROUVABLE;
        }
    }

    public InscriptionAppliquantResultat inscrireAppliquant(long idUser, int idEvent) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        Cursor cursor = db.query(
                BaseContrat.EvenementTable.TABLE_NAME,
                null,
                BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idEvent)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int nbBenevolesMax = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_MAX));
            int nbBenevolesAcceptes = cursor.getInt(cursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES));

            boolean estListeAttente = nbBenevolesAcceptes >= nbBenevolesMax;

            // Insertion dans la table Inscription
            ContentValues values = new ContentValues();
            values.put(BaseContrat.InscriptionTable.ID_UTILISATEUR, idUser);
            values.put(BaseContrat.InscriptionTable.ID_EVENEMENT, idEvent);
            values.put(BaseContrat.InscriptionTable.ROLE, "appliquant");

            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
            values.put(BaseContrat.InscriptionTable.DATE_INSCRIPTION, formatDate.format(new Date()));

            long insertId = db.insert(BaseContrat.InscriptionTable.TABLE_NAME, null, values);
            cursor.close();

            if (insertId == -1) {
                return InscriptionAppliquantResultat.INSERTION_ECHEC;
            }
            else if(estListeAttente) {
                return InscriptionAppliquantResultat.LISTE_ATTENTE;
            }
            else {
                return InscriptionAppliquantResultat.SUCCES;
            }

        } else {
            if (cursor != null) cursor.close();
            return InscriptionAppliquantResultat.EVENEMENT_INTROUVABLE;
        }
    }



    public int annulerInscription(int idUser, int idEvent) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        // Lire le rôle de l'inscription
        Cursor cursor = db.query(
                BaseContrat.InscriptionTable.TABLE_NAME,
                new String[]{BaseContrat.InscriptionTable.ROLE},
                BaseContrat.InscriptionTable.ID_UTILISATEUR + " = ? AND " +
                        BaseContrat.InscriptionTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idUser), String.valueOf(idEvent)},
                null, null, null
        );

        if (cursor == null || !cursor.moveToFirst()) {
            if (cursor != null) cursor.close();
            return 0; // Aucune inscription retrouvée
        }

        // Récupère le rôle
        String role = cursor.getString(cursor.getColumnIndexOrThrow(BaseContrat.InscriptionTable.ROLE));
        cursor.close();

        // Mettre à jour la date d'annulation
        ContentValues values = new ContentValues();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
        values.put(BaseContrat.InscriptionTable.DATE_ANNULATION, formatDate.format(new Date()));

        int result = db.update(BaseContrat.InscriptionTable.TABLE_NAME,
                values,
                BaseContrat.InscriptionTable.ID_UTILISATEUR + " = ? AND " +
                        BaseContrat.InscriptionTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idUser), String.valueOf(idEvent)});

        // Mettre à jour les compteurs dans la table Evenement
        Cursor eventCursor = db.query(
                BaseContrat.EvenementTable.TABLE_NAME,
                null,
                BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                new String[]{String.valueOf(idEvent)},
                null, null, null
        );

        if (eventCursor != null && eventCursor.moveToFirst()) {
            ContentValues updateEvent = new ContentValues();

            if (role.equalsIgnoreCase("visiteur")) {
                int current = eventCursor.getInt(eventCursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_INSCRIPTIONS));
                if (current > 0) {
                    updateEvent.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, current - 1);
                    updateEvent.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, 0);
                }
            } else if (role.equalsIgnoreCase("benevole")) {
                int current = eventCursor.getInt(eventCursor.getColumnIndexOrThrow(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES));
                if (current > 0) {
                    updateEvent.put(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES, current - 1);
                    updateEvent.put(BaseContrat.EvenementTable.COMPLET_BENEVOLE, 0);
                }
            }

            db.update(
                    BaseContrat.EvenementTable.TABLE_NAME,
                    updateEvent,
                    BaseContrat.EvenementTable.ID_EVENEMENT + " = ?",
                    new String[]{String.valueOf(idEvent)}
            );

            eventCursor.close();
        }

        return result;
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
