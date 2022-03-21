package ua.khpi.oop.lab05;

import java.util.Iterator;

import ua.khpi.oop.MyExpression;
import ua.khpi.oop.WordIterator; 

public class Main {

	static String srcDef =  "У лукоморья дуб зеленый;\n"
			+"Златая цепь на дубе том:\n"
			+"И днем и ночью кот ученый\n"
			+"Все ходит по цепи кругом;\n";


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n=================================");
		MyExpression me = new MyExpression(srcDef);
		for (String i : new WordIterator<String>(me)) System.out.println(i);
		System.out.println("----------------------------------");
		Iterator <String> ws = new WordIterator<String>(me).iterator();
		while (ws.hasNext()) 
			System.out.println(ws.next());
		System.out.println("----------------------------------");
		new WordIterator<String>(me).forEach(System.out::println);;
		System.out.println("====================================");
	}

}
