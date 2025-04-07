package sqlite;

import android.provider.BaseColumns;

public class Event {
    public static final String DB_NAME = "EVENTS.DB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Events";

    public class Colonnes {
        public static final String ID = BaseColumns._ID;
        public static final String NOM = "NOM_EVENT";
    }
}
