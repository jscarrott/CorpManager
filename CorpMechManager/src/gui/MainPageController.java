package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import main.CoordinatingClass;
import main.Formation;
import main.Member;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainPageController extends AnchorPane implements Initializable {

	@FXML
	javafx.scene.control.ListView<Member> allMembersList;
	@FXML
	javafx.scene.control.ListView<Member> currentMembersList;
	@FXML javafx.scene.control.ListView<Formation> formationList;
	@FXML javafx.scene.control.ListView<String> currentFormationComposition;
	private CoordinatingClass application;
	private ObservableList<Member> allMembers;
	private ObservableList<Member> currentMembers;
	private ObservableList<Formation> allFormations;
	private ObservableList<String> formComposition;

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
	
	@FXML protected void formationClickedOn(MouseEvent E){
		formComposition = FXCollections.observableArrayList(formationList.getSelectionModel().getSelectedItem().getComposition());
		 currentFormationComposition.setItems(formComposition);
	}
}
