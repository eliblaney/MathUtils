package me.eli.mathutils.shapes;

public class Trapazoid implements Polygon {
	
	private double base1, base2, leg1, leg2, height;
	
	public Trapazoid(double base1, double base2, double leg1, double leg2, double height) {
		this.base1 = base1;
		this.base2 = base2;
		this.leg1 = leg1;
		this.leg2 = leg2;
		this.height = height;
	}
	
	@Override
	public double getArea() {
		return ((base1 + base2) / 2) * height;
	}

	@Override
	public int getPoints() {
		return 4;
	}

	@Override
	public double getPerimeter() {
		return base1 + base2 + leg1 + leg2;
	}

	@Override
	public boolean isParallelogram() {
		return false;
	}
}
