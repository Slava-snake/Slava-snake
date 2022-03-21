package ua.khpi.oop;

public class MyExpression extends WordContainer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	char[] delim = {' ', ',', '.', '-', ':', ';', '+', '?', '!', '"', '(', ')',
					'&', '*', '/', '\\', '=', '{', '}', '[', ']', '|',
					'\n', '\t', '\r', '\f', '\0'};
	private int[] beginPos;
	private StringBuilder text;
	private boolean IsCharDelim(char ch) {
		for (char i : delim) 
			if (ch==i) return true;
		return false;
	}
	public void newText(String ns) {
		int len=ns.length();
		text= new StringBuilder(ns);
		int beginWord;
		int i=0;
		beginPos = new int[100];
		clear();
		while (i<len) {
			beginWord=-1;
			while ((i<len) && IsCharDelim(text.charAt(i))) i++;
			if (i<len) beginWord=i;
			while ((i<len) && !IsCharDelim(text.charAt(i))) i++;
			if (beginWord>=0) {
				word[words]= text.substring(beginWord, i);
				beginPos[words]=beginWord;
				words++;
			}
		}
	}
	public MyExpression(String s) {
		newText(s);
	}
	public boolean changeWord(String newstr) {
		boolean conv=false;
		int convertlen=newstr.length();
		for (int i=0; i<words; i++) {
			if (word[i].length()==convertlen) {
				conv=true;
				change(i,newstr);
				text.replace(beginPos[i], convertlen, newstr);
			}
		}
		return conv;
	}
	public String[] getWords() {
		String[] gw= new String[words];
		for(int i=0; i<words; i++) gw[i]=word[i];
		return gw;
	}
}
