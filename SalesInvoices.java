package ua.khpi.oop;

import java.io.Serializable;

public class SalesInvoices extends Folder <SalesInvoice> implements Serializable {
	private static final long serialVersionUID = 1L;
		String	prompt = "++)";
			
	public SalesInvoices() {
		item = new SalesInvoice[capacity];
	}
	public SalesInvoices(Bookkeeping b) {
		item = new SalesInvoice[capacity];
		bookkeeping = b;
	}
	private void resize() {
		if(capacity<=count) {
			while (capacity<=count) capacity*=2;
			SalesInvoice[] a = new SalesInvoice[capacity];
			a = item.clone();
			item = a;
		}
	}
	public int indexSalesInvoice(SalesInvoice s) {
		int result=0;
		int once=0;
		for (int i=0; i<count; i++) 
			if ((item[i].agent.equals(s.agent))&&
				(item[i].date==s.date)&&
				(item[i].sum==s.sum)) {
				result=i;
				once++;
			}
		return (once==0) ? -1 : (once==1)?result:-once;
	}	
	public boolean onlyOne(SalesInvoice p) {
		return indexSalesInvoice(p)>=0;
	}	
	public SalesInvoice add() {
		resize();
		item[count] = new SalesInvoice(count,bookkeeping);
		return item[count++];
	}
	public SalesInvoice toSalesInvoice(int index) {
		if (validIndex(index)) return item[index];
		return null;
	}
}
