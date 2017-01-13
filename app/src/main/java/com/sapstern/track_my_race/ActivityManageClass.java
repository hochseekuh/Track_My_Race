package com.sapstern.track_my_race;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sapstern.track_my_race.boat_class.BoatClass;
import com.sapstern.track_my_race.sql_lite_db.BoatRaceTrackerDBAdapter;

import java.util.List;

public class ActivityManageClass extends AppCompatActivity {

    private Button addClassButton = null;
    private SharedPreferences thePrefs = null;
    private EditText theBoatClassText = null;
    private EditText theYardStickText = null;
    private List<BoatClass> boatClassList = null;

    private BoatRaceTrackerDBAdapter theBoatRaceDatabase = null;
    private Cursor theCursor = null;


    //Hashtable tab;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_register_class);

        theBoatRaceDatabase = new BoatRaceTrackerDBAdapter(this);
        theBoatRaceDatabase = theBoatRaceDatabase.open();


        theBoatClassText = (EditText) findViewById(R.id.boatClass);
        theYardStickText = (EditText) findViewById(R.id.yardStick);

        addClassButton = (Button) findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                long returnValue =  theBoatRaceDatabase.createBoatClass(theBoatClassText.getText().toString(),theYardStickText.getText().toString());
                CharSequence theText = null;
                if (returnValue == -1)
                {
                    theText = getString(R.string.error_insert_class);
                }
                else
                {
                    theText = getString(R.string.success_insert_class);
                }
                Toast.makeText(getApplicationContext(), theText, Toast.LENGTH_SHORT).show();






            }
        });


    }


}
