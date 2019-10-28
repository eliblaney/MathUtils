package me.eli.mathutils.polynomial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
	
	private static final Pattern POLYNOMIAL_PATTERN = Pattern.compile("(-?\\w*\\b\\d+)x\\^(-?\\w*\\d+\\b)", Pattern.CASE_INSENSITIVE | Pattern.COMMENTS);
	private Monomial[] monomials;
	
	public Polynomial(Monomial... m) {
		this.monomials = m;
		simplify();
	}
	
	public Polynomial(List<Monomial> monomials) {
		this(monomials.toArray(new Monomial[monomials.size()]));
	}
	
	public Polynomial(String polynomial) {
		this(parse(polynomial));
	}
	
	public Polynomial(List<Long> coefficients, List<Integer> exponents) {
		monomials = toMonomials(coefficients, exponents);
		simplify();
	}
	
	public static Monomial[] parse(String polynomial) {
		// format polynomial to standard format
		polynomial = polynomial.toLowerCase().replaceAll("[^\\^](\\d+)(^x|$)", "$1x^0").replaceAll("(\\D|^)x", "1x").replaceAll("x($|[^\\^])", "x^1").replace(" ", "");
		Matcher m = POLYNOMIAL_PATTERN.matcher(polynomial);
		// compile all the things into a list of coefficients and exponents
		List<Long> coefficients = new ArrayList<>();
		List<Integer> exponents = new ArrayList<>();
		int i = 0;
		while(m.find()) {
			for(i = 0; i < m.groupCount() + 1; i++) {
				String s = m.group(i);
				try {
					switch(i % 3) {
						case 0: // the monomial as a string
							continue;
						case 1: // the coefficient
							coefficients.add(Long.parseLong(s));
							break;
						case 2: // the exponent
							exponents.add(Integer.parseInt(s));
					}
				} catch(NumberFormatException e) {
					System.out.println("Error parsing: " + s);
				}
			}
		}
		fillMissing(coefficients, exponents);
		return toMonomials(coefficients, exponents);
	}
	
	private static void fillMissing(List<Long> coefficients, List<Integer> exponents) {
		// fill in missing terms (monomials with 0 coefficients)
		int laste = Integer.MIN_VALUE;
		for(int i = 0; i < exponents.size(); i++) {
			int e = exponents.get(i);
			while(laste > e + 1) {
				coefficients.add(i, 0L);
				exponents.add(i, --laste);
			}
			laste = e;
		}
		// fill in trailing terms (monomials with 0 coefficients -> 0 constant)
		while(laste > 0) {
			coefficients.add(0L);
			exponents.add(--laste);
		}
	}
	
	private void fillMissing() {
		List<Long> coefficients = new ArrayList<>();
		List<Integer> exponents = new ArrayList<>();
		for(Monomial m : monomials) {
			coefficients.add(m.getCoefficient());
			exponents.add(m.getExponent());
		}
		fillMissing(coefficients, exponents);
		this.monomials = toMonomials(coefficients, exponents);
	}
	
	private static Monomial[] toMonomials(List<Long> coefficients, List<Integer> exponents) {
		Monomial[] monomials = new Monomial[coefficients.size()];
		for(int i = 0; i < coefficients.size(); i++)
			monomials[i] = new Monomial(coefficients.get(i), exponents.get(i));
		return monomials;
	}
	
	private void simplify() {
		List<Monomial> monomials = new ArrayList<>();
		this.monomials = getNonZeroMonomials();
		for(int i = getDegree(); i >= 0; i--) {
			Monomial[] m = getMonomialsWithExponent(i);
			long c = 0;
			for(Monomial mono : m)
				c += mono.getCoefficient();
			monomials.add(new Monomial(c, i));
		}
		this.monomials = monomials.toArray(new Monomial[monomials.size()]);
		fillMissing();
	}
	
	public Monomial[] getMonomials() {
		return monomials.clone();
	}
	
	public Monomial[] getNonZeroMonomials() {
		List<Monomial> m = new ArrayList<>();
		for(Monomial monomial : monomials) {
			if(monomial.getCoefficient() != 0)
				m.add(monomial);
		}
		return m.toArray(new Monomial[m.size()]);
	}
	
	private Monomial[] getMonomialsWithExponent(int exponent) {
		List<Monomial> m = new ArrayList<>();
		for(Monomial monomial : monomials) {
			if(monomial.getExponent() == exponent)
				m.add(monomial);
		}
		return m.toArray(new Monomial[m.size()]);
	}
	
	public int getDegree() {
		int degree = 0;
		for(Monomial monomial : monomials) {
			int e = monomial.getExponent();
			if(e > degree)
				degree = e;
		}
		return degree;
	}
	
	public long[] getCoefficients() {
		long[] c = new long[monomials.length];
		int i = 0;
		for(Monomial m : monomials)
			c[i++] = m.getCoefficient();
		return c;
	}
	
	public int[] getExponents() {
		int[] e = new int[monomials.length];
		int i = 0;
		for(Monomial m : monomials)
			e[i++] = m.getExponent();
		return e;
	}
	
	public Polynomial negate() {
		List<Monomial> monomials = new ArrayList<>();
		for(Monomial m : getMonomials())
			monomials.add(new Monomial(-m.getCoefficient(), m.getExponent()));
		return new Polynomial(monomials);
	}
	
	public Polynomial add(Polynomial p) {
		simplify();
		p.simplify();
		int d = getDegree();
		if(p.getDegree() > d)
			d = p.getDegree();
		List<Monomial> monomials = new ArrayList<>();
		for(int i = d; i >= 0; i--) {
			// these should only have 0-1 elements each because they're simplified
			Monomial[] m = getMonomialsWithExponent(i);
			Monomial[] mp = p.getMonomialsWithExponent(i);
			if(m.length == 0) // exponent not defined? just skip it
				monomials.add(mp[0]);
			else if(mp.length == 0)
				monomials.add(m[0]);
			else // add coefficients
				monomials.add(new Monomial(m[0].getCoefficient() + mp[0].getCoefficient(), i));
		}
		Polynomial polynomial = new Polynomial(monomials);
		polynomial.simplify(); // for good measure
		return polynomial;
	}
	
	public Polynomial subtract(Polynomial p) {
		return add(p.negate()); // add the negative
	}
	
	public Polynomial multiply(Polynomial p) {
		simplify();
		p.simplify();
		List<Monomial> monomials = new ArrayList<>();
		// distribute all the terms
		for(Monomial m1 : getMonomials()) {
			for(Monomial m2 : p.getMonomials())
				monomials.add(m1.multiply(m2));
		}
		Polynomial polynomial = new Polynomial(monomials);
		polynomial.simplify();
		return polynomial;
	}
	
	public RationalFraction divide(Polynomial p) {
		return new RationalFraction(this, p);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(monomials);
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
		Polynomial other = (Polynomial) obj;
		if (!Arrays.equals(monomials, other.monomials))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < monomials.length; i++) {
			Monomial m = monomials[i];
			long c = m.getCoefficient();
			int e = m.getExponent();
			if (c != 0)
				s += (c < 0 ? ("-" + (i == 0 ? "" : " ") + -c) : ((i == 0 ? "" : "+ ") + c)) + (e == 0 ? "" : ("x" + (e != 1 ? "^" + e : ""))) + " ";
		}
		return s;
	}

}
