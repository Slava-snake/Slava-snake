package ua.khpi.oop;

import java.io.Serializable;

public class SalesInvoice extends Invoice implements Serializable{
	private static final long serialVersionUID = 1L;
			String	prompt = "+)";
			
	public SalesInvoice(int n, Bookkeeping b) {
		super(n);
		bookkeeping = b;
	}
	public int conduct() {
		int result=check();
		if (result==0) 
			result = sale(0);
		return result;
	}
	public String toString() {
		return prompt+super.toString();
	}
	public String toTitle() {
		return "++++++++ Расходная накладная "+super.toTitle()+" ++++++++";
	}
	public String toHeader() {
		return toTitle()+"\nсумма: "+sum+"\nполучатель: "+agent.name;
	}
}
