package com.sarah.applicationsqak.modele.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtil extends SQLiteOpenHelper {

    public DbUtil(Context context) {
        super(context, BaseContrat.DB_NAME, null, BaseContrat.DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String requeteCreationUtilisateur = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT UNIQUE, " +
                        "%s TEXT UNIQUE, " +
                        "%s TEXT, " +
                        "%s TEXT CHECK (length(%s) >= 8), " +
                        "%s TEXT)",
                BaseContrat.UtilisateurTable.TABLE_NAME,
                BaseContrat.UtilisateurTable.ID_UTILISATEUR,
                BaseContrat.UtilisateurTable.PRENOM,
                BaseContrat.UtilisateurTable.NOM,
                BaseContrat.UtilisateurTable.COURRIEL,
                BaseContrat.UtilisateurTable.NUM_TEL,
                BaseContrat.UtilisateurTable.BIO,
                BaseContrat.UtilisateurTable.MOT_DE_PASSE,
                BaseContrat.UtilisateurTable.MOT_DE_PASSE,
                BaseContrat.UtilisateurTable.IMAGE_URL
        );
        db.execSQL(requeteCreationUtilisateur);

        String requeteCreationOrganisateur = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT UNIQUE, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT CHECK (length(%s) >= 8), " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s TEXT)",
                BaseContrat.OrganisateurTable.TABLE_NAME,
                BaseContrat.OrganisateurTable.ID_ORGANISATEUR,
                BaseContrat.OrganisateurTable.PRENOM,
                BaseContrat.OrganisateurTable.NOM,
                BaseContrat.OrganisateurTable.COURRIEL,
                BaseContrat.OrganisateurTable.BIO,
                BaseContrat.OrganisateurTable.NOM_ORGANISATEUR,
                BaseContrat.OrganisateurTable.MOT_DE_PASSE, BaseContrat.OrganisateurTable.MOT_DE_PASSE,
                BaseContrat.OrganisateurTable.NB_EVENTS,
                BaseContrat.OrganisateurTable.IMAGE_URL
        );
        db.execSQL(requeteCreationOrganisateur);

        String requeteCreationStatistique = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER DEFAULT 0)",
                BaseContrat.StatistiqueTable.TABLE_NAME,
                BaseContrat.StatistiqueTable.ID_STATISTIQUE,
                BaseContrat.StatistiqueTable.NB_VISITEURS,
                BaseContrat.StatistiqueTable.NB_BENEVOLES,
                BaseContrat.StatistiqueTable.NB_LIKES,
                BaseContrat.StatistiqueTable.NB_VUES,
                BaseContrat.StatistiqueTable.NB_PARTAGES
        );
        db.execSQL(requeteCreationStatistique);

        String requeteCreationEvenement = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER CHECK (%s IN (0,1)), " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT CHECK (%s IN ('disponible', 'termine')), " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER DEFAULT 0, " +
                        "%s INTEGER CHECK (%s IN (0,1)), " +
                        "%s INTEGER CHECK (%s IN (0,1)), " +
                        "%s TEXT, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
                BaseContrat.EvenementTable.TABLE_NAME,
                BaseContrat.EvenementTable.ID_EVENEMENT,
                BaseContrat.EvenementTable.ID_STATISTIQUE,
                BaseContrat.EvenementTable.ID_ORGANISATEUR,
                BaseContrat.EvenementTable.NOM_EVENT,
                BaseContrat.EvenementTable.LIEU,
                BaseContrat.EvenementTable.DATE_DEBUT,
                BaseContrat.EvenementTable.DATE_FIN,
                BaseContrat.EvenementTable.NB_BENEVOLES_MAX,
                BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX,
                BaseContrat.EvenementTable.ETAT_BENEVOLE, BaseContrat.EvenementTable.ETAT_BENEVOLE,
                BaseContrat.EvenementTable.CATEGORIE,
                BaseContrat.EvenementTable.DESCRIPTION,
                BaseContrat.EvenementTable.ETAT, BaseContrat.EvenementTable.ETAT,
                BaseContrat.EvenementTable.NB_INSCRIPTIONS,
                BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES,
                BaseContrat.EvenementTable.COMPLET_BENEVOLE, BaseContrat.EvenementTable.COMPLET_BENEVOLE,
                BaseContrat.EvenementTable.COMPLET_VISITEUR, BaseContrat.EvenementTable.COMPLET_VISITEUR,
                BaseContrat.EvenementTable.IMAGE_URL,
                BaseContrat.EvenementTable.ID_ORGANISATEUR, BaseContrat.OrganisateurTable.TABLE_NAME, BaseContrat.OrganisateurTable.ID_ORGANISATEUR,
                BaseContrat.EvenementTable.ID_STATISTIQUE, BaseContrat.StatistiqueTable.TABLE_NAME, BaseContrat.StatistiqueTable.ID_STATISTIQUE
        );
        db.execSQL(requeteCreationEvenement);

        String requeteCreationInscription = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s TEXT CHECK(%s IN('benevole', 'visiteur', 'appliquant')), " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
                BaseContrat.InscriptionTable.TABLE_NAME,
                BaseContrat.InscriptionTable.ID_INSCRIPTION,
                BaseContrat.InscriptionTable.ID_UTILISATEUR,
                BaseContrat.InscriptionTable.ID_EVENEMENT,
                BaseContrat.InscriptionTable.ROLE, BaseContrat.InscriptionTable.ROLE,
                BaseContrat.InscriptionTable.DATE_INSCRIPTION,
                BaseContrat.InscriptionTable.DATE_ANNULATION,
                BaseContrat.InscriptionTable.ID_UTILISATEUR, BaseContrat.UtilisateurTable.TABLE_NAME, BaseContrat.UtilisateurTable.ID_UTILISATEUR,
                BaseContrat.InscriptionTable.ID_EVENEMENT, BaseContrat.EvenementTable.TABLE_NAME, BaseContrat.EvenementTable.ID_EVENEMENT
        );
        db.execSQL(requeteCreationInscription);

        String requeteCreationCommentaire = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
                BaseContrat.CommentaireTable.TABLE_NAME,
                BaseContrat.CommentaireTable.ID_COMMENTAIRE,
                BaseContrat.CommentaireTable.ID_EVENEMENT,
                BaseContrat.CommentaireTable.ID_UTILISATEUR,
                BaseContrat.CommentaireTable.MESSAGE,
                BaseContrat.CommentaireTable.DATE_ENVOI,
                BaseContrat.CommentaireTable.ID_UTILISATEUR, BaseContrat.UtilisateurTable.TABLE_NAME, BaseContrat.UtilisateurTable.ID_UTILISATEUR,
                BaseContrat.CommentaireTable.ID_EVENEMENT, BaseContrat.EvenementTable.TABLE_NAME, BaseContrat.EvenementTable.ID_EVENEMENT
        );
        db.execSQL(requeteCreationCommentaire);

        String requeteCreationMessage = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
                BaseContrat.MessageTable.TABLE_NAME,
                BaseContrat.MessageTable.ID_MESSAGE,
                BaseContrat.MessageTable.ID_EVENEMENT,
                BaseContrat.MessageTable.MOYEN_COMMUNICATION,
                BaseContrat.MessageTable.TYPE_DESTINATAIRE,
                BaseContrat.MessageTable.MESSAGE,
                BaseContrat.MessageTable.DATE_ENVOI,
                BaseContrat.MessageTable.ID_EVENEMENT, BaseContrat.EvenementTable.TABLE_NAME, BaseContrat.EvenementTable.ID_EVENEMENT
        );
        db.execSQL(requeteCreationMessage);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
