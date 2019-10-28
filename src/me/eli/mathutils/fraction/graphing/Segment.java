package me.eli.mathutils.fraction.graphing;

import me.eli.mathutils.SquareRoot;

public class Segment implements GraphComponent {
	
	private MultidimensionalPair a, b;
	
	public Segment(MultidimensionalPair a, MultidimensionalPair b) {
		this.a = a;
		this.b = b;
		if(a.getDimensions() != b.getDimensions())
			throw new IllegalArgumentException("Dimension mismatch!");
	}
	
	public MultidimensionalPair getEndA() {
		return a;
	}
	
	public MultidimensionalPair getEndB() {
		return b;
	}
	
	public double[] getPoints() {
		double[] l = new double[a.getPoints().length + b.getPoints().length];
		for(int i = 0; i < l.length; i++) {
			l[i] = a.getPoints()[i];
			l[i] = b.getPoints()[i];
		}
		return l;
	}
	
	public int getDimensions() {
		return a.getDimensions();
	}
	
	public double getLength() {
		return a.distance(b);
	}
	
	public SquareRoot getLengthRoot() {
		return a.distanceRoot(b);
	}
	
	@Override
	public String toString() {
		return "{" + a.toString() + " : " + b.toString() + "}"; 
	}
	
}
