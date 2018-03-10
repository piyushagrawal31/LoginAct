package com.example.rucha.loginact.Database;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

import com.example.rucha.loginact.R;

public class DatabaseActivity extends ListActivity {

    private SQLiteDatabase lclDb = null;
    private DatabaseOpenHelper lclDbHelper;
    private SimpleCursorAdapter lclAdapter;

//    public DatabaseActivity(SQLiteDatabase lclDb) {
//        this.lclDb = lclDb;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

//      Create new Database Helper
        lclDbHelper = new DatabaseOpenHelper(this);

//      Get the database for writing
        lclDb = lclDbHelper.getWritableDatabase();

        clearAll();

//      Insert records
        insertArtists();

//      Create a cursor
        Cursor cursor = readArtists();
        lclAdapter = new SimpleCursorAdapter(this, R.layout.database_list_layout, cursor,
               DatabaseOpenHelper.columns, new int[] {R.id._id, R.id.name }, 0 );

        setListAdapter(lclAdapter);

        Button lclbtnFix = (Button) findViewById(R.id.btnFix);
        lclbtnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fix();

//              Redisplay data
                lclAdapter.getCursor().requery();
                lclAdapter.notifyDataSetChanged();
            }
        });
    }

//  Modify the contents of database
    private void fix() {
        lclDb.delete(DatabaseOpenHelper.TABLE_NAME, DatabaseOpenHelper.ARTIST_NAME + "=?",
                new String[] {"Atif Aslam"});

        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.ARTIST_NAME, "Sir A.R.Rehman");

        lclDb.update(DatabaseOpenHelper.TABLE_NAME, values, DatabaseOpenHelper.ARTIST_NAME + "=?",
                new String[] {"A.R.Rehman"});
    }

    //    Returns all Artists record from database
    private Cursor readArtists() {
        return lclDb.query(DatabaseOpenHelper.TABLE_NAME, DatabaseOpenHelper.columns, null, new String[]{},
               null, null, null );
    }

    //    Inserting records in database
    private void insertArtists() {

        ContentValues values = new ContentValues();

        values.put(DatabaseOpenHelper.ARTIST_NAME, "Sonu Nigam");
        lclDb.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DatabaseOpenHelper.ARTIST_NAME, "A.R.Rehman");
        lclDb.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DatabaseOpenHelper.ARTIST_NAME, "Shreya Ghoshal");
        lclDb.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DatabaseOpenHelper.ARTIST_NAME, "Atif Aslam");
        lclDb.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        values.clear();

        values.put(DatabaseOpenHelper.ARTIST_NAME, "Arijit Singh");
        lclDb.insert(DatabaseOpenHelper.TABLE_NAME, null, values);
        values.clear();
    }

    //    Delete all records
    private void clearAll() {
        lclDb.delete(DatabaseOpenHelper.TABLE_NAME, null, null);
    }
}
