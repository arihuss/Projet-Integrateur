package com.sarah.applicationsqak.modele.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sarah.applicationsqak.modele.Evenement;
import com.sarah.applicationsqak.modele.sqlite.BaseContrat;
import com.sarah.applicationsqak.modele.sqlite.DbUtil;

import java.util.ArrayList;
import java.util.List;

public class EvenementDao {
    private DbUtil dbUtil;

    public EvenementDao(Context context) {
        dbUtil = new DbUtil(context);
    }

    public List<Evenement> getEvenements() {
        List<Evenement> evenements = new ArrayList<>();
        SQLiteDatabase db = dbUtil.getReadableDatabase();

        Cursor cursor = db.query(BaseContrat.EvenementTable.TABLE_NAME, null, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();

            // Remplir la liste
            do {
                Evenement e = new Evenement();
            }while(cursor.moveToNext());
        }

        return cursor;
    }
}
