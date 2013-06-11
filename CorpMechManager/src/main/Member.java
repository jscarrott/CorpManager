package main;

import java.util.ArrayList;

public class Member {
	
	private String name;
	private ArrayList<Mech> mechList;
	
	public Member(String name2) {
		setName(name2);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Mech> getMechList() {
		return mechList;
	}
	public void setMechList(ArrayList<Mech> mechList) {
		this.mechList = mechList;
	}

}
