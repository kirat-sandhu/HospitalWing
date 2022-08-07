package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


//Main file creates controller for the primary UI page and sets the stage as created in the fxml file.
//Code from FXML user Interface Coding challenge was used in this file
//For sample there is one manager created, two nurses and murse with id 201 is assigned one shift
public class Main extends Application {
	Stage mainStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/Wing.fxml"));	
			WingCode controller	=(WingCode) loader.getController();
		
			Scene scene = new Scene(root,800,350);

			controller.applicationStage = primaryStage;
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
