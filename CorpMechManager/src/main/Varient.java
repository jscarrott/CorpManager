package main;

import javax.xml.bind.annotation.XmlTransient;

public class Varient {
	
	private String name;
	private String smurfyURL;
	private String parentMech;
	
	public Varient(String name2, String mech) {
		setName(name2);
		setMech(mech);
		setSmurfyURL("empty");
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
	public String getMech() {
		return parentMech;
	}
	@XmlTransient
	public void setMech(String mech) {
		this.parentMech = mech;
	}

	@Override
	public
	String toString(){
		return this.name;
	}
}
