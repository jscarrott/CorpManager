package main;

import java.util.ArrayList;

public class Formation {
	private String name;
	private ArrayList<Varient> composition;
	
	
	public Formation(String name2, ArrayList<Varient> varientList) {
		setName(name2);
		setComposition(varientList);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Varient> getComposition() {
		return composition;
	}
	public void setComposition(ArrayList<Varient> composition) {
		this.composition = composition;
	}
	
	

}
