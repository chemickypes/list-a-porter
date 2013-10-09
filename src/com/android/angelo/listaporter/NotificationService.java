package com.android.angelo.listaporter;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.android.angelo.usedobject.ListItem;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder; 

public class NotificationService extends Service implements ChildEventListener{

	@Override
	public void onCancelled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChildChanged(DataSnapshot snapshot, String arg1) {
		 @SuppressWarnings("unchecked")
		Map<String,Object> value = (HashMap<String, Object>) snapshot.getValue();
		String nome = (String)(value).get("nome");
		String desc = (String)(value).get("desc");
		String data = (String)(value).get("data");
		String bigd = (String)(value).get("bigd");
		setNotification(nome, desc);
		
	}

	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void onCreate(){
		
	}
	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    //TODO do something useful
		setServerComunication();
	    return Service.START_NOT_STICKY;
	  }

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void setServerComunication(){
		Firebase fr = new Firebase("https://hooloovoo.firebaseio.com");
		fr.addChildEventListener(this);
	}
	
	private void setNotification(String title,String desc){
		// Prepare intent which is triggered if the
		// notification is selected

		Intent intent = new Intent(this, MainActivity.class);
//		Bundle bun = new Bundle();
//		try {
//			bun.putParcelable("newItem", new ListItem("NuovoItem", 
//					"Descr nuovo item", "09/10/2013", "45738348637"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		intent.putExtras(bun);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Build notification  
		// Actions are just fake
		Notification.Builder mNotifyBuilder = new Notification.Builder(this)
		        .setContentTitle(title+ " has been changed")
		        .setContentText(desc)
		        .setContentIntent(pIntent)
		        .setSmallIcon(R.drawable.ic_not_social_person)
		        .setAutoCancel(true)
		        .setNumber(1);
		    
		  
		NotificationManager notificationManager = 
		  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Notification not = mNotifyBuilder.build();
		not.defaults |= Notification.DEFAULT_ALL;

		notificationManager.notify(0, not); 
	}

	

}
