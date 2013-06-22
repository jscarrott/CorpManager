package gui;

import java.net.URL;
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

public class AddMechPageController  extends AnchorPane implements Initializable{
	
	CoordinatingClass coord;
	private ObservableList<Mech> allMechs;
	@FXML javafx.scene.control.ListView<Mech> allMechList;
	@FXML TextField nameField;
 
	public void setApp(CoordinatingClass coord) {
		this.coord = coord;
		allMechs = FXCollections.observableArrayList(coord.getMechs().values());
		allMechList.setItems(allMechs);
		allMechs.addListener(new ListChangeListener<Mech>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Mech> c) {
				allMechList.setItems(allMechs);
				
			}
		});	
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	@FXML protected void handleCreateButtonPress(ActionEvent E) throws JAXBException{
		if(!allMechs.contains(coord.getMechs().get(nameField.getText()))){
			coord.addNewMech(nameField.getText());
			coord.saveToXmlFile();
		}
		
	}
	@FXML protected void handleRemoveButton(ActionEvent E) throws JAXBException{
		allMechs.remove(allMechList.getSelectionModel().getSelectedItem());
		coord.saveToXmlFile();
		
	}

}
