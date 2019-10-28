package me.eli.mathutils.fraction.graphing;

import java.util.ArrayList;
import java.util.List;

public class Plane {
	
	private final int dimensions;
	private final List<GraphComponent> pairs;
	
	{
		pairs = new ArrayList<GraphComponent>();
	}
	
	public Plane(int dimensions) {
		this.dimensions = dimensions;
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
	public void verify(GraphComponent p) {
		if(p.getDimensions() != getDimensions())
			throw new IllegalArgumentException("Attempted to place invalid pair in dimension");
	}
	
	public boolean contains(GraphComponent p) {
		loop: for(GraphComponent m : pairs) {
			if(!m.getClass().isAssignableFrom(p.getClass()) || !p.getClass().isAssignableFrom(m.getClass()))
				continue;
			for(int i = 0; i < m.getDimensions(); i++) {
				if(m.getPoints()[i] != p.getPoints()[i])
					continue loop;
			}
			return true;
		}
		return false;
	}
	
	public boolean add(GraphComponent p) {
		verify(p);
		if(contains(p))
			return false;
		return pairs.add(p);
	}
	
	public boolean remove(GraphComponent p) {
		verify(p);
		loop: for(GraphComponent m : pairs) {
			for(int i = 0; i < m.getDimensions(); i++) {
				if(m.getPoints()[i] != p.getPoints()[i])
					continue loop;
			}
			pairs.remove(m);
			return true;
		}
		return false;
	}
	
	public GraphComponent[] getComponents() {
		return pairs.toArray(new GraphComponent[pairs.size()]);
	}
	
	public int getComponentsAmount() {
		return getComponents().length;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(GraphComponent p : pairs)
			s += ", " + p.toString();
		return getDimensions() + "DPlane:[" + s.substring(2) + "]";
	}
	
}
