package ua.khpi.oop.lab03;

public class Main {

	static String src =  "У лукоморья дуб зеленый;\n"
						+"Златая цепь на дубе том:\n"
						+"И днем и ночью кот ученый\n"
						+"Все ходит по цепи кругом;\n";

	public static class MyExpression {
		class BegLen {
			int begin;
			int length;
		}
		char[] delim = {' ', ',', '.', '-', ':', ';', '+', '?', '!', '"', '(', ')',
						'&', '*', '/', '\\', '=', '{', '}', '[', ']', '|',
						'\n', '\t', '\r', '\f', '\0'};
		int	  delimN = delim.length;	
		private int words;
		BegLen[] beginLen;
		public boolean IsCharDelim(char ch) {
			for (char i : delim) 
				if (ch==i) return true;
			return false;
		}
		public int countWords(StringBuilder s) {
			int len=s.length();
			int beginWord;
			int i=0;
			words=0;
			beginLen = new BegLen[100];
			while (i<len) {
				beginWord=-1;
				while ((i<len) && IsCharDelim(s.charAt(i))) i++;
				if (i<len) beginWord=i;
				while ((i<len) && !IsCharDelim(s.charAt(i))) i++;
				if (beginWord>=0) {
					beginLen[words]= new BegLen();
					beginLen[words].begin=beginWord;
					beginLen[words].length=i-beginWord;
					words++;
				}
			}
			return words;
		}
		public boolean convertString(StringBuilder s, String newstr) {
			boolean conv=false;
			int convertlen=newstr.length();
			for (int i=countWords(s)-1; i>=0; i--) {
				if (beginLen[i].length==convertlen) {
					conv=true;
					for (int j=0; j<convertlen; j++) 
						s.setCharAt(beginLen[i].begin+j, newstr.charAt(j));
				}
			}
			return conv;
		}
	}
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder(src);
		System.out.println(src);
		MyExpression m = new MyExpression();
		if (m.convertString(str,"ура"))
			System.out.println(str);
		else
			System.out.println("\nОШИПКА");
	}

}
