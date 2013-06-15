package main;

import java.util.ArrayList;

public class Formation {
	private String name;
	private ArrayList<String> composition;
	
	
	public Formation(String name2, ArrayList<String> varientList) {
		setName(name2);
		setComposition(varientList);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getComposition() {
		return composition;
	}
	public void setComposition(ArrayList<String> composition) {
		this.composition = composition;
	}
	
	
@Override
public String toString(){
	return name;
}
}
