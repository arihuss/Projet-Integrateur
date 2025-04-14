package com.sarah.applicationsqak.modele.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class SeedDB {

    public static void insererDonneesInitiales(SQLiteDatabase db) {
        insererUtilisateurs(db);
        insererOrganisateurs(db);
        insererEvenements(db);
    }

    private static void insererUtilisateurs(SQLiteDatabase db) {

    }

    private static void insererOrganisateurs(SQLiteDatabase db) {
        ContentValues org1 = new ContentValues();
        org1.put(BaseContrat.OrganisateurTable.PRENOM, "Sophie");
        org1.put(BaseContrat.OrganisateurTable.NOM, "Lemieux");
        org1.put(BaseContrat.OrganisateurTable.COURRIEL, "sophie@ecoaction.ca");
        org1.put(BaseContrat.OrganisateurTable.NOM_ORGANISATEUR, "ÉcoAction Montréal");
        org1.put(BaseContrat.OrganisateurTable.BIO, "Organisatrice d’événements environnementaux à Montréal.");
        org1.put(BaseContrat.OrganisateurTable.MOT_DE_PASSE, "sophie1234");
        org1.put(BaseContrat.OrganisateurTable.NB_EVENTS, 2);
        org1.put(BaseContrat.OrganisateurTable.IMAGE_URL, "https://exemple.com/images/sophie.jpg");

        db.insert(BaseContrat.OrganisateurTable.TABLE_NAME, null, org1);

        // Organisateur 2
        ContentValues org2 = new ContentValues();
        org2.put(BaseContrat.OrganisateurTable.PRENOM, "Maxime");
        org2.put(BaseContrat.OrganisateurTable.NOM, "Girard");
        org2.put(BaseContrat.OrganisateurTable.COURRIEL, "maxime@entraide.ca");
        org2.put(BaseContrat.OrganisateurTable.NOM_ORGANISATEUR, "Entraide Québec");
        org2.put(BaseContrat.OrganisateurTable.BIO, "Fervent défenseur des causes sociales.");
        org2.put(BaseContrat.OrganisateurTable.MOT_DE_PASSE, "entraideMax123");
        org2.put(BaseContrat.OrganisateurTable.NB_EVENTS, 1);
        org2.put(BaseContrat.OrganisateurTable.IMAGE_URL, "https://exemple.com/images/maxime.jpg");

        db.insert(BaseContrat.OrganisateurTable.TABLE_NAME, null, org2);

        // Organisateur 3
        ContentValues org3 = new ContentValues();
        org3.put(BaseContrat.OrganisateurTable.PRENOM, "Lina");
        org3.put(BaseContrat.OrganisateurTable.NOM, "Bouchard");
        org3.put(BaseContrat.OrganisateurTable.COURRIEL, "lina@sportif.org");
        org3.put(BaseContrat.OrganisateurTable.NOM_ORGANISATEUR, "Bénévoles Sportifs");
        org3.put(BaseContrat.OrganisateurTable.BIO, "Coordonne les événements sportifs communautaires.");
        org3.put(BaseContrat.OrganisateurTable.MOT_DE_PASSE, "sport1234");
        org3.put(BaseContrat.OrganisateurTable.NB_EVENTS, 3);
        org3.put(BaseContrat.OrganisateurTable.IMAGE_URL, "https://exemple.com/images/lina.jpg");

        db.insert(BaseContrat.OrganisateurTable.TABLE_NAME, null, org3);
    }

    private static void insererEvenements(SQLiteDatabase db) {
        for (int i = 1; i <= 20; i++) {
            ContentValues values = new ContentValues();
            values.put(BaseContrat.EvenementTable.ID_ORGANISATEUR, (i % 3) + 1); // Alternance entre 3 organisateurs
            values.put(BaseContrat.EvenementTable.ID_STATISTIQUE, i); // ou une valeur fixe si tu as une stat

            values.put(BaseContrat.EvenementTable.NOM_EVENT, "Événement #" + i);
            values.put(BaseContrat.EvenementTable.LIEU, i + " rue Principale, Laval");
            values.put(BaseContrat.EvenementTable.DATE_DEBUT, i + " avril 2025 5 PM");
            values.put(BaseContrat.EvenementTable.DATE_FIN, i + " avril 2025 8 PM");

            values.put(BaseContrat.EvenementTable.NB_BENEVOLES_MAX, 5 + (i % 6));  // entre 5 et 10
            values.put(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX, 10 + (i % 11)); // entre 10 et 20
            values.put(BaseContrat.EvenementTable.ETAT_BENEVOLE, (i % 2)); // 0 ou 1

            // Catégorie parmi ton enum
            String[] categories = {
                    "Environnement", "Aide alimentaire", "Événement sportif", "Collecte de fonds",
                    "Culture et arts", "Soutien communautaire", "Santé"
            };
            values.put(BaseContrat.EvenementTable.CATEGORIE, categories[i % categories.length]);

            values.put(BaseContrat.EvenementTable.DESCRIPTION, "Description de l'événement #" + i);
            values.put(BaseContrat.EvenementTable.ETAT, (i % 5 == 0) ? "Complet" : "Disponible"); // 1 sur 5 complet

            values.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, i % 5);
            values.put(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES, i % 3);

            values.put(BaseContrat.EvenementTable.COMPLET_BENEVOLE, (i % 4 == 0) ? 1 : 0);
            values.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, (i % 6 == 0) ? 1 : 0);

            values.put(BaseContrat.EvenementTable.IMAGE_URL, "https://exemple.com/image" + i + ".jpg");

            db.insert(BaseContrat.EvenementTable.TABLE_NAME, null, values);
        }
    }
}
