package com.example.perkusista;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.view.View;

//import android.widget.Button;
import android.app.Activity;
import android.widget.ImageButton;



public class Perkusista extends Activity 
{
	
//	private Button button1;
//	private Button button2;
//	private Button button3;
//	private Button button4;
//	private Button button5;
//	private Button button6;
//	private Button button7;
//	private Button button8;
	
	private MediaPlayer mediaSource[] = new MediaPlayer[8];
	private static int mediaReferences[] = {R.raw.a3,R.raw.a2,R.raw.a1,R.raw.a4,R.raw.a5,R.raw.a6,R.raw.a7,R.raw.a8};
	
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) 
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_perkusista);
//		
//		button1 = (Button) findViewById(R.id.button1);
//		mediaSource[0] = MediaPlayer.create(this, mediaReferences[0]);
//		mediaSource[1] = MediaPlayer.create(this, mediaReferences[1]);
//		mediaSource[2] = MediaPlayer.create(this, mediaReferences[2]);
//		mediaSource[3] = MediaPlayer.create(this, mediaReferences[3]);
//		mediaSource[4] = MediaPlayer.create(this, mediaReferences[4]);
//		mediaSource[5] = MediaPlayer.create(this, mediaReferences[5]);
//		mediaSource[6] = MediaPlayer.create(this, mediaReferences[6]);
//		mediaSource[7] = MediaPlayer.create(this, mediaReferences[7]);
//
//	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.perkusista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
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
	
	public void playSound01(View v)
	{
		int id = Integer.parseInt((String) v.getTag());
		System.out.println(id);
		if (mediaSource[id].isPlaying() == true)
			{
				mediaSource[id].pause();
				mediaSource[id].seekTo(0);
			}
		mediaSource[id].start();
//		Integer.parseInt((String) v.getTag());
//		System.out.println(v.getTag());
		
	}
	
	
// 	
	ImageButton imageButton;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perkusista); 
//		
		
//		button1 = (Button) findViewById(R.id.button1);
		mediaSource[0] = MediaPlayer.create(this, mediaReferences[0]);
		mediaSource[1] = MediaPlayer.create(this, mediaReferences[1]);
		mediaSource[2] = MediaPlayer.create(this, mediaReferences[2]);
		mediaSource[3] = MediaPlayer.create(this, mediaReferences[3]);
		mediaSource[4] = MediaPlayer.create(this, mediaReferences[4]);
		mediaSource[5] = MediaPlayer.create(this, mediaReferences[5]);
		mediaSource[6] = MediaPlayer.create(this, mediaReferences[6]);
		mediaSource[7] = MediaPlayer.create(this, mediaReferences[7]);
		
//		addListenerOnButton();
	}
 
//	public void addListenerOnButton() {
// 
//		imageButton = (ImageButton) findViewById(R.id.crash1);
// 
//		imageButton.setOnClickListener(new OnClickListener() {
// 
	
			public void onClick(View v) 
			{
				playsound(v);
			}
			
			public void playsound(View v)
			{
				int id = Integer.parseInt((String) v.getTag());
				System.out.println(id);
				if (mediaSource[id].isPlaying() == true)
					{
						mediaSource[id].pause();
						mediaSource[id].seekTo(0);
					}
				mediaSource[id].start();
			}
 
//		});
 
	
}
