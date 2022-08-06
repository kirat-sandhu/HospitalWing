package application;

import java.util.Date;

public class Person  {

	private String firstName = "";
	private String lastName = "";
	private Date dateOfBirth;
	 
	
	public Person(String fName, String lName, Date dob)
	{
		firstName = fName;
		lastName = lName;
		dateOfBirth = dob;
		
	}
	
	//Getter method that returns firat and last Name
	public String getName()
	{
		return firstName + " " + lastName;
		
	}
	
	private void setFirstName(String fName)
	{
		if(fName!=null)
		firstName = fName;
	}
	private void setLasttName(String lName)
	{
		if(lName!=null)
		lastName = lName;
	}
	
	public  String toString()
	{
		
		String newPerson = "";
		
		if(newPerson !=null)
		{
			newPerson = getName() + " \t"+    dateOfBirth.toString();
		}
		return newPerson;
	}
}
