package com.android.angelo.listaporter;

import java.text.ParseException;
import java.util.ArrayList; 

import com.android.angelo.usedobject.ListItem;
import com.android.angelo.widget.ListAdapter;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements ListView.OnItemClickListener {

	ArrayList<ListItem> mData; 
	ListAdapter mAdapter;
	DrawerLayout mDrawer;
	ListView mDrawerList;
	ActionBarDrawerToggle mActionBarDrawerToggle;
	 
	String[] mStringVector;
	CharSequence mTitle;
	CharSequence mDrawerTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitle = getTitle();
		mDrawerTitle = mTitle + " Drawer";
        
		setNavigationDrawer();
		setDataArrayList();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
		
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

	
	@Override 
	public void onResume(){
		super.onResume();
		boolean orientation = (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)?
				true:false;
		mAdapter = new ListAdapter(this, mData,orientation);
		setListAdapter(mAdapter);
	}
	
	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "DrawerList item clicked: "+mStringVector[position],
        		Toast.LENGTH_LONG).show();
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
		if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }else{ 
	        	switch (item.getItemId()) {
	        		case R.id.action_settings:
	        			openSettings();
	        		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        	}
	        }
	}
	
	 @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view	        
	        return super.onPrepareOptionsMenu(menu);
	    }
	
	private void openSettings(){
		Intent intent = new Intent();
        intent.setClass(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, 0); 
	}
	
	private void setNavigationDrawer(){
		 mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	     mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.drawable.ic_drawer, 
	        		R.string.drawer_open, R.string.drawer_close){
	        	/** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };
	     mDrawer.setDrawerListener(mActionBarDrawerToggle);
	     mDrawer.setDrawerListener(mActionBarDrawerToggle);
	     mDrawerList = (ListView) findViewById(R.id.left_drawer);
	        
	     mStringVector = getResources().getStringArray(R.array.toast_string);
	        
	     mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,mStringVector));
	     mDrawerList.setOnItemClickListener(this);
	        
	        
	}
	
	//make a static list
	private void setDataArrayList(){
		mData = new ArrayList<ListItem>();
		for(int i = 1;i<11;i++){
			
			try {
				mData.add(new ListItem("Item qqqqqqqqqqqqqqqqqqqqqqqqqqqq "+i, "Object Item eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee #"+i, 
						"22/03/2013", "100.182739273737265541"));
				Log.d("added_item", "item add "+i);
			} catch (ParseException e) {
				Log.e("nodata", "nodata");
			}
			
		}
		Log.d("size data", ""+mData.size());
	}

}
