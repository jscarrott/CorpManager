package main;

import gui.GUImain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.util.Callback;

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
	private long lastTimeBuff;

	public CoordinatingClass() throws JAXBException, IOException, InterruptedException {
		members = new TreeMap<String, Member>();
		mechs = new TreeMap<String, Mech>();
		formations = new TreeMap<String, Formation>();
		readFromXmlFile();
		File buffPathFile = new File("data.xml");
		lastTimeBuff = buffPathFile.lastModified();
		pollFile();
	}
	
	void pollFile(){
		Timer timer = new Timer();

		timer.schedule( new TimerTask() {
		    
		    File buffPathFile = new File("data.xml");
			public void run() {	
		    	if(lastTimeBuff != buffPathFile.lastModified()){
		    		try {
						readFromXmlFile();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("poll file");
					lastTimeBuff = buffPathFile.lastModified();
		    	}
		    }
		 }, 0, 5*1000);
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

	public boolean addNewMember(String name) {
		try {
			members.put(name, new Member(name));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeMember(String name) {
		try {
			members.remove(name);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addNewMech(String name) {
		try {
			mechs.put(name, new Mech(name));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeMech(String name) {
		try {
			mechs.remove(name);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addNewVarient(String name, String varName) {
		try {

			mechs.get(name).addVarient(varName);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeVarient(String name, String varName) {
		try {
			if (mechs.get(name) != null) {
				mechs.get(name).removeVarient(varName);
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void addFormation(String name, ArrayList<String> varientList) {
		formations.put(name, new Formation(name, varientList));
	}

	public void addMechToMember(String name, Mech mech) {
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
			ArrayList<MappableVarient> mapVar = new  ArrayList<>();
			for (Varient var : mech.getVarients()) {
				MappableVarient buffMapVar = new MappableVarient();
				buffMapVar.setName(var.getName());
				buffMapVar.setParentMech(var.getMech());
				buffMapVar.setSmurfyURL(var.getSmurfyURL());
				mapVar.add(buffMapVar );
//				for(MappableVarient mapperVar : mapVar){
//					if(var.getName().equals(mapperVar.getName())){
//						mapperVar.setName(var.getName());
//						mapperVar.setSmurfyURL(mapperVar.getSmurfyURL());
//					}
//				}
				//mapVar.get(var.getName()).setName(var.getName());
			//.get(var.getName()).setName(var.getMech());
			//	mapVar.get(var.getName()).setName(var.getSmurfyURL());
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

	public void addNewMember(String name, ArrayList<String> mechList) {
		members.put(name, new Member(name));
		members.get(name).setMechList(mechList);

	}

	public void addNewMech(String name, ArrayList<MappableVarient> arrayList) {
		mechs.put(name, new Mech(name));
		Collection< MappableVarient> collection = arrayList;
	//	MappableVarient[] collArray = (MappableVarient[]) collection.toArray();
	//	collArray[0].getName();
		for (MappableVarient mapVarient : arrayList) {
			mechs.get(name).addVarient(mapVarient.getName());
			MappableVarient check = mapVarient;
			check = mapVarient;
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

	public void addNewVarient(String name, String varName, String smurfyURL) {
		mechs.get(name).addVarient(varName, smurfyURL);
		
	}

	public ArrayList<Varient> getAllVarients() {
		Collection<Mech> mechs = getMechs().values();
		ArrayList<Varient> varients = new ArrayList<>();
		for(Mech mech : mechs){
			ArrayList<Varient> buffVarients= mech.getVarients();
			for(Varient var : buffVarients){
				varients.add(var);
			}
		}
		return varients;
	}
	
	public void getUsableFormations(ArrayList<Member> currentMembers){
		ArrayList<Formation> allFormations = new ArrayList<>();
		for(Formation form : getFormations().values()){
			allFormations.add(form);
		}
		
	}
}
