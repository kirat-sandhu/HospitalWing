package application;

import java.util.ArrayList;
import java.util.Date;

//Nurse is a child class of person but they  have their unique employee Id and own schedule
//Schedule is a list of Shift Objects
// manager adds/creates nurses and adds shifts to their schedule
//nurses can only search for their schedule

public class Nurse extends Person {

	private long empNumber;
	private ArrayList<Shift> mySchedule;
	
	//constructor that makes a nurse object that has id, name , date of birth and their pown schedule
	public Nurse(String fName, String lName, Date dob, long nurseId) 
	{
		
		super(fName,lName,dob);
		empNumber = nurseId;
		mySchedule = new ArrayList<Shift>();
	}
	
	

	
	
	
	
	//copy constructor creates a deep copy of the Nurse instance pased as an argument
	public Nurse(Nurse toCopy)
	{
		super(toCopy.getFName(),toCopy.getLName(),toCopy.getDOB()); 
		this.empNumber = toCopy.getEmployeeId();
		copySchedule(toCopy.getSchedule());//.copy();
		
	}
	
//overrides the  method in parent class and returns name of a nurse or the error if no nurse with id exists
//@param--id of the nurse whose name will be returned
	public String getName(long nurseId)
	{
		if(nurseId==empNumber)
		return super.getName();
		else
			return "Please enter a valid employee Number. ";
	}
	
	//adds newShift in the schedule of the nurse if shift is valid
	//returns string with text error if shift was not valid
	//and empty string otherwise
	public String addShifts(Shift oneShift)
	{
		
		boolean shiftIsValid= false;
		String error = "";
		int i =0;
		
		Shift newShift = oneShift;
		if(this.getSchedule().size()==0)
			this.getSchedule().add((newShift));
		else
		{	
			for( i = 0; i< this.getSchedule().size();i++)
			{	
				shiftIsValid = (!mySchedule.get(i).overlapsWith(newShift));//|| newShift.shiftLengthIsValid();
						
				if(!shiftIsValid)
				{	
					error= "error";
					return error;
				
				}
				else if(!this.getSchedule().get(i).isBefore(newShift))
				{
					this.getSchedule().add(i, (newShift));
				//	mySchedule.add((newShift));
					return error;
				
				}
				
			
			}
			mySchedule.add((newShift));
			//System.out.println("in nurse add :"+error);
		}
		
		return error;
	}
	
	//overrides the method in parent class
	//prints  of a nurse
	public String toString() 
	{
		return "\t"+ empNumber + "\t" + super.toString() ;
	}
	
	//returns a string containg schedule of a nurse when called on that instance
	public String printScheduleOfNurse()
	{
		
		String myShifts="";
		if(mySchedule.size()==0)
			return "No shifts assigned";
		myShifts = "Schedule for " + super.getName() + '\n';
		myShifts = myShifts + '\t' + '\t'+"Start Time" +'\t' + '\t'+"End Time"+ '\n';

		for(int i = 0; i< mySchedule.size();i++)
		{
			myShifts =myShifts + (i+1) + '\t' + mySchedule.get(i).toString()+"\n";
		}
		
		
		return myShifts;
	}
	
	//returns employee number for a given nurse
	public long getEmployeeId()
	{
		return empNumber;
	}
	
	//getter method that returns schedule that is list of shift objects for a given nurse
	
	public ArrayList<Shift> getSchedule()
	{
		
		return this.mySchedule;
	}
	
	//seeter method makes a deep copy of arraylist containing shift objects
	public void copySchedule(ArrayList<Shift> toCopy )
	{
		this.mySchedule= new ArrayList<Shift>();
		
		for(Shift n :toCopy)
		{
			Shift newShift = n;
			mySchedule.add(newShift);
		}
		
	}
}
