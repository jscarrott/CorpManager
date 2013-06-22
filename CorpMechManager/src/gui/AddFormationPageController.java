package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import main.CoordinatingClass;
import main.Varient;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddFormationPageController extends AnchorPane implements Initializable{
	
	@FXML private ListView<Varient> allVarientsList;
	@FXML private ListView<Varient> compositionList;
	@FXML private TextField nameField;
	ObservableList<Varient> allVarients;
	ObservableList<Varient> composition;
	
	
	

	
	private CoordinatingClass coord;

	public void setApp(CoordinatingClass coord){
		this.coord = coord;
		allVarients = FXCollections.observableArrayList(coord.getAllVarients());
		
		allVarientsList.setItems(allVarients);
		
		allVarients.addListener(new ListChangeListener<Varient>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Varient> c) {
				allVarientsList.setItems(allVarients);
				
			}
		});
		
		composition = FXCollections.observableArrayList();
		compositionList.setItems(composition);
		
		composition.addListener(new ListChangeListener<Varient>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Varient> c) {
				compositionList.setItems(composition);
				
			}
		});
	
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	
}

@FXML protected void handleCreateButtonPress(ActionEvent E) throws JAXBException{
	ArrayList<String> nameList = new ArrayList<>();
	for(Varient var : composition){
		nameList.add(var.getName());
	}
	coord.addFormation(nameField.getText(), nameList);
	coord.saveToXmlFile();
}

@FXML protected void handleRemoveButtonPress(ActionEvent E){
	composition.remove(compositionList.getSelectionModel().getSelectedItem());
}

@FXML protected void handleAddButtonPress(ActionEvent E){
	composition.add(allVarientsList.getSelectionModel().getSelectedItem());
	
}
}