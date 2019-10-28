package me.eli.mathutils.shapes;

public class Rectangle implements Polygon {
	
	private double length, width;
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getWidth() {
		return width;
	}

	@Override
	public double getArea() {
		return length * width;
	}

	@Override
	public int getPoints() {
		return 4;
	}

	@Override
	public double getPerimeter() {
		return (2 * length) + (2 * width);
	}

	@Override
	public boolean isParallelogram() {
		return true;
	}
	
}
