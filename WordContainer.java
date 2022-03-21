package ua.khpi.oop;

import java.io.Serializable;

public class WordContainer implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int words;
	private int capacity=100;
	public char separator;
	protected String[] word;
	public void clear() {
		words=0;
		word=new String[capacity];
	}
	public WordContainer() {
		clear();
		separator=' ';
	}
	public String toString() {
		StringBuilder result=new StringBuilder();
		for(int i=0; i<words; i++) {
			result.append(word[i]);
			result.append(separator);
		}
		if (words>0) result.setLength(result.length()-1);//delete last separator
		return result.toString();
	}
	public void add(String s) {
		if (words<capacity) {
			word[words]=s;
			words++;
		}
	}
	public void change(int index, String s) {
		if ((index>=0)&&(index<words)) word[index]=s;
	}
	public boolean remove(String s) {
		for (int i=0; i<words; i++) {
			if (word[i].equals(s)) {
				for (int j=i; j<words; j++) 
					word[j]=word[j+1];
				words--;
				return true;
			}
		}
		return false;
	}
	public boolean remove(int index) {
		if ((words>0)&&(index>=0)&&(index<words)) {
			for (int j=index; j<words; j++) 
				word[j]=word[j+1];
			words--;
			return true;
		}
		return false;
	}
	public Object[] toArray() {
		return (Object[])word;
	}
	public int size() {
		return words;
	}
	public int getIndex(String s) {
		for (int i=0; i<words; i++)
			if (word[i].equals(s)) return i;
		return -1;
	}
	public boolean contains(String s) {
		return getIndex(s)>=0;
	}
	public boolean containsAll(WordContainer ss) {
		for (int j=0; j<ss.words; j++) {
			boolean absent=true;
			for (int i=0; i<this.words; i++)
				if (this.word[i].equals(ss.word[j])) {
					absent=false;
					break;
				};
			if (absent) return false;	
		}
		return true;
	}
	public String getItem(int index) {
		return word[index];
	}
}

