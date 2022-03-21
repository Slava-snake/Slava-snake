package ua.khpi.oop;

import java.io.Serializable;

public class Invoice extends ProductDoc implements Serializable{
	private static final long serialVersionUID = 1L;
	public Agent			agent;
	
	public Invoice() {}
	public Invoice(int n) {
		super(n,10);
	}
	public String toStatusString() {
		switch (status) {
			case CLOSED		: return " опнбедемю ";
			case ANNULLED	: return " нрлемемю ";
			case READY		: return "";
		}
		return "";
	}
	public int check() {
		int result = super.check();
		if ((result==0)&&(agent==null))
			return -6;
		return result;
	}
	public String toString() {
		return super.toString()+" "+agent.name;
	}
	
	public String toTitle() {
		return "╧"+number+" "+dateToString()+" "+toStatusString();
	}
}

