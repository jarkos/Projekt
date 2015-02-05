package com.example.perkusista;

import java.util.concurrent.locks.LockSupport;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MetronomActivity extends Activity 
{
	
	private SeekBar mSeekBar; //pole paska przewijania do ustawiania tempa
	private TextView textView; // pole wyœwietlaj¹ce tempo
	MediaPlayer toc; //pole dŸwiêku metronomu 
	int mediaReferences[] = {R.raw.toc};
	static boolean interruptFlagMetronom; //flaga za pomoc¹ której przerywamy dzia³anie metronomu
	static int tempo=0; //pole okreœlaj¹ce tempo ustawione na pasku 
	static Thread metronomThread; // pole w¹tku metronomu
	int soundID; //id dŸwiêku 
	private static SoundPool soundPool;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) // metoda wywo³ywana na pocz¹tku ka¿dego uruchomienia activity metronomu
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_metronom);//przypisanie layoutu
		
		toc = MediaPlayer.create(this, mediaReferences[0]); //przypisanie dŸwiêku metronomu 
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundID = soundPool.load(this, R.raw.toc, 1); //
		
		mSeekBar = (SeekBar) findViewById(R.id.tempo); //przypisanie paska przewijania do ustawiania tempa
		mSeekBar.setMax(200); //max wartoœæ paska
		textView = (TextView) findViewById(R.id.textView2); //pole textowe wyœwietlaj¹ce aktualne tempo
		textView.setText(mSeekBar.getProgress() + "/" + mSeekBar.getMax()); //ustawienie pola textowego by pobiera³ wartoœæ z paska
		
		mSeekBar.setOnSeekBarChangeListener //lisener zmiany stanu paska
		(new OnSeekBarChangeListener() 
		{
			int progress = 0;
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) 
			{
				textView.setText(progress + "/" + seekBar.getMax());
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) 
			{}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) 
			{
				progress = progresValue;
				tempo = (60 * 1000)/progress; //wartoœæ obliczonych uderzeñ na minute metronomu
			}
		});
		
		
	}
	
	public void startMetro(View v) //metoda tworz¹ca w¹tek metronomu
	{
		if(metronomThread==null)
		{
			interruptFlagMetronom=false;
			metronomThread= new Thread(new Runnable() 
			{
				public void run() 
				{		
					System.out.println("START CLICK");
					do{
							
						soundPool.play(soundID, 1.0f, 1.0f, 0, 0, 1.0f); //odtworzenie dŸwiêku metronomu 
						LockSupport.parkNanos(tempo * 1000000); //stopowaie w¹tku na czas zale¿ny od tempa 
							
					}while (interruptFlagMetronom==false);
				}
			});
			metronomThread.start();
		}
	}
	
	public void stopMetro(View v) //metoda zatrzymuj¹ca w¹tek metronomu i usuwaj¹ca odwo³anie do niego
	{
		if(interruptFlagMetronom==false)
		{
			metronomThread.interrupt();
			metronomThread=null;
			interruptFlagMetronom = true;
			System.out.println("STOP CLICK");
		}
	}
		
	public void playRythm1(View v) //metoda Tutorialu uruchamiaj¹ca g³ówne activity (Perkusiste) i wywo³uj¹ca metode playRythm1 w g³ównym activity
	{
		Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);
	    startActivity(parentActivityIntent);
	    Perkusista.playRythm1(v);
	}
	
	public void playRythm2(View v) 
	{
		Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);
	    startActivity(parentActivityIntent);
	    Perkusista.playRythm2(v);
	}
	
	public void playRythm3(View v)
	{
		Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);
	    startActivity(parentActivityIntent);
	    Perkusista.playRythm3(v);
	}
	
	public void stopRhythm(View v) //zatrzymanie dŸwiêku odtwarzanego w g³ównym activity ( w tutorialu)
	{
		
		Perkusista.interruptFlag=true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) //obs³uga menu gdy jest wybrana pozycja w menu opcji.
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
