package com.sapstern.track_my_race.sql_lite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by matthias on 03/12/16.
 */
public class BoatRaceTrackerDBAdapter implements  BoatRaceDBInterface{


    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;



    private final Context mCtx;




    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public BoatRaceTrackerDBAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the boat classs database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public BoatRaceTrackerDBAdapter open() throws SQLException {
        mDbHelper = DatabaseHelper.getInstance(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new tracking boat class. If the boat class is
     * successfully created return the new rowId for that boatclass, otherwise return
     * a -1 to indicate failure.
     *
     * @param theBoatClassName the name of the boatclass
     * @return rowId or -1 if failed
     */
    public long createBoatClass(String theBoatClassName, String theYardstick) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_BOAT_CLASS_NAME, theBoatClassName);
        initialValues.put("yardstick", theYardstick);

        //Hier die Verprobung, ob es die Klasse schon gibt fertigstellen
        Cursor resultCursor =  mDb.rawQuery(DATABASE_CLASS_SELECT_SINGLE, new String[]{theBoatClassName});
        int count = resultCursor.getCount();
        resultCursor.close();
        //den Cursor abfragen
        if (count > 0)
            return -1;
        else
            return mDb.insert(DATABASE_TABLE_CLASSES, null, initialValues);
    }


    /**
     * Delete the boat classs with the given rowId
     *
     * @param rowId id of boat class to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteBoatClass(long rowId) {

        return mDb.delete(DATABASE_TABLE_CLASSES, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all boat classs in the database
     *
     * @return Cursor over all boat classs
     */
    public Cursor fetchAllBoatClasses() {


        return mDb.query(DATABASE_TABLE_CLASSES, new String[] {KEY_ROWID, KEY_BOAT_CLASS_NAME}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the boat class that matches the given rowId
     *
     * @param rowId id of boat class to retrieve
     * @return Cursor positioned to matching boat class, if found
     * @throws SQLException if boat class could not be found/retrieved
     */
    public Cursor fetchBoatClass(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE_CLASSES, new String[] {KEY_ROWID,
                                KEY_BOAT_CLASS_NAME}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the boat class using the details provided. The boat class to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @param rowId id of boat class to update
     * @param theBoatClassName value to set boat class name to
     * @return true if the boat class was successfully updated, false otherwise
     */
    public boolean updateBoatClass(long rowId, String theBoatClassName) {
        ContentValues args = new ContentValues();
        args.put(KEY_BOAT_CLASS_NAME, theBoatClassName);

        return mDb.update(DATABASE_TABLE_CLASSES, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
