package main;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(namespace = "CorpManager")
@XmlType(propOrder = { "members", "mechs", "formations"})
public class MappableCoordClass {
	
	private Map<String , MappableMember> members;
	private Map<String, MappableMech>  mechs;
	private Map<String, MappableFormation> formations;
	
	
	public Map<String, MappableMember> getMembers() {
		return members;
	}
	public void setMembers(Map<String, MappableMember> members) {
		this.members = members;
	}
	public Map<String, MappableMech> getMechs() {
		return mechs;
	}
	public void setMechs(Map<String, MappableMech> mechs) {
		this.mechs = mechs;
	}
	public Map<String, MappableFormation> getFormations() {
		return formations;
	}
	public void setFormations(Map<String, MappableFormation> formations) {
		this.formations = formations;
	}

}
