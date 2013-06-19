package main;

import java.util.ArrayList;

public class MappableMech {
	
	private String name;
	private ArrayList<MappableVarient> varients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<MappableVarient> getVarients() {
		return varients;
	}
	public void setVarients(ArrayList<MappableVarient> mapVar) {
		this.varients = mapVar;
	}

}
