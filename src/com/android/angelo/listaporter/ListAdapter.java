package com.android.angelo.listaporter;

import java.util.ArrayList;

import com.android.angelo.usedobject.ListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private class ViewHolder{
		public TextView nome;
		public TextView desc;
		public TextView data;
		public TextView bigD;
	}
	
	ArrayList<ListItem> data;
	Context mContext;
	LayoutInflater mLayoutInflater;
	ViewHolder mViewHolder;
	
	public ListAdapter(Context context, ArrayList<ListItem> data) {
		this.data = data;
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) 
				this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		mViewHolder = null;
		if(view == null){
			view = mLayoutInflater.inflate(R.layout.list_item_layout, null);
			mViewHolder = new ViewHolder();
			mViewHolder.nome = (TextView) view.findViewById(R.id.textView_Nome);
			mViewHolder.desc = (TextView) view.findViewById(R.id.textView_Desc);
			mViewHolder.data = (TextView) view.findViewById(R.id.textView_Data);
			mViewHolder.bigD = (TextView) view.findViewById(R.id.textView_BigD);
			view.setTag(mViewHolder);
		}else{
			mViewHolder = (ViewHolder) view.getTag();
		}
		final ListItem item = (ListItem) data.get(position);
		mViewHolder.nome.setText(item.getmName());
		mViewHolder.desc.setText(item.getmDesc());
		mViewHolder.data.setText(item.getmDataString());
		mViewHolder.bigD.setText(item.getmValueString());
		return view;
	}
	
	public void add(Object ob){
		data.add((ListItem)ob);
	}
	
	public void remove(Object ob){
		data.remove(ob);
	}

}
