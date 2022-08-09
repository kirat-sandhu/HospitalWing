package application;


import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This class reacts with the user and sets stage accordingly
//A lot of code in this class makes a stage for the user to interact and waits for action event by the use
//Since making stage requires huge chunks of code, to keep this class action event specific, 
//i tried making all ui in different fxml files, which is more sophisticated method of coding
//eg. ManagerGUI.fxml was made  but I could not make multiple controllers communicate effectively... 
//but i want to work on it later so I included that file but havent used it


//This class  code has DatePicker objects 	 
//code to convert local date to date was taken from https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type


public class WingCode {
	
	
			Stage applicationStage ; 
			 
		    //for simplicity of this project we will create one manager with id 101
			private Manager iManager=new Manager("Kyle","Bates", new Date(),101);

//a lot of variables(hboxes, vboxes, textfields , labels, buttons etc.) are declared and are used to make scene. 
			//To avoid passing a lot of variables to a method, they are declared and initialised acc. to action events on different stages
			
			
			
			
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
    private Button makeSceduleButton;;
    
	Date managerDOB = new Date();
	private ChoiceBox<Integer> hrsInput ;
	private	ChoiceBox<Integer> minutesInput ;

	
	
/**
*Helper method that validates the input when user enters id
*@param input-->input from textfields
*@return the number input by user and  0 if input is not a valid number 
**/

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
	 
	/*method that prints the nurses in the list when print all nurses button is pressed
	 * makes a new ui to print list
	 * **/
	 
	private void printNurseListNewScene(Scene mainScene)
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
	 
	 /**method that checks if the input is a valid name
	  * valid name can have only alphabets and atmost 2 spaces
	  * @param-input as entered by the user
	  * */
	 
	 private boolean validNames(String input)
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
	 
	/**
	 * method that fills an array with integers with the given range
	 * @param- minimum and maximum  Value to fill in array
	 * @return filled array
	 * */
	 public int[] arrayOfIntegers(int start, int end)
	 {
		 int[] arrayFilledWithIntegers = new int[1+end-start];
		 
		 for(int i= 0; i<end-start;i++)
			 arrayFilledWithIntegers[i] = start +i;
		 return arrayFilledWithIntegers;
		 
	 }
	 
	 /*method to check if the string has just digits, returns true if the input has only digits and false otherwise
	  * @param - value entered by the user
	  * 
	  * */
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

	 
	 
//This method code has DatePicker objects that  	 
 //code to convert local date to date was taken from https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
/**After entering values into the nurse field, when user clicks add button, this method
 * adds the given nurse to the list.
 * @param cuurent scene, previous scene to return to after work is done and event of button click
 * **/
	 void addButtonPressed(Scene newScene,Scene prevScene,ActionEvent event)
	 {
		 if(dateOfBirth.getValue() == null || !validNames(firstNameTextfield.getText()) || !validNames(lastNameTextfield.getText()))
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
	 
	 //makes a  new UI where a manager can add nurses to his wing by inputting a valid name and date of birth
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
	 
	 
	 /**creates GUI when user presses search for a nurse Schedule by giving nuse Id.....
	  * @param idInput - value entered by user in id TextField
	  * ****/
	 
	 public void printScheduleOfNurseButtonPressed(String idInput)
	 {
	    	Scene prevScene = applicationStage.getScene();
	    	VBox scheduleContainer= new VBox(200);
	    	Label scheduleOfANurse = new Label("");
			  doneButton = new Button("Done");

	    	scheduleContainer.getChildren().addAll(scheduleOfANurse,doneButton);
	    	Scene newScene = new Scene(scheduleContainer);
	    	if(idInput==null)
	    		nurseListLabel.setText("Enter valid employee Id in the text field");
	
	    	else if((extractID(idInput)==0))
	    		nurseListLabel.setText("Enter valid employee Id  in the text field");
	    	else
	    	{
	    		scheduleOfANurse.setText(iManager.printSchedule(extractID(idInput)));
		    	applicationStage.setScene(newScene);

	    	};
	    	
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(prevScene));

	    	
	 }
	 
	  /*
	    * creates GUI for manager when user chooses to use it as a manager 
	    * @param--> actionEvent--->event of clicking manager button from 1st window
	    * 
	    * **/
	    
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
    		makeSceduleButton= new Button("Assign Schedule");
			  doneButton = new Button("Done");

    		 printNurseScheduleButton = new Button("Search schedule of a nurse");
    		searchNurseAchedule.getChildren().addAll(searchNurseScheduleLabel,printNurseScheduleButton,nurseEmp);

    
    		printNurseScheduleButton.setOnAction(doneEvent->printScheduleOfNurseButtonPressed(nurseEmp.getText()));
    	//	else
    	//		printNurseScheduleButton.setOnAction(doneEvent->nurseListLabel.setText("Enter valid employee Id"));
    		
	    	 printNurseListButton = new Button("Print all the nurses");
	    	 
	    	 

	    	addNurseButton = new Button("Add new Nurse");
	    

	    	addNurseButtonContainer.getChildren().addAll(addNurseButton);
	    	managerWindowContainer.getChildren().addAll(searchNurseAchedule,printNurseListButton,addNurseButtonContainer,makeSceduleButton,doneButton);
	    	Scene newScene = new Scene(managerWindowContainer);
	    	applicationStage.setScene(newScene);
	    //	Scene newScene = new Scene(managerWindowContainer);
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(mainScene));
	    	printNurseListButton.setOnAction(doneEvent->printNurseListNewScene(newScene));

	    	makeSceduleButton.setOnAction(doneEvent->assignShiftLaunchScene(newScene));

	    	addNurseButton.setOnAction(doneEvent->addNurseNewScene());
    	
    	}
    	
		
		
    	
    }
    
  //  https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ChoiceBox.html--->was trying toadd values to choiceBox
    /*Helper method 
     * fills the given choice with integers in the given range
     * @param-->choicebox to fill andrange
     * @return-->choicebox after filling
     * 
     * **/
	private ChoiceBox<Integer> fillChoiceBoxWithValues(ChoiceBox<Integer> inputBox, int minValue, int maxValue)
    {
    		for(int i = minValue; i<=maxValue;i++)
    	{
    		inputBox.getItems().add(i);
    	}
    	return inputBox;
    	
    }
    
    
    
     /*Helper method 
    * Assigns shift to the nurse, using herId,
    * @param-->all input fields on the assign Schedule Window
    * uses these values to make new Shift assignmShifts to the nurses
    * handles input errors for the assign shift
    * **/
   private void assignShiftButtonPressed(String inputId,Label inputError,DatePicker assignShiftForDate, int hoursInput, int minutesInput,TextField shiftLength  )
    {
    	long nurseId= extractID(inputId);
    	Shift defaltShift = new Shift();
    	if(assignShiftForDate.getValue()== null)
    	{
    		inputError.setText("Choose a valid date");	
    		return;
    	}
    	
    	 LocalDate i = assignShiftForDate.getValue();
	    //get the milliseconds for the date and then add hours and minutes worth milliseconds
        Date startDateInput = new Date(Date.from(i.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()).getTime() +
        		defaltShift.convertHoursToMilliseconds((hoursInput + (minutesInput/60.0))));
    	
      //  System.out.println(startDateInput.toString());
    //    System.out.println(defaltShift.convertHoursToMilliseconds((hoursInput + minutesInput/60)));
    //    System.out.println(hoursInput+"  "+ minutesInput/60.0);

        if((extractID(shiftLength.getText())==0))
        		inputError.setText("Enter valid shift length");	
        else if(nurseId==0)
    		inputError.setText("Enter valid Nurse Id");		
    	else

    	{  Shift newShift = new Shift(startDateInput,extractID(shiftLength.getText()));		
    		inputError.setText(iManager.assignShift(nurseId,newShift));
    	}
    
    }
   
   
   /*Helper method 
    * creates new GUI when user presses make Schedule button
    * input prevScene- previous window,when the user clicks done button and is done assigning shift, 
    * takes him to the previous window
    * 
    * 
    * **/
     private void assignShiftLaunchScene(Scene prevScene)
    {
     	VBox shiftsWindowContainer= new VBox(50);
     	Button assignShiftButton = new Button("Assign this Shift");
     	HBox inputFieldsContainer = new HBox();
     	HBox inputTextFieldLabelContainer = new HBox();
     	VBox shiftLengthContainer = new VBox();
		doneButton = new Button("Done");
		DatePicker assignShiftForDate = new DatePicker();
		TextField shiftLength = new TextField();
		TextField nurseIdForShift = new TextField();
		Label inputErrorLabel = new Label("");
		Label shiftLengthLabel=new Label( "Enter length of shift in hours");
		Label nurseIdLabel=new Label( "Enter nurseId to assign this shift:");

		 hrsInput = new ChoiceBox<Integer>();
		hrsInput= fillChoiceBoxWithValues(hrsInput,1,24);
		 minutesInput = new ChoiceBox<Integer>();
		//minutesInput= fillChoiceBoxWithValues(minutesInput,0,60);
		 minutesInput.getItems().add(0);
		 minutesInput.getItems().add(15);
		 minutesInput.getItems().add(30);
		 minutesInput.getItems().add(45);

	//	int hours = ;
		// minutes = ;
		inputTextFieldLabelContainer.getChildren().addAll(new Label("Below choose a date and select time(choosing 10 from 1st and 0 from 2nd list would assign shift starting at 10:00 AM for chosen date)for assigning shift."));
		inputFieldsContainer.getChildren().addAll(assignShiftForDate,hrsInput,minutesInput,shiftLength);
		shiftLengthContainer.getChildren().addAll(shiftLengthLabel,shiftLength,nurseIdLabel,nurseIdForShift);
		shiftsWindowContainer.getChildren().addAll(inputTextFieldLabelContainer,inputFieldsContainer,shiftLengthContainer,inputErrorLabel,assignShiftButton,doneButton);
		Scene newScene = new Scene(shiftsWindowContainer);
		applicationStage.setScene(newScene);
		
		
      //  Shift newShiftTime = new Shift(startDateInput,extractID(shiftLength.getText()));		
        assignShiftButton.setOnAction(doneEvent->assignShiftButtonPressed(nurseIdForShift.getText(),inputErrorLabel,assignShiftForDate,(int)hrsInput.getValue() ,(int)minutesInput.getValue(),shiftLength));
    	doneButton.setOnAction(doneEvent->applicationStage.setScene(prevScene));

    }
    
     
     /// This method is called when the user chooses the nurseGUI
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
	    	
	    		printNurseScheduleButton.setOnAction(doneEvent->printScheduleOfNurseButtonPressed(nurseEmp.getText()));
    		
	    	doneButton.setOnAction(doneEvent->applicationStage.setScene(mainScene));

    }
}


///------------------------------------------------------------------------------------------------------------

//The code below this line works fine but was designed to take input as a string and convert into Date , but later i discovered Datepicker and removed it 
/***
//takes a string and returns a array of integers in the format[date, month, year]
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
**/
//-----------------------------------------------------------------------------------------------------------------------------------------
//The code below was written to manage another fxml ManagerGUI controller but couldnt figure out how to move back and forth one stage to another. I wanted to keep it bcz I want to work on it later






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


