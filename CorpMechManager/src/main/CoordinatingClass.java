package main;

import java.util.ArrayList;
import java.util.Map;

public class CoordinatingClass {
	
	private Map<String , Member> members;
	private Map<String, Mech>  mechs;
	private Map<String, Formation> formations;
	
	public Map<String, Member> getMembers() {
		return members;
	}
	public void setMembers(Map<String, Member> members) {
		this.members = members;
	}
	public Map<String, Mech> getMechs() {
		return mechs;
	}
	public void setMechs(Map<String, Mech> mechs) {
		this.mechs = mechs;
	}
	
	boolean addNewMember(String name){
		try{
			members.put(name, new Member(name));
			return true;
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	boolean removeMember(String name){
		try{
					members.remove(name);
				
			
			return true;
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	boolean addNewMech(String name){
		try{
			mechs.put(name, new Mech(name));
			return true;
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	boolean removeMech(String name){
		try{
			mechs.remove(name);
			return true;
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean addNewVarient(String name){
		try{
			if(mechs.get(name) != null){
				 mechs.get(name).addVarient(name);
			}
			return true;
			
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}


boolean removeVarient(String name){
	try{
		if(mechs.get(name) != null){
			 mechs.get(name).removeVarient(name);
		}
		return true;
		
	} catch( Exception e) {
		e.printStackTrace();
		return false;
	}
}

void addFormation(String name, ArrayList<Varient> varientList){
	formations.put(name, new Formation(name, varientList));
}
}
