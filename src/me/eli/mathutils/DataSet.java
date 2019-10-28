package me.eli.mathutils;

public class DataSet<A, B> {
	
	private A a;
	private B b;
	
	public DataSet() {
		this.a = null;
		this.b = null;
	}
	
	public DataSet(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	public A getA() {
		return a;
	}
	
	public B getB() {
		return b;
	}
	
	public A setA(A a) {
		A old = this.a;
		this.a = a;
		return old;
	}
	
	public B setB(B b) {
		B old = this.b;
		this.b = b;
		return old;
	}
	
}
