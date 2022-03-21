package ua.khpi.oop;

import java.io.Serializable;

public class Folder <T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public int 			capacity=100;
	public int 			count=0;
	public double		money;
	public Bookkeeping	bookkeeping;
	public T[]			item;
	
	protected boolean validIndex(int n) {
		return (n>=0)&&(n<count);
	}
}
