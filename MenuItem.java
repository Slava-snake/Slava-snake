package ua.khpi.oop;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private static final long serialVersionUID = 1L;
	public char let;
	public String text;
	public MenuItem (char l, String t) {
		let=l;
		text=t;
	}
}	

