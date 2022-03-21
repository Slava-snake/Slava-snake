package ua.khpi.oop;

import java.io.Serializable;

public class Properties implements Serializable{
	private static final long serialVersionUID = 1L;
	private int capacity=10;
	public  int count;
	public Property[]	property;
	
	public Properties() {
		property = new Property[capacity];
	}

	private boolean validIndex(int n) {
		return (n>=0)&&(n<count);
	}
	public int findSame(Property p) {
		int result=0;
		int once=0;
		for (int i=0; i<count; i++)
			if ((property[i].property.matches(p.property))&&
				(property[i].value.matches(p.value))) {
				result=i;
				once++;
			}
		return (once==0) ? -1 : (once==1)?result:-once;
	}
	public boolean sameAs(Properties p) {
		if (count!=p.count) return false;
		for (int i=0; i<count; i++)
			if (findSame(p.property[i])<0) return false;
		return true;
	}
	private void resize() {
		if (count==capacity) {
			capacity+=10;
			Property[] p = new Property[capacity];
			property = p;
		}
	}
	public Property getProperty(int n) {
		if (validIndex(n)) return property[n];
		return null;
	}
	public Property getProperty(String s) {
		for (int i=0; i<count; i++)
			if (property[i].property.matches(s)) return property[i];
		return null;
	}
	public int add(Property p) {
		if (p!=null) {
			resize();
			property[count]=p;
			return count++;
		}
		else return -1;
	}
	public Property add() {
		resize();
		return property[count++]=new Property();
	}
	public void remove(Property p) {
		for(int i=0; i<count; i++)
			if (property[i].equals(p)) {
				for (int j=i+1; j<count; j++) property[j-1]=property[j];
				count--;
				break;
			}
	}
	public String toString() {
		String s="";
		for (int i=0; i<count; i++)
			s=s.concat(property[i].property+"="+property[i].value+" ");
		return s;
	}
	public String toLine(char delimiter) {
		String s="";
		for (int i=0; i<count; i++)
			s=s.concat(property[i].property+"="+property[i].value+" "+delimiter+" ");
		return s;
	}
	public String toTab() {
		String s="";
		for (int i=0; i<count; i++)
			s=s.concat(property[i].property+"\t = "+property[i].value+"\n");
		return s;
	}
}

