package com.sapstern.track_my_race.sql_lite_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by matthias on 29/12/16.
 */


public  class DatabaseHelper extends SQLiteOpenHelper implements  BoatRaceDBInterface{

    //declaration of singleton
    private static DatabaseHelper sInstance;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //factory method
    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating tables" + DATABASE_CLASSES_CREATE + " & " + DATABASE_PARTICIPANTS_CREATE );
        db.execSQL(DATABASE_CLASSES_CREATE);
        db.execSQL(DATABASE_PARTICIPANTS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_CLASSES);
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_PARTICIPANTS);
        onCreate(db);
    }
}

