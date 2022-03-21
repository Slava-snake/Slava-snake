package ua.khpi.oop;

import java.io.Serializable;
import java.util.Scanner;

public class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
	public String title;
	private String prompt;
	private MenuItem[] menuItem;
	private Scanner scanner;
	private String s;
	public Menu(String title, String prompt, MenuItem[] item) {
		this.title = title;
		this.prompt = prompt;
		menuItem = item;
		scanner = new Scanner(System.in);
	}
	private boolean notMenuLetter(char ch) {
		for (MenuItem i: menuItem )
			if (ch==i.let) return false;
		return true;
	}
	public char dlgMenu() {
		do {
			System.out.println(title);
			for (MenuItem i: menuItem)	System.out.println(i.let+" - "+i.text);
			System.out.print(prompt);
			s = scanner.nextLine();
		} while ((s.length()!=1)|| notMenuLetter(s.charAt(0)));
		return s.charAt(0);
	}
}

