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
			error(pr," ���� ������");
			return "";
		}
		else if (s.isEmpty()) {
			error(pr," ������ �����");
			return "";
		}
		return s;
	}
	static void error(String pr, String text) {
		System.out.println(pr+" ������: "+text);
	}
	static void manageAgent(Agent a, boolean newAgent) {
		if (a!=null) {
			String pr="@) ";
			String s;
			if (newAgent) {
				System.out.println("@@@ ����� ���������� @@@");
				while ((s=getString(pr," ��������: "))=="");
				a.name=s;
				while ((s=getString(pr," �����: "))=="");
				a.address=s;
				while ((s=getString(pr," �������,����: "))=="");
				a.phoneFax=s;
				while ((s=getString(pr," ����������� �����: "))=="");
				a.e_mail=s;
			}
			Menu menu = new Menu("\n @@@@@@@@ ���������� �"+a.number+" @@@@@@@@\n"+
								a.name+"\n"+a.address+"\n"+a.phoneFax+"\n"+a.e_mail,"@>", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('N',"������������� ��������"),
					new MenuItem('A',"������������� �����"),
					new MenuItem('P',"������������� �������,����"),
					new MenuItem('E',"������������� e-mail"),
							}	);
			char	mn;
			while ((mn=menu.dlgMenu())!='Q') {
				switch (mn) {
					case 'N' :	while ((s=getString(pr," ��������: "))=="");
								a.name=s;
								break;
					case 'A' :	while ((s=getString(pr," �����: "))=="");
								a.address=s;
								break;
					case 'P' :	while ((s=getString(pr," �������,����: "))=="");
								a.phoneFax=s;
								break;
					case 'E' :	while ((s=getString(pr," ����������� �����: "))!="");
								a.e_mail=s;
								break;
				}
				menu.title="\n @@@@@@@@ ���������� �"+a.number+"@@@@@@@@\n"+a.name+"\n"+a.address+"\n"+a.phoneFax+"\n"+a.e_mail;
			}
		}
		else
			error("@)", "�������� ������");
	}
	static Agent maintainingAgents(boolean select) {
		Agent a;
		Menu menu = new Menu("\n@@@@@@@@@ ����������� @@@@@@@@@","@@>", new MenuItem[] {
						new MenuItem('Q',"�����"),
						new MenuItem('V',"������� ���������"),
						new MenuItem('v',"������� �������"),
						new MenuItem('F',"����� �����������"),
						new MenuItem('N',"������� �����������"),
						new MenuItem('S',"������� �����������") } );
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
				case 'F' :	if ((a= shop.agents.findAgent(getString("@>","����� � �������:"),
													getString("@>","����� � ������:"),
													getString("@>","����� � ��������.�����:"),
													getString("@>","����� � e-mail:")))!=null) {
								manageAgent(a,false);
								if (select) return a;
							}
							else error("@>","������ ������ ���");
							break;
				case 'N' :	manageAgent(a=shop.agents.add(),true);
							if (select) return a;
							break;
				case 'S' :	System.out.print("����� @>");
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
			error(pr," ��� ������");
			return;
		}
		String s;
		char mn;
		if (newProperty) {
			System.out.println("^ ����� �������� ^");
			while ((s=getString(pr," ��������: "))=="");
			p.property=s;
			while ((s=getString(pr," ��������: "))=="");
			p.value=s;
		}
		Menu menu = new Menu("\n^^^ ������������� �������� ������ �"+t.number+" ^^^\n"+t.toString()+"\n^^ ��������: "+p.property+"\t��������: "+p.value,
				"^>", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('D',"������� ��� ��������"),
					new MenuItem('P',"�������� �������� ��������"),
					new MenuItem('V',"�������� ��������")
					} );
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'P':	while ((s=getString(pr," ��������: "))=="");
							p.property=s;
							break;
				case 'V':	while ((s=getString(pr," ��������: "))=="");
							p.value=s;
							break;
				case 'D':	t.properties.remove(p);
							return;
			}
			menu.title="\n^^^ ������������� �������� ������ �"+t.number+" ^^^\n"+t.toString()+"\n^^ ��������: "+p.property+"\t��������: "+p.value;
		}
	}
	static void manageProperties(ProductItem t) {
		String pr="^) ";
		char mn;
		Menu menu = new Menu("\n^^^^^^^^ �������� ������ �"+t.number+" ^^^^^^^^\n"+t.toString(),"^>", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('F',"����� ��������"),
					new MenuItem('S',"������� �������� �� ������"),
					new MenuItem('V',"�������� ���"),
					new MenuItem('A',"�������� ��������")
										}	);
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'S':	System.out.print("����� "+pr);
							manageProperty(t.properties.getProperty(getNumber()),t,false);
							break;
				case 'A':	manageProperty(t.properties.add(),t,true);
							break;
				case 'V':	System.out.println(pr+"��������\t  ��������");
							for(int i=0; i<t.properties.count; i++)
								System.out.println(pr+"#"+(i+1)+"\t"+t.properties.property[i].toStringTab());
							break;
				case 'F':	manageProperty(t.properties.getProperty(getString(pr,"��������: ")),t,false);
							break;
			}
			menu.title="\n ^^^^^^^^ ����� �"+t.number+" ^^^^^^^^\n"+t.toString();
		}
	}
	static void manageProductItem(ProductItem t) {
		String pr="*) ";
		if (t!=null) {
			String s;
			char mn;
			if (t.designation=="") {
				System.out.println("*** ����� ����� ***");
				while ((s=getString(pr," ��������: "))=="");
				t.designation=s;
				System.out.print(pr+" ����-���: ");
				t.scan=getLong();
				while ((s=getString(pr," ������� ���������: "))=="");
				t.measure=s;
				manageProperties(t);
			}
			Menu menu = new Menu("\n ******** ����� �"+t.number+" ********\n"+t.toString(),"*>", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('D',"������������� ��������"),
					new MenuItem('S',"������������� ����-���"),
					new MenuItem('M',"������������� ������� ���������"),
					new MenuItem('P',"������������� ��������")
									}	);
			while ((mn=menu.dlgMenu())!='Q') {
				switch (mn) {
					case 'D' :	while ((s=getString(pr," ��������: "))=="");
								t.designation=s;
								break;
					case 'S' :	System.out.print(pr+" ����-���: ");
								t.scan=getLong();
								break;
					case 'M' :	while ((s=getString(pr," ������� ���������: "))=="");
								t.measure=s;
								break;
					case 'P' :	manageProperties(t);
								break;
				}
				menu.title="\n ******** ����� �"+t.number+" ********\n"+t.toString();
			}
		}
		else error(pr,"�������� ������");
	}
	static void setProductSalePrice(Product t, String pr) {
		do {
			System.out.print(pr+"��������� ����:");
			t.salePrice=getFloat();
		} while (t.salePrice<=0);
	}
	static void manageProduct(Product t) {
		if (t!=null) {
			String pr="*) ";
			char mn;
			Menu menu = new Menu("\n ******** ������������� ����� �"+t.number+" ********\n"+t.toString(),"*>", new MenuItem[] {
						new MenuItem('Q',"�����"),
						new MenuItem('E',"������������� ���������"),
						new MenuItem('P',"������ ������"),
						new MenuItem('S',"������ ������"),
						new MenuItem('T',"���������� ����"),
						new MenuItem('R',"�������")					}	);
			while ((mn=menu.dlgMenu())!='Q') {
				switch (mn) {
					case 'E' :	manageProductItem(t);
								break;
					case 'R' :	System.out.println(pr+"��������: "+t.remain+t.measure);
								break;
					case 'T' :	setProductSalePrice(t,pr);
								break;
					case 'P' :	System.out.println(pr+"����� ��������:");
								for(int i=0; i<t.income.count; i++) {
									PurchaseInvoice p=(PurchaseInvoice) t.income.obj[i];
									System.out.println(pr+"��������� �"+p.number+" "+p.dateToString()+" "+p.countProduct(t)+t.measure);
								}
								break;
					case 'S' :	System.out.println(pr+"����� ������������:");
								for(int i=0; i<t.transfer.count; i++) {
									System.out.print(pr);
									switch (t.transfer.rec[i].type) {
										case 0 :	SalesInvoice s=(SalesInvoice) t.transfer.rec[i].obj;
													System.out.println("��������� �"+s.number+" "+s.dateToString()+" "+s.countProduct(t)+t.measure);
													break;
										case 1 :	Cheque c=(Cheque) t.transfer.rec[i].obj;
													System.out.println("��� �"+c.number+" "+c.dateToString()+" "+c.countProduct(t)+t.measure);
													break;
										case 2 :	/*���*/
													break;
									}
								}
								break;
				}
				menu.title="\n ******** ������������� ����� �"+t.number+" ********\n"+t.toString();
			}
		}
	}
	static Product maintainingProducts(boolean select, boolean purch) {
		Product t;
		String pr="*>";
		Menu menu;
		if (purch) menu= new Menu("\n********* ������ *********","**>", new MenuItem[] {
						new MenuItem('Q',"�����"),
						new MenuItem('V',"������� ���������"),
						new MenuItem('v',"������� �������"),
						new MenuItem('F',"����� �����"),
						new MenuItem('f',"����� ����� �� ����-����"),
						new MenuItem('N',"������� �����"),
						new MenuItem('S',"������� ����� �� ������") } );
		else menu= new Menu("\n ******** ������ ********","**>", new MenuItem[] {
				new MenuItem('Q',"�����"),
				new MenuItem('V',"������� ���������"),
				new MenuItem('v',"������� �������"),
				new MenuItem('F',"����� �����"),
				new MenuItem('f',"����� ����� �� ����-����"),
				new MenuItem('S',"������� ����� �� ������") } );
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
				case 'S' :	System.out.print("����� "+pr);
							t=shop.products.findProductByIndex(getNumber());
							if (select && (t!=null)) return t;
							manageProduct(t);
							break;
				case 'f' :	t=shop.products.findProductByScan(getLong());
							if (select && (t!=null)) return t;
							manageProduct(t);
							break;
				case 'F' :	t=shop.products.findProductByDesignation(getString(pr,"��������:"));
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
			s="\n-- ��������� ��������� �"+i.number+" --";
			pr="->";
		}
		else {
			s="\n++ ��������� ��������� �"+i.number+" ++";
			pr="+>";
		}
		if (i.status==DocStatus.READY)
			return  new Menu(s,pr, new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('V',"������� ������"),
					new MenuItem('H',"������� �����"),
					new MenuItem('E',"������������� �����"),
					new MenuItem('v',"������� �������"),
					new MenuItem('P',"������������� ������� �� ������"),
					new MenuItem('N',"�������� �������"),
					new MenuItem('A',"������������ ���������(������������)"),
					new MenuItem('C',"�������� ���������(������������)")
					 } );
		else
			return  new Menu(s+i.toStatusString()+pr.charAt(0)+pr.charAt(0),pr, new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('V',"������� ������"),
					new MenuItem('v',"������� �������"),
					new MenuItem('H',"������� �����") }  );
	}
	static void editInvoiceHeader(Invoice i, String header, String pr) {
		String s;
		Agent a;
		System.out.println(pr+header);
		do {
			s=getString(pr,"����("+ProductDoc.dateFormat+"):");
			if ((s=="") && (i.date!=0)) break;
			i.setDate(s);
		} while (i.date==0);
		do {
			System.out.print(pr+"����������:");
			a=maintainingAgents(true);
			if (a!=null) i.agent=a;
		} while (i.agent==null);
			
	}
	static void editPurchaseInvoiceHeader(PurchaseInvoice i, String header, String pr) {
		editInvoiceHeader(i,header,pr);
		String s=getString(pr,"����� ��������� ����������:");
		if (s!="") i.agentInvoiceNum=s;
		do {
			s=getString(pr,"���� ��������� ����������("+ProductDoc.dateFormat+"):");
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
		System.out.print(pr+" ����� ������:");
		ProductRecord prec=new ProductRecord(maintainingProducts(true,purch),0,0);
		if (prec.product==null) return null;
		do {
			System.out.print(pr+" ����������:");
			prec.quantity=getFloat();
		} while (prec.quantity<=0);
		do {
			System.out.print(pr+" ����:");
			prec.price=getFloat();
			if (!purch && (prec.price==0)) {
				prec.price=prec.product.salePrice;
				System.out.println(pr+"(auto) "+prec.price);
			}
		} while ((!purch)&&(prec.price<prec.product.salePrice));
		if (purch) 
			while (prec.product.salePrice<=prec.price) {
				if (!shop.markupPrice(prec))
				System.out.print(" ! ���������� ���� ������� ! ");
				setProductSalePrice(prec.product, pr);
			}
		return prec;
	}
	static void showProductRecord(int pos, ProductRecord pr, String prompt) {
		System.out.println(prompt+pr.toInvoiceString());
	}
	static boolean editProductRecord(int pos, ProductRecord pr, String prompt, boolean purch) {
		char mn;
		Menu menu = new Menu("\n,,,,,�������������� ������� �"+pos+",,,,,\n"+pr.toString(),",,>", new MenuItem[] {
				new MenuItem('Q',"�����"),
				new MenuItem('S',"������� �����"),
				new MenuItem('N',"���������� ������"),
				new MenuItem('P',"���� ������"),
				new MenuItem('D',"������� ��� �������")  } );
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'S' :	System.out.print(pr+" ����� ������:");
							pr.product=maintainingProducts(true,purch);
							break;
				case 'N' :	do {
								System.out.print(pr+" ���������� ������:");
								pr.quantity=getFloat();
							} while (pr.quantity<=0);
							break;
				case 'P' :	do {
								System.out.print(pr+" ���� ������:");
								pr.price=getFloat();
								if (!purch && (pr.price==0)) {
									pr.price=pr.product.salePrice;
									System.out.println(pr+"(auto) "+pr.price);
								}
							} while ((!purch)&&(pr.price<pr.product.salePrice));
							break;
				case 'D' :	return false;
			}
			menu.title="\n,,,,,�������������� ������� �"+pos+",,,,,\n"+pr.toString();
		}
		return true;
	}
	static void managePurchaseInvoice(PurchaseInvoice p){
		String pr="-)";
		char	mn;
		if (p.agent==null) editPurchaseInvoiceHeader(p,"--- ����� ��������� ��������� �"+p.number+" ---",pr);
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
				case 'P' :	System.out.print("����� ������� "+pr);
							int i=getNumber()-1;
							ProductRecord prec = p.toProductRecord(i);
							ProductRecord prec_clone=prec.clone();
							if (prec!=null) {
								if (editProductRecord(i+1,prec_clone,pr,true))
									p.changePos(i,prec_clone);
								else {
									p.removePos(i);
									System.out.println(pr+"������� �������");
								}	
							}
							break;
				case 'N' :	prec = newProductRecord(true,pr);
							if (prec!=null)
								p.addPos(prec);
							else
								error(pr,"������� �� ���������");
							break;
				case 'A' :	p.annul();
							System.out.println(pr+"��������� ������������");
							menu = selectInvoiceMenu(p,true);
							break;
				case 'C' :	int c=p.conduct();
							if (c==0) {
								System.out.println(pr+"��������� ��������� �������");
								menu=selectInvoiceMenu(p,true);
							}
							else
								error(pr,p.toErrorString(c));
			}
	}
	static void maintainingPurchaseInvoices (){
		String	pr="--)";
		Menu menu = new Menu("\n-------- ��������� ��������� ---------","-->", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('N',"������� ����� ���������"),
					new MenuItem('v',"C����� ���� (�� ������ � �����)"),
					new MenuItem('V',"C����� ���� (�� ����� � ������)"),
					new MenuItem('S',"������� ��������� �� ������") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'S' :	System.out.print("����� "+pr);
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
		if (s.agent==null) editInvoiceHeader(s,"+++ ����� ��������� ��������� �"+s.number+" +++",pr);
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
				case 'P' :	System.out.print("����� ������� "+pr);
							int i=getNumber()-1;
							ProductRecord prec = s.toProductRecord(i);
							ProductRecord prec_clone=prec.clone();
							if (prec!=null) {
								if (editProductRecord(i+1,prec_clone,pr,true))
									s.changePos(i,prec_clone);
								else {
									s.removePos(i);
									System.out.println(pr+"������� �������");
								}	
							}
							break;
				case 'N' :	prec = newProductRecord(false,pr);
							if (prec!=null)
								s.addPos(prec);
							else
								error(pr,"������� �� ���������");
							break;
				case 'A' :	s.annul();
							System.out.println(pr+"��������� ������������");
							menu = selectInvoiceMenu(s,false);
							break;
				case 'C' :	int c=s.conduct();
							if(c==0) {
								System.out.println(pr+"��������� ��������� �������");
								menu=selectInvoiceMenu(s,false);
							}
							else
								error(pr,s.toErrorString(c));
							break;
			}
	}
	static void maintainingSalesInvoices (){
		String	pr="++)";
		Menu menu = new Menu("\n++++++++ ��������� ��������� ++++++++","++>", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('N',"������� ����� ���������"),
					new MenuItem('v',"C����� ���� (�� ������ � �����)"),
					new MenuItem('V',"C����� ���� (�� ����� � ������)"),
					new MenuItem('S',"������� ��������� �� ������") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'S' :	System.out.print("����� "+pr);
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
		Menu menu = new Menu("\n&&&&&&&&&&&& ����������� &&&&&&&&&&","&&>", new MenuItem[] {
				new MenuItem('Q',"�����"),
				new MenuItem('M',"����� �� �����"),
				new MenuItem('B',"������"),
				new MenuItem('D',"�����"),
				new MenuItem('C',"������"),
				new MenuItem('d',"���� �����"),
				new MenuItem('w',"����� �����")		}	);
		while ((mn=menu.dlgMenu())!='Q') {
			switch (mn) {
				case 'M':	System.out.println(bo+shop.bookkeeping.money); break;
				case 'D':	System.out.println(bo+shop.bookkeeping.debit); break;
				case 'C':	System.out.println(bo+shop.bookkeeping.credit); break;
				case 'B':	System.out.println(bo+(shop.bookkeeping.credit-shop.bookkeeping.debit)); break;
				case 'd':	System.out.print(bo+"������� ����� ������ ++>");
							d=getDouble();
							if (d>0.0)
								System.out.println(bo+"������ "+d+" ������� (�� ����� "+ shop.bookkeeping.depositeMoney(d)+")");
							else
								System.out.println(bo+"������ �� �������");
							break;
				case 'w':	System.out.print(bo+"������� ����� ������� ++>");
							d=getDouble();
							if (d>0.0) {
								if (shop.bookkeeping.withdrawMoney(d))
									System.out.println(bo+"������ "+d+" �������� (�� ����� "+shop.bookkeeping.money+")");
								else
									System.out.println(bo+"������������ �������");
							}
							else
								System.out.println(bo+"������ �� ��������");
							break;
			}
			
		}
	}
	static Menu selectChequeMenu(Cheque c, String pr) {
		if (c.status==DocStatus.READY)
			return new Menu("\n$$$$$$$$ ��� �"+c.number+" $$$$"+c.toStatusString()+"$$$$\n"+
							"����: "+c.dateToString()+"\n�������: "+c.countPos+"\n�����: "+c.sum,pr, new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('V',"������� ������"),
					new MenuItem('v',"������� �������"),
					new MenuItem('P',"������������� ������� �� ������"),
					new MenuItem('N',"�������� �������"),
					new MenuItem('A',"������������ ���(������������)"),
					new MenuItem('C',"�������� ���- ������� (������������)") } );
		else
			return new Menu("\n$$$$$$$$ ��� �"+c.number+"$$$$"+c.toStatusString()+"$$$$\n"+
					"����: "+c.dateToString()+"\n�������: "+c.countPos+"\n�����: "+c.sum,pr, new MenuItem[] {
			new MenuItem('Q',"�����"),
			new MenuItem('V',"������� ������"),
			new MenuItem('v',"������� �������") }  );		
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
													"\n\t����������: "+p.quantity+
													"\n\t����: "+p.price+
													"\n  ����� �������: "+(p.quantity*p.price));
							}
							break;
				case 'P' :	System.out.print("����� ������� "+pr);
							int i=getNumber()-1;
							ProductRecord prec = c.toProductRecord(i);
							ProductRecord prec_clone=prec.clone();
							if (prec!=null) {
								if (editProductRecord(i+1,prec_clone,pr,false))
									c.changePos(i,prec_clone);
								else {
									c.removePos(i);
									System.out.println(pr+"������� �������");
								}	
							}
							break;
				case 'N' :	prec = newProductRecord(false,pr);
							if (prec!=null)
								c.addPos(prec);
							else
								error(pr,"������� �� ���������");
							break;
				case 'A' :	c.annul();
							System.out.println(pr+"��� �����������");
							menu = selectChequeMenu(c,pr);
							break;
				case 'C' :	int r=c.conduct();
							if (r==0) {
								System.out.println(pr+"��� ������ �������");
								menu=selectChequeMenu(c,pr);
							}
							else
								error(pr,c.toErrorString(r));
							break;
			}
			menu.title="\n$$$$$$$$ ��� �"+c.number+" $$$$"+c.toStatusString()+"$$$$\n"+
					"����: "+c.dateToString()+"\n�������: "+c.countPos+"\n�����: "+c.sum;
		}
	}
	static void maintainingCheques (){
		String	pr="$$)";
		Menu menu = new Menu("\n$$$$$$$$ ��������� ���� $$$$$$$$","$$>", new MenuItem[] {
					new MenuItem('Q',"�����"),
					new MenuItem('N',"������� ����� ���"),
					new MenuItem('v',"C����� ���� (�� ������ � �����)"),
					new MenuItem('V',"C����� ���� (�� ����� � ������)"),
					new MenuItem('S',"������� ��� �� ������") } );
		char	mn;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'S' :	System.out.print("����� "+pr);
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
		Menu menu = new Menu("\n============������� "+shop.name+"============","==>", new MenuItem[] {
				 new MenuItem('Q',"�����"),
				 new MenuItem('P',"��������� ���������"),
				 new MenuItem('S',"��������� ���������"),
				 new MenuItem('C',"������� �������"),
				 new MenuItem('T',"�������� ����������"),
				 new MenuItem('B',"�����������"),
				 new MenuItem('A',"�����������"),
				 new MenuItem('R',"������������� �������")	}	);
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
				case 'R':	shop.name=getString("==>","����� �������� ");
							menu.title="\n============������� "+shop.name+"============";
							break;
			}
		saveShop();
	}
		
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		// if (!shopload()) then
			// ELSE
		Menu menu = new Menu("\n<<<<< ���������� ������� >>>>>",">>", new MenuItem[] {
				 new MenuItem('Q',"�����"),
				 new MenuItem('L',"��������� ������� �� �����"),
				 new MenuItem('N',"������� ����� �������"),
				 new MenuItem('A',"�������") } );
		char mn;
		String fn;
		String name;
		float auto;
		double start;
		while ((mn=menu.dlgMenu())!='Q')
			switch (mn) {
				case 'L':	fn=getString(">>>","��� ����� ��� �������� ");
							if (fn!="")
								shop = loadShop(fn);
							else
								shop = loadShop(myShop);
							maintainingShop();
							break;
				case 'N':	name=getString(">>","�������� �������� >>>");
							System.out.print(">> ��������� ������� >>>");
							start=getDouble();
							System.out.print(">> ������� �������������� ������� (0-��� �������������)>>>");
							auto=getFloat();
							fn=getString(">>>","��� ����� ��� �������� ");
							shop = new Shop(start,auto);
							shop.name=name;
							shop.filename=fn;
							maintainingShop();
							break;
				case 'A':	System.out.println("\t\t\t���������� �������\n"+
												"    ��� ���������� ������� ����������� �� ������ ������������ �������7 ���,\n"+
												"���� ������� - ������������� ��������-���������������� ������� ��� ����������\n"+
												"������� ���������� (����������) �������. ���� ����������. ���������� �����������\n"+
												"����� ��� ����������, ����� ���������� ���������� ������� � �������� ������.\n"+
												"����������� �� ������� ����������� ����� ������ �������� � ���������� �������\n"+
												"� ������������ �������������, ����������� �� �������� �������� ����������.\n"+
												"    ���������� ���������� ���������� ���������� ���� ��� �����-������ ������. � �����\n"+
												"��������� ���� ��������� ��� ����������, � ��������������� ��� �������.\n\n"+
												"  �������� ������� ������ ���-120� ��������� �.�.");
							break;
			}
		scanner.close();
	}

}
