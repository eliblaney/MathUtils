package me.eli.mathutils.fraction;

public class Fraction implements IFraction {
	
	private double numerator = 0, denominator = 1, decimal = 0;
	
	public Fraction(double n, double d) {
		this.numerator = n;
		this.denominator = d;
		this.decimal = this.numerator / this.denominator;
	}
	
	public Fraction copy() {
		return new Fraction(numerator, denominator);
	}
	
	@Override
	public double getDecimal() {
		return this.decimal;
	}
	
	@Override
	public double getNumerator() {
		return this.numerator;
	}

	@Override
	public double getDenominator() {
		return this.denominator;
	}

	@Override
	public double setNumerator(double n) {
		double t = this.numerator;
		this.numerator = n;
		return t;
	}

	@Override
	public double setDenominator(double n) {
		double t = this.denominator;
		this.denominator = n;
		if(this.denominator == 0)
			throw new ArithmeticException("Denominator can not be 0!");
		return t;
	}
	
	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(decimal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(denominator);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(numerator);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (Double.doubleToLongBits(decimal) != Double.doubleToLongBits(other.decimal))
			return false;
		if (Double.doubleToLongBits(denominator) != Double.doubleToLongBits(other.denominator))
			return false;
		if (Double.doubleToLongBits(numerator) != Double.doubleToLongBits(other.numerator))
			return false;
		return true;
	}
}
