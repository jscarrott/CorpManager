package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Mech {

	private String name;
	private ArrayList<Varient> varients;
	
	public Mech(String name2) {
		varients = new ArrayList<Varient>();
		setName(name2);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Varient> getVarients() {
		return varients;
	}
	public void setVarients(ArrayList<Varient> varients) {
		this.varients = varients;
	}
	
	public void addVarient(String name){
		varients.add( new Varient(name, this.getName()));
	}
	public boolean removeVarient(String name){
		if(varients.remove(name)){
			return true;
		}
		else{return false;}
	}
	
	@Override
	public String toString(){
		return name;
	}
	public void addVarient(String varName, String smurfyURL) {
		varients.add( new Varient(varName,  this.getName()));
		for(Varient varient : varients){
			if(varient.getName().equals(varName)){
				varient.setSmurfyURL(smurfyURL);
			}
		}
		
		
	}
}
