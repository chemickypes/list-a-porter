package com.android.angelo.listaporter;

import java.text.ParseException;
import java.util.ArrayList; 

import com.android.angelo.usedobject.ListItem;
import com.android.angelo.widget.ListAdaprterLikeGP;
import com.android.angelo.widget.ListAdapter;
import com.android.angelo.usedobject.UndoBarController; 

import android.os.Bundle;
import android.os.Parcelable;
import android.app.ListActivity;
import android.content.Context;
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

public class MainActivity extends ListActivity implements ListView.OnItemClickListener,
														ListView.OnItemLongClickListener,
														UndoBarController.UndoListener{

	
	private class DrawerListener implements ListView.OnItemClickListener{
		String[] mStringVector;
		Context mContext;

		public DrawerListener(String[] mStringVector, Context context) {
			this.mStringVector = mStringVector;
			this.mContext = context;
		}

		@Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        Toast.makeText(mContext, "DrawerList item clicked: "+mStringVector[position],
	        		Toast.LENGTH_LONG).show();
	        mDrawer.closeDrawer(mDrawerList);
	    }
	}
		
	ArrayList<ListItem> mData; 
//	ListAdapter mAdapter;
	ListAdaprterLikeGP mAdapter;
	DrawerLayout mDrawer;
	ListView mDrawerList;
	ActionBarDrawerToggle mActionBarDrawerToggle;
	
	UndoBarController mUndoBarController;
	
	 
	String[] mStringVector;
	CharSequence mTitle;
	CharSequence mDrawerTitle;
	boolean isDrawerVisible;
	static boolean isServiceStarted = false;
	
	
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
		
        mUndoBarController = new UndoBarController(findViewById(R.id.undobar), this);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this); 
        

	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        
        try{
    		Bundle bun = getIntent().getExtras();
    		ListItem item = (ListItem) bun.getParcelable("newItem");
    		mData.add(item);
    		}catch(NullPointerException ex){
    			
    		}
        
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

	
	@Override 
	public void onResume(){
		super.onResume();
		boolean orientation = (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)?
				true:false;
		mAdapter = new ListAdaprterLikeGP(this, mData,orientation);
		setListAdapter(mAdapter);
		if(!isServiceStarted) startNotificationService();


	}
	
	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putParcelable("item", mData.get(position));
		intent.putExtras(bundle);
        intent.setClass(MainActivity.this, ShowItemActivity.class);
        startActivityForResult(intent, 0);
    }
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		mUndoBarController.showUndoBar(
                false,
                getString(R.string.undobar_sample_message),
                null);
		return true;
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
	 
	 @Override
	 public void onBackPressed(){
		 if(isDrawerVisible){
			 mDrawer.closeDrawer(mDrawerList);
		 
		 }else{
			 super.onBackPressed();
		 }
	 }
	 
	 @Override
	public void onUndo(Parcelable token) {
		// TODO Auto-generated method stub
			
	}
	
	private void openSettings(){
		Intent intent = new Intent();
        intent.setClass(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, 0); 
	}
	
	private void setNavigationDrawer(){
		 isDrawerVisible = false;
		 mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
	     mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.drawable.ic_drawer, 
	        		R.string.drawer_open, R.string.drawer_close){
	        	/** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                isDrawerVisible = false;
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle(mDrawerTitle);
	                isDrawerVisible = true;
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };
	     mDrawer.setDrawerListener(mActionBarDrawerToggle);
	     mDrawer.setDrawerListener(mActionBarDrawerToggle);
	     mDrawerList = (ListView) findViewById(R.id.left_drawer);
	        
	     mStringVector = getResources().getStringArray(R.array.toast_string);
	        
	     mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,mStringVector));
	     mDrawerList.setOnItemClickListener(new DrawerListener(mStringVector, this));
	        
	        
	}
	
	private void startNotificationService(){
		isServiceStarted = true;
		Intent service = new Intent(this,NotificationService.class);
		startService(service);
	}
	
	
	//make a static list
	private void setDataArrayList(){
		mData = new ArrayList<ListItem>();
		for(int i = 1;i<21;i++){
			
			try {
				mData.add(new ListItem("Item "+i, "Object Item  #"+i, 
						"22/03/2013", "100.182739273737265541"));
				Log.d("added_item", "item add "+i);
			} catch (ParseException e) {
				Log.e("nodata", "nodata");
			}
			
		}
		Log.d("size data", ""+mData.size());
	}
	

	

	

}
