package me.eli.mathutils.fraction.graphing;


public class OrderedPair extends MultidimensionalPair {
	
	private double x, y;
	
	public OrderedPair(double x, double y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
