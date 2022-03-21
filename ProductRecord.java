package ua.khpi.oop;

import java.io.Serializable;

public class ProductRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	public Product	product;
	public float	quantity;
	public float	price;
	
	public ProductRecord() {}
	public ProductRecord(Product n, float q, float p) {
		product=n;
		quantity=q;
		price=p;
	}
	public String toString() {
		return "["+product.number+"] "+product.designation+" "+quantity+product.measure+" * "+price;
	}
	public String toInvoiceString() {
		return "["+product.number+"] "+product.designation+" ("+product.properties.toString()+") "+quantity+product.measure+" * "+price;
	}
	public ProductRecord clone() {
		ProductRecord pr = new ProductRecord(product,quantity,price);
		return pr;
	}
	public void returnProductRecord() {
		product.remain+=quantity;
	}
}

