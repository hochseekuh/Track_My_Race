package com.sapstern.track_my_race.sql_lite_db;

/**
 * Created by matthias on 29/12/16.
 */

public interface BoatRaceDBInterface {

    public static final String TAG = "BoatRaceTrackerDBA";

    public final String DATABASE_NAME = "boatRaceTrackerDB";

    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_TABLE_CLASSES = "boatclasses";

    public static final String DATABASE_TABLE_PARTICIPANTS = "participant";

    public static final String KEY_BOAT_CLASS_NAME = "boatclassname";

    public static final String KEY_ROWID = "_id";

    /**
     * Database creation sql statement
     */
    public static final String DATABASE_CLASSES_CREATE =  "create table "+DATABASE_TABLE_CLASSES+" ("+KEY_ROWID+" integer primary key autoincrement, "+KEY_BOAT_CLASS_NAME+" text not null, isaf_id text, yardstick text);";

    public static final String DATABASE_PARTICIPANTS_CREATE =  "create table "+DATABASE_TABLE_PARTICIPANTS+" ("+KEY_ROWID+" integer primary key autoincrement, surname text not null, firstname text, title text, isHelsman boolean, boatclassname text, sailnumber text);";

    public static final String DATABASE_CLASS_SELECT_SINGLE =  "select count(*) from "+DATABASE_TABLE_CLASSES+" where "+KEY_BOAT_CLASS_NAME+" = ?";




}
