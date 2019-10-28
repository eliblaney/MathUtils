package me.eli.mathutils.fraction;

public class UnsolvedFraction implements IFraction {
	
	private double known = 0;
	
	private FractionLocation unknown;
	
	public UnsolvedFraction(double known, FractionLocation unknown) {
		this.known = known;
		this.unknown = unknown;
		if(this.unknown == null)
			throw new IllegalArgumentException("FractionLocation can not be null!");
		if(this.unknown == FractionLocation.DENOMINATOR && known == 0)
			throw new ArithmeticException("Denominator can not be 0!");
	}
	
	public double getKnown() {
		return known;
	}
	
	public FractionLocation getUnknown() {
		return unknown;
	}
	
	public UnsolvedFraction recreate(double known, FractionLocation unknown) {
		this.known = known;
		this.unknown = unknown;
		return this;
	}
	
	public UnsolvedFraction copy() {
		return new UnsolvedFraction(known, unknown);
	}
	
	public Fraction solve(double x) {
		if(unknown == FractionLocation.NUMERATOR)
			return new Fraction(x, known);
		else if(unknown == FractionLocation.NUMERATOR)
			return new Fraction(known, x);
		return null;
	}
	
	@Override
	public double getDecimal() {
		return Double.NaN;
	}
	
	@Override
	public double getNumerator() {
		return getUnknown() == FractionLocation.NUMERATOR ? Double.NaN : known;
	}

	@Override
	public double getDenominator() {
		return getUnknown() == FractionLocation.DENOMINATOR ? Double.NaN : known;
	}

	@Override
	public double setNumerator(double n) {
		if(getUnknown() == FractionLocation.NUMERATOR)
			throw new IllegalStateException("Numerator is reserved for variable!");
		double t = this.known;
		this.known = n;
		return t;
	}

	@Override
	public double setDenominator(double n) {
		if(getUnknown() == FractionLocation.DENOMINATOR)
			throw new IllegalStateException("Denominator is reserved for variable!");
		double t = this.known;
		this.known = n;
		if(this.known == 0)
			throw new ArithmeticException("Denominator can not be 0!");
		return t;
	}
	
	@Override
	public String toString() {
		if(unknown == FractionLocation.NUMERATOR)
			return "x/" + known;
		else if(unknown == FractionLocation.DENOMINATOR)
			return known + "/x";
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(known);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((unknown == null) ? 0 : unknown.hashCode());
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
		UnsolvedFraction other = (UnsolvedFraction) obj;
		if (Double.doubleToLongBits(known) != Double.doubleToLongBits(other.known))
			return false;
		if (unknown != other.unknown)
			return false;
		return true;
	}
}
