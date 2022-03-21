package ua.khpi.oop;

import java.io.Serializable;

public class TypeSpending implements Serializable {
	private static final long serialVersionUID = 1L;
	public int type;
	public Object obj;

	public TypeSpending() {}
	public TypeSpending(int t, Object o) {
		type=t;
		obj=o;
	}
}
