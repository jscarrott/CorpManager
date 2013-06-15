package main;

import java.util.ArrayList;

public class Member {
	
	private String name;
	private ArrayList<String> mechList;
	
	
	
	public Member(String name2) {
		setName(name2);
		mechList = new ArrayList<String>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getMechList() {
		return mechList;
	}
	public void setMechList(ArrayList<String> mechList) {
		this.mechList = mechList;
	}
	public void addMech(Mech newMech) {
		this.mechList.add(newMech.getName());
		
	}
	@Override
	public String toString(){
		 return name;
	}

}
