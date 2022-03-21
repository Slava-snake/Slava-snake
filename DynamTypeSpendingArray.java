package ua.khpi.oop;

import java.io.Serializable;

public class DynamTypeSpendingArray implements Serializable{
	private static final long serialVersionUID = 1L;
	private int capacity=100;
	public int count=0;
	public TypeSpending[] rec;
//	public DynamTypeSpendingArray() {}
	public DynamTypeSpendingArray() {
		rec = new TypeSpending[capacity];
	}
	private void resize() {
		if (count==capacity) {
			capacity*=2;
			TypeSpending[] a = new TypeSpending[capacity];
			rec = a;
		}
	}
	public int add(TypeSpending n) {
		resize();
		rec[count] = n;
		return ++count;
	}
}

