package me.eli.mathutils.fraction.graphing;

import java.util.Arrays;

import me.eli.mathutils.SquareRoot;

public class MultidimensionalPair implements GraphComponent {
	
	private double[] points;
	
	public MultidimensionalPair(double... points) {
		this.points = points;
	}
	
	public double[] getPoints() {
		return Arrays.copyOf(points, points.length);
	}
	
	public int getDimensions() {
		return points.length;
	}
	
	public double distance(MultidimensionalPair p) {
		return distanceRoot(p).getDecimal();
	}
	
	public SquareRoot distanceRoot(MultidimensionalPair p) {
		if(p.getDimensions() != getDimensions())
			throw new IllegalArgumentException("Pairs in different dimensions");
		double d = 0;
		for(int i = 0; i < getDimensions(); i++) 
			d += Math.pow(p.getPoints()[i] - getPoints()[i], 2);
		return new SquareRoot(d);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(double d : points)
			s += ", " + d;
		return "(" + s.substring(2) + ")";
	}
	
}
