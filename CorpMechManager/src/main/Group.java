package main;

import java.util.ArrayList;
import java.util.Iterator;
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
				ArrayList<MechCount> buffCount = theMechCount;
				CounterLoop : for(MechCount mechCounter : buffCount){
				
			
				if(mechCounter.getNameOfMech().equals(mech.getName())){
					for(MechCount mechCounter2 : theMechCount){
						if(mechCounter2.getNameOfMech().equals(mechCounter.getNameOfMech())){
							int tally = mechCounter2.getNumberOfMechs();
							tally++;
							mechCounter2.setNumberOfMechs(tally);
							endCheck = 0;
							break CounterLoop;
						}
					}
					
				}
				endCheck++;
				if(endCheck == theMechCount.size()){
					theMechCount.add(new MechCount(mech.getName()));
					break CounterLoop;
				}
				
			}
			}
			
//			MechCount buff = new MechCount(mech.getName());
		}
		Iterator<MechCount> it = theMechCount.iterator();
		while(it.hasNext()){
			MechCount buff = it.next();
			System.out.println("Group mech count");
			System.out.println(buff.nameOfMech);
			System.out.println(buff.numberOfMechs);
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

	/**
	 * @return the theMechCount
	 */
	public ArrayList<MechCount> getTheMechCount() {
		return theMechCount;
	}

	/**
	 * @param theMechCount the theMechCount to set
	 */
	public void setTheMechCount(ArrayList<MechCount> theMechCount) {
		this.theMechCount = theMechCount;
	}

}
