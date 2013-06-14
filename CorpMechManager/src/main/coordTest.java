package main;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;


import org.junit.Before;
import org.junit.Test;

public class coordTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JAXBException, FileNotFoundException {
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
		test.saveToXmlFile();
		test.readFromXmlFile();
		Member testy = test.getMembers().get("sue");
		System.out.println(test.getMembers().get("Sue").getMechList());
		test.getMembers();
	}

}
