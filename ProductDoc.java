package ua.khpi.oop;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDoc implements Serializable  {
	private static final long serialVersionUID = 1L;
	private		  int				capacity;
	public		  DocStatus			status;
	public		  int				number;
	public		  long				date;
	public		  float				sum;
	public		  int				countPos;
	public        Bookkeeping		bookkeeping;
	public		  ProductRecord[]	position;
	public static String 			dateFormat="yyyy.MM.dd";
	public static SimpleDateFormat	format = new SimpleDateFormat(dateFormat);
	
	public ProductDoc () {
		position = new ProductRecord[capacity];
	}
	public ProductDoc (int n, int cap) {
		capacity = cap;
		status = DocStatus.READY;
		number = n;
		position = new ProductRecord[capacity];
	}
	public char toStatusChar() {
		switch (status) {
			case READY 		: return '*';
			case CLOSED		: return ' ';
			case ANNULLED	: return '-';
		}
		return '@';
	}
	private void resize() {
		if(capacity<=countPos) {
			while (capacity<=countPos) capacity+=10;
			ProductRecord[] a = new ProductRecord[capacity];
			a=position.clone();
			position=a;
		}
	}
	private boolean validIndex(int n) {
		return (n>=0)&&(n<countPos);
	}
	public void addPos(ProductRecord pr) {
		resize();
		position[countPos]=pr;
		countPos++;
		sum+=pr.quantity*pr.price;
	}
	public ProductRecord toProductRecord(int n) {
		if (validIndex(n)) return position[n];
		return null;
	}
	public float countProduct(Product p) {
		float c=0;
		for(int i=0; i<countPos; i++)
			if (position[i].product.equals(p)) c+=position[i].quantity;
		return c;
	}
	public void changePos(ProductRecord pr_old, ProductRecord pr_new) {
		if (status==DocStatus.READY) {
			sum+=pr_new.quantity*pr_new.price-pr_old.quantity*pr_old.price;
			pr_old=pr_new.clone();
		}
	}
	public void changePos(int i, ProductRecord pr_new) {
		if ((status==DocStatus.READY) && validIndex(i)) {
			sum+=pr_new.quantity*pr_new.price-position[i].quantity*position[i].price;
			position[i]=pr_new;
		}
	}
	public boolean removePos(int n) {
		if ((status==DocStatus.READY) && validIndex(n)) {
			sum-=position[n].price*position[n].quantity;
			for(int i=n+1; i<countPos; i++)
				position[i-1]=position[i];
			countPos--;
			return true;
		}
		return false;
	}
	public boolean joinUpPositions() {
		boolean result=false;
		for(int i=0; (i-1)<countPos; i++) {
			ProductRecord pr1=position[i];
			for(int j=i+1; j<countPos;){
				ProductRecord pr2=position[j];
				if ((pr1.product.equals(pr2.product))&&(pr1.price==pr2.price)) {
					result=true;
					sum+=pr2.quantity*pr2.price;
					pr1.quantity+=pr2.quantity;
					removePos(j);
				}
				else j++;
			}
		}
		return result;
	}
	public int check() {
		if (status!=DocStatus.READY) return -1;
		if (date==0) return -2;
		if (countPos<=0) return -3;
		if (sum<=0) return -4;
		float su=0;
		for (int i=0; i<countPos; i++)
			su+=position[i].quantity*position[i].price;
		if (su!=sum) return -5;
		return 0;
	}
	public String toErrorString(int error) {
		switch (error) {
			case -1 : return "операция запрещена";
			case -2 : return "не установлена дата";
			case -3 : return "нет позиций";
			case -4 : return "нулевая сумма";
			case -5 : return "неверная сумма";
			case -6 : return "не выбран контрагент";
			default : if (error>0) return "нехватка товара - позиция #"+error+". "+
								position[error-1].product.toShortString()+
								" осталось "+position[error-1].product.remain+position[error-1].product.measure;
		}
		return "";
	}
	
	public String dateToString(long dt) {
		if (dt==0) return "";
		return format.format(new Date(dt));
	}
	public String dateToString() {
		if (date==0) return "";
		return format.format(new Date(date));
	}
	public void setDate(String s) {
		if (s=="") 
			date = new Date().getTime();
		else
			date = convertDate(s);
	}
	public long convertDate(String s) {
		try {
			return format.parse(s).getTime(); }
		catch (ParseException exc) {
			return 0;
		}
	}	
	
	public String toString() {
		return "№"+number+toStatusChar()+" "+dateToString()+" "+sum;
	}
	
	public void annul() {
		if (status==DocStatus.READY) 
			status=DocStatus.ANNULLED;
	}
	protected void purchase() {
		for (int i=0; i<countPos; i++) {
			position[i].product.remain+=position[i].quantity;
			position[i].product.income.add(this);
		}
		bookkeeping.purchase(sum);
		status= DocStatus.CLOSED;
	}
	protected int sale(int type) {
		int result=0;
		for (int i=0; i<countPos; i++)
			if ((position[i].product.remain-=position[i].quantity)<0)
				result=i+1;
		if (result!=0)
			for(int i=0; i<result; i++)
				position[i].returnProductRecord();
		if (result==0) {
			bookkeeping.sale(sum);
			status= DocStatus.CLOSED;
			for(int i=0; i<countPos; i++)
				position[i].product.transfer.add(new TypeSpending(type,this));
		}
		return result;
	}

}
