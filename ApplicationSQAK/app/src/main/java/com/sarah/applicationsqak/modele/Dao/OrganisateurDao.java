package com.sarah.applicationsqak.modele.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sarah.applicationsqak.modele.Organisateur;
import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

public class OrganisateurDao {

    private SQLiteDatabase db;

    public OrganisateurDao(Context context) {
        DbUtil dbHelper = new DbUtil(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public Organisateur getOrganisateurParId(long idOrg) {

        String[] colonnes = {
                BaseContrat.OrganisateurTable.ID_ORGANISATEUR,
                BaseContrat.OrganisateurTable.PRENOM,
                BaseContrat.OrganisateurTable.NOM,
                BaseContrat.OrganisateurTable.COURRIEL,
                BaseContrat.OrganisateurTable.BIO,
                BaseContrat.OrganisateurTable.NOM_ORGANISATEUR,
                BaseContrat.OrganisateurTable.MOT_DE_PASSE,
                BaseContrat.OrganisateurTable.NB_EVENTS,
                BaseContrat.OrganisateurTable.IMAGE_URL
        };

        String selection = BaseContrat.OrganisateurTable.ID_ORGANISATEUR + " = ?";
        String[] selectionArgs = { String.valueOf(idOrg) };

        Cursor cursor = db.query(
                BaseContrat.OrganisateurTable.TABLE_NAME,
                colonnes,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Organisateur organisateur = null;
        if (cursor.moveToFirst()) {
            organisateur = new Organisateur(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7),
                    cursor.getString(8)
            );
        }
        cursor.close();
        return organisateur;

    }


}
