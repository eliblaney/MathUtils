package me.eli.mathutils.equations;

import javax.script.ScriptException;

public class Solver {
	
	private final Expression e;
	
	public Solver(Expression e) {
		this.e = e;
	}
	
	public Expression getEquation() {
		return e;
	}
	
	public Object solve() {
		return solve(e);
	}
	
	public static Object solve(Expression e) {
		try {
			return e.getEngine().eval(e.toString());
		} catch(ScriptException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
}
