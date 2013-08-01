package com.example.nativeapp;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


public class SequencerActivity extends Activity implements OnTouchListener {

	private SoundPool soundPool;
    private int[] soundID=new int[8];
    boolean loaded = false;
    private int i=0;
    private boolean[] timeDivs=new boolean[16];
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sequencer);
		View a = findViewById(R.id.pad1);
        a.setOnTouchListener(this);
        View b = findViewById(R.id.pad2);
        b.setOnTouchListener(this);
        View c = findViewById(R.id.pad3);
        c.setOnTouchListener(this);
        View d = findViewById(R.id.pad4);
        d.setOnTouchListener(this);
        View e = findViewById(R.id.pad5);
        e.setOnTouchListener(this);
        View f = findViewById(R.id.pad6);
        f.setOnTouchListener(this);
        View g = findViewById(R.id.pad7);
        g.setOnTouchListener(this);
        View h = findViewById(R.id.pad8);
        h.setOnTouchListener(this);
        View i = findViewById(R.id.kit1);
        i.setOnTouchListener(this);
        View j = findViewById(R.id.kit2);
        j.setOnTouchListener(this);
        View k = findViewById(R.id.kit3);
        k.setOnTouchListener(this);
        View l = findViewById(R.id.menu);
        l.setOnTouchListener(this);
        View m = findViewById(R.id.playPause);
        m.setOnTouchListener(this);
        View t1 = findViewById(R.id.timeDiv1);
        t1.setOnTouchListener(this);
        View t2 = findViewById(R.id.timeDiv2);
        t2.setOnTouchListener(this);
        View t3 = findViewById(R.id.timeDiv3);
        t3.setOnTouchListener(this);
        View t4 = findViewById(R.id.timeDiv4);
        t4.setOnTouchListener(this);
        View t5 = findViewById(R.id.timeDiv5);
        t5.setOnTouchListener(this);
        View t6 = findViewById(R.id.timeDiv6);
        t6.setOnTouchListener(this);
        View t7 = findViewById(R.id.timeDiv7);
        t7.setOnTouchListener(this);
        View t8 = findViewById(R.id.timeDiv8);
        t8.setOnTouchListener(this);
        View t9 = findViewById(R.id.timeDiv9);
        t9.setOnTouchListener(this);
        View t10 = findViewById(R.id.timeDiv10);
        t10.setOnTouchListener(this);
        View t11 = findViewById(R.id.timeDiv11);
        t11.setOnTouchListener(this);
        View t12 = findViewById(R.id.timeDiv12);
        t12.setOnTouchListener(this);
        View t13 = findViewById(R.id.timeDiv13);
        t13.setOnTouchListener(this);
        View t14 = findViewById(R.id.timeDiv14);
        t14.setOnTouchListener(this);
        View t15 = findViewById(R.id.timeDiv15);
        t15.setOnTouchListener(this);
        View t16 = findViewById(R.id.timeDiv16);
        t16.setOnTouchListener(this);
        
        // Set the hardware buttons to control the music
        // this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // Load the sound
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        load();
	}

	public void load()
    {
    	soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                    int status) {
                loaded = true;
            }
        });
    	soundID[0] = soundPool.load(this, R.raw.kick, 1);
        soundID[1] = soundPool.load(this, R.raw.snare, 1);
	  	soundID[2] = soundPool.load(this, R.raw.clap, 1);
  		soundID[3] = soundPool.load(this, R.raw.cc, 1);
  		soundID[4] = soundPool.load(this, R.raw.riffe, 1);
  		soundID[5] = soundPool.load(this, R.raw.riffc, 1);
  		soundID[6] = soundPool.load(this, R.raw.riffa, 1);
  		soundID[7] = soundPool.load(this, R.raw.riffd4, 1);
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
	                soundPool.play(soundID[i], 1, 1, 1, 0, 1f);
	            }
	        }
	    }
	    
	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
	    	if(v==findViewById(R.id.pad1))
	    	{
	    		i=0;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad2))
	    	{
	    		i=1;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad3))
	    	{
	    		i=2;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad4))
	    	{
	    		i=3;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad5))
	    	{
	    		i=4;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad6))
	    	{
	    		i=5;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad7))
	    	{
	    		i=6;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.pad8))
	    	{
	    		i=7;
	    		play(i,event);
	    	}
	    	else if(v==findViewById(R.id.menu))
	    	{
	    		//Intent mainIntent = new Intent(sequencerActivityClass, SequencerActivity.class);
				//startActivity(mainIntent);
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
	    	else if(v==findViewById(R.id.timeDiv1))
	    		timeDivs[0]=!timeDivs[0];
	    	else if(v==findViewById(R.id.timeDiv2))
	    		timeDivs[1]=!timeDivs[1];
	    	else if(v==findViewById(R.id.timeDiv3))
	    		timeDivs[2]=!timeDivs[2];
	    	else if(v==findViewById(R.id.timeDiv4))
	    		timeDivs[3]=!timeDivs[3];
	    	else if(v==findViewById(R.id.timeDiv5))
	    		timeDivs[4]=!timeDivs[4];
	    	else if(v==findViewById(R.id.timeDiv6))
	    		timeDivs[5]=!timeDivs[5];
	    	else if(v==findViewById(R.id.timeDiv7))
	    		timeDivs[6]=!timeDivs[6];
	    	else if(v==findViewById(R.id.timeDiv8))
	    		timeDivs[7]=!timeDivs[7];
	    	else if(v==findViewById(R.id.timeDiv9))
	    		timeDivs[8]=!timeDivs[8];
	    	else if(v==findViewById(R.id.timeDiv10))
	    		timeDivs[9]=!timeDivs[8];
	    	else if(v==findViewById(R.id.timeDiv11))
	    		timeDivs[10]=!timeDivs[10];
	    	else if(v==findViewById(R.id.timeDiv12))
	    		timeDivs[11]=!timeDivs[11];
	    	else if(v==findViewById(R.id.timeDiv13))
	    		timeDivs[12]=!timeDivs[12];
	    	else if(v==findViewById(R.id.timeDiv14))
	    		timeDivs[13]=!timeDivs[13];
	    	else if(v==findViewById(R.id.timeDiv15))
	    		timeDivs[14]=!timeDivs[14];
	    	else if(v==findViewById(R.id.timeDiv16))
	    		timeDivs[15]=!timeDivs[15];
	    	
	        return false;
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sequencer, menu);
		return true;
	}
	
	

}
