package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.util.Calendar;
import java.util.Date;


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
		Shift m1 = new Shift(new Date(3444444),8);
		Shift m2 = new Shift(new Date(34444440),10);
		Nurse n1 = new Nurse("Ian","Corbette", new Date(),203);
		
		System.out.println(m1.toString());
		System.out.println(m2.toString());
		
		Manager iManager=new Manager("Kyle","Bates", new Date(),101);
		iManager.addNurses("Ian","Corbette", new Date());
		iManager.assignShift(201, m1);
		
	}
}
