package gui;

import java.net.URL;
import java.util.ResourceBundle;

import main.CoordinatingClass;
import main.Mech;
import main.Varient;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setApp(CoordinatingClass coord){
		this.coord = coord;
		
		Varients = FXCollections.observableArrayList(coord.getMechs().get(0).getVarients().values());
		VarientList.setItems(Varients);
		Varients.addListener(new ListChangeListener<Varient>(){
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Varient> c) {
				VarientList.setItems(Varients);
				
			}
		});
	}
	

}
