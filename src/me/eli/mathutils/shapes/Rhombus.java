package me.eli.mathutils.shapes;

public class Rhombus implements Polygon {
	
	private double side, d1, d2;
	
	public Rhombus(double side, double d1, double d2) {
		this.side = side;
		this.d1 = d1;
		this.d2 = d2;
	}
	
	public double getSide() {
		return side;
	}
	
	public double getDiagonal1() {
		return d1;
	}
	
	public double getDiagonal2() {
		return d2;
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
		return 4 * side;
	}

	@Override
	public boolean isParallelogram() {
		return true;
	}
}
