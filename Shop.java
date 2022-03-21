package ua.khpi.oop;

import java.io.Serializable;

public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	public Bookkeeping		bookkeeping;
	public Products			products;
	public PurchaseInvoices	purchaseInvoices;
	public SalesInvoices	salesInvoices;
	public Cheques			cheques;
	public Agents			agents;
	public float			autoMarkupPrices;
	public String			filename;
	public String			name;
	
	public Shop() {}
	public Shop(double start) {
		bookkeeping = new Bookkeeping(start);
		agents = new Agents();
		purchaseInvoices = new PurchaseInvoices(bookkeeping);
		salesInvoices = new SalesInvoices(bookkeeping);
		products = new Products();
		cheques = new Cheques(bookkeeping);
	}
	public Shop(double start, float autoPercents) {
		bookkeeping = new Bookkeeping(start);
		agents = new Agents();
		purchaseInvoices = new PurchaseInvoices(bookkeeping);
		salesInvoices = new SalesInvoices(bookkeeping);
		products = new Products();
		cheques = new Cheques(bookkeeping);
		autoMarkupPrices = autoPercents/100+1;
	}
	public boolean markupPrice(ProductRecord pr) {
		if (autoMarkupPrices>1) {
			if (pr.product.salePrice < (pr.price*autoMarkupPrices))
				pr.product.salePrice = pr.price*autoMarkupPrices;
			return true;
		}
		else return false;
	}
}
