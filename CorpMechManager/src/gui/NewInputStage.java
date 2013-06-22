package gui;

import java.io.InputStream;

import main.CoordinatingClass;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NewInputStage extends Stage {
	
	Stage page  = new Stage();
	private CoordinatingClass coord;
	
	public void start(String inputType) throws Exception{
		page.initModality(Modality.WINDOW_MODAL);
		switch (inputType){
		case "1": {
			AddMemberPageController mainPage = (AddMemberPageController) replaceSceneContent("AddMemberPage.fxml");
			mainPage.setApp(coord);
			page.showAndWait();
			break;
		}
		
		case "2" : {
			AddMechPageController mainPage = (AddMechPageController) replaceSceneContent("AddMechPage.fxml");
			mainPage.setApp(coord);
			page.showAndWait();
			break;
		}
		case "3" : {
			AddVarientPageController mainPage = (AddVarientPageController) replaceSceneContent("AddVarientPage.fxml");
			mainPage.setApp(coord);
			page.showAndWait();
			break;
		}
		case "4" : {
			AddFormationPageController mainPage = (AddFormationPageController) replaceSceneContent("AddFormationPage.fxml");
			mainPage.setApp(coord);
			page.showAndWait();
			break;
		}
		}
		

	}
	
	Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = NewInputStage.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(NewInputStage.class.getResource(fxml));
        AnchorPane pagePane;
        try {
            pagePane = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(pagePane, 800, 600);
        page.setScene(scene);
        page.sizeToScene();
        return (Initializable) loader.getController();
    

}
	
	public void setCoord(CoordinatingClass app){
		this.coord = app;
		
	}
}
