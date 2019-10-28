package me.eli.mathutils.polynomial;

public class Monomial {
	
	private final long coefficient;
	private final int exponent;
	
	public Monomial(long coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	public long getCoefficient() {
		return this.coefficient;
	}
	
	public int getExponent() {
		return this.exponent;
	}
	
	public Monomial negate() {
		return new Monomial(-getCoefficient(), getExponent());
	}
	
	public Monomial reciprocal() {
		// invert coefficient and negate the exponent
		return new Monomial(1 / getCoefficient(), -getExponent());
	}
	
	public Monomial add(Monomial m) {
		if(getExponent() != m.getExponent())
			throw new IllegalArgumentException("Unequal exponents");
		//add coefficients if equal exponents
		return new Monomial(getCoefficient() + m.getCoefficient(), getExponent());
	}
	
	public Monomial subtract(Monomial m) {
		return add(m.negate()); // add the negative
	}
	
	public Monomial multiply(Monomial m) {
		// multiply coefficients, add exponents
		return new Monomial(getCoefficient() * m.getCoefficient(), getExponent() + m.getExponent());
	}
	
	public Monomial divide(Monomial m) {
		// multiply by the reciprocal
		return multiply(m.reciprocal());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (coefficient ^ (coefficient >>> 32));
		result = prime * result + exponent;
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
		Monomial other = (Monomial) obj;
		if (coefficient != other.coefficient)
			return false;
		if (exponent != other.exponent)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		// shortened names for laziness
		long c = this.coefficient;
		int e = this.exponent;
		return(c + (e == 0 ? "" : ("x" + (e != 1 ? "^" + e : ""))));
	}
}
