package ua.khpi.oop;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Doc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public		int				countPos;
	public		Bookkeeping		bookkeeping;
	public		ProductRecord[]	pos;
	public	static 	String 			dateFormat="yyyy.MM.dd";
	public	static	SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	
	public Doc () {
		pos = new ProductRecord[10];
	}
	public String dateToString(long dt) {
		if (dt==0) return "";
		return format.format(new Date(dt));
	}
}
