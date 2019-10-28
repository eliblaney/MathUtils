package me.eli.mathutils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import me.eli.mathutils.equations.Expression;
import me.eli.mathutils.fraction.Fraction;
import me.eli.mathutils.fraction.IFraction.FractionLocation;
import me.eli.mathutils.fraction.UnsolvedFraction;
import me.eli.mathutils.fraction.graphing.MultidimensionalPair;
import me.eli.mathutils.fraction.graphing.Plane;
import me.eli.mathutils.fraction.graphing.Segment;
import me.eli.mathutils.polynomial.Monomial;
import me.eli.mathutils.polynomial.Polynomial;
import me.eli.mathutils.polynomial.RationalFraction;

public final class MathUtils {
	
	public static final BigDecimal π = new BigDecimal("3.14159265358979323846264338327050288419716939937510582097494459230781640628620899862803482534211706798");
	public static final BigDecimal PI = π;
	public static final BigDecimal τ = π.multiply(new BigDecimal("2"));
	public static final BigDecimal TAU = τ;
	
	private MathUtils() {};
	
	public static void main(String... args) {
		Plane p = new Plane(3);
		p.add(new MultidimensionalPair(1, 2, 3));
		p.add(new Segment(new MultidimensionalPair(2, 3, 4), new MultidimensionalPair(3, 4, 5)));
		System.out.println(p);
		String e = "7 | 3";
		System.out.println(e + " = " + new Expression(e).getSolver().solve());
		System.out.println();
		
		String[] polynomials = {"x", "x^2", "-5x^4 + 3x^2 - 12", "2x^12 - 11x + 3", "5x - 1"};
		for(String s : polynomials) {
			Polynomial poly = new Polynomial(s);
			System.out.print("Polynomial: ");
			System.out.println(poly);
			System.out.print("Derivative: ");
			System.out.println(MathUtils.derive(poly));
			System.out.println();
		}
		Polynomial p1 = new Polynomial("5x^3 - 2x^2 + 7x - 3");
		Polynomial p2 = new Polynomial("2x - 1");
		System.out.println("        P1 : " + p1);
		System.out.println("        P2 : " + p2);
		System.out.println("       dP1 : " + derive(p1));
		System.out.println("       dP2 : " + derive(p2));
		System.out.println("   P1 + P2 : " + p1.add(p2));
		System.out.println("d(P1 + P2) : " + derive(p1.add(p2)));
		System.out.println("   P1 - P2 : " + p1.subtract(p2));
		System.out.println("d(P1 - P2) : " + derive(p1.subtract(p2)));
		System.out.println("   P1 * P2 : " + p1.multiply(p2));
		System.out.println("d(P1 * P2) : " + derive(p1.multiply(p2)));
		System.out.println("   P1 / P2 : " + p1.divide(p2));
		System.out.println("d(P1 / P2) : " + derive(p1.divide(p2)));
	}
	
	public static double add(double... in) {
		double n = 0;
		for(double d : in)
			n += d;
		return n;
	}
	
	public static double multiply(double... in) {
		double n = 1;
		for(double d : in)
			n *= d;
		return n;
	}
	
	public static double divide(double n, double... in) {
		for(double d : in)
			n /= d;
		return n;
	}
	
	public static double arithmeticMean(double... in) {
		return add(in) / in.length;
	}
	
	public static double geometricMean(double... in) {
		return Math.sqrt(multiply(in));
	}
	
	public static SquareRoot geometricMeanRoot(double... in) {
		return new SquareRoot(multiply(in));
	}
	
	public static double standardDeviation(double... v) {
		return standardDeviationRoot(v).getDecimal();
	}
	
	public static double median(Double... v) {
		LinkedList<Double> l = new LinkedList<Double>(Arrays.asList(v));
		while(l.size() > 2) {
			l.remove(0);
			l.remove(l.size() - 1);
		}
		if (l.size() < 2)
			return l.get(0);
		return arithmeticMean(l.get(0), l.get(1));
	}
	
	public static double[] mode(Double... v) {
		HashMap<Double, Integer> count = new HashMap<Double, Integer>();
		for(Double d : v) {
			if (count.containsKey(d))
				count.put(d, count.get(d) + 1);
			else
				count.put(d, 1);
		}
		LinkedList<Entry<Double, Integer>> top = new LinkedList<Entry<Double, Integer>>();
		for(Entry<Double, Integer> e : count.entrySet()) {
			if (top.size() < 1 || e.getValue().intValue() > top.getFirst().getValue().intValue()) {
				top.clear();
				top.add(e);
			} else if (top.size() > 0 && e.getKey().doubleValue() != top.getFirst().getKey().doubleValue() && e.getValue().intValue() == top.getFirst().getValue().intValue())
				top.add(e);
		}
		double[] d = new double[top.size()];
		for(int i = 0; i < d.length; i++)
			d[i] = top.get(i).getKey().doubleValue();
		return d;
	}
	
	public static SquareRoot standardDeviationRoot(double... v) {
		double mean = 0;
		for(double d : v)
			mean += d;
		mean /= v.length;
		double[] v2 = new double[v.length];
		for(int i = 0; i < v2.length; i++)
			v2[i] = Math.pow(v[i] - mean, 2);
		double total = 0;
		for(double d : v2)
			total += d;
		total /= v.length;
		return new SquareRoot(total);
	}
	
	public static double pythagoreanTheorem(double a, double b) {
		return pythagoreanTheoremRoot(a, b).getDecimal();
	}
	
	public static SquareRoot pythagoreanTheoremRoot(double a, double b) {
		return new SquareRoot(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	public static DataSet<Double, Double> quadraticFormula(double a, double b, double c) {
		DataSet<Double, Double> d = new DataSet<Double, Double>();
		double f = ((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
		double s = ((-b) - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
		d.setA(f);
		d.setB(s);
		return d;
	}
	
	public static boolean proportion(Fraction a, Fraction b) {
		return a.getNumerator() * b.getDenominator() == a.getDenominator() * b.getNumerator();
	}
	
	public static double proportion(Fraction a, UnsolvedFraction b) {
		if (b.getUnknown() == FractionLocation.DENOMINATOR)
			return (a.getDenominator() * b.getKnown()) / a.getNumerator();
		else if (b.getUnknown() == FractionLocation.NUMERATOR)
			return (a.getNumerator() * b.getKnown()) / a.getDenominator();
		return Double.NaN;
	}
	
	public static Polynomial derive(Polynomial p) {
		Monomial[] oldm = p.getMonomials();
		Monomial[] monomials = new Monomial[oldm.length];
		int i = 0;
		for(Monomial m : oldm) // derive each monomial
			monomials[i++] = derive(m);
		return new Polynomial(monomials);
	}
	
	public static Monomial derive(Monomial m) {
		long c = m.getCoefficient();
		int e = m.getExponent();
		// power rule
		return new Monomial(c * e, c != 0 ? e - 1 : 0);
	}
	
	public static RationalFraction derive(RationalFraction rf) {
		Polynomial n = rf.getNumerator();
		Polynomial d = rf.getDenominator();
		// quotioent rule
		return new RationalFraction(d.multiply(derive(n)).subtract(n.multiply(derive(d))), d.multiply(d));
	}
}
