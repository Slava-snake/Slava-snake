package ua.khpi.oop;

import java.io.Serializable;

public class Cheques extends Folder<Cheque> implements Serializable {
	private static final long serialVersionUID = 1L;

	public Cheques() {
		item = new Cheque[capacity];
	}
	public Cheques(Bookkeeping b) {
		item = new Cheque[capacity];
		bookkeeping = b;
	}
	private void resize() {
		if (count==capacity) {
			capacity*=2;
			Cheque[] a = new Cheque[capacity];
			a = item.clone();
			item = a;
		}
	}
	public Cheque add() {
		resize();
		item[count] = new Cheque(count,bookkeeping);
		return item[count++];
	}
	public Cheque toCheque(int index) {
		if (validIndex(index)) return item[index];
		return null;
	}
}
