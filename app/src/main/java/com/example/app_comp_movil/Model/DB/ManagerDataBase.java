package com.example.app_comp_movil.Model.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManagerDataBase extends SQLiteOpenHelper {


    private static final String DATA_BASE = "dbUser";
    private static final int VERSION = 1;
    private static final String TABLE_USER = "users";
    private static final String TABLE_CARD = "cards";
    private static final String QUERY_TABLE_USER = "CREATE TABLE "+TABLE_USER+
            "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_name VARCHAR(150) NOT NULL, " +
            "user_email VARCHAR(150) NOT NULL, " +
            "user_password VARCHAR(150) NOT NULL " +
            ");";

    private static final String QUERY_CARD_TABLE = "CREATE TABLE "+TABLE_CARD+
            "(card_id INTEGER PRIMARY KEY, "+
            "card_title VARCHAR(150), "+
            "card_img VARCHAR(300), "+
            "user_id INTEGER NOT NULL, "+
            "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE );";

    public ManagerDataBase(@Nullable Context context) {
        super(context, DATA_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(QUERY_TABLE_USER);
        database.execSQL(QUERY_CARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        final String DB_DOWN_USER = "DROP TABLE IF EXISTS "+TABLE_USER;
        final String DB_DOWN_CARD = "DROP TABLE IF EXISTS "+TABLE_CARD;
        database.execSQL(DB_DOWN_USER);
        database.execSQL(DB_DOWN_CARD);
        onCreate(database);
    }
}
