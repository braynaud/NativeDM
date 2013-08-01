package com.example.nativeapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		final Activity menuActivityClass = this;
		//Drum Machine
		final Button drum = (Button) findViewById(R.id.button1);
        drum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Navigates to drum machine page
            	Intent mainIntent = new Intent(menuActivityClass, MainActivity.class);
				startActivity(mainIntent);
            }
        });
        //Sequencer
        final Button sequence = (Button) findViewById(R.id.button2);
        sequence.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	
            }
        });
        //Create Kits
        final Button create = (Button) findViewById(R.id.button3);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        //Browse kits
        final Button browse = (Button) findViewById(R.id.button4);
        browse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Navigate to Browser page
            	Intent mainIntent = new Intent(menuActivityClass, BrowserActivity.class);
				startActivity(mainIntent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
