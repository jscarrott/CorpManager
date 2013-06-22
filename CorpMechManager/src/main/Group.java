package main;

import java.util.ArrayList;
import java.util.Map;

public class Group {
	
	private ArrayList<Member> members;
	private ArrayList<Mech> mechs;
	private Map<String, Mech> allMechs;
	private ArrayList<MechCount> theMechCount;
	
	public Group(ArrayList<Member> members,  Map<String, Mech> mechs){
		setMembers(members);
		setAllMechs(mechs);
		setMechs(getAllMechs(members));
		theMechCount = new ArrayList<>();
		fillInMechCount();
	}
	
	public ArrayList<Mech> getAllMechs(ArrayList<Member> currentMembers){
		ArrayList<Mech> availableMechs = new ArrayList<>();
		for(Member member : currentMembers){
			ArrayList<String> memberMechs = member.getMechList();
			for(String mechName : memberMechs){
				availableMechs.add(allMechs.get(mechName));
			}
			
		}
		return availableMechs;
	}

	
	public void fillInMechCount(){
		
		for(Mech mech : mechs){
			int endCheck = 0;
			if(theMechCount.size() == 0){
				theMechCount.add(new MechCount(mech.getName()));
				endCheck++;
			}
			else{
				CounterLoop : for(MechCount mechCounter : theMechCount){
			boolean match;
			
				if(mechCounter.getNameOfMech().equals(mech.getName())){
					int tally = mechCounter.getNumberOfMechs();
					tally++;
					mechCounter.setNumberOfMechs(tally);
					match = true;
					endCheck = 0;
					break CounterLoop;
				}
				endCheck++;
				if(endCheck == theMechCount.size()){
					theMechCount.add(new MechCount(mech.getName()));
				}
				
			}
			}
			
//			MechCount buff = new MechCount(mech.getName());
		}
	}
	/**
	 * @return the members
	 */
	public ArrayList<Member> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}

	/**
	 * @return the mechs
	 */
	public ArrayList<Mech> getMechs() {
		return mechs;
	}

	/**
	 * @param mechs the mechs to set
	 */
	public void setMechs(ArrayList<Mech> mechs) {
		this.mechs = mechs;
	}

	/**
	 * @return the allMechs
	 */
	public Map<String, Mech> getAllMechs() {
		return allMechs;
	}

	/**
	 * @param allMechs the allMechs to set
	 */
	public void setAllMechs(Map<String, Mech> allMechs) {
		this.allMechs = allMechs;
	}

}
