package com.sarah.applicationsqak.modele.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sarah.applicationsqak.modele.Utilisateur;
import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

public class UtilisateurDao {
    private SQLiteDatabase db;

    public UtilisateurDao(Context context) {
        DbUtil dbHelper = new DbUtil(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // Insérer un utilisateur
    public long insererUtilisateur(Utilisateur utilisateur) {
        ContentValues values = new ContentValues();
        values.put(BaseContrat.UtilisateurTable.PRENOM, utilisateur.getPrenom());
        values.put(BaseContrat.UtilisateurTable.NOM, utilisateur.getNom());
        values.put(BaseContrat.UtilisateurTable.COURRIEL, utilisateur.getCourriel());
        values.put(BaseContrat.UtilisateurTable.NUM_TEL, utilisateur.getNumTel());
        values.put(BaseContrat.UtilisateurTable.BIO, utilisateur.getBio());
        values.put(BaseContrat.UtilisateurTable.MOT_DE_PASSE, utilisateur.getMotDePasse());
        values.put(BaseContrat.UtilisateurTable.IMAGE_URL, utilisateur.getImageUrl());

        return db.insert(BaseContrat.UtilisateurTable.TABLE_NAME, null, values);
    }

    // Authentifier un utilisateur par courriel + mdp
    public Utilisateur getUtilisateurParIdentifiants(String courriel, String motDePasse) {
        String[] colonnes = {
                BaseContrat.UtilisateurTable.ID_UTILISATEUR,
                BaseContrat.UtilisateurTable.PRENOM,
                BaseContrat.UtilisateurTable.NOM,
                BaseContrat.UtilisateurTable.COURRIEL,
                BaseContrat.UtilisateurTable.NUM_TEL,
                BaseContrat.UtilisateurTable.BIO,
                BaseContrat.UtilisateurTable.MOT_DE_PASSE,
                BaseContrat.UtilisateurTable.IMAGE_URL
        };

        String selection = BaseContrat.UtilisateurTable.COURRIEL + " = ? AND " +
                BaseContrat.UtilisateurTable.MOT_DE_PASSE + " = ?";
        String[] selectionArgs = { courriel, motDePasse };

        Cursor cursor = db.query(
                BaseContrat.UtilisateurTable.TABLE_NAME,
                colonnes,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Utilisateur utilisateur = null;
        if (cursor.moveToFirst()) {
            utilisateur = new Utilisateur(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }
        cursor.close();
        return utilisateur;
    }
}

