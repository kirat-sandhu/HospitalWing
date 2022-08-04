package application;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WingCode {
	 Stage applicationStage;


    @FXML
    private Button managerButton;

    @FXML
    private Button userNurseButton;

    @FXML
	private Label nurseListLabel;
	
    @FXML
    private Button printNurseScheduleButton;
	
    @FXML
    private Button printNurseListButton; 
    
    @FXML
    private Button addNurseButton; 
    
    private VBox addNurseContainer ;
	 
    private TextField firstNameTextfield;
    private Label addNurseLabel ;
    private Label nurseInputError;
    private TextField lastNameTextfield ;
    private TextField dateOfBirth;
	 

    private HBox textFieldContainer  ;
	 
    private HBox errorContainer ;
	 
    private Button addnewNurseButton;
    private HBox buttonContainer ;
    
	Date managerDOB = new Date();
	private ArrayList<Room>roomList;
	private int roomCapacityInWing = 20;
	private Manager iManager=new Manager("Kyle","Bates", managerDOB,101);
	
   
	 //I .matches("[0-9.]+")) to check if string --->This code was copied from
	 public long extractID(String input)
	 {
		 return Long.parseLong(input);
	 }
	 
	 
	 
	 void printNurseListNewScene()
	 {
		VBox nurseListContainer = new VBox(400); 
		 nurseListLabel= new Label();
		 nurseListLabel.setText(iManager.toString());
		 nurseListContainer.getChildren().addAll(nurseListLabel);
		 
		 Scene newScene= new Scene(nurseListContainer);
		 applicationStage.setScene(newScene);
	 }
    
	 public boolean validNames(String input)
	 {
		 boolean isValid = false;
		 int spaceCount = 0;
		 for(char ch : input.toCharArray())
		 {
			 if((ch>='a' && ch <='z') || ch>='A' && ch <='Z')
					 isValid = true;
			 else if(ch==' '&& spaceCount!=input.length()&&spaceCount<2)
			 {
				 ++spaceCount;
				 isValid = true;
			 }
			 else
				 isValid = false; 
				 
		 }
		 return isValid;
		 
	 }
	 
	 public int[] arrayOfIntegers(int start, int end)
	 {
		 int[] arrayFilledWithIntegers = new int[1+end-start];
		 
		 for(int i= 0; i<end-start;i++)
			 arrayFilledWithIntegers[i] = start +i;
		 return arrayFilledWithIntegers;
		 
	 }
	 
	 
//	 takes a string and returns a array of integers in the format[date, month, year]
	 public int[] arrayOfDOB(String input)
	 {
		 int[] arrayFilledWithIntegers = new int[3];
		 if(input.length()!=10)
			 Arrays.fill(arrayFilledWithIntegers,0);
		 else {
			 for(int i= 0; i<input.length();i++)
			 {
			 if(input.charAt(i)=='-')
			 {
				 if(i!=2 && i!=5)
					Arrays.fill(arrayFilledWithIntegers,0);
			 }
			 else if(i!=2 && i!=5 &&!Character.isDigit(input.charAt(i)))
			 {
					Arrays.fill(arrayFilledWithIntegers,0); 
			 }
			 
			 else
				 {arrayFilledWithIntegers[0] = Integer.parseInt(input.substring(0,2));
				 arrayFilledWithIntegers[1] = Integer.parseInt(input.substring(3,5));
				 arrayFilledWithIntegers[2] = Integer.parseInt(input.substring(6));
			 }
			}	 
		 
		 
	 }
		 return arrayFilledWithIntegers;

	 
	}
	 
	 
	 
	 public Date setDate(int[] date)
	 {
		 Calendar c = Calendar.getInstance(); 
		 c.set(Calendar.DATE, date[0]);
		 c.set(Calendar.MONTH, date[1]);
		 c.set(Calendar.YEAR, date[2]);

		 return ( Date)( c.getTime());
		 
	 }
	 
	 void addButtonPressed(Scene newScene)
	 {
		 nurseInputError.setText("");
		 Date dob = new Date();
		 int[] arrayDOB = arrayOfDOB(dateOfBirth.getText());
		 System.out.println(arrayDOB[0]);
		 System.out.println(arrayDOB[1]);
		 System.out.println(arrayDOB[2]);

		 if(arrayDOB[0]==0||arrayDOB[1]==0||arrayDOB[2]==0||(arrayDOB[0]>31 ||arrayDOB[1]>12 || arrayDOB[2]>2022 ||arrayDOB[2]<1900))
			 nurseInputError.setText("Please Enter valid date of birth in format :  DD-MM-YYYY");
		 else
		 {
			  dob = setDate(arrayDOB);

		 }
		 

		 if(validNames(firstNameTextfield.getText()) &&validNames(lastNameTextfield.getText()))
		 {
			 iManager.addNurses(firstNameTextfield.getText(),lastNameTextfield.getText(), dob);
			 addnewNurseButton.setOnAction(doneEvent->addNurseLabel.setText("Nurse Successfully Added"));
		 }
		 
		 else
			 addnewNurseButton.setOnAction(doneEvent->addNurseLabel.setText("Please enter valid first and lastname"));

	    	applicationStage.setScene(newScene);

		 
	 }
	 //https://www.geeksforgeeks.org/javafx-choicebox/---->code for adding array  values to choicebox
	 //brings up new window where a manager can add nurses to his wing by inputting a valid name and date of birth
	 void addNurseNewScene()
	 {
		  addNurseContainer = new VBox(50);
		 
		  firstNameTextfield = new TextField();
		  addNurseLabel = new Label("Please Enter first name, last Name and Date of Birth respectively");
		  nurseInputError = new Label("");
		  lastNameTextfield = new TextField();
		  dateOfBirth = new TextField();
		 

		  textFieldContainer  = new HBox();
		 textFieldContainer.getChildren().addAll(firstNameTextfield,lastNameTextfield,dateOfBirth);
		 
		  errorContainer  = new HBox();
		 errorContainer.getChildren().addAll(nurseInputError);
		 
		  addnewNurseButton = new Button("Add");
		  buttonContainer  = new HBox();
		  
		 buttonContainer.getChildren().addAll(addnewNurseButton);
		 addNurseContainer.getChildren().addAll(addNurseLabel,textFieldContainer,errorContainer,addnewNurseButton);

	    	Scene newScene = new Scene(addNurseContainer);
	    	
	    	applicationStage.setScene(newScene);

			 System.out.println(dateOfBirth.getText());

	    	addnewNurseButton.setOnAction(doneEvent->addButtonPressed(newScene));

	    
	 }
    @FXML
    void accessManager(ActionEvent event)
    {
    	Scene mainScene = applicationStage.getScene();
    	
    	nurseListLabel= new Label("");
    	VBox managerWindowContainer= new VBox(50);
    	managerWindowContainer.getChildren().add(nurseListLabel);
    	
    	{
    		HBox searchNurseAchedule = new HBox();
    		Label searchNurseScheduleLabel = new Label("");
    		HBox addNurseButtonContainer = new HBox();
    		TextField nurseEmp = new TextField();
    		 printNurseScheduleButton = new Button("Search schedule of a nurse");
    		searchNurseAchedule.getChildren().addAll(searchNurseScheduleLabel,nurseEmp,printNurseScheduleButton);

    		
    		if(nurseEmp!=null && nurseEmp.getText().matches("[0-9.]+"))
    		printNurseScheduleButton.setOnAction(doneEvent->nurseListLabel.setText(iManager.printSchedule(extractID(nurseEmp.getText()))));
    		else
    			printNurseScheduleButton.setOnAction(doneEvent->nurseListLabel.setText("Enter valid employee Id"));
    		
	    	 printNurseListButton = new Button("Print all the nurses");
	    	 
	    //	printNurseListButton.setOnAction(doneEvent->nurseListLabel.setText(iManager.toString()));

	    	printNurseListButton.setOnAction(doneEvent->printNurseListNewScene());

	    	addNurseButton = new Button("Add new Nurse");

	    	addNurseButtonContainer.getChildren().addAll(addNurseButton);
	    	managerWindowContainer.getChildren().addAll(searchNurseAchedule,printNurseListButton,addNurseButtonContainer);
	    	

	    	addNurseButton.setOnAction(doneEvent->addNurseNewScene());

	    	
	    	
    		
    	//	quizGradeContainer.getChildren().add(rowContainer);
    	
    	}
    	
		//doneButton.setOnAction(doneEvent-> calculateAverageQuizGrade(mainScene,quizGradeTextFieldList));
		//quizGradeContainer.getChildren().add(doneButton);
		
		
    	Scene newScene = new Scene(managerWindowContainer);
    	applicationStage.setScene(newScene);
    
    }
}
