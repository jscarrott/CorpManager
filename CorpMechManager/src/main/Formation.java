package main;

import java.util.ArrayList;

public class Formation {
	private String name;
	private ArrayList<String> composition;
	private ArrayList<MechCount> mechCount;

	
	
	public Formation(String name2, ArrayList<String> varientList) {
		setName(name2);
		setComposition(varientList);

		mechCount = new ArrayList<>();
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
	
	
/**
	 * @return the mechCount
	 */
	public ArrayList<MechCount> getMechCount() {
		return mechCount;
	}
	/**
	 * @param mechCount the mechCount to set
	 */
	public void generateMechCount(ArrayList<Mech> allMechs) {
		for(Mech mech : allMechs){
			int endCheck = 0;
			if(mechCount.size() == 0){
				mechCount.add(new MechCount(mech.getName()));
				endCheck++;
			}
			else{
				CounterLoop : for(MechCount mechCounter : mechCount){

			
				if(mechCounter.getNameOfMech().equals(mech.getName())){
					int tally = mechCounter.getNumberOfMechs();
					tally++;
					mechCounter.setNumberOfMechs(tally);
					endCheck = 0;
					break CounterLoop;
				}
				endCheck++;
				if(endCheck == mechCount.size()){
					mechCount.add(new MechCount(mech.getName()));
				}
				
			}
			}
			
//			MechCount buff = new MechCount(mech.getName());
		}
	}
@Override
public String toString(){
	return name;
}
}
