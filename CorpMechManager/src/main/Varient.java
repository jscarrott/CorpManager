package main;

public class Varient {
	
	private String name;
	private String smurfyURL;
	private Mech parentMech;
	
	public Varient(String name2, Mech mech) {
		setName(name2);
		setMech(mech);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSmurfyURL() {
		return smurfyURL;
	}
	public void setSmurfyURL(String smurfyURL) {
		this.smurfyURL = smurfyURL;
	}
	public Mech getMech() {
		return parentMech;
	}
	public void setMech(Mech mech) {
		this.parentMech = mech;
	}

}
