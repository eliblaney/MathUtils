package me.eli.mathutils;

public class SquareRoot {
	
	private double v;
	private String str = null;
	
	public SquareRoot(double value) {
		this.v = value;
		recalculate();
	}
	
	private void recalculate() {
		double root = Math.sqrt(v);
		if (root == Math.floor(root))
			str = "" + ((long) root);
		else
			str = recalculate(v);
	}
	
	private String recalculate(double v) {
		String str = null;
		int s = 2, inc = 3;
		long i = 4;
		while(i < v) {
			if(v % i == 0) {
				double vi = (v / i);
				boolean vii = vi == Math.floor(vi);
				if(s == 1)
					str = "√" + (vii ? ("" + vi).replace(".0", "") : vi);
				else
					str = s + "√" + (vii ? ("" + vi).replace(".0", "") : vi);
				String st = recalculate(v / i);
				String[] ar = st.split("√");
				if(ar[0].length() > 0)
					s *= Integer.parseInt(ar[0]);
				double d = Double.parseDouble(ar[1]);
				str = s + "√" + (d == Math.floor(d) ? ("" + d).replace(".0", "") : d);
				return str;
			}
			inc += 2;
			i += inc;
			s++;
		}
		str = "√" + (v == Math.floor(v) ? ("" + v).replace(".0", "") : v);
		return str;
	}
	
	public double getDecimal() {
		return Math.sqrt(v);
	}
	
	public double getValue() {
		return v;
	}
	
	public double setValue(double value) {
		double old = this.v;
		this.v = value;
		recalculate();
		return old;
	}
	
	public boolean isIrrational() {
		return toString().contains("√");
	}
	
	public String toString() {
		return str;
	}
}
