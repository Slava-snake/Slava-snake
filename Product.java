package ua.khpi.oop;

import java.io.Serializable;

public class Product extends ProductItem implements Serializable{
	private static final long serialVersionUID = 1L;
	public float					remain;
	public float					salePrice;
	public DynamObjArray			income;
	public DynamTypeSpendingArray	transfer;
	
	public Product() {}
	public Product(int n, String d, String m, long s, Properties p) {
		super(n, d, m, s, p);
		income = new DynamObjArray();
		transfer = new DynamTypeSpendingArray();
	}
	public Product(int n) {
		super(n);
		income = new DynamObjArray();
		transfer = new DynamTypeSpendingArray();
	}
	public String toTab() {
		return toString()+"\n   в наличии: "+remain+measure+"\n   цена: "+salePrice;
	}
}

