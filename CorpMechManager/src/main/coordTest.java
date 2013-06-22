package main;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;


import org.junit.Before;
import org.junit.Test;

public class coordTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JAXBException, IOException, InterruptedException {
		CoordinatingClass test = new CoordinatingClass();
		test.readFromXmlFile();
		test.addNewMech("bob");
		test.addNewMech("JR7-F");
//		Mech rty = test.getMechs().get("bob");
		//Varient qwe = rty.getVarients().get("abbey5");
		test.addNewMember("sue");
		test.addNewMember("aaa");
		test.addNewMember("bbb");
		test.addNewMember("ccc");
		test.addNewMember("ddd");
		test.addNewMember("eee");
		test.addNewVarient("bob", "abbey");
		test.addNewVarient("JR7-F", "abbey9");
		test.addNewVarient("JR7-F", "abbey4");
		test.addNewVarient("JR7-F", "abbey5");
		test.addMechToMember("sue", test.getMechs().get("bob"));
		test.addMechToMember("sue", test.getMechs().get("JR7-F"));

		ArrayList<String> testformcomp = new ArrayList<>();
		testformcomp.add("bob");
		test.addFormation("test formation", testformcomp);
		test.saveToXmlFile();
		test.readFromXmlFile();
		Member testy = test.getMembers().get("sue");
		Mech jenner = test.getMechs().get("JR7-F");
		System.out.println(test.getMembers().get("sue").getMechList());
		test.getMembers();
		ArrayList<Member> cMembers = new ArrayList<>();
		cMembers.add(test.getMembers().get("sue"));
		Group qwerty = new Group(cMembers, test.getMechs());
	}

}
