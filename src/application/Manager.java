package application;

import java.util.ArrayList;
import java.util.Date;



//Managers are the employees that have list of nurses working, 
//can add nurses to the list and remove them. they also assign shifts to all the nurses

//For simplicity of this project there will be only one Manager with employee number --101

public class Manager extends Person {
	
	private ArrayList<Nurse> nurseList;
	private long empNumber;
	private  long firstEmpNum = 10001;
	public Manager(String fName, String lName, Date dob, long empId) 
	{
		
		super(fName,lName,dob);
		empNumber = empId;
		nurseList = new ArrayList<Nurse>();
		
	}

	//returns the next Available Employee Number to be Assigned when a new Nurse Is Added to the Wing
	//Even On removing nurse from the list, the employee number associated will not be available for any other person
	//valid employee numbers start from 1001 , will be 1002 , 1003....and so on
	public long assignEmployeeNumber()
	{
		return ++firstEmpNum;
		
		
	}
	
	//adds a given nurse to the list and assigns an employee Number
	public void addNurses(String fName, String lName, Date dob)
	{
		Nurse newNurse = new Nurse(fName, lName, dob,assignEmployeeNumber() ) ;
		nurseList.add(newNurse);
		
	}
	
	
	//searches nurses by employee id and removes it from the list
	public void removeNurse(long empId)
	{
		for(Nurse n :nurseList)
		{
			if(n.getEmployeeId()==empId)
			nurseList.remove(n);
		}
	}
	
	
	//assigns shift to nurses using their employee Number
	//and returns a string 
	public String assignShift(long empNumber,Date start, Date end)
	{
		String addShiftError="";
		
		Shift newShift = new Shift(start, end);
		for(Nurse n : nurseList)
		{
			if(n.getEmployeeId()==empNumber)
			{
				
				addShiftError = n.addShifts(newShift);
				if(addShiftError.equals("error"))
					addShiftError = "Please add a valid shift";
				else
				addShiftError ="Shift was successfully added "; 
				return addShiftError;
			}			
		}
		
		return "Nurse Not Found. Please enter a valid employee Id";
	}
//assigns removes the given given from the schedule of a given nurse	
	public String unassignShift(long empNumber,Date start, Date end)
	{
		String removeShiftError="";
		ArrayList<Shift> schedule ;
		Shift newShift = new Shift(start, end);
		for(Nurse n : nurseList)
		{
			if(n.getEmployeeId()==empNumber)
			{
				schedule = n.getSchedule();
				for(int i = 0;i<schedule.size();i++)
				{
					if(schedule.get(i).equals(newShift))
					{
						schedule.remove(i);
						removeShiftError=" Shift Successfully removed.";
						return schedule.get(i).toString() + removeShiftError;
					}
					else
						removeShiftError=" No such shift exists in the nurse Schedule  :";

				}
			}
		}
		return newShift.toString() + removeShiftError;
				
	}			
	public String printSchedule(long empId)
	{
		String sch = "";
		for(Nurse n : nurseList)
		{
			if(n.getEmployeeId()==empNumber)
			{
				sch += n.getSchedule();
			}
			
		}
		return sch;
	}
}
