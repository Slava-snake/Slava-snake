package ua.khpi.oop;

import java.io.Serializable;
import java.util.Arrays;

public class DynamObjArray implements Serializable{
	private static final long serialVersionUID = 1L;
	private int capacity=100;
	public int count=0;
	public Object[] obj;
	
//	public DynamObjArray() {}
	public DynamObjArray() {
		obj = new Object[capacity];
	}
	private void resize() {
		if (count==capacity) {
			capacity*=2;
			Object[] a = new Object[capacity];
			obj = a;
		}
	}
	private boolean contains(Object o) {
		for(int i=0; i<count; i++)
			if (obj[i].equals(o)) return true;
		return false;
	}
	public void add(Object o) {
		if (!contains(o)) {
			resize();
			obj[count] = o;
			count++;
		}
	}
	public Object[] getAll() { 
		return Arrays.copyOfRange(obj, 0, count);
	}
}
