package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Mech {

	private String name;
	private Map<String, Varient> varients;
	
	public Mech(String name2) {
		varients = new TreeMap<String,Varient>();
		setName(name2);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Varient> getVarients() {
		return varients;
	}
	public void setVarients(Map<String, Varient> varients) {
		this.varients = varients;
	}
	
	public void addVarient(String name){
		varients.put(name, new Varient(name, this.getName()));
	}
	public boolean removeVarient(String name){
		if(varients.remove(name) != null){
			return true;
		}
		else{return false;}
	}
}
