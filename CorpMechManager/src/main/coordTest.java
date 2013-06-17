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
		test.addNewMech("bob");
		test.addNewMember("sue");
		test.addNewMember("aaa");
		test.addNewMember("bbb");
		test.addNewMember("ccc");
		test.addNewMember("ddd");
		test.addNewMember("eee");
		test.addNewVarient("bob", "abbey");
		test.addMechToMember("sue", test.getMechs().get("bob"));
		ArrayList<String> testformcomp = new ArrayList<>();
		testformcomp.add("bob");
		test.addFormation("test formation", testformcomp);
		test.saveToXmlFile();
		test.readFromXmlFile();
		Member testy = test.getMembers().get("sue");
		System.out.println(test.getMembers().get("sue").getMechList());
		test.getMembers();
	}

}
