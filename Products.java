package ua.khpi.oop;

import java.io.Serializable;

public class Products implements Serializable {
	private static final long serialVersionUID = 1L;
	public int count;
	private int capacity=100;
	public Product[] 	product;

//ublic Products({}
	public Products() {
		product = new Product[capacity];
	}
	private boolean validIndex(int n) {
		return (n>=0)&&(n<count);
	}
	public Product findProductByDesignation(String s) {
		for (int i=0; i<count; i++)
			if (product[i].designation.matches(s)) return product[i];
		return null;
	}
	public Product findProductByScan(long s) {
		for (int i=0; i<count; i++)
			if (product[i].scan==s) return product[i];
		return null;
	}
	public Product findProductByIndex(int i) {
		if (validIndex(i)) return product[i];
		return null;
	}
	public int findProduct(ProductItem p) {//-1=no such; -2=two match...
		int result=0;
		int once=0;
		for (int i=0; i<count; i++)
			if ((product[i].designation.matches(p.designation))&&
				(product[i].measure.matches(p.measure))&&
				(product[i].scan==p.scan)&&
				(product[i].properties.sameAs(p.properties))) {
				result=i;
				once++;
			}
		return (once==0) ? -1 : (once==1)?result:-once;
	}
	public boolean onlyOne(ProductItem p) {
		return findProduct(p)>=0;
	}
	private void resize() {
		if (count==capacity) {
			capacity*=2;
			Product[] a = new Product[capacity];
			product = a;
		}
	}
	public Product add() {
		resize();
		product[count]=new Product(count++);
		return product[count-1];
	}
	public int add(ProductItem p) {
		if (findProduct(p)==-1) {
			resize();
			product[count].designation=p.designation;
			product[count].measure=p.measure;
			product[count].scan=p.scan;
			product[count].properties=p.properties;
			return count++;
		}
		else return -1;
	}
	public boolean incoming(int numP, float quant, int purch) {
		boolean result=validIndex(numP);
		if (result) {
			product[numP].income.add(purch);
			product[numP].remain+=quant;
		}	
		return result;
	}
	public float getRemain(int index) {
		if (validIndex(index)) return product[index].remain;
		return 0;
	}
	public float getSalePrice(int index) {
		if (validIndex(index)) return product[index].salePrice;
		return 0;
	}
	public Product getProduct(int index) {
		if (validIndex(index)) return product[index];
		return null;
	}
}

