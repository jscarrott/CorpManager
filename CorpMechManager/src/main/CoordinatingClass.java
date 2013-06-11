package main;

import java.util.Collection;

public class CoordinatingClass {
	
	private Collection<Member> members;
	private Collection<Mech> mechs;
	
	public Collection<Member> getMembers() {
		return members;
	}
	public void setMembers(Collection<Member> members) {
		this.members = members;
	}
	public Collection<Mech> getMechs() {
		return mechs;
	}
	public void setMechs(Collection<Mech> mechs) {
		this.mechs = mechs;
	}
	
	boolean addNewMember(String name){
		try{
			members.add(new Member(name));
			return true;
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	boolean removeMember(String name){
		try{
			for(Member iter : members){
				if(iter.getName().equals(name)){
					members.remove(iter);
				}
			}
			return true;
		} catch( Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
