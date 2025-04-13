package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtil extends SQLiteOpenHelper {

    public DbUtil(Context context) {
        super(context, BaseContrat.DB_NAME, null, BaseContrat.DB_VERSION);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        // Active les contraintes de clé étrangère
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création des tables
        String requeteCreationUtilisateur = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s text, %s text, %s text unique, %s text unique, %s text, %s text CHECK (length (%s)>= 8))",
            BaseContrat.UtilisateurTable.TABLE_NAME,
            BaseContrat.UtilisateurTable.ID_UTILISATEUR,
            BaseContrat.UtilisateurTable.PRENOM,
            BaseContrat.UtilisateurTable.NOM,
            BaseContrat.UtilisateurTable.COURRIEL,
            BaseContrat.UtilisateurTable.NUM_TEL,
            BaseContrat.UtilisateurTable.BIO,
            BaseContrat.UtilisateurTable.MOT_DE_PASSE,
            BaseContrat.UtilisateurTable.MOT_DE_PASSE);
        db.execSQL(requeteCreationUtilisateur);

        String requeteCreationOrganisateur = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s text, %s text, %s text unique, %s text, %s text, %s text, %s integer default 0 CHECK (length (%s)>= 8))",
                BaseContrat.OrganisateurTable.TABLE_NAME,
                BaseContrat.OrganisateurTable.ID_ORGANISATEUR,
                BaseContrat.OrganisateurTable.PRENOM,
                BaseContrat.OrganisateurTable.NOM,
                BaseContrat.OrganisateurTable.COURRIEL,
                BaseContrat.OrganisateurTable.BIO,
                BaseContrat.OrganisateurTable.NOM_ORGANISATEUR,
                BaseContrat.OrganisateurTable.MOT_DE_PASSE,
                BaseContrat.OrganisateurTable.NB_EVENTS,
                BaseContrat.OrganisateurTable.MOT_DE_PASSE);
        db.execSQL(requeteCreationOrganisateur);

        String requeteCreationStatistique = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s integer default 0, %s integer default 0, %s integer default 0, %s integer default 0, %s integer default 0)",
                BaseContrat.StatistiqueTable.TABLE_NAME,
                BaseContrat.StatistiqueTable.ID_STATISTIQUE,
                BaseContrat.StatistiqueTable.NB_VISITEURS,
                BaseContrat.StatistiqueTable.NB_BENEVOLES,
                BaseContrat.StatistiqueTable.NB_LIKES,
                BaseContrat.StatistiqueTable.NB_VUES,
                BaseContrat.StatistiqueTable.NB_PARTAGES);
        db.execSQL(requeteCreationStatistique);

        String requeteCreationEvenement = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s integer , %s integer , %s text, %s text, %s text, %s text, %s integer default 0, " +
                        "%s integer default 0, %s integer check (%s IN (0,1)), %s text, %s text, %s text CHECK (%s IN ('disponible', 'termine')), %s integer default 0, %s integer default 0, %s integer check (%s IN (0,1)), " +
                        "%s integer check (%s IN (0,1)), FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE, FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE)",
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
                BaseContrat.EvenementTable.ID_ORGANISATEUR, BaseContrat.OrganisateurTable.TABLE_NAME, BaseContrat.OrganisateurTable.ID_ORGANISATEUR,
                BaseContrat.EvenementTable.ID_STATISTIQUE, BaseContrat.StatistiqueTable.TABLE_NAME, BaseContrat.StatistiqueTable.ID_STATISTIQUE);
        db.execSQL(requeteCreationEvenement);

        String requeteCreationInscription = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s integer, %s integer, %s text CHECK(%s IN('benevole', 'visiteur', 'appliquant')), " +
                        "%s text, %s text, FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE, FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE)",
                BaseContrat.InscriptionTable.TABLE_NAME,
                BaseContrat.InscriptionTable.ID_INSCRIPTION,
                BaseContrat.InscriptionTable.ID_UTILISATEUR,
                BaseContrat.InscriptionTable.ROLE, BaseContrat.InscriptionTable.ROLE,
                BaseContrat.InscriptionTable.DATE_INSCRIPTION,
                BaseContrat.InscriptionTable.DATE_ANNULATION,
                BaseContrat.InscriptionTable.ID_INSCRIPTION, BaseContrat.UtilisateurTable.TABLE_NAME, BaseContrat.UtilisateurTable.ID_UTILISATEUR,
                BaseContrat.InscriptionTable.ID_EVENEMENT, BaseContrat.EvenementTable.TABLE_NAME, BaseContrat.EvenementTable.ID_EVENEMENT);
        db.execSQL(requeteCreationInscription);

        String requeteCreationCommentaire = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s integer, %s integer, %s text, %s text," +
                        "FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE, FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE)",
                BaseContrat.CommentaireTable.TABLE_NAME,
                BaseContrat.CommentaireTable.ID_COMMENTAIRE,
                BaseContrat.CommentaireTable.ID_EVENEMENT,
                BaseContrat.CommentaireTable.MESSAGE,
                BaseContrat.CommentaireTable.DATE_ENVOI,
                BaseContrat.CommentaireTable.ID_UTILISATEUR, BaseContrat.UtilisateurTable.TABLE_NAME, BaseContrat.UtilisateurTable.ID_UTILISATEUR,
                BaseContrat.CommentaireTable.ID_EVENEMENT, BaseContrat.EvenementTable.TABLE_NAME, BaseContrat.EvenementTable.ID_EVENEMENT);
        db.execSQL(requeteCreationCommentaire);

        String requeteCreationMessage = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s integer, %s text, %s text, %s text, %s text, FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE)",
                BaseContrat.MessageTable.TABLE_NAME,
                BaseContrat.MessageTable.ID_MESSAGE,
                BaseContrat.MessageTable.MOYEN_COMMUNICATION,
                BaseContrat.MessageTable.TYPE_DESTINATAIRE,
                BaseContrat.MessageTable.MESSAGE,
                BaseContrat.MessageTable.DATE_ENVOI,
                BaseContrat.CommentaireTable.ID_EVENEMENT, BaseContrat.EvenementTable.TABLE_NAME, BaseContrat.EvenementTable.ID_EVENEMENT);
        db.execSQL(requeteCreationMessage);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
