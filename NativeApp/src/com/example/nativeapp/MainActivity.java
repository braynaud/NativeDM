package com.example.nativeapp;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.content.res.AssetManager;
 
public class MainActivity extends Activity implements OnTouchListener {
    private SoundPool soundPool;
    AssetManager am = this.getAssets();
    private int[] soundID=new int[8];
    boolean loaded = false;
    private int i=0;
    
 
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
         // Set the hardware buttons to control the music
        // this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
         // Load the sound
         soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
         soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
             @Override
             public void onLoadComplete(SoundPool soundPool, int sampleId,
                     int status) {
                 loaded = true;
             }
         });
       /* try {
			soundID[0] = soundPool.load(am.openFd("kit/kick.wav"), 1);
			soundID[1] = soundPool.load(am.openFd("kit/snare.wav"), 1);
	  		soundID[2] = soundPool.load(am.openFd("kit/clap.wav"), 1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
  		soundID[3] = soundPool.load(this, R.raw.cc, 1);
  		soundID[4] = soundPool.load(this, R.raw.riffe, 1);
  		soundID[5] = soundPool.load(this, R.raw.riffc, 1);
  		soundID[6] = soundPool.load(this, R.raw.riffa, 1);
  		soundID[7] = soundPool.load(this, R.raw.riffd4, 1);
    }
 
    @Override
    public boolean onTouch(View v, MotionEvent event) {
    	if(v==findViewById(R.id.Button01))
    		i=0;
    	else if(v==findViewById(R.id.Button02))
    		i=1;
    	else if(v==findViewById(R.id.Button03))
    		i=2;
    	else if(v==findViewById(R.id.Button04))
    		i=3;
    	else if(v==findViewById(R.id.Button05))
    		i=4;
    	else if(v==findViewById(R.id.Button06))
    		i=5;
    	else if(v==findViewById(R.id.Button07))
    		i=6;
    	else if(v==findViewById(R.id.Button08))
    		i=7;
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Getting the user sound settings
            AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volume = actualVolume / maxVolume;
            if (loaded) {
                soundPool.play(soundID[i], volume, volume, 1, 0, 1f);
            }
        }
        return false;
    }
}