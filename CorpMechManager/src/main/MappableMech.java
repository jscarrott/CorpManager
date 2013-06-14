package main;

import java.util.Map;

public class MappableMech {
	
	private String name;
	private Map<String, MappableVarient> varients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, MappableVarient> getVarients() {
		return varients;
	}
	public void setVarients(Map<String, MappableVarient> varients) {
		this.varients = varients;
	}

}
