package com.example.perkusista;


import java.util.concurrent.locks.LockSupport;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;


public class Perkusista extends Activity //parent Activity
{
	static Thread playRythmThread = null; 
	public static MediaPlayer mediaSource[] = new MediaPlayer[8]; //tablica do wype�nienia referencjami do naszych dzwi�k�w
	private static int mediaReferences[] = {R.raw.a3,R.raw.a2,R.raw.a1,R.raw.a4,R.raw.a5,R.raw.a6,R.raw.a7,R.raw.a8}; //tablica z id d�wi�k�w
	static ImageButton imgBtn1,imgBtn2,imgBtn3,imgBtn4,imgBtn5,imgBtn6,imgBtn7,imgBtn8; // przyciski b�bn�w
	public static boolean interruptFlag; //flaga za pomoc� kt�rej przerywamy dzia�anie danego w�tku 
	Animation anim = null; // pole naszej animacji
	
	public boolean onCreateOptionsMenu(Menu menu) //Wywo�anie menu opcji + dodawanie element�w do action bara
	{
		getMenuInflater().inflate(R.menu.perkusista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) //obs�uga menu gdy jest wybrana pozycja w menu opcji.
	{
		int id = item.getItemId(); 
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) // metoda wywo�ywana na pocz�tku ka�dego uruchomienia inicjalizuj�ca elementy aplikacji
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perkusista); //wybranie layoutu
		
		mediaSource[0] = MediaPlayer.create(this, mediaReferences[0]); //tworzenie element�w typu Media Player i przypisanie d�wi�k�w do tablicy
		mediaSource[1] = MediaPlayer.create(this, mediaReferences[1]);
		mediaSource[2] = MediaPlayer.create(this, mediaReferences[2]);
		mediaSource[3] = MediaPlayer.create(this, mediaReferences[3]);
		mediaSource[4] = MediaPlayer.create(this, mediaReferences[4]);
		mediaSource[5] = MediaPlayer.create(this, mediaReferences[5]);
		mediaSource[6] = MediaPlayer.create(this, mediaReferences[6]);
		mediaSource[7] = MediaPlayer.create(this, mediaReferences[7]);
		
		imgBtn1 = (ImageButton) findViewById(R.id.snare); //przypisanie przycisk�w zdefiniowanych w layout'cie layoutu
		imgBtn2 = (ImageButton) findViewById(R.id.floortom);
		imgBtn3 = (ImageButton) findViewById(R.id.tom2);
		imgBtn4 = (ImageButton) findViewById(R.id.tom3);
		imgBtn5 = (ImageButton) findViewById(R.id.ride1);
		imgBtn6 = (ImageButton) findViewById(R.id.tom1);
		imgBtn7 = (ImageButton) findViewById(R.id.hi_hat1);
		imgBtn8 = (ImageButton) findViewById(R.id.crash1);
		
		anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha); //przypisanie animacji
		
		addListenerOnButton(); // dodanie nas�uchu na przyciski b�bn�w
	}

	
	public void playSound01(View v) // metoda odtwarzaj�ca d�wi�k po klikni�ciu na przycisk b�bna
	{
		int id = Integer.parseInt((String) v.getTag()); //pobieranie id z tagu zamieszczonego w layoucie przy pomocy interefejsu View
		System.out.println(id);
		if (mediaSource[id].isPlaying() == true)
			{
				mediaSource[id].pause();
				mediaSource[id].seekTo(0);
			}
		mediaSource[id].start();
	}
	
	public void showMetronomMenu(View v) //wywo�anie activity metronomu
	{
		Intent i = new Intent(getBaseContext(),MetronomActivity.class);                      
		startActivity(i);
	}
	
	@SuppressLint("NewApi")
	public static void playRythm1(View v) //odtworzenie rytmu nr 1 w tutorialu
	{
		interruptFlag=false;
		playRythmThread= new Thread(new Runnable() //tworzenie w�tku odtwarzaj�cego nasz przyk�adowy rytm 
		{
			public void run() 
			{	
				while (interruptFlag==false)
				{
					for(int i=0;i<4;i++)
					 {
						imgBtn3.callOnClick(); //wywo�anie przycisni�cia danego b�bna/talerza wraz z animacj�
						LockSupport.parkNanos(500 * 1000000); //funkcja stopuj�ca w�tek na dany czas
						imgBtn7.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
						imgBtn1.callOnClick();
						LockSupport.parkNanos(800 * 1000000);
					 }
				};
			}
		});
		playRythmThread.start();
	}
	
	@SuppressLint("NewApi")
	public static void playRythm2(View v) //Rytm 2 tutorialu
	{
		interruptFlag=false;
		playRythmThread= new Thread(new Runnable() 
		{
			public void run() 
			{	
				while (interruptFlag==false)
				{
					for(int i=0;i<4;i++)
					 {
						imgBtn3.callOnClick();
						imgBtn7.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
						imgBtn1.callOnClick();
						LockSupport.parkNanos(800 * 1000000);
					 }
				};
			}
		});
		
		playRythmThread.start();
	}
	
	@SuppressLint("NewApi")
	public static void playRythm3(View v) //Rytm 3 tutorialu
	{
		interruptFlag=false;
		playRythmThread= new Thread(new Runnable() 
		{
			public void run() 
			{	
				while (interruptFlag==false)
				{
					for(int i=0;i<4;i++)
					 {
						imgBtn3.callOnClick();
						imgBtn8.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
						imgBtn7.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
						imgBtn7.callOnClick();
						imgBtn1.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
						imgBtn7.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
						imgBtn7.callOnClick();
						imgBtn8.callOnClick();
						LockSupport.parkNanos(500 * 1000000);
					 }
				};
			}
		});
		
		playRythmThread.start();
	}
	
	OnClickListener ocl = new OnClickListener() // lisener w kt�rym nas�uchujemy przucisni�cia danego b�bna/talerza, odtwarzaj�cy animacje
	{

		@Override
		public void onClick(final View v) 
		{
			runOnUiThread(new Runnable() 
			{
			     @Override
			     public void run() 
			     {
			    	 v.startAnimation(anim);
			     }
			});
			playSound01(v);
		}
	};
	
	public void addListenerOnButton() //dodanie nas�uchu do przycisk�w 
	{
		imgBtn1.setOnClickListener(ocl);
		imgBtn2.setOnClickListener(ocl);
		imgBtn3.setOnClickListener(ocl);
		imgBtn4.setOnClickListener(ocl);
		imgBtn5.setOnClickListener(ocl);
		imgBtn6.setOnClickListener(ocl);
		imgBtn7.setOnClickListener(ocl);
		imgBtn8.setOnClickListener(ocl);
	}
	 
}
