package main;

public class MechCount {
	int numberOfMechs;
	String nameOfMech;
	
	public MechCount(String mechName){
		 numberOfMechs = 1;
		 this.nameOfMech = mechName;
	}

	/**
	 * @return the numberOfMechs
	 */
	public int getNumberOfMechs() {
		return numberOfMechs;
	}

	/**
	 * @param numberOfMechs the numberOfMechs to set
	 */
	public void setNumberOfMechs(int numberOfMechs) {
		this.numberOfMechs = numberOfMechs;
	}

	/**
	 * @return the nameOfMech
	 */
	public String getNameOfMech() {
		return nameOfMech;
	}

	/**
	 * @param nameOfMech the nameOfMech to set
	 */
	public void setNameOfMech(String nameOfMech) {
		this.nameOfMech = nameOfMech;
	}

}
