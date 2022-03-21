package ua.khpi.oop;

import java.io.Serializable;

public class Bookkeeping implements Serializable {
	private static final long serialVersionUID = 1L;
	public double	debit;
	public double	credit;
	public double	money;
	public double	deposited;
	public double	withdrawn;
	
	public Bookkeeping() {}
	public Bookkeeping(double start) {
		money=start;
	}
	public double depositeMoney(double m) {
		deposited+=m;
		return money+=m;
	}
	public boolean withdrawMoney(double m) {
		if (m>money) return false;
		withdrawn+=m;
		money-=m;
		return true;
	}
	public void purchase(float m) {
		debit+=(double)m;
		money-=(double)m;
	}
	public void sale(float m) {
		credit+=(double)m;
		money+=(double)m;
	}
}
