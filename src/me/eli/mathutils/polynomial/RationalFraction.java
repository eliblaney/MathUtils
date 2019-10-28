package me.eli.mathutils.polynomial;

public class RationalFraction {
	
	private final Polynomial numerator, denominator;
	
	public RationalFraction(Polynomial numerator, Polynomial denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public RationalFraction(Polynomial numerator) {
		this.numerator = numerator;
		this.denominator = new Polynomial(new Monomial(1, 0));
	}
	
	public Polynomial getNumerator() {
		return numerator;
	}
	
	public Polynomial getDenominator() {
		return denominator;
	}
	
	public RationalFraction negate() {
		return new RationalFraction(numerator.negate(), denominator);
	}
	
	public RationalFraction reciprocal() {
		return new RationalFraction(getDenominator(), getNumerator());
	}
	
	public RationalFraction add(RationalFraction rf) {
		Polynomial d = getDenominator();
		Polynomial rd = rf.getDenominator();
		if(d.equals(rd))
			return new RationalFraction(getNumerator().add(rf.getNumerator()), d);
		// get a common denominator and add
		return new RationalFraction(getNumerator().multiply(rd).add(rf.getNumerator().multiply(d)), d.multiply(rd));
	}
	
	public RationalFraction subtract(RationalFraction rf) {
		return add(rf.negate());
	}
	
	public RationalFraction multiply(RationalFraction rf) {
		return new RationalFraction(getNumerator().multiply(rf.getNumerator()), getDenominator().multiply(rf.getDenominator()));
	}
	
	public RationalFraction divide(RationalFraction rf) {
		return multiply(rf.reciprocal());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result + ((numerator == null) ? 0 : numerator.hashCode());
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
		RationalFraction other = (RationalFraction) obj;
		if (denominator == null) {
			if (other.denominator != null)
				return false;
		} else if (!denominator.equals(other.denominator))
			return false;
		if (numerator == null) {
			if (other.numerator != null)
				return false;
		} else if (!numerator.equals(other.numerator))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + getNumerator() + ") / (" + getDenominator() + ")";
	}
	
}
