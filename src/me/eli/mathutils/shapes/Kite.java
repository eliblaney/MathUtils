package me.eli.mathutils.shapes;

public class Kite implements Polygon {
	
	private double a, b, d1, d2;
	
	public Kite(double a, double b, double d1, double d2) {
		this.a = a;
		this.b = b;
		this.d1 = d1;
		this.d2 = d2;
	}
	
	@Override
	public double getArea() {
		return (d1 * d2) / 2;
	}

	@Override
	public int getPoints() {
		return 4;
	}

	@Override
	public double getPerimeter() {
		return (2 * a) + (2 * b);
	}

	@Override
	public boolean isParallelogram() {
		return false;
	}
}
