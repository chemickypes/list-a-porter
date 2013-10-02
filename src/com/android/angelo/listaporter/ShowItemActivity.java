package com.android.angelo.listaporter;

import com.android.angelo.usedobject.ListItem;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class ShowItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_item);
		// Show the Up button in the action bar.
		setupActionBar();
		
		ShowItemFragment frag = (ShowItemFragment) getFragmentManager().findFragmentById(R.id.fr_show_item_to_activity);
		Bundle bun = getIntent().getExtras();
		ListItem item = (ListItem) bun.getParcelable("item");
		frag.setInfo(item);
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_notify:
			setNotification();
		return true;	
		
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private void setNotification(){
		// Prepare intent which is triggered if the
		// notification is selected

		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Build notification  
		// Actions are just fake
		Notification.Builder mNotifyBuilder = new Notification.Builder(this)
		        .setContentTitle("New mail from " + "test@gmail.com")
		        .setContentText("Subject")
		        .setContentIntent(pIntent)
		        .setSmallIcon(R.drawable.ic_not_social_person)
		        .setNumber(1);
		    
		  
		NotificationManager notificationManager = 
		  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Notification not = mNotifyBuilder.build();
		not.defaults |= Notification.DEFAULT_ALL;

		notificationManager.notify(0, not); 
		
		
		
		
	}

}
