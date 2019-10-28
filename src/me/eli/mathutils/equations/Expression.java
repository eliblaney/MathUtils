package me.eli.mathutils.equations;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Expression {
	
	private final String s;
	private final ScriptEngine e = new ScriptEngineManager().getEngineByName("js");
	
	public Expression(String e) {
		this.s = e;
		try {
			this.e.eval("function ignore(e){}");
		} catch(ScriptException e1) {
			e1.printStackTrace();
		}
		verify();
	}
	
	public void verify() {
		try {
			e.eval("ignore(\"" + s + "\");");
		} catch(ScriptException e) {
			throw new IllegalArgumentException("Invalid expression!");
		}
	}
	
	public ScriptEngine getEngine() {
		return e;
	}
	
	public Solver getSolver() {
		return new Solver(this);
	}
	
	public Object solve() {
		return getSolver().solve();
	}
	
	@Override
	public String toString() {
		return s;
	}
	
}
