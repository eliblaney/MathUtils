package me.eli.mathutils.shapes;

import java.math.BigDecimal;

import me.eli.mathutils.MathUtils;

public class Circle implements Shape {
	
	private double radius = Double.NaN;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double setRadius(double radius) {
		double old = this.radius;
		this.radius = radius;
		return old;
	}
	
	@Override
	public double getArea() {
		return MathUtils.π.multiply(new BigDecimal("" + Math.pow(radius, 2))).doubleValue();
	}
	
	public double getCircumference() {
		return MathUtils.τ.multiply(new BigDecimal("" + radius)).doubleValue();
	}
	
}
