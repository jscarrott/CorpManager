package main;

import gui.GUImain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Application;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "CorpManager")
@XmlType(propOrder = { "members", "mechs", "formations" })
public class CoordinatingClass {

	private Map<String, Member> members;
	private Map<String, Mech> mechs;
	private Map<String, Formation> formations;

	public CoordinatingClass() throws FileNotFoundException, JAXBException {
		members = new TreeMap<String, Member>();
		mechs = new TreeMap<String, Mech>();
		formations = new TreeMap<String, Formation>();
		readFromXmlFile();
	}

	public Map<String, Member> getMembers() {
		return members;
	}

	@XmlElement
	public void setMembers(Map<String, Member> members) {
		this.members = members;
	}

	public Map<String, Mech> getMechs() {
		return mechs;
	}

	@XmlElement
	public void setMechs(Map<String, Mech> mechs) {
		this.mechs = mechs;
	}

	boolean addNewMember(String name) {
		try {
			members.put(name, new Member(name));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean removeMember(String name) {
		try {
			members.remove(name);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean addNewMech(String name) {
		try {
			mechs.put(name, new Mech(name));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean removeMech(String name) {
		try {
			mechs.remove(name);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean addNewVarient(String name, String varName) {
		try {

			mechs.get(name).addVarient(varName);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	boolean removeVarient(String name) {
		try {
			if (mechs.get(name) != null) {
				mechs.get(name).removeVarient(name);
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	void addFormation(String name, ArrayList<String> varientList) {
		formations.put(name, new Formation(name, varientList));
	}

	void addMechToMember(String name, Mech mech) {
		members.get(name).addMech(mech);
	}

	public void saveToXmlFile() throws JAXBException {
		String Config_XML = "data.xml";
		MappableCoordClass mapCoord = new MappableCoordClass();
		TreeMap<String, MappableMember> mapMembers = new TreeMap<>();
		for (Member member : members.values()) {
			mapMembers.put(member.getName(), new MappableMember());
			mapMembers.get(member.getName()).setName(member.getName());
			mapMembers.get(member.getName()).setMechList(member.getMechList());
		}
		mapCoord.setMembers(mapMembers);

		TreeMap<String, MappableMech> mapMechs = new TreeMap<>();

		for (Mech mech : mechs.values()) {
			mapMechs.put(mech.getName(), new MappableMech());
			mapMechs.get(mech.getName()).setName(mech.getName());
			TreeMap<String, MappableVarient> mapVar = new TreeMap<>();
			for (Varient var : mech.getVarients().values()) {
				mapVar.put(var.getName(), new MappableVarient());
				mapVar.get(var.getName()).setName(var.getName());
				mapVar.get(var.getName()).setName(var.getMech());
				mapVar.get(var.getName()).setName(var.getSmurfyURL());
			}
			mapMechs.get(mech.getName()).setVarients(mapVar);

		}
		mapCoord.setMechs(mapMechs);

		TreeMap<String, MappableFormation> mapForm = new TreeMap<>();
		for (Formation form : formations.values()) {
			mapForm.put(form.getName(), new MappableFormation());
			mapForm.get(form.getName()).setName(form.getName());
			mapForm.get(form.getName()).setComposition(form.getComposition());
		}

		mapCoord.setFormations(mapForm);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(MappableCoordClass.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(mapCoord, System.out);

		// Write to File
		m.marshal(mapCoord, new File(Config_XML));
	}

	public void readFromXmlFile() throws JAXBException, FileNotFoundException {
		String Config_XML = "data.xml";

		JAXBContext context = JAXBContext.newInstance(MappableCoordClass.class);
		Unmarshaller um = context.createUnmarshaller();

		MappableCoordClass readInCoordClass = (MappableCoordClass) um.unmarshal(new FileReader(Config_XML));
		for (MappableMech mapMech : readInCoordClass.getMechs().values()) {
			addNewMech(mapMech.getName(), mapMech.getVarients());
		}

		for (MappableMember mapMember : readInCoordClass.getMembers().values()) {
			addNewMember(mapMember.getName(), mapMember.getMechList());
		}

		for (MappableFormation mapForm : readInCoordClass.getFormations().values()) {
			addFormationMap(mapForm.getName(), mapForm.getComposition());
		}
	}

	private void addFormationMap(String name, ArrayList<String> composition) {
		ArrayList<String> mechListBuff = new ArrayList<String>();

		for (String mapVar : composition) {
			mechListBuff.add(mapVar);
		}

		formations.put(name, new Formation(name, mechListBuff));

	}

	private void addNewMember(String name, ArrayList<String> mechList) {
		members.put(name, new Member(name));
		members.get(name).setMechList(mechList);

	}

	private void addNewMech(String name, Map<String, MappableVarient> varients) {
		mechs.put(name, new Mech(name));
		for (MappableVarient mapVarient : varients.values()) {
			mechs.get(name).addVarient(mapVarient.getName());
		}

	}

	@XmlElement
	private void setFormations(Map<String, Formation> formationsIn) {
		this.formations = formationsIn;

	}

	public Map<String, Formation> getFormations() {
		return formations;
	}

	public static void main(String[] args) {
		Application.launch(GUImain.class);

	}
}
