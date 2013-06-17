package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.CoordinatingClass;
import main.Mech;

public class AddMemberPageController  extends AnchorPane implements Initializable {
	
	@FXML javafx.scene.control.ListView<Mech> allMechList;
	@FXML javafx.scene.control.ListView<Mech> memberMechList;
	@FXML TextField nameField;
	CoordinatingClass coord1;
	private ObservableList<Mech> allMechs;
	private ObservableList<Mech> theirMechs;
	

	public void setApp(CoordinatingClass coord) {
	 coord1 = coord;
		
	 allMechs = FXCollections.observableArrayList(coord.getMechs().values());
		allMechList.setItems(allMechs);
		allMechs.addListener(new ListChangeListener<Mech>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Mech> c) {
				allMechList.setItems(allMechs);
				
			}
		});	
		
		theirMechs = FXCollections.observableArrayList();
		memberMechList.setItems(theirMechs);
		theirMechs.addListener(new ListChangeListener<Mech>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Mech> c) {
				memberMechList.setItems(theirMechs);
				
			}
		});	

	 
	}

	
	@FXML protected void handleAddButton(ActionEvent E){
		if(!theirMechs.contains(allMechList.getSelectionModel().getSelectedItem())){
			theirMechs.add(allMechList.getSelectionModel().getSelectedItem());
		}
		
	}
	
	@FXML protected void handleRemoveButton(ActionEvent E){
		theirMechs.remove(memberMechList.getSelectionModel().getSelectedItem());
		
	}
	
	@FXML protected void handleCreateButtonPress(ActionEvent E){
		ArrayList<String> buffList = new ArrayList<String>();
		for(Mech mech : theirMechs){
			buffList.add(mech.getName());
		}
		
		coord1.addNewMember(nameField.getText(), buffList);
		try {
			coord1.saveToXmlFile();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
