package application;

import java.time.LocalDate;
import java.util.Date;


//parent class of nurse, manager and patient..
//To limit the scope of this project patient wasnt included in this project
//Person has first and last name and date of birth 
//
public class Person  {

	private String firstName = "";
	private String lastName = "";
	private Date dateOfBirth;
	 
	//construcor cretes a new person with a name and date of birth
	public Person(String fName, String lName, Date dob)
	{
		firstName = fName;
		lastName = lName;
		dateOfBirth = dob;
		
	}
	
	//copy constructor creates a deep copy of the person passed as argument
	public Person(Person toCopy)
	{
		this.setFirstName(toCopy.firstName);
		this.setLastName(toCopy.lastName);
		this.dateOfBirth = toCopy.dateOfBirth;
		
	}
	//Getter method that returns first and last Name in one string
	public String getName()
	{
		return firstName + " " + lastName;
		
	}
	
	//Getter method that returns first in one string

	public String getFName()
	{
		return firstName ;
		
	}
	//Getter method that returns  last Name in one string

	public String getLName()
	{
		return  lastName;
		
	}
	//Getter method that returns date of birth of this person
	public Date getDOB()
	{
		return dateOfBirth;
		
	}
	
	//setter methods that set firstname of this person to the string passed
	private void setFirstName(String fName)
	{
		if(fName!=null)
		firstName = fName;
	}
	//setter methods that set lastname of this person to the string passed

	private void setLastName(String lName)
	{
		if(lName!=null)
		lastName = lName;
	}
	//returns a string with this persons name and date of birth
	public  String toString()
	{
		
		String newPerson = "";
		
		if(newPerson !=null)
		{
			newPerson = getName() + " \t"+   ( dateOfBirth).toString();
		}
		return newPerson;
	}
}
