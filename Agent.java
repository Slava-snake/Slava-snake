package ua.khpi.oop;

import java.io.Serializable;

public class Agent implements Serializable{
	private static final long serialVersionUID = 1L;
	public int		number;
	public String	name;
	public String	address;
	public String	phoneFax;
	public String	e_mail;
	
	public Agent() {}
	public Agent(int i, String n, String a, String pf, String e) {
		number=i;
		name=n;
		address=a;
		phoneFax=pf;
		e_mail=e;
	}
	public Agent(int i) {
		number=i;
	}
	public String asString() {
		return name+" "+address+" "+phoneFax+" "+e_mail;
	}
	public String asShortString() {
		return name+" "+address;
	}
}

