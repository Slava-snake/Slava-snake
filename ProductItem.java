package ua.khpi.oop;

import java.io.Serializable;

public class ProductItem implements Serializable {
	private static final long serialVersionUID = 1L;
	public int				number;
	public String			designation;
	public String			measure;
	public long				scan;
	public Properties		properties;
	
	public ProductItem() {}
	public ProductItem(int n, String d, String m, long s, Properties p) {
		number=n;
		designation=d;
		measure=m;
		scan=s;
		properties=p;
	}
	public ProductItem(ProductItem p) {
		designation=p.toString();
		measure=p.toString();
		scan=p.scan;
		properties=p.properties;
	}
	public ProductItem(int n) {
		number=n;
		designation="";
		measure="";
		properties = new Properties();
	}
	public String toString() {
		return "#"+number+" "+designation+"\t("+properties.toString()+")\n   ["+scan+"] {"+measure+"}";
	}
	public String toShortString() {
		return "#"+number+" "+designation+" {"+measure+"}";
	}
}

