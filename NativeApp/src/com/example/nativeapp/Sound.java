package com.example.nativeapp;
//add previous to config.xml


import android.media.SoundPool;
import android.media.AudioManager;

public class Sound
{

	private SoundPool kit=new SoundPool(4, AudioManager.STREAM_MUSIC, 0 );
	private int sounds;
	
	/*public static loadKit()
	{
		if(action=="loadKit")
		{
			String kitnb=args.getString(0);
			loadKit(kitnb);
		}
		else if(action=="playSound")
		{
			int i=args.getInt(0);
			kit.play(sounds[i],1,1,0,0,1);
		}
	}*/

	public void loadKit()
	{
		for(int i=0;i<=8;++i)
		{
			String myUri="/android_asset/www/kit/cc";
			sounds=kit.load(myUri, 1);
			kit.play(sounds,1,1,0,0,1);
		}
	}	
}