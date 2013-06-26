package main;

import java.util.ArrayList;
import java.util.Iterator;

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
	public void generateMechCount(ArrayList<Mech> allMechs) {//TODO bug where mechs are duplicated and the count mysteriously increased. 
		mechCount = new ArrayList<>();
		for(Mech mech : allMechs){
			int endCheck = 0;
			if(mechCount.size() == 0){
				mechCount.add(new MechCount(mech.getName()));
				endCheck++;
			}
			else{
				ArrayList<MechCount> buffCount = mechCount;
				CounterLoop : for(MechCount mechCounter : buffCount){
				
			
				if(mechCounter.getNameOfMech().equals(mech.getName())){
					for(MechCount mechCounter2 : mechCount){
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
				if(endCheck == mechCount.size()){
					mechCount.add(new MechCount(mech.getName()));
					break CounterLoop;
				}
				
			}
				
				}
			
			
//			MechCount buff = new MechCount(mech.getName());
		}
		Iterator<MechCount> it = mechCount.iterator();
		while(it.hasNext()){
			MechCount buff = it.next();
			System.out.println("Formation mech Count");
			System.out.println(buff.nameOfMech);
			System.out.println(buff.numberOfMechs);
		}
	}
@Override
public String toString(){
	return name;
}
}
