package com.example.loginandpass;

import java.util.ArrayList;
import java.util.Collections;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends ActionBarActivity {

	private boolean boomed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// default stuff
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// used for getting text from EditText
		final Resources res = getResources();
		// added button for use
		final Button b = (Button) findViewById(R.id.button);
		// added texts for use
		final EditText u = (EditText) findViewById(R.id.username);
		final EditText p = (EditText) findViewById(R.id.password);
		// setting the button on click listener
		b.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (boomed) {
					Toast.makeText(getApplicationContext(),
							R.string.judgment_toast, Toast.LENGTH_LONG).show();
				} else {
					// if the text from u and p matches the ones in database
					if (u.getText()
							.toString()
							.equalsIgnoreCase(res.getString(R.string.user_name))
							&& p.getText().toString()
									.equals(res.getString(R.string.pass_word))) {
						// if correct, shows it
						Toast.makeText(getApplicationContext(),
								"GET THE FUCK IN", Toast.LENGTH_LONG).show();

						// creating a new intent to call the
						// WelcomeBackNiqqaActivityClass
						Intent i = new Intent(MainActivity.this,
								WelcomeBackNiqqaActivity.class);
						// creating a string to be passed to that class
						String s = "this info is passed from MainActivity class";
						// adding the string to the intent to be passed, first
						// parameter is the Name of the added string to be
						// referenced, the second parameter is the actual string
						i.putExtra(WelcomeBackNiqqaActivity.ACCESS_NAME, s);
						// starting activity in the WelcomeBackNiqqaActivity and
						// w8ing for result to come back after that activity has
						// ended
						startActivityForResult(i, 0);
					}

					else {
						// GTFO
						Toast.makeText(getApplicationContext(),
								"GET THE FUCK OUT", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		boomed = data.getBooleanExtra(
				WelcomeBackNiqqaActivity.BOOMED_ACCESS_NAME, false);
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
