package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUtil extends SQLiteOpenHelper {

    public DBUtil(Context context) {
        super(context, Event.DB_NAME, null, Event.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String requeteCreation = String.format("Create table %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s text, %S double)",
        Event.TABLE_NAME,
        Event.Colonnes.ID,
        Event.Colonnes.NOM);
    db.execSQL(requeteCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String requeteModification = String.format("alter table %s ADD %s int not null",
                Event.TABLE_NAME, "DESCRIPTION");
        db.execSQL(requeteModification);
    }
}
