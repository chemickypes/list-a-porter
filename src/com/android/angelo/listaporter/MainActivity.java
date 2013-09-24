package com.android.angelo.listaporter;

import java.text.ParseException;
import java.util.ArrayList; 

import com.android.angelo.usedobject.ListItem;

import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;

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
		mAdapter = new ListAdapter(this, mData);
		setListAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
