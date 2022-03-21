package ua.khpi.oop;

import java.io.Serializable;

public class Cheque extends ProductDoc implements Serializable  {
	private static final long serialVersionUID = 1L;

	public Cheque() {}
	public Cheque(int n, Bookkeeping b) {
		super(n,10);
		bookkeeping = b;
		setDate("");
	}
	public String toStatusString() {
		switch (status) {
			case CLOSED		: return " «¿ –€“ ";
			case ANNULLED	: return " Œ“Ã≈Õ≈Õ ";
			case READY		: return "";
		}
		return "";
	}
	public int conduct() {
		int result;
		if ((result=check())==0) 
			result = sale(1);
		return result;
	}
	public String toTitle() {
		return "π"+number+toStatusChar()+" "+dateToString()+" "+toStatusString();
	}

}
