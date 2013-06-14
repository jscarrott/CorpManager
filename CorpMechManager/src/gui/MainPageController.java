package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import main.CoordinatingClass;
import main.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainPageController extends AnchorPane implements Initializable {
	
	@FXML javafx.scene.control.ListView<Member> allMembersList;
   private CoordinatingClass application;
	private ObservableList<Member> allMembers;
	private ObservableList<File> currentMembers;
	
	void setApp(CoordinatingClass application1){
		this.application = application1;
	allMembers = FXCollections.observableArrayList(application.getMembers().values());
		allMembersList .setItems(allMembers);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
