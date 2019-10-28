package me.eli.mathutils.fraction;

public interface IFraction {
	
	public double getDecimal();
	
	public double getNumerator();
	
	public double getDenominator();
	
	public double setNumerator(double n);
	
	public double setDenominator(double n);
	
	public static enum FractionLocation {
		NUMERATOR, DENOMINATOR;
	}
}
