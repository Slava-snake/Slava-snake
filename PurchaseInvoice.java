package ua.khpi.oop;

import java.io.Serializable;

public class PurchaseInvoice extends Invoice implements Serializable {
	private static final long serialVersionUID = 1L;
			String	prompt = "-)";
	public	String	agentInvoiceNum;
	public	long	agentInvoiceDate;

	public PurchaseInvoice() {} 
	public PurchaseInvoice(int n, Bookkeeping b) {
		super(n);
		bookkeeping = b;
		agentInvoiceNum="";
	}
	public int conduct() {
		int result=check();
		if (result==0) purchase();
		return result;
	}
	public String toString() {
		return prompt+super.toString();
	}
	public String toTitle() {
		return "-------- Приходная накладная "+super.toTitle()+" --------";
	}
	public String toHeader() {
		return toTitle()+"\nсумма: "+sum+"\nпоставщик: "+agent.name+
						 "\n  накладная: "+agentInvoiceNum+"  дата: "+dateToString(agentInvoiceDate);
	}
}
