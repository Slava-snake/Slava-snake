package ua.khpi.oop.lab04;

import ua.khpi.oop.lab03.Main.MyExpression;
import java.util.Scanner;

public class Main {

	static boolean debugInfo=true;
	
	static String src =  "У лукоморья дуб зеленый;\n"
						+"Златая цепь на дубе том:\n"
						+"И днем и ночью кот ученый\n"
						+"Все ходит по цепи кругом;\n";

	public static class MenuItem {
		char let;
		String text;
		public MenuItem (char l, String t) {
			let=l;
			text=t;
		}
	}	
	
	public static class MainMenu {
		MenuItem[] mainMenu = {
								new MenuItem('e',"Exit"),
								new MenuItem('s',"Show current string"),
								new MenuItem('n',"Enter new string"),
								new MenuItem('c',"Change string"),
								new MenuItem('h',"Helping information"),
								new MenuItem('d',"Debugging information"),
								new MenuItem('a',"About program")
							  };
		public void showMenu() {
			System.out.println("\n===========================");
			for (MenuItem i: mainMenu )
				System.out.println(i.let+" - "+i.text);
			System.out.print("=>");
		}
	}
	
	public static void changeDebug() {
		if (debugInfo) System.out.println("--процедура changeDebug--");  
		debugInfo=!debugInfo;
	}
	
	public static void aboutInfo() {
		if (debugInfo) System.out.println("--процедура aboutInfo--");  
		System.out.println("Лазуренко В.В.");
	}
	
	public static void helpInfo(boolean cmdline) {
		if (debugInfo) System.out.println("--процедура helpInfo--");  
		if (cmdline) {
			System.out.println("аргументы командной строки :");
			System.out.println("[-s]/[-show]   вывод обрабатываемого текста");
			System.out.println("[-n]/[-new]    ввод нового текста для обработки");
			System.out.println("[-c]/[-change] изменение текста заменой слова");
			System.out.println("[-h]/[-help]   вывод этой информации");
			System.out.println("[-d]/[-debug]  вкл/выкл режим отладочной информации");
			System.out.println("[-a]/[-about]  вывод информации о программе");
		}
		else {
			System.out.println("пункты меню :");
			System.out.println("[e] выход из программы");
			System.out.println("[s] вывод обрабатываемого текста");
			System.out.println("[n] ввод нового текста для обработки");
			System.out.println("[c] изменение текста заменой слова");
			System.out.println("[h] вывод этой информации");
			System.out.println("[d] вкл/выкл режим отладочной информации");
			System.out.println("[a] вывод информации о программе");
		}
	}
	
	public static void unknownParam() {
		if (debugInfo) System.out.println("--процедура unknownParam--");  
		System.out.println("неизвестный аргумент");
	}
	
	public static void showSrc(StringBuilder s) {
		if (debugInfo) System.out.println("--процедура showSrc--");  
		System.out.println(s);
	}
	
	public static void enterNew(StringBuilder s, String ns) {
		if (debugInfo) System.out.println("--процедура enterNew--текст("+ns.length()+"):"+ns);  
		s.replace(0, 65535, ns);
	}
	
	public static void changeStr(boolean success) {
		if (debugInfo) {
			System.out.print("--процедура changeStr ");
			if (success)
				System.out.println("успешна--");
			else
				System.out.println("без изменений--");
		}
	}
	

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder(src);
		MyExpression me = new MyExpression();
		boolean successReplace;
		if (args.length!=0) {
			int narg=0;
			while (narg<args.length) {
				if (debugInfo) System.out.println("--обработка аргумента №"+narg+"--");
				switch (args[narg]) {
					case "-a","-about": 
						aboutInfo(); 
						narg++;
						break;
					case "-h","-help": 
						helpInfo(true); 
						narg++;
						break;
					case "-d","-debug":
						changeDebug();
						narg++;
						break;
					case "-s","-show":
						showSrc(str);
						narg++;
						break;
					case "-n","-new":
						enterNew(str,args[++narg]);
						narg++;
						break;
					case "-c","-change":
						changeStr(me.convertString(str,args[++narg]));
						narg++;
						break;
					default:
						unknownParam();
						return ;
				}
			}
		}
		else {
			MainMenu menu = new MainMenu();
			Scanner scanner = new Scanner(System.in);
			StringBuilder menuLet = new StringBuilder();
			String ns;
			do {
				if (debugInfo) System.out.println("--меню диалога--");  
				menu.showMenu();
				menuLet.replace(0, 65535, scanner.nextLine());
				if (debugInfo) System.out.println("--введено <"+menuLet+">--");  
				if (menuLet.length()==1) {
					switch (menuLet.charAt(0)) {
						case 'e' : break;
						case 'a' : aboutInfo(); break;
						case 'h' : helpInfo(false); break;
						case 'd' : changeDebug(); break;
						case 's' : showSrc(str); break;
						case 'n' : System.out.print("<новый текст>=>"); 
								   ns=scanner.nextLine();
								   enterNew(str, ns);
								   break;
						case 'c' : System.out.print("<слово замены>=>"); 
								   successReplace = me.convertString(str, scanner.nextLine());
								   changeStr(successReplace);
								   if (successReplace) 
									   System.out.println("Замена произведена.");
								   else   
									   System.out.println("Замена не произошла.");
								   break;
						default  :
							unknownParam();
					}
				}
			} while ((menuLet.charAt(0)!='e')||(menuLet.length()!=1));
			scanner.close();
			if (debugInfo) System.out.println("--выход--");  
		}
	}

}
