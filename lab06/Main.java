package ua.khpi.oop.lab06;

//import ua.khpi.oop.MyExpression;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import ua.khpi.oop.WordContainer;
import ua.khpi.oop.MenuItem; 

public class Main {

	static String srcDef =  "” лукоморь€ дуб зеленый;\n"
							+"«лата€ цепь на дубе том:\n"
							+"» днем и ночью кот ученый\n"
							+"¬се ходит по цепи кругом;\n";
	
	public static void serializeWordContainer(WordContainer wc, String fn) {
		ObjectOutputStream oStream;
		try {
			oStream = new ObjectOutputStream(new FileOutputStream(fn));
			oStream.writeObject(wc);
	        oStream.close();} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static WordContainer deserializeWordContainer(String fn) {
		ObjectInputStream iStream;
		WordContainer wc = new WordContainer();
		try {
			iStream = new ObjectInputStream(new FileInputStream(fn));
			try {
				wc = (WordContainer) iStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			iStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wc;
	}
	
	public static class MainMenu  {
		MenuItem[] mainMenu = {
								new MenuItem('e',"Exit"),
								new MenuItem('s',"Show all words in line"),
								new MenuItem('n',"Number of words"),
								new MenuItem('c',"Change word"),
								new MenuItem('w',"Wipe out all words"),
								new MenuItem('r',"Remove word"),
								new MenuItem('a',"Add new word"),
								new MenuItem('f',"Find word"),
								new MenuItem('d',"Delimiter change")
							  };
		public void showMenu() {
			System.out.println("\n===========================");
			for (MenuItem i: mainMenu )
				System.out.println(i.let+" - "+i.text);
			System.out.print("=>");
		}
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MyExpression me = new MyExpression(srcDef);
//		serializeWordContainer(me, "pushkin01.out");
		WordContainer awc = deserializeWordContainer("pushkin01.out");
//		new WordIterator<String>(awc).forEach(System.out::println);
		MainMenu menu = new MainMenu();
		Scanner scanner = new Scanner(System.in);
		StringBuilder menuLet = new StringBuilder();
		do {
			menu.showMenu();
			menuLet.replace(0, 65535, scanner.nextLine());
			if (menuLet.length()==1) {
				switch (menuLet.charAt(0)) {
					case 'e':	break;
					case 'a':	System.out.println(">");
								awc.add(scanner.nextLine());
								break;
					case 'w':	awc.clear();
								break;
					case 'f':	System.out.println("<искомое слово>=>");
								if (awc.contains(scanner.nextLine()))
									System.out.println("это слово есть");
								else
									System.out.println("этого слова нет");
								break;
					case 's':	System.out.println(awc.toString());
								break;
					case 'n':	System.out.print("слов в контейнере :"+awc.size()); 
								break;
					case 'c':	System.out.print("<слово замены>=>");
								int ch=awc.getIndex(scanner.nextLine());
								if (ch>=0) {
									System.out.print("<слово новое>=>");
									awc.change(ch, scanner.nextLine());
									System.out.println("замена произведена");
								}
								else
									System.out.println("такого слова нет");
								break;
					case 'r':	System.out.print("<слово удалить>=>");
								if (awc.remove(scanner.nextLine())) 
									System.out.println("слово удалено");
								else
									System.out.println("ошибка удалени€");
								break;
					case 'd':	System.out.print("<новый разделитель>=>");
								menuLet.replace(0, 1, scanner.nextLine());
								awc.separator=menuLet.charAt(0);
								break;
						
				}
			}
		} while ((menuLet.charAt(0)!='e')||(menuLet.length()!=1));
		scanner.close();
		
	}

}
