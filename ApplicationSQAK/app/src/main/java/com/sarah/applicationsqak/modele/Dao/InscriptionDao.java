package com.sarah.applicationsqak.modele.Dao;

import android.content.Context;

import com.sarah.applicationsqak.modele.sqlite.DbUtil;

public class InscriptionDao {
    private DbUtil dbUtil;

    public InscriptionDao(Context context) {
        dbUtil = new DbUtil(context);
    }


}
