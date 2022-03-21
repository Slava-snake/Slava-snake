package ua.khpi.oop;

import java.io.Serializable;

public class Property implements Serializable {
	private static final long serialVersionUID = 1L;
	public String property;
	public String value;
	
	public Property() {}
	public Property(String p, String v) {
		property=p;
		value=v;
	}
	public String toString() {
		return property+" "+value;
	}
	public String toStringTab() {
		return property+"\t|  "+value;
	}
}
