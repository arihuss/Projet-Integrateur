package com.sarah.applicationsqak.modele.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SeedDB {

    public static void insererDonneesInitiales(SQLiteDatabase db) {
        Log.d("SEED", "Insertion des données initiales....");
        insererUtilisateurs(db);
        insererOrganisateurs(db);
        insererStatistiques(db);
        insererEvenements(db);
        insererEvenementsComplets(db);
        insererInscriptions(db);
    }

    private static void insererUtilisateurs(SQLiteDatabase db) {
        for (int i = 1; i <= 20; i++) {
            ContentValues user = new ContentValues();
            user.put(BaseContrat.UtilisateurTable.PRENOM, "UtilisateurPrenom" + i);
            user.put(BaseContrat.UtilisateurTable.NOM, "Nom" + i);
            user.put(BaseContrat.UtilisateurTable.COURRIEL, "user" + i + "@email.com");
            user.put(BaseContrat.UtilisateurTable.NUM_TEL, "51400000" + String.format("%02d", i));  // Ex: 5140000001
            user.put(BaseContrat.UtilisateurTable.BIO, "Bio de l'utilisateur " + i);
            user.put(BaseContrat.UtilisateurTable.MOT_DE_PASSE, "password" + i);  // Assure-toi que ça respecte la règle des 8 caractères
            user.put(BaseContrat.UtilisateurTable.IMAGE_URL, "https://exemple.com/user" + i + ".jpg");

            db.insert(BaseContrat.UtilisateurTable.TABLE_NAME, null, user);


        }

        // Compte Roma
        ContentValues userRoma = new ContentValues();
        userRoma.put(BaseContrat.UtilisateurTable.PRENOM, "Roma");
        userRoma.put(BaseContrat.UtilisateurTable.NOM, "Des Ruisseaux");
        userRoma.put(BaseContrat.UtilisateurTable.COURRIEL, "roma@example.com");
        userRoma.put(BaseContrat.UtilisateurTable.NUM_TEL, "450-888-1912");
        userRoma.put(BaseContrat.UtilisateurTable.BIO, "Directrice de Camp de Jour Camp Académie Laval Souvenir");
        userRoma.put(BaseContrat.UtilisateurTable.MOT_DE_PASSE, "roma1234");
        userRoma.put(BaseContrat.UtilisateurTable.IMAGE_URL, "https://exemple.com/images/sophie.jpg");

        db.insert(BaseContrat.UtilisateurTable.TABLE_NAME, null, userRoma);
    }


    private static void insererOrganisateurs(SQLiteDatabase db) {
        Log.d("SEED", "Insertion de 3 organisateurs...");

        // Organisateur 1
        ContentValues org1 = new ContentValues();
        org1.put(BaseContrat.OrganisateurTable.PRENOM, "Sophie");
        org1.put(BaseContrat.OrganisateurTable.NOM, "Lemieux");
        org1.put(BaseContrat.OrganisateurTable.COURRIEL, "sophie@ecoaction.ca");
        org1.put(BaseContrat.OrganisateurTable.NOM_ORGANISATEUR, "ÉcoAction Montréal");
        org1.put(BaseContrat.OrganisateurTable.BIO, "Organisatrice d’événements environnementaux à Montréal.");
        org1.put(BaseContrat.OrganisateurTable.MOT_DE_PASSE, "sophie1234");
        org1.put(BaseContrat.OrganisateurTable.NB_EVENTS, 2);
        org1.put(BaseContrat.OrganisateurTable.IMAGE_URL, "https://github.com/romadr199/images-sqak/blob/main/images-organisateurs/1.png?raw=true");

        long id = db.insert(BaseContrat.OrganisateurTable.TABLE_NAME, null, org1);
        if(id == -1) {
            Log.d("SEED", "Échec d'insertion de l'organisateur #1");
        }

        // Organisateur 2
        ContentValues org2 = new ContentValues();
        org2.put(BaseContrat.OrganisateurTable.PRENOM, "Maxime");
        org2.put(BaseContrat.OrganisateurTable.NOM, "Girard");
        org2.put(BaseContrat.OrganisateurTable.COURRIEL, "maxime@entraide.ca");
        org2.put(BaseContrat.OrganisateurTable.NOM_ORGANISATEUR, "Entraide Québec");
        org2.put(BaseContrat.OrganisateurTable.BIO, "Fervent défenseur des causes sociales.");
        org2.put(BaseContrat.OrganisateurTable.MOT_DE_PASSE, "entraideMax123");
        org2.put(BaseContrat.OrganisateurTable.NB_EVENTS, 1);
        org2.put(BaseContrat.OrganisateurTable.IMAGE_URL, "https://github.com/romadr199/images-sqak/blob/main/images-organisateurs/2.jpg?raw=true");

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
        org3.put(BaseContrat.OrganisateurTable.IMAGE_URL, "https://github.com/romadr199/images-sqak/blob/main/images-organisateurs/3.png?raw=true");

        db.insert(BaseContrat.OrganisateurTable.TABLE_NAME, null, org3);
    }

    private static void insererStatistiques(SQLiteDatabase db) {
        for (int i = 1; i <= 23; i++) {
            ContentValues values = new ContentValues();
            values.put(BaseContrat.StatistiqueTable.NB_VISITEURS, 0);
            values.put(BaseContrat.StatistiqueTable.NB_BENEVOLES, 0);
            values.put(BaseContrat.StatistiqueTable.NB_LIKES, 0);
            values.put(BaseContrat.StatistiqueTable.NB_VUES, 0);
            values.put(BaseContrat.StatistiqueTable.NB_PARTAGES, 0);

            db.insert(BaseContrat.StatistiqueTable.TABLE_NAME, null, values);
        }
    }

    private static void insererEvenements(SQLiteDatabase db) {
        Log.d("SEED", "Insertion de 20 événements...");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        for (int i = 1; i <= 20; i++) {
            ContentValues values = new ContentValues();
            values.put(BaseContrat.EvenementTable.ID_ORGANISATEUR, (i % 3) + 1); // Alternance entre 3 organisateurs
            values.put(BaseContrat.EvenementTable.ID_STATISTIQUE, i); // ou une valeur fixe si tu as une stat

            values.put(BaseContrat.EvenementTable.NOM_EVENT, "Événement #" + i);

            // Lieux
            String[] lieux = {"Laval", "Montreal", "Brossard", "Quebec"};
            values.put(BaseContrat.EvenementTable.LIEU, i + " rue Principale, " + lieux[i % lieux.length]);
            values.put(BaseContrat.EvenementTable.DESCRIPTION, "Description de l'événement #" + i);
            values.put(BaseContrat.EvenementTable.ETAT_BENEVOLE, (i % 2)); // 0 ou 1

            // Catégorie parmi ton enum
            String[] categories = {
                    "ENVIRONNEMENT", "COMMUNAUTAIRE", "CULTUREL", "SANTE",
                    "EDUCATION", "SPORTS"
            };
            values.put(BaseContrat.EvenementTable.CATEGORIE, categories[i % categories.length]);


            int nbInscriptions = i % 5;
            int nbParticipantsMax = 10 + (i % 11);

            int nbBenevolesAcceptes = i % 3;
            int nbBenevolesMax = 5 + (i % 6);

            // Ajouter les valeurs dans le ContentValues
            values.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, nbInscriptions);
            values.put(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX, nbParticipantsMax);

            values.put(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES, nbBenevolesAcceptes);
            values.put(BaseContrat.EvenementTable.NB_BENEVOLES_MAX, nbBenevolesMax);

            // Calcul dynamique des complétions
            int completVisiteur = (nbInscriptions >= nbParticipantsMax) ? 1 : 0;
            int completBenevole = (nbBenevolesAcceptes >= nbBenevolesMax) ? 1 : 0;

            values.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, completVisiteur);
            values.put(BaseContrat.EvenementTable.COMPLET_BENEVOLE, completBenevole);

            // État général
            if (completVisiteur == 1 && completBenevole == 1) {
                values.put(BaseContrat.EvenementTable.ETAT, "Complet");
            } else {
                values.put(BaseContrat.EvenementTable.ETAT, "Disponible");
            }

            String[] imageNames = {"1.jpg", "2.png", "3.jpg", "4.jpg", "5.png", "6.jpg", "7.jpg", "8.jpeg", "9.png"};

            values.put(BaseContrat.EvenementTable.IMAGE_URL, "https://github.com/romadr199/images-sqak/blob/main/images-evenements/" + imageNames[i%imageNames.length] + "?raw=true");

            // Pour les dates au format dd/MM/yyyy
            Calendar cal = Calendar.getInstance();
            cal.set(2025, Calendar.APRIL, i);
            String dateDebut = format.format(cal.getTime());

            cal.set(Calendar.HOUR_OF_DAY, 20); // facultatif ici
            String dateFin = format.format(cal.getTime());

            values.put(BaseContrat.EvenementTable.DATE_DEBUT, dateDebut);
            values.put(BaseContrat.EvenementTable.DATE_FIN, dateFin);

            long id = db.insert(BaseContrat.EvenementTable.TABLE_NAME, null, values);

            if(id == -1) {
                Log.d("SEED", "Échec d'insertion des événements");
            }
        }
    }

    private static void insererEvenementsComplets(SQLiteDatabase db) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        Calendar cal = Calendar.getInstance();

        for (int i = 1; i <= 3; i++) {
            ContentValues values = new ContentValues();

            values.put(BaseContrat.EvenementTable.ID_ORGANISATEUR, i);
            values.put(BaseContrat.EvenementTable.ID_STATISTIQUE, 20 + i);
            values.put(BaseContrat.EvenementTable.NOM_EVENT, "Événement Complet #" + i);

            values.put(BaseContrat.EvenementTable.LIEU, "123 rue Principale, Montreal");

            int nbParticipantsMax = 20;
            int nbBenevolesMax = 5;

            // valeurs égales aux max pour marquer complet
            int nbInscriptions = 20;
            int nbBenevolesAcceptes = 5;

            values.put(BaseContrat.EvenementTable.NB_PARTICIPANTS_MAX, nbParticipantsMax);
            values.put(BaseContrat.EvenementTable.NB_INSCRIPTIONS, nbInscriptions);
            values.put(BaseContrat.EvenementTable.NB_BENEVOLES_MAX, nbBenevolesMax);
            values.put(BaseContrat.EvenementTable.NB_BENEVOLES_ACCEPTES, nbBenevolesAcceptes);

            values.put(BaseContrat.EvenementTable.COMPLET_VISITEUR, 1);
            values.put(BaseContrat.EvenementTable.COMPLET_BENEVOLE, 1);
            values.put(BaseContrat.EvenementTable.ETAT, "Complet");

            values.put(BaseContrat.EvenementTable.ETAT_BENEVOLE, 1);
            values.put(BaseContrat.EvenementTable.CATEGORIE, "COMMUNAUTAIRE");
            values.put(BaseContrat.EvenementTable.DESCRIPTION, "Événement déjà complet à l'avance.");

            // Pour les images
            String[] imageNames = {"1.jpg", "2.png", "3.jpg", "4.jpg", "5.png", "6.jpg", "7.jpg", "8.jpeg", "9.png"};

            values.put(BaseContrat.EvenementTable.IMAGE_URL, "https://github.com/romadr199/images-sqak/blob/main/images-evenements/" + imageNames[i%imageNames.length] + "?raw=true");

            // Dates
            cal.set(2025, Calendar.APRIL, 10 + i);  // 11, 12, 13 avril
            String dateDebut = format.format(cal.getTime());

            cal.set(Calendar.HOUR_OF_DAY, 20);
            String dateFin = format.format(cal.getTime());

            values.put(BaseContrat.EvenementTable.DATE_DEBUT, dateDebut);
            values.put(BaseContrat.EvenementTable.DATE_FIN, dateFin);

            long id = db.insert(BaseContrat.EvenementTable.TABLE_NAME, null, values);

            if (id == -1) {
                Log.d("INSERT", "Erreur d'insertion de l'événement complet #" + i);
            } else {
                Log.d("INSERT", "Événement complet #" + i + " inséré avec succès (id=" + id + ")");
            }
        }

    }

    private static void insererInscriptions(SQLiteDatabase db) {

    }
}
