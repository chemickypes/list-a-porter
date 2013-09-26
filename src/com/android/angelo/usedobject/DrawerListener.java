package com.android.angelo.usedobject;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DrawerListener implements ListView.OnItemClickListener{
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
    }

}
