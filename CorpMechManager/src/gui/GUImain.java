package gui;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.CoordinatingClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUImain extends Application {
	
	private Stage stage;
	public CoordinatingClass coordClass;

	@Override
	public void start(Stage primaryStage) throws Exception {
		coordClass = new CoordinatingClass();
		stage = primaryStage;
       stage.setTitle("Corp Manager");
       stage.setMinWidth(300);
       stage.setMinHeight(500);
       gotoMainPage();
       primaryStage.show();
	    }

	private void gotoMainPage() {
		try {
            MainPageController mainPage = (MainPageController) replaceSceneContent("MainPage.fxml");
            mainPage.setApp(coordClass);
            //mainPage.initialize(null, null);
        } catch (Exception ex) {
            Logger.getLogger(GUImain.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = GUImain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(GUImain.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
}
