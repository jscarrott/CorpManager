package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import main.CoordinatingClass;
import main.Mech;
import main.Varient;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddVarientPageController extends AnchorPane implements Initializable {
	
	@FXML TextField nameField;
	@FXML TextField smurfyField;
	@FXML ListView<Varient>  VarientList;
	@FXML ChoiceBox<Mech> chooseMechBox;
	private ObservableList<Varient> Varients;
	private CoordinatingClass coord;
	private ObservableList<Mech> mechs;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setApp(CoordinatingClass coord){
		this.coord = coord;
		System.out.println(coord.getMechs().get("JR7-F").getVarients().size());
		Varients = FXCollections.observableArrayList(coord.getMechs().get("JR7-F").getVarients());
		if(!Varients.isEmpty()){
					VarientList.setItems(Varients);

		}
		
		Varients.addListener(new ListChangeListener<Varient>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Varient> c) {
				VarientList.setItems(Varients);
				
			}
		});
		
		mechs = FXCollections.observableArrayList(coord.getMechs().values());
		mechs.addListener(new ListChangeListener<Mech>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Mech> c) {
				chooseMechBox.setItems(mechs);
				
			}
		});
		chooseMechBox.setItems(mechs);
	}
	
	
	@FXML protected void handleCreateButtonPress(ActionEvent E){
		if(!smurfyField.getText().equals("")){
			coord.addNewVarient(chooseMechBox.getSelectionModel().getSelectedItem().getName(), nameField.getText(), smurfyField.getText());
			for(Varient var : coord.getMechs().get(chooseMechBox.getSelectionModel().getSelectedItem().getName()).getVarients()){
				if(var.getName().equals(nameField.getText())){
					Varients.add(var);
				}
			}
			
		}
		else{
			System.out.println(chooseMechBox.getSelectionModel().getSelectedItem().getName() +"  +" + nameField.getText());
			coord.addNewVarient(chooseMechBox.getSelectionModel().getSelectedItem().getName(), nameField.getText());
			for(Varient var : coord.getMechs().get(chooseMechBox.getSelectionModel().getSelectedItem().getName()).getVarients()){
				if(var.getName().equals(nameField.getText())){
					Varients.add(var);
				}
			}

		}
		try {
			coord.saveToXmlFile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		VarientList.setItems(Varients);
		
	}
	@FXML protected void handleRemoveButton(ActionEvent E){
		coord.removeVarient(chooseMechBox.getSelectionModel().getSelectedItem().getName(), VarientList.getSelectionModel().getSelectedItem().getName());
	}

}
