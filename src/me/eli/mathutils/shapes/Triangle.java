package me.eli.mathutils.shapes;


public class Triangle implements Polygon {
	
	private final double base, leg, height, hyp;
	
	public Triangle(double base, double leg, double hyp) {
		this.base = base;
		this.leg = leg;
		this.hyp = hyp;
		this.height = Math.sqrt((Math.pow(hyp, 2) + Math.pow(leg, 2) - Math.pow(base, 2)) / 2);
	}
	
	public boolean isRight() {
		return Math.pow(base, 2) + Math.pow(leg, 2) == Math.pow(hyp, 2);
	}
	
	public double getBase() {
		return base;
	}
	
	public double getLeg() {
		return leg;
	}
	
	public double getHypotenuse() {
		return hyp;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getA() {
		return Math.toDegrees(Math.acos(((Math.pow(base, 2) + Math.pow(hyp, 2)) - Math.pow(leg, 2)) / (2 * base * hyp)));
	}
	
	public double getB() {
		return Math.toDegrees(Math.acos(((Math.pow(leg, 2) + Math.pow(hyp, 2)) - Math.pow(base, 2)) / (2 * leg * hyp)));
	}
	
	public double getC() {
		return Math.toDegrees(Math.acos(((Math.pow(base, 2) + Math.pow(leg, 2)) - Math.pow(hyp, 2)) / (2 * base * leg)));
	}
	
	@Override
	public double getArea() {
		return (base * height) / 2;
	}

	@Override
	public int getPoints() {
		return 3;
	}

	@Override
	public double getPerimeter() {
		return base + leg + hyp;
	}

	@Override
	public boolean isParallelogram() {
		return false;
	}
}
