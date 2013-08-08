package com.example.nativeapp;

import java.util.*;
import java.util.concurrent.*;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.*;


public class SequencerActivity extends Activity implements OnTouchListener {

	private SoundPool soundPool;
    private int[] soundID=new int[8];
    boolean loaded = false;
    private int i=0;
    private int j=0;
    private int bpmVal=500;
    private ScheduledExecutorService timer;
    private Button padSel;
    private Button prevPad=null;//initial call sets padSel to null
    private boolean[] SoundOne=new boolean[16];
    private boolean[] SoundTwo=new boolean[16];
    private boolean[] SoundThree=new boolean[16];
    private boolean[] SoundFour=new boolean[16];
    private boolean[] SoundFive=new boolean[16];
    private boolean[] SoundSix=new boolean[16];
    private boolean[] SoundSeven=new boolean[16];
    private boolean[] SoundEight=new boolean[16];
   
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//onTouch listener on each button
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
        SeekBar seekBpm = (SeekBar)findViewById(R.id.bpm); 
        final TextView bpm = (TextView)findViewById(R.id.bpmText);
        seekBpm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        { 
        	   @Override 
        	   public void onProgressChanged(SeekBar seekBpm, int progress, boolean fromUser) 
        	   {
        		/**progress represents the bpm**/
        		progress+=60;
        		bpmVal=(int)500*progress/120;
        	    // TODO Auto-generated method stub 
        	    bpm.setText(String.valueOf(progress)); 
        	   } 

        	   @Override 
        	   public void onStartTrackingTouch(SeekBar seekBar) { 
        	    // TODO Auto-generated method stub 
        	   } 

        	   @Override 
        	   public void onStopTrackingTouch(SeekBar seekBar) { 
        	    // TODO Auto-generated method stub 
        	   } 
        });

        
        // Set the hardware buttons to control the music
        // this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // Load the sound
        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        load();//function calls the method that will load the sounds
	}

	public void load()
    {
		//all the sounds are loaded in advance
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
	
	//plays a sound and selects the corresponding pad
	 public void play(int i, View v, MotionEvent event)
	    {
		 	padSel=prevPad;//prevPad is null, then the colored pad
	    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
	            if (loaded) {
	                soundPool.play(soundID[i], 1, 1, 1, 0, 1f);
	            }
	            prevPad=((Button)v);
	            (prevPad).setBackgroundColor(Color.BLUE);
	    		if(padSel!=null&&padSel!=prevPad)
	    			padSel.setBackgroundResource(android.R.drawable.btn_default);//button slightly changes
	            
	        }
	    }
	    //onTouch calls a function each time a button is clicked
	    @Override
	    public boolean onTouch(View v, MotionEvent event) 
	    {
	    	//pad buttons set the value of i (sound that will be played)
	    	if(v==findViewById(R.id.pad1))
	    	{
	    		i=0;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad2))
	    	{
	    		i=1;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad3))
	    	{
	    		i=2;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad4))
	    	{
	    		i=3;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad5))
	    	{
	    		i=4;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad6))
	    	{
	    		i=5;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad7))
	    	{
	    		i=6;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.pad8))
	    	{
	    		i=7;
	    		play(i,v,event);
	    	}
	    	else if(v==findViewById(R.id.menu))
	    	{
	    		//Intent mainIntent = new Intent(sequencerActivityClass, SequencerActivity.class);
				//startActivity(mainIntent);
	    	}
	    	else if(v==findViewById(R.id.kit1))
	    	{
	    		//implement loadKit method
	    	}
	    	else if(v==findViewById(R.id.kit2))
	    	{
	    		
	    	}
	    	else if(v==findViewById(R.id.kit3))
	    	{
	    		
	    	}
	    	else if(v==findViewById(R.id.playPause))
	    	{
	    		//starts and cancels sequencer. some issues to fix
	    		boolean on = ((ToggleButton) v).isChecked();

	    		if(on)
	    			seqPause();
	    		else
	    			seqPlay();
	    	}
	    	//each time division calls the setPlay() method with its corresponding position in the soundDiv array
	    	if(padSel!=null)
	    	{
	    		if(v==findViewById(R.id.timeDiv1))
	    			setPlay(0);
	    		else if(v==findViewById(R.id.timeDiv2))
	    			setPlay(1);
	    		else if(v==findViewById(R.id.timeDiv3))
	    			setPlay(2);
	    		else if(v==findViewById(R.id.timeDiv4))
	    			setPlay(3);
	    		else if(v==findViewById(R.id.timeDiv5))
	    			setPlay(4);
	    		else if(v==findViewById(R.id.timeDiv6))
	    			setPlay(5);
	    		else if(v==findViewById(R.id.timeDiv7))
	    			setPlay(6);
	    		else if(v==findViewById(R.id.timeDiv8))
	    			setPlay(7);
	    		else if(v==findViewById(R.id.timeDiv9))
	    			setPlay(8);
	    		else if(v==findViewById(R.id.timeDiv10))
	    			setPlay(9);
	    		else if(v==findViewById(R.id.timeDiv11))
	    			setPlay(10);
	    		else if(v==findViewById(R.id.timeDiv12))
	    			setPlay(11);
	    		else if(v==findViewById(R.id.timeDiv13))
	    			setPlay(12);
	    		else if(v==findViewById(R.id.timeDiv14))
	    			setPlay(13);
	    		else if(v==findViewById(R.id.timeDiv15))
	    			setPlay(14);
	    		else if(v==findViewById(R.id.timeDiv16))
	    			setPlay(15);
	    	}
	        return false;
	    }
	    
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sequencer, menu);
		return true;
	}

	
	//for each divPlay (changed in timer) checks which sounds will be played, this is where we need to start threading, we'll need to
	//synchronize them with the timer
	public void setPlay(int divPlay)
	{
		System.out.println("i="+i+" is this function called?");
		switch(i)
		{
			case 0: SoundOne[divPlay]=!SoundOne[divPlay];
			break;
			case 1: SoundTwo[divPlay]=!SoundTwo[divPlay];
			break;
			case 2: SoundThree[divPlay]=!SoundThree[divPlay];
			break;
			case 3: SoundFour[divPlay]=!SoundFour[divPlay];
			break;
			case 4: SoundFive[divPlay]=!SoundFive[divPlay];
			break;
			case 5: SoundSix[divPlay]=!SoundSix[divPlay];
			break;
			case 6: SoundSeven[divPlay]=!SoundSeven[divPlay];
			break;
			case 7: SoundEight[divPlay]=!SoundEight[divPlay];
			break;
		}
	}
	
	//Pauses the timer and the sequence, does not work yet for some reason
	public void seqPause()
	{
		timer.shutdownNow();
	}
	
	//creates a new scheduledthreadpoolexecutor, previously timer every time the play button is pressed, should be canceled on pause
	public void seqPlay()
	{
		timer=Executors.newScheduledThreadPool(2);
		timer.scheduleAtFixedRate(new Runnable() 
		{
			
		      public void run() 
		      {
		    	  System.out.println(SoundOne[j]+","+SoundTwo[j]+","+SoundEight[j]);
		    	  if(SoundOne[j])
		    		  soundPool.play(soundID[0], 1, 1, 1, 0, 1f);
		    	  if(SoundTwo[j])
		    		  soundPool.play(soundID[1], 1, 1, 1, 0, 1f);
		    	  if(SoundThree[j])
		    		  soundPool.play(soundID[2], 1, 1, 1, 0, 1f);
		    	  if(SoundFour[j])
		    		  soundPool.play(soundID[3], 1, 1, 1, 0, 1f);
		    	  if(SoundFive[j])
		    		  soundPool.play(soundID[4], 1, 1, 1, 0, 1f);
		    	  if(SoundSix[j])
		    		  soundPool.play(soundID[5], 1, 1, 1, 0, 1f);
		    	  if(SoundSeven[j])
		    		  soundPool.play(soundID[6], 1, 1, 1, 0, 1f);
		    	  if(SoundEight[j])
		    		  soundPool.play(soundID[7], 1, 1, 1, 0, 1f);
		    	  ++j;
		    	  if(j==16)
		    		  j=0;
		    	  
		      }
		}, 0, bpmVal, TimeUnit.MILLISECONDS);
		
	}
	
}
	
