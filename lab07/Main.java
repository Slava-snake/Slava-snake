package ua.khpi.oop.lab07;

import ua.khpi.oop.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
	
	static Shop shop;
	static String myShop="my.shop";
	static Shop loadShop(String f) {
		ObjectInputStream iStream;
		Shop sh = new Shop();
		try {
			iStream = new ObjectInputStream(new FileInputStream(f));
			try {
				sh = (Shop) iStream.readObject();
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
		return sh;
	}
	static void saveShop() {
		ObjectOutputStream oStream;
		try {
			if (shop.filename=="") shop.filename=myShop;
			oStream = new ObjectOutputStream(new FileOutputStream(shop.filename));
			oStream.writeObject(shop);
	        oStream.close();} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Scanner scanner = new Scanner(System.in);
	static double getDouble() {
		try {
			return Double.valueOf(scanner.nextLine());}
		catch (NumberFormatException nfe) {
			return (double)0;}
	}
	static float getFloat() {
		try {
			return Float.valueOf(scanner.nextLine());}
		catch (NumberFormatException nfe) {
			return (float)0;
		}
	}
	static int getNumber() {
		try {
			return Integer.valueOf(scanner.nextLine());}
		catch (NumberFormatException nfe) {
			return 0;}
	}
	static long getLong() {
		try {
			return Long.valueOf(scanner.nextLine());}
		catch (NumberFormatException nfe) {
			return 0;}
	}
	static String getString(String pr, String text) {
		System.out.print(pr+text);
		String s=scanner.nextLine();
		if (s.isBlank()) {
			error(pr," мало текста");
			return "";
		}
		else if (s.isEmpty()) {
			error(pr," вообще пусто");
			return "";
		}
		return s;
	}
	static void error(String pr, String text) {
		System.out.println(pr+" ошибка: "+text);
	}
	static void manageAgent(Agent a, boolean newAgent) {
		if (a!=null) {
			String pr="@) ";
			String s;
			if (newAgent) {
				System.out.println("@@@ Новый контрагент @@@");
				while ((s=getString(pr," название: "))=="");
				a.name=s;
				while ((s=getString(pr," адрес: "))=="");
				a.address=s;
				while ((s=getString(pr," телефон,факс: "))=="");
				a.phoneFax=s;
				while ((s=getString(pr," электронная почта: "))=="");
				a.e_mail=s;
			}
			Menu menu = new Menu("\n @@@@@@@@ Контрагент №"+a.number+" @@@@@@@@\n"+
								a.name+"\n"+a.address+"\n"+a.phoneFax+"\n"+a.e_mail,"@>", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('N',"Редактировать название"),
					new MenuItem('A',"Редактировать адрес"),
					new MenuItem('P',"Редактировать телефон,факс"),
					new MenuItem('E',"Редактировать e-mail"),
							}	);
			char	mn;
			while ((mn=menu.dlgMenu())!='Q') {
				switch (mn) {
					case 'N' :	while ((s=getString(pr," название: "))=="");
								a.name=s;
								break;
					case 'A' :	while ((s=getString(pr," адрес: "))=="");
								a.address=s;
								break;
					case 'P' :	while ((s=getString(pr," телефон,факс: "))=="");
								a.phoneFax=s;
								break;
					case 'E' :	while ((s=getString(pr," электронная почта: "))!="");
								a.e_mail=s;
								break;
				}
				menu.title="\n @@@@@@@@ Контрагент №"+a.number+"@@@@@@@@\n"+a.name+"\n"+a.address+"\n"+a.phoneFax+"\n"+a.e_mail;
			}
		}
		else
			error("@)", "неверный индекс");
	}
	static Agent maintainingAgents(boolean select) {
		Agent a;
		Menu menu = new Menu("\n@@@@@@@@@ Контрагенты @@@@@@@@@","@@>", new MenuItem[] {
						new MenuItem('Q',"Выход"),
						new MenuItem('V',"Промотр подробный"),
						new MenuItem('v',"Промотр краткий"),
						new MenuItem('F',"Найти контрагента"),
						new MenuItem('N',"Создать контрагента"),
						new MenuItem('S',"Выбрать контрагента") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'v' :	for(int i=0; i<shop.agents.count; i++) {
								a=shop.agents.getAgentByIndex(i);
								System.out.println("#"+a.number+"\t"+a.asShortString());
							}
							break;
				case 'V' :	for(int i=0; i<shop.agents.count; i++) {
								a=shop.agents.getAgentByIndex(i);
								System.out.println("#"+a.number+"\t"+a.name);
								System.out.println("\t"+a.address);
								System.out.println("\t"+a.phoneFax);
								System.out.println("\t"+a.e_mail);
							}
							break;
				case 'F' :	if ((a= shop.agents.findAgent(getString("@>","найти в названи:"),
													getString("@>","найти в адресе:"),
													getString("@>","найти в телефоне.факсе:"),
													getString("@>","найти в e-mail:")))!=null) {
								manageAgent(a,false);
								if (select) return a;
							}
							else error("@>","ничего такого нет");
							break;
				case 'N' :	manageAgent(a=shop.agents.add(),true);
							if (select) return a;
							break;
				case 'S' :	System.out.print("номер @>");
							a=shop.agents.getAgentByIndex(getNumber());
							if (select && (a!=null)) return a;
							manageAgent(a,false);
							break;
			}
		}
		return null;
	}
	static void manageProperty(Property p, ProductItem t, boolean newProperty) {
		String pr=") ";
		if ((t==null)||(p==null)) {
			error(pr," нет такого");
			return;
		}
		String s;
		char mn;
		if (newProperty) {
			System.out.println("^ Новое свойство ^");
			while ((s=getString(pr," название: "))=="");
			p.property=s;
			while ((s=getString(pr," значение: "))=="");
			p.value=s;
		}
		Menu menu = new Menu("\n^^^ Редактировать свойство товара №"+t.number+" ^^^\n"+t.toString()+"\n^^ свойство: "+p.property+"\tзначение: "+p.value,
				"^>", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('D',"Удалить это свойство"),
					new MenuItem('P',"Изменить название свойства"),
					new MenuItem('V',"Изменить значение")
					} );
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'P':	while ((s=getString(pr," название: "))=="");
							p.property=s;
							break;
				case 'V':	while ((s=getString(pr," значение: "))=="");
							p.value=s;
							break;
				case 'D':	t.properties.remove(p);
							return;
			}
			menu.title="\n^^^ Редактировать свойство товара №"+t.number+" ^^^\n"+t.toString()+"\n^^ свойство: "+p.property+"\tзначение: "+p.value;
		}
	}
	static void manageProperties(ProductItem t) {
		String pr="^) ";
		char mn;
		Menu menu = new Menu("\n^^^^^^^^ Свойства товара №"+t.number+" ^^^^^^^^\n"+t.toString(),"^>", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('F',"Найти свойство"),
					new MenuItem('S',"Выбрать свойство по номеру"),
					new MenuItem('V',"Показать все"),
					new MenuItem('A',"Добавить свойство")
										}	);
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'S':	System.out.print("номер "+pr);
							manageProperty(t.properties.getProperty(getNumber()),t,false);
							break;
				case 'A':	manageProperty(t.properties.add(),t,true);
							break;
				case 'V':	System.out.println(pr+"свойство\t  значение");
							for(int i=0; i<t.properties.count; i++)
								System.out.println(pr+"#"+(i+1)+"\t"+t.properties.property[i].toStringTab());
							break;
				case 'F':	manageProperty(t.properties.getProperty(getString(pr,"название: ")),t,false);
							break;
			}
			menu.title="\n ^^^^^^^^ Товар №"+t.number+" ^^^^^^^^\n"+t.toString();
		}
	}
	static void manageProductItem(ProductItem t) {
		String pr="*) ";
		if (t!=null) {
			String s;
			char mn;
			if (t.designation=="") {
				System.out.println("*** Новый товар ***");
				while ((s=getString(pr," название: "))=="");
				t.designation=s;
				System.out.print(pr+" скан-код: ");
				t.scan=getLong();
				while ((s=getString(pr," единицы измерения: "))=="");
				t.measure=s;
				manageProperties(t);
			}
			Menu menu = new Menu("\n ******** Товар №"+t.number+" ********\n"+t.toString(),"*>", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('D',"Редактировать название"),
					new MenuItem('S',"Редактировать скан-код"),
					new MenuItem('M',"Редактировать единицы измерения"),
					new MenuItem('P',"Редактировать свойства")
									}	);
			while ((mn=menu.dlgMenu())!='Q') {
				switch (mn) {
					case 'D' :	while ((s=getString(pr," название: "))=="");
								t.designation=s;
								break;
					case 'S' :	System.out.print(pr+" скан-код: ");
								t.scan=getLong();
								break;
					case 'M' :	while ((s=getString(pr," единицы измерения: "))=="");
								t.measure=s;
								break;
					case 'P' :	manageProperties(t);
								break;
				}
				menu.title="\n ******** Товар №"+t.number+" ********\n"+t.toString();
			}
		}
		else error(pr,"неверный индекс");
	}
	static void setProductSalePrice(Product t, String pr) {
		do {
			System.out.print(pr+"продажная цена:");
			t.salePrice=getFloat();
		} while (t.salePrice<=0);
	}
	static void manageProduct(Product t) {
		if (t!=null) {
			String pr="*) ";
			char mn;
			Menu menu = new Menu("\n ******** Редактировать товар №"+t.number+" ********\n"+t.toString(),"*>", new MenuItem[] {
						new MenuItem('Q',"Выход"),
						new MenuItem('E',"Редактировать параметры"),
						new MenuItem('P',"Приход товара"),
						new MenuItem('S',"Расход товара"),
						new MenuItem('T',"Установить цену"),
						new MenuItem('R',"Остатки")					}	);
			while ((mn=menu.dlgMenu())!='Q') {
				switch (mn) {
					case 'E' :	manageProductItem(t);
								break;
					case 'R' :	System.out.println(pr+"осталось: "+t.remain+t.measure);
								break;
					case 'T' :	setProductSalePrice(t,pr);
								break;
					case 'P' :	System.out.println(pr+"товар поступал:");
								for(int i=0; i<t.income.count; i++) {
									PurchaseInvoice p=(PurchaseInvoice) t.income.obj[i];
									System.out.println(pr+"накладная №"+p.number+" "+p.dateToString()+" "+p.countProduct(t)+t.measure);
								}
								break;
					case 'S' :	System.out.println(pr+"товар расходовался:");
								for(int i=0; i<t.transfer.count; i++) {
									System.out.print(pr);
									switch (t.transfer.rec[i].type) {
										case 0 :	SalesInvoice s=(SalesInvoice) t.transfer.rec[i].obj;
													System.out.println("накладная №"+s.number+" "+s.dateToString()+" "+s.countProduct(t)+t.measure);
													break;
										case 1 :	Cheque c=(Cheque) t.transfer.rec[i].obj;
													System.out.println("чек №"+c.number+" "+c.dateToString()+" "+c.countProduct(t)+t.measure);
													break;
										case 2 :	/*акт*/
													break;
									}
								}
								break;
				}
				menu.title="\n ******** Редактировать товар №"+t.number+" ********\n"+t.toString();
			}
		}
	}
	static Product maintainingProducts(boolean select, boolean purch) {
		Product t;
		String pr="*>";
		Menu menu;
		if (purch) menu= new Menu("\n********* Товары *********","**>", new MenuItem[] {
						new MenuItem('Q',"Выход"),
						new MenuItem('V',"Промотр подробный"),
						new MenuItem('v',"Промотр краткий"),
						new MenuItem('F',"Найти товар"),
						new MenuItem('f',"Найти товар по скан-коду"),
						new MenuItem('N',"Создать товар"),
						new MenuItem('S',"Выбрать товар по номеру") } );
		else menu= new Menu("\n ******** Товары ********","**>", new MenuItem[] {
				new MenuItem('Q',"Выход"),
				new MenuItem('V',"Промотр подробный"),
				new MenuItem('v',"Промотр краткий"),
				new MenuItem('F',"Найти товар"),
				new MenuItem('f',"Найти товар по скан-коду"),
				new MenuItem('S',"Выбрать товар по номеру") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'v' :	for(int i=0; i<shop.products.count; i++)
								System.out.println(shop.products.product[i].toShortString());
							break;
				case 'V' :	for(int i=0; i<shop.products.count; i++) 
								System.out.println(shop.products.product[i].toTab());
							break;
				case 'N' :	manageProductItem(t=shop.products.add());
							if (select) return t;
							break;
				case 'S' :	System.out.print("номер "+pr);
							t=shop.products.findProductByIndex(getNumber());
							if (select && (t!=null)) return t;
							manageProduct(t);
							break;
				case 'f' :	t=shop.products.findProductByScan(getLong());
							if (select && (t!=null)) return t;
							manageProduct(t);
							break;
				case 'F' :	t=shop.products.findProductByDesignation(getString(pr,"название:"));
							if (select && (t!=null)) return t;
							manageProduct(t);
							break;
			}
		}
		return null;
	}
	static Menu selectInvoiceMenu(Invoice i, boolean purch) {
		String s;
		String pr;
		if (purch) {
			s="\n-- Приходная накладная №"+i.number+" --";
			pr="->";
		}
		else {
			s="\n++ Расходная накладная №"+i.number+" ++";
			pr="+>";
		}
		if (i.status==DocStatus.READY)
			return  new Menu(s,pr, new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('V',"Промотр полный"),
					new MenuItem('H',"Промотр шапки"),
					new MenuItem('E',"Редактировать шапку"),
					new MenuItem('v',"Промотр позиций"),
					new MenuItem('P',"Редактировать позицию по номеру"),
					new MenuItem('N',"Добавить позицию"),
					new MenuItem('A',"Аннулировать накладную(окончательно)"),
					new MenuItem('C',"Провести накладную(окончательно)")
					 } );
		else
			return  new Menu(s+i.toStatusString()+pr.charAt(0)+pr.charAt(0),pr, new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('V',"Промотр полный"),
					new MenuItem('v',"Промотр позиций"),
					new MenuItem('H',"Промотр шапки") }  );
	}
	static void editInvoiceHeader(Invoice i, String header, String pr) {
		String s;
		Agent a;
		System.out.println(pr+header);
		do {
			s=getString(pr,"дата("+ProductDoc.dateFormat+"):");
			if ((s=="") && (i.date!=0)) break;
			i.setDate(s);
		} while (i.date==0);
		do {
			System.out.print(pr+"контрагент:");
			a=maintainingAgents(true);
			if (a!=null) i.agent=a;
		} while (i.agent==null);
			
	}
	static void editPurchaseInvoiceHeader(PurchaseInvoice i, String header, String pr) {
		editInvoiceHeader(i,header,pr);
		String s=getString(pr,"номер накладной поставщика:");
		if (s!="") i.agentInvoiceNum=s;
		do {
			s=getString(pr,"дата накладной поставщика("+ProductDoc.dateFormat+"):");
			if (s=="") break;
		} while ((i.agentInvoiceDate=i.convertDate(s))==0);
	}
	static void showInvoicePos(Invoice i) {
		for(int j=0; j<i.countPos; j++)
			System.out.println((j+1)+". "+i.position[j].toString());
	}
	static void showInvoiceFullPos(Invoice i) {
		for(int j=0; j<i.countPos; j++)
			System.out.println((j+1)+". "+i.position[j].toInvoiceString());
	}
	static ProductRecord newProductRecord(boolean purch, String pr) {
		System.out.print(pr+" выбор товара:");
		ProductRecord prec=new ProductRecord(maintainingProducts(true,purch),0,0);
		if (prec.product==null) return null;
		do {
			System.out.print(pr+" количество:");
			prec.quantity=getFloat();
		} while (prec.quantity<=0);
		do {
			System.out.print(pr+" цена:");
			prec.price=getFloat();
			if (!purch && (prec.price==0)) {
				prec.price=prec.product.salePrice;
				System.out.println(pr+"(auto) "+prec.price);
			}
		} while ((!purch)&&(prec.price<prec.product.salePrice));
		if (purch) 
			while (prec.product.salePrice<=prec.price) {
				if (!shop.markupPrice(prec))
				System.out.print(" ! установить цену продажи ! ");
				setProductSalePrice(prec.product, pr);
			}
		return prec;
	}
	static void showProductRecord(int pos, ProductRecord pr, String prompt) {
		System.out.println(prompt+pr.toInvoiceString());
	}
	static boolean editProductRecord(int pos, ProductRecord pr, String prompt, boolean purch) {
		char mn;
		Menu menu = new Menu("\n,,,,,Редактирование позиции №"+pos+",,,,,\n"+pr.toString(),",,>", new MenuItem[] {
				new MenuItem('Q',"Выход"),
				new MenuItem('S',"Выбрать товар"),
				new MenuItem('N',"Количество товара"),
				new MenuItem('P',"Цена товара"),
				new MenuItem('D',"Удалить эту позицию")  } );
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'S' :	System.out.print(pr+" выбор товара:");
							pr.product=maintainingProducts(true,purch);
							break;
				case 'N' :	do {
								System.out.print(pr+" количество товара:");
								pr.quantity=getFloat();
							} while (pr.quantity<=0);
							break;
				case 'P' :	do {
								System.out.print(pr+" цена товара:");
								pr.price=getFloat();
								if (!purch && (pr.price==0)) {
									pr.price=pr.product.salePrice;
									System.out.println(pr+"(auto) "+pr.price);
								}
							} while ((!purch)&&(pr.price<pr.product.salePrice));
							break;
				case 'D' :	return false;
			}
			menu.title="\n,,,,,Редактирование позиции №"+pos+",,,,,\n"+pr.toString();
		}
		return true;
	}
	static void managePurchaseInvoice(PurchaseInvoice p){
		String pr="-)";
		char	mn;
		if (p.agent==null) editPurchaseInvoiceHeader(p,"--- Новая приходная накладная №"+p.number+" ---",pr);
		Menu menu = selectInvoiceMenu(p,true);
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'v' :	showInvoicePos(p);
							break;
				case 'H' :	System.out.println(p.toHeader());
							break;
				case 'V' :	System.out.println(p.toHeader());
							showInvoiceFullPos(p);
							break;
				case 'E' :	editPurchaseInvoiceHeader(p,p.toTitle(),pr);
							break;
				case 'P' :	System.out.print("номер позиции "+pr);
							int i=getNumber()-1;
							ProductRecord prec = p.toProductRecord(i);
							ProductRecord prec_clone=prec.clone();
							if (prec!=null) {
								if (editProductRecord(i+1,prec_clone,pr,true))
									p.changePos(i,prec_clone);
								else {
									p.removePos(i);
									System.out.println(pr+"позиция удалена");
								}	
							}
							break;
				case 'N' :	prec = newProductRecord(true,pr);
							if (prec!=null)
								p.addPos(prec);
							else
								error(pr,"позиция не добавлена");
							break;
				case 'A' :	p.annul();
							System.out.println(pr+"накладная аннулирована");
							menu = selectInvoiceMenu(p,true);
							break;
				case 'C' :	int c=p.conduct();
							if (c==0) {
								System.out.println(pr+"накладная проведена успешно");
								menu=selectInvoiceMenu(p,true);
							}
							else
								error(pr,p.toErrorString(c));
			}
	}
	static void maintainingPurchaseInvoices (){
		String	pr="--)";
		Menu menu = new Menu("\n-------- Приходные накладные ---------","-->", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('N',"Создать новую накладную"),
					new MenuItem('v',"Cписок всех (от старых к новым)"),
					new MenuItem('V',"Cписок всех (от новых к старым)"),
					new MenuItem('S',"Выбрать накладную по номеру") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'S' :	System.out.print("номер "+pr);
							managePurchaseInvoice(shop.purchaseInvoices.toPurchaseInvoice(getNumber()));
							break;
				case 'N' :	managePurchaseInvoice(shop.purchaseInvoices.add());
							break;
				case 'v' :	for(int i=0; i<shop.purchaseInvoices.count; i++)
								System.out.println(shop.purchaseInvoices.toPurchaseInvoice(i).toString());
							break;
				case 'V' :	for(int i=shop.purchaseInvoices.count-1; i>=0; i--)
								System.out.println(shop.purchaseInvoices.toPurchaseInvoice(i).toString());
							break;
			}
	}
	static void manageSalesInvoice(SalesInvoice s){
		String pr="+)";
		char	mn;
		if (s.agent==null) editInvoiceHeader(s,"+++ Новая расходная накладная №"+s.number+" +++",pr);
		Menu menu = selectInvoiceMenu(s,false);
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'v' :	showInvoicePos(s);
							break;
				case 'H' :	System.out.println(s.toHeader());
							break;
				case 'V' :	System.out.println(s.toHeader());
							showInvoiceFullPos(s);
							break;
				case 'E' :	editInvoiceHeader(s,s.toTitle(),pr);
							break;
				case 'P' :	System.out.print("номер позиции "+pr);
							int i=getNumber()-1;
							ProductRecord prec = s.toProductRecord(i);
							ProductRecord prec_clone=prec.clone();
							if (prec!=null) {
								if (editProductRecord(i+1,prec_clone,pr,true))
									s.changePos(i,prec_clone);
								else {
									s.removePos(i);
									System.out.println(pr+"позиция удалена");
								}	
							}
							break;
				case 'N' :	prec = newProductRecord(false,pr);
							if (prec!=null)
								s.addPos(prec);
							else
								error(pr,"позиция не добавлена");
							break;
				case 'A' :	s.annul();
							System.out.println(pr+"накладная аннулирована");
							menu = selectInvoiceMenu(s,false);
							break;
				case 'C' :	int c=s.conduct();
							if(c==0) {
								System.out.println(pr+"накладная проведена успешно");
								menu=selectInvoiceMenu(s,false);
							}
							else
								error(pr,s.toErrorString(c));
							break;
			}
	}
	static void maintainingSalesInvoices (){
		String	pr="++)";
		Menu menu = new Menu("\n++++++++ Расходные накладные ++++++++","++>", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('N',"Создать новую накладную"),
					new MenuItem('v',"Cписок всех (от старых к новым)"),
					new MenuItem('V',"Cписок всех (от новых к старым)"),
					new MenuItem('S',"Выбрать накладную по номеру") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'S' :	System.out.print("номер "+pr);
							manageSalesInvoice(shop.salesInvoices.toSalesInvoice(getNumber()));
							break;
				case 'N' :	manageSalesInvoice(shop.salesInvoices.add());
							break;
				case 'v' :	for(int i=0; i<shop.salesInvoices.count; i++)
								System.out.println(shop.salesInvoices.toSalesInvoice(i).toString());
							break;
				case 'V' :	for(int i=shop.salesInvoices.count-1; i>=0; i--)
								System.out.println(shop.salesInvoices.toSalesInvoice(i).toString());
							break;
			}
	}
	static void maintainingBookkeeping() {
		String bo="++) ";
		double d;
		char mn;
		Menu menu = new Menu("\n&&&&&&&&&&&& Бухгалтерия &&&&&&&&&&","&&>", new MenuItem[] {
				new MenuItem('Q',"Выход"),
				new MenuItem('M',"Денег на счету"),
				new MenuItem('B',"Баланс"),
				new MenuItem('D',"Дебит"),
				new MenuItem('C',"Кредит"),
				new MenuItem('d',"внос денег"),
				new MenuItem('w',"вынос денег")		}	);
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'M':	System.out.println(bo+shop.bookkeeping.money); break;
				case 'D':	System.out.println(bo+shop.bookkeeping.debit); break;
				case 'C':	System.out.println(bo+shop.bookkeeping.credit); break;
				case 'B':	System.out.println(bo+(shop.bookkeeping.credit-shop.bookkeeping.debit)); break;
				case 'd':	System.out.print(bo+"сколько денег внести ++>");
							d=getDouble();
							if (d>0.0)
								System.out.println(bo+"деньги "+d+" внесены (на счету "+ shop.bookkeeping.depositeMoney(d)+")");
							else
								System.out.println(bo+"ничего не внесено");
							break;
				case 'w':	System.out.print(bo+"сколько денег вынести ++>");
							d=getDouble();
							if (d>0.0) {
								if (shop.bookkeeping.withdrawMoney(d))
									System.out.println(bo+"деньги "+d+" вынесены (на счету "+shop.bookkeeping.money+")");
								else
									System.out.println(bo+"недостаточно средств");
							}
							else
								System.out.println(bo+"ничего не вынесено");
							break;
			}
			
		}
	}
	static Menu selectChequeMenu(Cheque c, String pr) {
		if (c.status==DocStatus.READY)
			return new Menu("\n$$$$$$$$ Чек №"+c.number+" $$$$"+c.toStatusString()+"$$$$\n"+
							"дата: "+c.dateToString()+"\nпозиций: "+c.countPos+"\nсумма: "+c.sum,pr, new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('V',"Промотр полный"),
					new MenuItem('v',"Промотр позиций"),
					new MenuItem('P',"Редактировать позицию по номеру"),
					new MenuItem('N',"Добавить позицию"),
					new MenuItem('A',"Аннулировать чек(окончательно)"),
					new MenuItem('C',"Провести чек- закрыть (окончательно)") } );
		else
			return new Menu("\n$$$$$$$$ Чек №"+c.number+"$$$$"+c.toStatusString()+"$$$$\n"+
					"дата: "+c.dateToString()+"\nпозиций: "+c.countPos+"\nсумма: "+c.sum,pr, new MenuItem[] {
			new MenuItem('Q',"Выход"),
			new MenuItem('V',"Промотр полный"),
			new MenuItem('v',"Промотр позиций") }  );		
	}
	static void manageCheque(Cheque c){
		String pr="$)";
		char	mn;
		Menu menu = selectChequeMenu(c,pr);
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'v' :	for(int i=0; i<c.countPos; i++)
								System.out.println((i+1)+". "+c.position[i].toString());
							break;
				case 'V' :	System.out.println(menu.title);
							for(int i=0; i<c.countPos; i++) {
								ProductRecord p=c.position[i];
								System.out.println((i+1)+". "+p.product.toString()+
													"\n\tколичество: "+p.quantity+
													"\n\tцена: "+p.price+
													"\n  сумма позиции: "+(p.quantity*p.price));
							}
							break;
				case 'P' :	System.out.print("номер позиции "+pr);
							int i=getNumber()-1;
							ProductRecord prec = c.toProductRecord(i);
							ProductRecord prec_clone=prec.clone();
							if (prec!=null) {
								if (editProductRecord(i+1,prec_clone,pr,false))
									c.changePos(i,prec_clone);
								else {
									c.removePos(i);
									System.out.println(pr+"позиция удалена");
								}	
							}
							break;
				case 'N' :	prec = newProductRecord(false,pr);
							if (prec!=null)
								c.addPos(prec);
							else
								error(pr,"позиция не добавлена");
							break;
				case 'A' :	c.annul();
							System.out.println(pr+"чек аннулирован");
							menu = selectChequeMenu(c,pr);
							break;
				case 'C' :	int r=c.conduct();
							if (r==0) {
								System.out.println(pr+"чек закрыт успешно");
								menu=selectChequeMenu(c,pr);
							}
							else
								error(pr,c.toErrorString(r));
							break;
			}
			menu.title="\n$$$$$$$$ Чек №"+c.number+" $$$$"+c.toStatusString()+"$$$$\n"+
					"дата: "+c.dateToString()+"\nпозиций: "+c.countPos+"\nсумма: "+c.sum;
		}
	}
	static void maintainingCheques (){
		String	pr="$$)";
		Menu menu = new Menu("\n$$$$$$$$ Продажные чеки $$$$$$$$","$$>", new MenuItem[] {
					new MenuItem('Q',"Выход"),
					new MenuItem('N',"Создать новый чек"),
					new MenuItem('v',"Cписок всех (от старых к новым)"),
					new MenuItem('V',"Cписок всех (от новых к старым)"),
					new MenuItem('S',"Выбрать чек по номеру") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'S' :	System.out.print("номер "+pr);
							manageCheque(shop.cheques.toCheque(getNumber()));
							break;
				case 'N' :	manageCheque(shop.cheques.add());
							break;
				case 'v' :	for(int i=0; i<shop.cheques.count; i++)
								System.out.println(shop.cheques.toCheque(i).toString());
							break;
				case 'V' :	for(int i=shop.cheques.count-1; i>=0; i--)
								System.out.println(shop.cheques.toCheque(i).toString());
							break;
			}
	}
	static void maintainingShop() {
		Menu menu = new Menu("\n============МАГАЗИН "+shop.name+"============","==>", new MenuItem[] {
				 new MenuItem('Q',"Выход"),
				 new MenuItem('P',"Приходные накладные"),
				 new MenuItem('S',"Расходные накладные"),
				 new MenuItem('C',"Чековые продажи"),
				 new MenuItem('T',"Товарная информация"),
				 new MenuItem('B',"Бухгалтерия"),
				 new MenuItem('A',"Контрагенты"),
				 new MenuItem('R',"Переименовать магазин")	}	);
		char mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'A':	maintainingAgents(false);
							break;
				case 'B':	maintainingBookkeeping();
							break;
				case 'P':	maintainingPurchaseInvoices();
							break;
				case 'S':	maintainingSalesInvoices();
							break;
				case 'C':	maintainingCheques();
							break;
				case 'T':	maintainingProducts(false,false);
							break;
				case 'R':	shop.name=getString("==>","новое название ");
							menu.title="\n============МАГАЗИН "+shop.name+"============";
							break;
			}
		saveShop();
	}
		
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		// if (!shopload()) then
			// ELSE
		Menu menu = new Menu("\n<<<<< Приложение МАГАЗИН >>>>>",">>", new MenuItem[] {
				 new MenuItem('Q',"Выход"),
				 new MenuItem('L',"Загрузить магазин из файла"),
				 new MenuItem('N',"Создать новый магазин"),
				 new MenuItem('A',"Справка") } );
		char mn;
		String fn;
		String name;
		float auto;
		double start;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'L':	fn=getString(">>>","имя файла для загрузки ");
							if (fn!="")
								shop = loadShop(fn);
							else
								shop = loadShop(myShop);
							maintainingShop();
							break;
				case 'N':	name=getString(">>","название магазина >>>");
							System.out.print(">> стартовый капитал >>>");
							start=getDouble();
							System.out.print(">> процент автоматической наценки (0-без автоматизации)>>>");
							auto=getFloat();
							fn=getString(">>>","имя файла для хранения ");
							shop = new Shop(start,auto);
							shop.name=name;
							shop.filename=fn;
							maintainingShop();
							break;
				case 'A':	System.out.println("\t\t\tПриложение МАГАЗИН\n"+
												"    Это приложение МАГАЗИН разработано на основе лабораторной работы№7 ООП,\n"+
												"цель которой - использование объектно-ориентированного подхода при разработке\n"+
												"объекта предметной (прикладной) области. Цель достигнута. Приложение разработано\n"+
												"почти без индексации, кроме требований предметной области и удобства выбора.\n"+
												"Отступление от задания обусловлено более гибким подходом к предметной области\n"+
												"и расширенными возможностями, основанными на изучении предмета разработки.\n"+
												"    Приложение использует консольное диалоговое меню для ввода-вывода данных. А также\n"+
												"сохраняет свое состояние при завершении, и восстанавливает при запуске.\n\n"+
												"  Выполнил студент группы КИТ-120в Лазуренко В.В.");
							break;
			}
		scanner.close();
	}

}
