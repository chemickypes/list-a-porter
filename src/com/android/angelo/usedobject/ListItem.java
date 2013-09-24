package com.android.angelo.usedobject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ListItem {
	private String mName;
	private String mDesc;
	private Date mDate;
	private BigDecimal mValue;
	
	private DateFormat dateformat;
	
	public ListItem(String name, String desc, Date date, BigDecimal value)  {
		this.dateformat = new SimpleDateFormat("dd/MM/yyyy",Locale.ITALIAN);
		
		setmName(name);
		setmDesc(desc);
		setmDate(date);
		setmValue(value);
		
	}

	public ListItem(String name, String desc, String date, String value) throws ParseException {
		this.dateformat = new SimpleDateFormat("dd/MM/yyyy",Locale.ITALIAN);
		
		setmName(name);
		setmDesc(desc);
		setmDate(date);
		setmValue(value);
		
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmDesc() {
		return mDesc;
	}

	public void setmDesc(String mDesc) {
		this.mDesc = mDesc;
	}

	public Date getmData() {
		return mDate;
	}
	
	public String getmDataString(){
		return dateformat.format(mDate);
	}

	public void setmDate(Date mData) {
		this.mDate = mData;
	}
	
	public void setmDate(String data) throws ParseException{
		Date d = dateformat.parse(data);
		setmDate(d);
	}

	public BigDecimal getmValue() {
		return mValue;
	}
	
	public String getmValueString(){
		return mValue.toString();
	}

	public void setmValue(BigDecimal mValue) {
		this.mValue = mValue;
	}
	
	public void setmValue(String value){
		this.mValue = new BigDecimal(value);
	}

}
