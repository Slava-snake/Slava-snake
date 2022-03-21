package ua.khpi.oop;

import java.util.Iterator; 

@SuppressWarnings("hiding")
public class WordIterator <String> implements Iterable<String> {
	private WordContainer wordArr;
	public WordIterator(WordContainer ww) {
		wordArr=ww;
	}
	@Override
	public Iterator <String> iterator(){
		Iterator <String> it = new Iterator <String>() {
			private int current=0;
			@Override
			public boolean hasNext() {
				return current<wordArr.size();
			}
			@SuppressWarnings("unchecked")
			@Override
			public String next(){
				return (String)wordArr.getItem(current++);
			}
			@Override
			public void remove() {
				wordArr.remove(current);
			}
		};
		return it;
	}
}

