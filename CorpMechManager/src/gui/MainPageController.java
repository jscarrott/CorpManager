package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import main.CoordinatingClass;
import main.Formation;
import main.Mech;
import main.Member;
import main.Varient;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainPageController extends AnchorPane implements Initializable {

	@FXML
	javafx.scene.control.ListView<Member> allMembersList;
	@FXML
	javafx.scene.control.ListView<Member> currentMembersList;
	@FXML javafx.scene.control.ListView<Formation> formationList;
	@FXML javafx.scene.control.ListView<String> currentFormationComposition;
	@FXML javafx.scene.control.ListView<Mech> mechList;
	@FXML javafx.scene.control.TableView<Varient> varientList;
	@FXML TableColumn<Varient, String> nameColumn;
	@FXML TableColumn<Varient, String> smurfyColumn;
	private CoordinatingClass application;
	private ObservableList<Member> allMembers;
	private ObservableList<Member> currentMembers;
	private ObservableList<Formation> allFormations;
	private ObservableList<String> formComposition;
	private ObservableList<Mech> allMechs;
	private ObservableList<Varient> currentVarients;

	void setApp(CoordinatingClass application1) {
		this.application = application1;
		allMembers = FXCollections.observableArrayList(application.getMembers().values());
		allMembersList.setItems(allMembers);
		allMembers.addListener(new ListChangeListener<Member>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Member> c) {
				allMembersList.setItems(allMembers);

			}

		});
		currentMembers = FXCollections.observableArrayList();
		currentMembers.addListener(new ListChangeListener<Member>() {
			
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Member> c) {
				currentMembersList.setItems(currentMembers);
				
			}
		});
		
		allFormations = FXCollections.observableArrayList(application.getFormations().values());
		formationList.setItems(allFormations);
		allFormations.addListener(new ListChangeListener<Formation>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Formation> c) {
				formationList.setItems(allFormations);
				
			}
			
		});
		formComposition = FXCollections.observableArrayList();
	formComposition.addListener(new ListChangeListener<String>() {
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
			currentFormationComposition.setItems(formComposition);
			
		}
	});
	

	allMechs = FXCollections.observableArrayList(application.getMechs().values());
	mechList.setItems(allMechs);
	allMechs.addListener(new ListChangeListener<Mech>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends Mech> c) {
			mechList.setItems(allMechs);
			
		}
	});	
	
	currentVarients = FXCollections.observableArrayList();
	varientList = new TableView<Varient>();
	
	
		pollLists();
	}
	
	@FXML protected void addMemberToCurrentButton(ActionEvent E){
		if(!currentMembers.contains(allMembersList.getSelectionModel().getSelectedItem())){
			currentMembers.add(allMembersList.getSelectionModel().getSelectedItem());
		}
	}

	@FXML protected void removeMemberFromCurrentButton(ActionEvent E){
		currentMembers.remove(currentMembersList.getSelectionModel().getSelectedItem());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	@FXML protected void handleEditVarientsButton(ActionEvent E) throws Exception{
		NewInputStage test = new NewInputStage();
		test.setCoord(application);
		test.start("3");
	}
	
	@FXML protected void formationClickedOn(MouseEvent E){
		formComposition = FXCollections.observableArrayList(formationList.getSelectionModel().getSelectedItem().getComposition());
		 currentFormationComposition.setItems(formComposition);
	}
	
	@FXML protected void addNewMemberButtonPressed(ActionEvent E) throws Exception{
		NewInputStage test = new NewInputStage();
		test.setCoord(application);
		test.start("1");
		
	}
	
	@SuppressWarnings("unchecked")
	@FXML protected void mechListClickedOn(MouseEvent E){
		currentVarients = FXCollections.observableArrayList((application.getMechs().get(mechList.getSelectionModel().getSelectedItem()).getVarients().values()));
	 //TODO update table
		varientList.setItems(currentVarients);
		
		nameColumn = new TableColumn<Varient, String>("name");
		smurfyColumn = new TableColumn<>("smurfyURL");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Varient, String>("name"));
		smurfyColumn.setCellValueFactory(new PropertyValueFactory<Varient, String>("smurfyURL"));
		varientList.getColumns().addAll(nameColumn, smurfyColumn);
		varientList.setItems(currentVarients);
	}
	
	@FXML protected void handleEditMechsButton(ActionEvent E) throws Exception{
		NewInputStage test = new NewInputStage();
		test.setCoord(application);
		test.start("2");
	}
	
	void pollLists(){
		Timer timer = new Timer();

		timer.schedule( new TimerTask() {

			@Override
			public void run() {
				allMembers.setAll(application.getMembers().values());
				
				allFormations.setAll(application.getFormations().values());
				allMechs.setAll(application.getMechs().values());
			}
		    
		    
		 }, 0, 5*1000);
	}
}
