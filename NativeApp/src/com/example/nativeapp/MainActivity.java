package com.example.nativeapp;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
 
public class MainActivity extends Activity implements OnTouchListener {
    private SoundPool soundPool;
    private int[] currentkit = new int[8];
    boolean loaded = false;
    private int i=0;
    private Path[] currentKit = new Path[8];
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         View a = findViewById(R.id.Button01);
         a.setOnTouchListener(this);
         View b = findViewById(R.id.Button02);
         b.setOnTouchListener(this);
         View c = findViewById(R.id.Button03);
         c.setOnTouchListener(this);
         View d = findViewById(R.id.Button04);
         d.setOnTouchListener(this);
         View e = findViewById(R.id.Button05);
         e.setOnTouchListener(this);
         View f = findViewById(R.id.Button06);
         f.setOnTouchListener(this);
         View g = findViewById(R.id.Button07);
         g.setOnTouchListener(this);
         View h = findViewById(R.id.Button08);
         h.setOnTouchListener(this);
         View i = findViewById(R.id.kit1);
         i.setOnTouchListener(this);
         View j = findViewById(R.id.kit2);
         j.setOnTouchListener(this);
         View k = findViewById(R.id.kit3);
         k.setOnTouchListener(this);
         View l = findViewById(R.id.menu);
         l.setOnTouchListener(this);
         
         // Set the hardware buttons to control the music
         // this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
         // Load the sound
         soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
         loadCurrentKit();
    }
 
    public void loadCurrentKit()
    {
    	soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                    int status) {
                loaded = true;
            }
        });
    	currentkit[0] = soundPool.load(this, R.raw.kick, 1);
    	currentkit[1] = soundPool.load(this, R.raw.snare, 1);
    	currentkit[2] = soundPool.load(this, R.raw.clap, 1);
    	currentkit[3] = soundPool.load(this, R.raw.cc, 1);
    	currentkit[4] = soundPool.load(this, R.raw.riffe, 1);
    	currentkit[5] = soundPool.load(this, R.raw.riffc, 1);
    	currentkit[6] = soundPool.load(this, R.raw.riffa, 1);
    	currentkit[7] = soundPool.load(this, R.raw.riffd4, 1);
    }
    
    public void play(int i, MotionEvent event)
    {
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Getting the user sound settings
            //AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            //float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            //float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            //float volume = actualVolume / maxVolume;
            if (loaded) {
                soundPool.play(currentkit[i], 1, 1, 1, 0, 1f);
            }
        }
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event) {
    	final Activity mainActivityClass = this;
    	if(v==findViewById(R.id.Button01))
    	{
    		i=0;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button02))
    	{
    		i=1;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button03))
    	{
    		i=2;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button04))
    	{
    		i=3;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button05))
    	{
    		i=4;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button06))
    	{
    		i=5;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button07))
    	{
    		i=6;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.Button08))
    	{
    		i=7;
    		play(i,event);
    	}
    	else if(v==findViewById(R.id.menu))
    	{
    		//switch to menu page
    		Intent mainIntent = new Intent(mainActivityClass, MenuActivity.class);
			startActivity(mainIntent);
    		
    	}
    	else if(v==findViewById(R.id.kit1))
    	{
    		
    	}
    	else if(v==findViewById(R.id.kit2))
    	{
    		
    	}
    	else if(v==findViewById(R.id.kit3))
    	{
    		
    	}
    	
        return false;
    }
}