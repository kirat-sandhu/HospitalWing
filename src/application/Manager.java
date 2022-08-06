package application;

import java.util.ArrayList;
import java.util.Date;

//For simplicity of this project there will be only one Manager with employee number --101

//Every wing in the hospital has one manager, who adds new nurses and assigns Shifts to them
//Managers are the employees that have list of nurses working, 
//can add nurses to the list and remove them. they also assign shifts to all the nurses
//Managers can add new nurses to their wing and when they add nurses an employeeNumber gets assigned to a nurse
//nurse List--> stores all the nurses under this nurses
//for sample two nurses with current date  as date of birth
//Overrides toString method from parent class

public class Manager extends Person {
	
	private ArrayList<Nurse> nurseList;
	private long empNumber;
	private  static long firstEmpNum = 200;
	public Manager(String fName, String lName, Date dob, long empId) 
	{
		
		super(fName,lName,dob);
		empNumber = empId;
		nurseList = new ArrayList<Nurse>();
		
		this.addNurses("Ian","Corbette", new Date());
		this.addNurses("Sara","Xeng", new Date());
		//System.out.println(assignShift(201,new Date(),new Date(new Date().getTime()+(3600000*8)) ));
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
		//System.out.println(newNurse.printScheduleOfNurse());
	}
	
	
	//searches nurses by employee id and removes it from the list of nurses
	//this method hasnt been used
	public void removeNurse(long empId)
	{
		for(Nurse n :nurseList)
		{
			if(n.getEmployeeId()==empId)
			nurseList.remove(n);
		}
	}
	
	
	//assigns shift to nurses using their employee Number
	//and returns a string that contains the error or the message that shift was successfully added
	//@param - employee id to whom we this new assign shift from start to end
	public String assignShift(long empNumber,Date start, Date end)
	{
		String addShiftError="";
		
		Shift newShift = new Shift(start, end);
		//System.out.println(start);
	//	System.out.println(end);


		for(Nurse n : nurseList)
		{
			if(n.getEmployeeId()==empNumber)
			{
				System.out.println(empNumber);
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
	
	//assigns shift to nurses using their employee Number
		//and returns a string that contains the error or the message that shift was successfully added
		//@param - employee id to whom we assign this new  shift 

	public String assignShift(long empNumber,Shift newShift)
	{
		String addShiftError="";
		
		
		for(Nurse n : nurseList)
		{
			if(n.getEmployeeId()==empNumber)
			{
				System.out.println("in assign shift"+ empNumber);
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
	//for this project this method was never used but i want to use it after
//removes a shift assigned to the nurse, and returns approporiate message whether it was an error or success removing shift
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
	
	//prints schedule assigned to the nurse
	//@param- id of the nurse whose schedule needs to be printed
	public String printSchedule(long empId)
	{
		
		String sch = "";
		int i = 0;
		if(nurseList.size()==0)
			return "No nurses exist";
		for(Nurse n : nurseList)
		{
			if(n.getEmployeeId()==empId)
			{
				sch = sch+ "Shift start Date"+"\t" +"Shift end time"+"\n";
				sch = "\t"+ n.getSchedule();
				System.out.println("in sched printing nurse matched "+sch);

			}
			System.out.println("in sched printing "+sch);
		}
		return sch;
	}
	//prints all nurses in the given nurse list under this manager
	public String printAllNurses()
	{
		String sch = "";
		if(nurseList.size()==0)
			return "No nurses exist";
		for(Nurse n : nurseList)
		{
			sch =sch+  n.toString()+'\n';
			System.out.println(sch);
		}
		return sch;
		
	}
}
