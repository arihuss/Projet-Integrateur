package com.sarah.applicationsqak.modele.Dao;

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.sarah.applicationsqak.modele.Categorie;
import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        if(cursor != null && cursor.moveToFirst()) {

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

    public List<Evenement> getEvenementsFiltres(String lieu, String etat, String role, String date, String recherche) {
        SQLiteDatabase db= dbUtil.getReadableDatabase();

        List<String> conditions = new ArrayList<>();  // contient tous les filtres sélectionnés
        List<String> valeurs = new ArrayList<>();

        // Si dans la Spinner des lieux, on a PAS coché 'Lieux' (tous les lieux), filtrer selon le lieu choisi
        if(!lieu.equalsIgnoreCase("Lieux")) {
            conditions.add("LIEU = ?");
            valeurs.add(lieu);
        }

        if(!etat.equalsIgnoreCase("Etats")) {
            conditions.add("ETAT = ?");
            valeurs.add(etat);
        }

        if(role.equalsIgnoreCase("benevole")) {
            conditions.add("NB_BENEVOLES_MAX > 0");
        }
        else if(role.equalsIgnoreCase("visiteur")) {
            conditions.add("NB_PARTICIPANTS_MAX > 0");
        }



        if(!date.isEmpty()) {
            String dateFormatee = convertirFormatDate(date);
            if(dateFormatee != null) {
                conditions.add("DATE_DEBUT LIKE ?");
                valeurs.add("%" + dateFormatee + "%");
            }

        }

        if(!recherche.trim().isEmpty()) {
            try {
                Categorie categorie = Categorie.fromLabel(recherche);
                conditions.add("CATEGORIE = ?");
                valeurs.add(categorie.getLabel());
            }
            catch(IllegalArgumentException e) {
                // La catégorie n'existe pas, on ajoute aucun filtre
                return null;
            }

        }

        String whereClause = conditions.isEmpty() ? null : TextUtils.join(" AND ", conditions);
        String[] whereArgs = valeurs.toArray(new String[0]);

        Cursor cursor = db.query(BaseContrat.EvenementTable.TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        List<Evenement> eventsfiltres = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()) {


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

                eventsfiltres.add(e);

            } while(cursor.moveToNext());
        }

        cursor.close();
        return eventsfiltres;
    }

    private String convertirFormatDate(String date) {
        try {
            // Format reçu du DatePickerDialog
            SimpleDateFormat formatEntree = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);

            // Convertir le String date choisie en objet Date
            Date parsedDate = formatEntree.parse(date);

            // Format qu'on veut pour la recherche dans la base de données
            SimpleDateFormat formatBD = new SimpleDateFormat("d MMM yyyy", Locale.CANADA);
            return formatBD.format(parsedDate);
        }
        catch(ParseException e){
            return null;
        }
    }

    public String getNomOrganisateurParId(int id) {
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.query(BaseContrat.OrganisateurTable.TABLE_NAME,
                new String[]{BaseContrat.OrganisateurTable.NOM_ORGANISATEUR},
                 BaseContrat.OrganisateurTable.ID_ORGANISATEUR + "= ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        String nom = "";
        if(cursor != null && cursor.moveToFirst()) {
            nom = cursor.getString(0);
        }

        cursor.close();
        return nom;
    }

    // À appeler après chaque nouvelle inscription
    private void verifierComplet(int idEvenement) {
        SQLiteDatabase db = dbUtil.getWritableDatabase();

        // Obtenir nb_inscriptions et nb_participants_max
        String query = "SELECT NB_INSCRIPTIONS, NB_PARTICIPANTS_MAX, NB_BENEVOLES_ACCEPTES, NB_BENEVOLES_MAX FROM " + BaseContrat.EvenementTable.TABLE_NAME
                + " WHERE ID_EVENEMENT = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idEvenement)});

        if(cursor.moveToFirst()) {
            int nbInscriptions = cursor.getInt(0);
            int nbMaxVisiteurs = cursor.getInt(1);
            int nbBenevoles = cursor.getInt(2);
            int nbMaxBenevoles = cursor.getInt(3);

            ContentValues values = new ContentValues();

            if(nbInscriptions >= nbMaxVisiteurs) {
                values.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, 1);
            }

            if(nbBenevoles >= nbMaxBenevoles) {
                values.put(BaseContrat.EvenementTable.COMPLET_BENEVOLE, 1);
            }

            if(values.size() > 0) {
                db.update(BaseContrat.EvenementTable.TABLE_NAME, values, "ID_EVENEMENT = ?", new String[]{String.valueOf(idEvenement)});
            }
        }
        cursor.close();
    }

    public List<Evenement> getEvenementsParUtilisateur(long userId) {
        List<Evenement> evenements = new ArrayList<>();
        SQLiteDatabase db = dbUtil.getReadableDatabase();

        String requete = "SELECT * FROM Evenement e " +
                "JOIN Inscription i ON e._id = i.ID_EVENEMENT " +
                "WHERE i.ID_UTILISATEUR = ? " +
                "ORDER BY e.DATE_DEBUT DESC";

        Cursor cursor = db.rawQuery(requete, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                Evenement evenement = new Evenement();

                evenement.setId(cursor.getInt(cursor.getColumnIndexOrThrow("ID_EVENEMENT")));
                evenement.setId_statistique(cursor.getInt(cursor.getColumnIndexOrThrow("ID_STATISTIQUE")));
                evenement.setId_organisateur(cursor.getInt(cursor.getColumnIndexOrThrow("ID_ORGANISATEUR")));
                evenement.setNomEvent(cursor.getString(cursor.getColumnIndexOrThrow("NOM_EVENT")));
                evenement.setLieu(cursor.getString(cursor.getColumnIndexOrThrow("LIEU")));
                evenement.setDateDebut(cursor.getString(cursor.getColumnIndexOrThrow("DATE_DEBUT")));
                evenement.setDateFin(cursor.getString(cursor.getColumnIndexOrThrow("DATE_FIN")));
                evenement.setNbBenevolesMax(cursor.getInt(cursor.getColumnIndexOrThrow("NB_BENEVOLES_MAX")));
                evenement.setNbParticipantsMax(cursor.getInt(cursor.getColumnIndexOrThrow("NB_PARTICIPANTS_MAX")));
                evenement.setEtatBenevole(cursor.getInt(cursor.getColumnIndexOrThrow("ETAT_BENEVOLE")));
                evenement.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION")));
                evenement.setEtat(cursor.getString(cursor.getColumnIndexOrThrow("ETAT")));
                evenement.setNbInscriptions(cursor.getInt(cursor.getColumnIndexOrThrow("NB_INSCRIPTIONS")));
                evenement.setNbBenevolesAcceptes(cursor.getInt(cursor.getColumnIndexOrThrow("NB_BENEVOLES_ACCEPTES")));
                evenement.setCompletBenevole(cursor.getInt(cursor.getColumnIndexOrThrow("COMPLET_BENEVOLE")));
                evenement.setCompletVisiteur(cursor.getInt(cursor.getColumnIndexOrThrow("COMPLET_VISITEUR")));
                evenement.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow("IMAGE_URL")));

                String catStr = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORIE"));
                if (catStr != null) {
                    evenement.setCategorie(Categorie.valueOf(catStr));
                }

                evenements.add(evenement);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return evenements;
    }

    public Evenement getEvenementParId(int id) {
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        Cursor cursor = db.query(
                BaseContrat.EvenementTable.TABLE_NAME,
                null,
                BaseContrat.EvenementTable.ID_EVENEMENT + " ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        Evenement evenement = null;
        if(cursor.moveToFirst()) {
            evenement = new Evenement();

            evenement.setId(cursor.getInt(cursor.getColumnIndexOrThrow("ID_EVENEMENT")));
            evenement.setId_statistique(cursor.getInt(cursor.getColumnIndexOrThrow("ID_STATISTIQUE")));
            evenement.setId_organisateur(cursor.getInt(cursor.getColumnIndexOrThrow("ID_ORGANISATEUR")));
            evenement.setNomEvent(cursor.getString(cursor.getColumnIndexOrThrow("NOM_EVENT")));
            evenement.setLieu(cursor.getString(cursor.getColumnIndexOrThrow("LIEU")));
            evenement.setDateDebut(cursor.getString(cursor.getColumnIndexOrThrow("DATE_DEBUT")));
            evenement.setDateFin(cursor.getString(cursor.getColumnIndexOrThrow("DATE_FIN")));
            evenement.setNbBenevolesMax(cursor.getInt(cursor.getColumnIndexOrThrow("NB_BENEVOLES_MAX")));
            evenement.setNbParticipantsMax(cursor.getInt(cursor.getColumnIndexOrThrow("NB_PARTICIPANTS_MAX")));
            evenement.setEtatBenevole(cursor.getInt(cursor.getColumnIndexOrThrow("ETAT_BENEVOLE")));
            evenement.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("DESCRIPTION")));
            evenement.setEtat(cursor.getString(cursor.getColumnIndexOrThrow("ETAT")));
            evenement.setNbInscriptions(cursor.getInt(cursor.getColumnIndexOrThrow("NB_INSCRIPTIONS")));
            evenement.setNbBenevolesAcceptes(cursor.getInt(cursor.getColumnIndexOrThrow("NB_BENEVOLES_ACCEPTES")));
            evenement.setCompletBenevole(cursor.getInt(cursor.getColumnIndexOrThrow("COMPLET_BENEVOLE")));
            evenement.setCompletVisiteur(cursor.getInt(cursor.getColumnIndexOrThrow("COMPLET_VISITEUR")));
            evenement.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow("IMAGE_URL")));

        }

        cursor.close();
        return evenement;
    }

    public int getNbLikesParIdStatistique(int idStat) {
        SQLiteDatabase db = dbUtil.getReadableDatabase();
        int likes = 0;

        Cursor cursor = db.query(BaseContrat.StatistiqueTable.TABLE_NAME,
                new String[]{BaseContrat.StatistiqueTable.NB_LIKES},
                BaseContrat.StatistiqueTable.ID_STATISTIQUE + " = ?",
                new String[]{String.valueOf(idStat)},
                null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            likes = cursor.getInt(0);
            cursor.close();
        }

        return likes;
    }



}
