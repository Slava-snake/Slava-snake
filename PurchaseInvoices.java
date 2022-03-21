package ua.khpi.oop;

import java.io.Serializable;

public class PurchaseInvoices extends Folder <PurchaseInvoice> implements Serializable {
	private static final long serialVersionUID = 1L;
			String	prompt = "--)";
			
	public	PurchaseInvoices() {
		item = new PurchaseInvoice[capacity];
	}
	public	PurchaseInvoices(Bookkeeping b) {
		item = new PurchaseInvoice[capacity];
		bookkeeping=b;
	}
	private void resize() {
		if (count==capacity) {
			capacity*=2;
			PurchaseInvoice[] a = new PurchaseInvoice[capacity];
			a = item.clone();
			item = a;
		}
	}
	public PurchaseInvoice add() {
		resize();
		item[count] = new PurchaseInvoice(count,bookkeeping);
		return item[count++];
	}
	public PurchaseInvoice toPurchaseInvoice(int index) {
		if (validIndex(index)) return item[index];
		return null;
	}
}
