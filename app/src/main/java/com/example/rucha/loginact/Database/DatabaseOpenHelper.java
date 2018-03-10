package com.example.rucha.loginact.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rucha on 07-03-2018.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper{

    final static String TABLE_NAME = "artists";
    final static String ARTIST_NAME = "name";
    final static String _ID = "_id";
    final static String[] columns = { _ID, ARTIST_NAME};
    final private static String NAME = "artist_db";
    final private static Integer VERSION = 1;
    final private Context lclcontext;

//    Create table query
    final private static String CREATE_TABLE = "CREATE TABLE artists ( " + _ID
                                            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + ARTIST_NAME + " TEXT NOT NULL)";


    public DatabaseOpenHelper(Context context){
        super(context, NAME, null, VERSION);
        this.lclcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void deleteDatabase() {
        lclcontext.deleteDatabase(NAME);
    }
}
