package com.sapstern.track_my_race;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.sapstern.track_my_race.sql_lite_db.BoatRaceTrackerDBAdapter;

public class ActivityMain extends AppCompatActivity {

    private BoatRaceTrackerDBAdapter theBoatRaceDatabase = null;
    private Cursor theCursor = null;

    RadioButton theRadioButtonRegisterBoatRace;
    RadioButton theRadioButtonManageBoatClass;
    RadioButton theRadioButtonManageParticipant;

//MFRI Hier muss eine Auswahl getroffen werden was zuerst gemacht werden soll
// 1) Datum, teinehmende Klassen, Anzahl der Wettfahrten, Punktsystem eingeben, bzw anzeigen
// 2) Bootsklassen anlegen, bzw anzeigen
// 2) Teilnehmer pro Klasse aufnehmen
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intentRegisterBoatRace = new Intent(this, ActivityRegisterRace.class);
        theRadioButtonRegisterBoatRace = (RadioButton) findViewById(R.id.radioButtonRegisterBoatRace);
        theRadioButtonRegisterBoatRace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intentRegisterBoatRace);
            }
        });
        final Intent intentManageClass = new Intent(this, ActivityManageClass.class);
        theRadioButtonManageBoatClass = (RadioButton) findViewById(R.id.radioButtonManageClass);
        theRadioButtonManageBoatClass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intentManageClass);
            }
        });
        final Intent intentManageParticipants = new Intent(this, ActivityManageClass.class);
        theRadioButtonManageParticipant = (RadioButton) findViewById(R.id.radioButtonRegisterParticipants);
        theRadioButtonManageParticipant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intentManageParticipants);
            }
        });

        theBoatRaceDatabase = new BoatRaceTrackerDBAdapter(this);
        theBoatRaceDatabase = theBoatRaceDatabase.open();
        theCursor = theBoatRaceDatabase.fetchAllBoatClasses();
//        // Aktivitaet starten je nachdem, ob es schon urls in der Datenbank gibt
//        if ( theCursor==null||theCursor.getCount()==0 )
//        {
//            displayCreateBoatClassActivity(theCursor);
//        }
//        else
//        {
//            displayBoatClassList(theCursor);
//        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //theBoatRaceDatabase.close();
    }
    private void displayCreateBoatClassActivity(Cursor theCursor) {
    }

    private void displayBoatClassList(Cursor theCursor) {
        //MFRI das mus gemacht werden
    }
}
