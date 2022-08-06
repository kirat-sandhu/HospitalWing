package application;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class WingCode {
	
	
			Stage applicationStage ; 
		
			  @FXML
			    private Button searchNurseButton;

			    @FXML
			    private TextField nurseIdTextField;

			    @FXML
			    private DatePicker dobTextfield;

			    @FXML
			    private TextField fNameTextfield;

			    @FXML
			    private TextField lNameTextfield;

			    @FXML
			    private Label nurseIDerrorLabel;

			    @FXML
			    private Button doneButton;

			    @FXML
			    private Label addNurseErrorLabel;
			    @FXML
			    private Button printAllNurses;
			    
			    //for simplicity of this project we will create one manager with id 101
				private Manager iManager=new Manager("Kyle","Bates", new Date(),101);

/***	
	 @FXML
	    void accessManager(ActionEvent event)
	    {
		 try {
	    	//Scene mainScene = applicationStage.getScene();
	    	FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/ManagerGUI.fxml"));	
		//	WingCode controller	=(WingCode) loader.getController();
		  Scene scene = new Scene(root,800,350);
		  Stage newStage = new Stage();
		  newStage.setScene(scene);
		// 	controller.applicationStage= super.getStage();
			applicationStage.setScene(scene);
			Scene mainScene= applicationStage.getScene();

			//applicationStage;
			doneButton = new Button("Done ");
	    	//doneButton.setOnAction(doneEvent->applicationStage.setScene(mainScene));

			//Main.primaryStage = 
		 } catch(Exception e) {
				e.printStackTrace();
			}
	    }

	 @FXML
	    void accessNurse(ActionEvent event)
	    {
	    	
	    }
	 
	 private long extractID(String input)
	 {
		 if(input.length()!=0)
		 {
			 for(char ch : input.toCharArray())
			 {
				 if(!Character.isDigit(ch))
					 return 0;
			 }
			 return Long.parseLong(input);
		 
		 }
		 else return 0;
	 }
	 
	 private void printScheduleOfNurseButtonPressed(long id)
	 {
		 //	Scene prevScene= applicationStage.getScene();
	    	nurseIDerrorLabel.setText("");

		 	VBox scheduleContainer= new VBox(50);
	    	Label scheduleOfANurse = new Label("");
	    	doneButton = new Button("Done");
	    	scheduleContainer.getChildren().addAll(scheduleOfANurse,doneButton);
	    	Scene newScene = new Scene(scheduleContainer);
	    	applicationStage.setScene(newScene);

	    	scheduleOfANurse.setText(iManager.printSchedule(id));

	    	
	    //	doneButton.setOnAction(doneEvent->applicationStage.setScene(super.getStage()));

	    	
	 }
	  @FXML
	    void findNurse(ActionEvent event) {
	    //	Scene mainScene = applicationStage.getScene();
	    	nurseIDerrorLabel.setText("");
	    	if(nurseIdTextField.getText()=="")
	    		nurseIDerrorLabel.setText("No Id Entered");
	    	else if(extractID(nurseIdTextField.getText())==0)
	    		searchNurseButton.setOnAction(doneEvent->nurseIDerrorLabel.setText(" Nurse not found in records.Enter valid ID "));
	    	else
	    	searchNurseButton.setOnAction(doneEvent->printScheduleOfNurseButtonPressed(extractID(nurseIdTextField.getText())));
	    	
		    	
	    }
	  
	  @FXML
	    void printAllNursesButtonPressed(ActionEvent event) {
		 // applicationStage.getScene();

			 VBox nurseListContainer = new VBox(100); 
			 Label nurseListLabel= new Label();

			 nurseListContainer.getChildren().addAll(nurseListLabel,doneButton);
			 
			 Scene newScene= new Scene(nurseListContainer);
			
			 applicationStage.setScene(newScene) ;
			// applicationStage.getscene();
			 applicationStage.setScene(newScene);

			 printAllNurses.setOnAction(doneEvent->nurseListLabel.setText(iManager.printAllNurses()));

		    	doneButton.setOnAction(doneEvent->applicationStage.setScene(newScene));

			 

	    }

}
*****/


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
    private DatePicker dateOfBirth;
	private Label searchNurseScheduleLabel;

    private HBox textFieldContainer  ;
	 
    private HBox errorContainer ;
	 
    private Button addnewNurseButton;
    private HBox buttonContainer ;
  //  private Button doneButton;
    
	Date managerDOB = new Date();
	private ArrayList<Room>roomList;
	private int roomCapacityInWing = 20;
//	private Manager iManager=new Manager("Kyle","Bates", managerDOB,101);
	
	
	public WingCode()
	{
		
		
	}
   
	 //I .matches("[0-9.]+")) to check if string --->This code was copied from
	 public long extractID(String input)
	 {
		 if(input.length()!=0)
		 {
			 for(char ch : input.toCharArray())
			 {
				 if(!Character.isDigit(ch))
					 return 0;
			 }
			 return Long.parseLong(input);
		 
		 }
		 else return 0;
	 }
	 
	 
	 
	 void printNurseListNewScene(Scene mainScene)
	 {
		   Scene prevScene = applicationStage.getScene();

		 VBox nurseListContainer = new VBox(100); 
		 nurseListLabel= new Label();
		 nurseListLabel.setText(iManager.printAllNurses());
		 doneButton = new Button("Done");
		 nurseListContainer.getChildren().addAll(nurseListLabel,doneButton);	 
		 Scene newScene= new Scene(nurseListContainer);
		 applicationStage.setScene(newScene);
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(prevScene));

		 
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
	 
	 
	 public boolean isValidNum(String input)
	 {
		 boolean isValid = true;;
		 for(char ch : input.toCharArray())
		 {
			 if(!Character.isDigit(ch))
				 isValid = false;
		 }
		 return isValid;
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
	 
	 
	 //to convert string to date
	 public Date setDate(int[] date)
	 {
		 Calendar c = Calendar.getInstance(); 
		 c.set(Calendar.DATE, date[0]);
		 c.set(Calendar.MONTH, date[1]);
		 c.set(Calendar.YEAR, date[2]);

		 return ( Date)( c.getTime());

			// Date dob = new Date();
		//	 int[] arrayDOB = arrayOfDOB(dateOfBirth.getText());
			 

			// if(arrayDOB[0]==0||arrayDOB[1]==0||arrayDOB[2]==0||(arrayDOB[0]>31 ||arrayDOB[1]>12 || arrayDOB[2]>2022 ||arrayDOB[2]<1900))
				// nurseInputError.setText("Please Enter valid date of birth in format :  DD-MM-YYYY");
			// else
			 
				 // dob = setDate(arrayDOB);
			 
		 
	 }
	 
	 
	 
	 //code to convert local date to date was taken from https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
	 void addButtonPressed(Scene newScene,Scene prevScene,ActionEvent event)
	 {
		 if(dateOfBirth.getValue() == null || !validNames(firstNameTextfield.getText()) ||validNames(lastNameTextfield.getText()))
		  { 
			 if(!validNames(firstNameTextfield.getText()) && validNames(lastNameTextfield.getText()))
				addNurseLabel.setText("Please enter valid first and selecct date of birth");
			 else  if(validNames(firstNameTextfield.getText()) && !validNames(lastNameTextfield.getText()))
				 addNurseLabel.setText("Please enter valid last name and select date of birth");
			 else
				addNurseLabel.setText("Please enter valid names and select date of birth");
			 return;

		  }
         LocalDate i = dateOfBirth.getValue();
         Date dob = Date.from(i.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			 iManager.addNurses(firstNameTextfield.getText(),lastNameTextfield.getText(),dob);
			 addNurseLabel.setText("Nurse Successfully Added");
	    	applicationStage.setScene(newScene);
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(prevScene));

	 }
	 
	 //brings up new window where a manager can add nurses to his wing by inputting a valid name and date of birth
	 void addNurseNewScene()
	 {
		    Scene prevScene = applicationStage.getScene();

		  addNurseContainer = new VBox(50);
		 
		  firstNameTextfield = new TextField();
		  addNurseLabel = new Label("Please Enter first name	, last Name 	and Date of Birth respectively");
		  nurseInputError = new Label("");
		  lastNameTextfield = new TextField();
		  dateOfBirth = new DatePicker();
		 

		  textFieldContainer  = new HBox();
		 textFieldContainer.getChildren().addAll(firstNameTextfield,lastNameTextfield,dateOfBirth);
		 
		  errorContainer  = new HBox();
		 errorContainer.getChildren().addAll(nurseInputError);
		 
		  addnewNurseButton = new Button("Add");
		  buttonContainer  = new HBox();
		  doneButton = new Button("Done");
		  
		 buttonContainer.getChildren().addAll(addnewNurseButton);
		 addNurseContainer.getChildren().addAll(addNurseLabel,textFieldContainer,errorContainer,addnewNurseButton,doneButton);

	    	Scene newScene = new Scene(addNurseContainer);
	    	
	    	applicationStage.setScene(newScene);
	    	addnewNurseButton.setOnAction(doneEvent->addButtonPressed(newScene,prevScene,doneEvent));
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(prevScene));
    
	 }
	 
	 public void printScheduleOfNurseButtonPressed(String idInput)
	 {
	    	Scene prevScene = applicationStage.getScene();
	    	VBox scheduleContainer= new VBox(200);
	    	Label scheduleOfANurse = new Label("");
			  doneButton = new Button("Done");

	    	scheduleContainer.getChildren().addAll(scheduleOfANurse,doneButton);
	    	Scene newScene = new Scene(scheduleContainer);

	    	if((extractID(idInput)==0))
	    		nurseListLabel.setText("Enter valid employee Id");
	    	else
	    	{
	    		scheduleOfANurse.setText(iManager.printSchedule(extractID(idInput)));
		    	applicationStage.setScene(newScene);

	    	};
	    	
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(prevScene));

	    	
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
			  doneButton = new Button("Done");

    		 printNurseScheduleButton = new Button("Search schedule of a nurse");
    		searchNurseAchedule.getChildren().addAll(searchNurseScheduleLabel,printNurseScheduleButton,nurseEmp);

    	//	System.out.println(extractID(nurseEmp.getText()));   
    	//	System.out.println(nurseEmp.getText()+" noooo");
    	//	if(extractID(nurseEmp.getText())!=0)
    		printNurseScheduleButton.setOnAction(doneEvent->printScheduleOfNurseButtonPressed(nurseEmp.getText()));
    	//	else
    	//		printNurseScheduleButton.setOnAction(doneEvent->nurseListLabel.setText("Enter valid employee Id"));
    		
	    	 printNurseListButton = new Button("Print all the nurses");
	    	 
	    	 

	    	addNurseButton = new Button("Add new Nurse");
	    

	    	addNurseButtonContainer.getChildren().addAll(addNurseButton);
	    	managerWindowContainer.getChildren().addAll(searchNurseAchedule,printNurseListButton,addNurseButtonContainer,doneButton);
	    	Scene newScene = new Scene(managerWindowContainer);
	    	applicationStage.setScene(newScene);
	    //	Scene newScene = new Scene(managerWindowContainer);
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(mainScene));
	    	printNurseListButton.setOnAction(doneEvent->printNurseListNewScene(newScene));

	    	

	    	addNurseButton.setOnAction(doneEvent->addNurseNewScene());
    	
    	}
    	
		
		
    	
    }
    
    
    @FXML
    void accessNurse(ActionEvent event)
    {
    	Scene mainScene = applicationStage.getScene();
    	VBox nurseWindowContainer= new VBox(50);
    	 HBox nurseTextContainer = new HBox(20);	
    	TextField nurseEmp = new TextField();
    	nurseListLabel= new Label("");
		  doneButton = new Button("Done");

		  Label schedule= new Label("");
			 printNurseScheduleButton = new Button("Search your schedule, by entering your ID");

		  nurseTextContainer.getChildren().addAll(printNurseScheduleButton,nurseEmp);
	    	nurseWindowContainer.getChildren().addAll(nurseTextContainer,schedule,nurseListLabel,doneButton);
	    	Scene newScene = new Scene(nurseWindowContainer);
	    	applicationStage.setScene(newScene);
	    	
	    //	printNurseScheduleButton.setOnAction(doneEvent->schedule.setText(iManager.printSchedule(extractID(nurseEmp.getText()))));
    		//if(extractID(nurseEmp.getText())!=0)
	    		printNurseScheduleButton.setOnAction(doneEvent->printScheduleOfNurseButtonPressed(nurseEmp.getText()));
    	//	else
    	//		printNurseScheduleButton.setOnAction(doneEvent->nurseListLabel.setText("Enter valid employee Id"));

	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(mainScene));

    }
}
