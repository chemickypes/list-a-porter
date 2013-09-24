package com.android.angelo.listaporter;

import java.text.ParseException;
import java.util.ArrayList; 

import com.android.angelo.usedobject.ListItem;
import com.android.angelo.widget.ListAdapter;


import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {

	ArrayList<ListItem> mData; 
	ListAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mData = new ArrayList<ListItem>();
		for(int i = 1;i<11;i++){
			
			try {
				mData.add(new ListItem("Item "+i, "Object Item #"+i, 
						"22/03/2013", "100.182739273737265541"));
				Log.d("added_item", "item add "+i);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Log.e("nodata", "nodata");
			}
			
		}
		Log.d("size data", ""+mData.size());
		
	}
	@Override 
	public void onResume(){
		super.onResume();
		mAdapter = new ListAdapter(this, mData);
		setListAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void openSettings(){
		Intent intent = new Intent();
        intent.setClass(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, 0); 
	}

}
