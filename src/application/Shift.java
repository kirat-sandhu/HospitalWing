 package application;

import java.util.Date;

///This class reuses a lot of code from my flight class we worked on in Coding Challenges.Similar to flights, shifts have a start date and end date
//Nurses have as schedule which is list of shift objects.
//A shift is valid if its length is 4,8 or hours.

public class Shift {


	private  Date start;
	private  Date end;
	private static long millisecondsPerHour = 3600000;
	 
	 public Shift() {
		 
		 start = new Date();
		 end = new Date(start.getTime()+millisecondsPerHour);
		 
	 }
 
	 //constructor that makes an instance of this class when given start and end date
	 //if given value for either is null, keeps their values unchanged
	 public Shift(Date startDate,Date endDate)
	{
		if(startDate !=null && endDate!=null && startDate.before(endDate))
		{
			start = new Date (startDate.getTime());
			end = new Date(endDate.getTime());
			
		}
		else if(startDate !=null && endDate!=null && startDate.compareTo(endDate)>=0)
		{
			start =new Date (startDate.getTime());
		}
		else if(startDate != null && endDate ==null)
		{
			start = new Date (startDate.getTime());
			end = endDate;	
		}
		else if(startDate == null && endDate !=null)
		{
			start = startDate;
			end = new Date(endDate.getTime());	
		}
		else 
		{
			start = startDate;
			end = endDate;	
		}
	}
	
//overloading constructor that takes start time of shift and length in terms of hours
//
	 public Shift(Date startDate, double lengthInHours)
	 {
		 Date endDate;
		 if(startDate!=null)
		 {  endDate = new Date (startDate.getTime()+convertHoursToMilliseconds(lengthInHours));
		 	this.start = startDate;
		 	this.end = endDate;
		 }
		 
		 
	 }
	 
	 //copy constructor that makes a deep copy of the shift object passed as argument
	 public Shift(Shift toCopy)
	 {
		 if(toCopy!=null)
		 { 
			 if( toCopy.start !=null && toCopy.end!=null )
		 {	 start =new Date(  toCopy.start.getTime());
		 	end = new Date(toCopy.end.getTime());}
			 else  if( toCopy.start ==null && toCopy.end!=null )
			 {	 start =null;
			 	end = new Date(toCopy.end.getTime());}
			 else  if( toCopy.end==null && toCopy.start!=null)
			 {	 start =new Date(  toCopy.start.getTime());
			 	end = null;
			 }
		 }
		 else
		 {
			 start = null;
			 end=null;
			 
		 }
	 }
	 
	//helper method that  converts hours passed as argumnent into milliseconds and  returns
	 public long convertHoursToMilliseconds(double lengthInHours)
	 {
		 return (long)(lengthInHours* millisecondsPerHour); 
		 
	 }
	//setter method- sets value passed as start if its valid departure time
	 public void setStart(Date startTime)
	 {
		 if(startTime!=null && end !=null && startTime.compareTo(end)<0)
			 start = new Date(startTime.getTime());
		 else if (startTime!=null && end ==null)
			 start = new Date(startTime.getTime());		 
	 }
	 
		//setter method- sets value passed as end if its valid departure time

	 public void setEnd(Date endTime)
	 {
		 if(endTime!=null && start !=null && endTime.compareTo(start)>0)
			 end = new Date (endTime.getTime());
		 else if(endTime!=null && start ==null)
			 end = new Date (endTime.getTime());
		
	 }
	 
	//getter method that returns a string with start date nd time of shift 
	 public String getStart()
	 {
		if(start!=null)
			return start.toString();
		else
			 return  "";
	 }
	
		//getter method that returns a string with end date nd time of shift 
	 public String getEnd()
	 {
		
		 	if(end!=null)
			return end.toString();
		 	else
		 		return "";
		
	 }
	 
	 
	 //returns true if two shifts start and end at same time;
	 public boolean equals(Shift otherShift)
	 {
		 if(otherShift!=null && this.end!=null&& otherShift.start!=null && otherShift.end!=null&& this.start!=null)
			 return otherShift.start.equals(this.start) &&otherShift.end.equals(this.end);
		 else
			 return false;
	 }
	//returns true if shift is 10 hours or 8 hours or 4 hours long and false otherwise
	 public boolean shiftLengthIsValid()
	 {
		 long dayOfArrival=0;
		 long dayOfDeparture=0;		
		 long estimatedLength = 0;
		 boolean isValid= false;
		
		 if(start==null || end == null||end.compareTo(start)<0)
			 estimatedLength= 0;
		 else
		 {
			 dayOfArrival=end.getTime();
			 dayOfDeparture= start.getTime();
			 estimatedLength = (( dayOfArrival-dayOfDeparture)/millisecondsPerHour);
		 }
		 
		 if(estimatedLength==8||estimatedLength==10||estimatedLength==4)
			 isValid=  true ;
		 
			 
			 return isValid;
	 }
	
	 //true if the diff. between two shifts is atleast 12 hours
	 public boolean timeDiffInShiftsValid(Shift otherShift)
	 {
		 if(otherShift!=null && this.end!=null&& otherShift.start!=null && otherShift.end!=null&& this.start!=null)
			 return (this.end.getTime() - otherShift.start.getTime()/(1000*3600)) >= 12;
			
			 else
				 return false;

	 }
	
	 //checks if the two shifts  overlaps 
	 public boolean overlapsWith(Shift otherShift)
	 {
		 if(otherShift!=null && this.end!=null&& otherShift.start!=null && otherShift.end!=null&& this.start!=null)
		 { 
			return ( this.start.before(otherShift.end)&&this.end.after(otherShift.start));
		 }
		  else 
			 return false;
	 }
	
	 //returns true if the newShift is before the current Shift
	 public boolean isBefore(Shift otherShift)
	 {
		 if(otherShift!=null && this.end!=null&& otherShift.start!=null)
		 return this.end.before(otherShift.start);
		 else
			 return false;
	 }
	 
	 //returns a string with a shift 
	 public String toString()
	 {
		 String shifttoPrint = "";
		 
		 shifttoPrint = this.getStart() + '\t' +'\t'+ this.getEnd() +'\n';
		 
		 return shifttoPrint ;
	 }
	
}


