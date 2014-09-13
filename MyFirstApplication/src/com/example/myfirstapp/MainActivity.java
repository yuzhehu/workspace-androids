package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.Collections;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends ActionBarActivity {
	private static final int IMAGE_PICKER_SELECT = 999;	
	private static final String TAG = MainActivity.class.getName();
	// this is like the main method of java, creates the view.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// restore saved state
		super.onCreate(savedInstanceState);
		// set content view
		setContentView(R.layout.my_view);
		// initialize UI elements
		// this is the text i created
		final TextView text = (TextView) findViewById(R.id.myText);
		// this is the button i had earlier
		final Button maButton = (Button) findViewById(R.id.my_button);
	    final ImageView image = (ImageView) findViewById(R.id.myImage);  
		/*
		 * set button listener to change text written
		 * maButton.setOnClickListener(new Button.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * try{ text.setText("u clicked the button"); }catch(Exception e){
		 * System.out.println("exception"); } }
		 * 
		 * });
		 */
		maButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				//image.setVisibility(View.VISIBLE);
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i,IMAGE_PICKER_SELECT);
			}
		});
		/*
		maButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
			
			//set the button listener to open web (Uri.parse("url here"))
				/*
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.google.com"));
				startActivity(browserIntent);*/
		
			//set the button listener to toggle visibility of the image viewer
				/*
				if(image.getVisibility() == View.VISIBLE)
				image.setVisibility(View.INVISIBLE);
				else
					image.setVisibility(View.VISIBLE);
			    	
			}
		});
		*/
	}
	
	
	
	//automtically called after an activity is invoked, in this case, the request code is the image picker select, result is ok,
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == IMAGE_PICKER_SELECT && resultCode == Activity.RESULT_OK && data != null)
		{
			//get the intent data
			Uri selectedImage = data.getData();
			//create an array of file paths
			String[]filePathColumn = {MediaStore.Images.Media.DATA};
			//create cursor
			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null,null);
			//move cursor to first target
			cursor.moveToFirst();
			//get the index of the cursor's first index in file path arrays
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			//store the file path in a string
			String picturePath = cursor.getString(columnIndex);
			//close the cursor
			cursor.close();
			
			
			//create ImageView obj
			ImageView image = (ImageView) findViewById(R.id.myImage2);
			
			//testing fit to screen
			image.setScaleType(ScaleType.FIT_XY);
			
			//set it to the bitmap and decode the file using the file path.
			image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			
			
			
			
			//breaking shit up
			ArrayList<Bitmap> mapChunks = breakItUp(BitmapFactory.decodeFile(picturePath), 9);
			/*
			MainActivity activity = (MainActivity)getActivity();
			Bitmap bitmap = getBitmapFromCameraData(data,activity);
			image.setImageBitmap(bitmap);*/
		}
	}
	
	public ArrayList<Bitmap> breakItUp(Bitmap m, int chunks)
	{
		ArrayList <Bitmap> mapChunks = new ArrayList<Bitmap>();
		int cols, rows;
	    cols = rows = (int) Math.sqrt(chunks);
		int chunkHeight = m.getHeight()/rows;
		int chunkWidth = m.getWidth()/cols;
		
		for(int i = 0; i < rows; i ++)
		{
			for(int j = 0; j < cols;j++)
			{
				mapChunks.add(Bitmap.createBitmap(m, i*chunkWidth, j*chunkHeight, chunkWidth, chunkHeight ));
			}
		}
		Collections.shuffle(mapChunks); 
		return mapChunks;
	}
	

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.i(TAG, "The activity is visble and about to be started.");
	}
	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.i(TAG, "The activity is visible and about to be restarted");
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		Log.i(TAG, "The activity is and has focus (it is now \"Resumed\")");
	}
	@Override
	protected void onPause()
	{
		super.onPause();
		Log.i(TAG, "Another activity is taking focus");
	}
	@Override
	protected void onStop()
	{
		super.onStop();
		Log.i(TAG,"The acitvity is no longer visible" );
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.i(TAG, "The activity is about to be destoyed");
	}
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
