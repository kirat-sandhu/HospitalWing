package application;

import java.util.ArrayList;
import java.util.Date;

public class Nurse extends Person {

	private long empNumber;
	private ArrayList<Shift> mySchedule;
	
	public Nurse(String fName, String lName, Date dob, long nurseId) 
	{
		
		super(fName,lName,dob);
		empNumber = nurseId;
		mySchedule = new ArrayList<Shift>();
	}


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
	public String addShifts(Shift newShift)
	{
		
		boolean shiftIsValid= false;
		String error = "";
		int i =0;
		
		if(mySchedule.size()==0)
			mySchedule.add(  (newShift));
		else
		{	
			for( i = 0; i< mySchedule.size();i++)
			{	
				shiftIsValid = (!mySchedule.get(i).overlapsWith(newShift))&&newShift.shiftLengthIsValid()&&
						(!mySchedule.get(i).timeDiffInShiftsValid(newShift));
				if(!shiftIsValid)
				{	
					error= "error";
					return error;
				
				}
				else if(!mySchedule.get(i).isBefore(newShift))
				{
					mySchedule.add(i, (newShift));
					return error;
				}
				
			
			}
			mySchedule.add((newShift));
			System.out.println(error);
		}
		
		return error;
	}
	//prints  of a nurse
	public String toString() 
	{
		return "\t"+ empNumber + "\t" + super.toString() ;
	}
	
	public String printScheduleOfNurse()
	{
		
		String myShifts="";
		if(mySchedule.size()==0)
			return "No shifts assigned";
		myShifts = "Schedule for" + getName() + '\n';
		
		
		for(int i = 0; i< mySchedule.size();i++)
		{
			myShifts =myShifts + i + '\t' +mySchedule.get(i).toString();
		}
		
		
		return myShifts;
	}
	
	//returns employee number for a given nurse
	public long getEmployeeId()
	{
		return empNumber;
	}
	//returns schedule for a given nurse
	
	public ArrayList<Shift> getSchedule()
	{
		
		return this.mySchedule;
	}
}
