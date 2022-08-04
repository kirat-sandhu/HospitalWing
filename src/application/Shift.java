 package application;

import java.util.Date;

public class Shift {


	private  Date start;
	private  Date end;
	
	 
	 public Shift() {
		 
		 start = new Date();
		 end = new Date(start.getTime()+3600000);
		 
	 }
 
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
	

	 
	 //copy constructor
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
	//setter method- sets value passed as departure if its valid departure time
	 public void setStart(Date startTime)
	 {
		 if(startTime!=null && end !=null && startTime.compareTo(end)<0)
			 start = new Date(startTime.getTime());
		 else if (startTime!=null && end ==null)
			 start = new Date(startTime.getTime());		 
	 }
	 
	 public void setEnd(Date endTime)
	 {
		 if(endTime!=null && start !=null && endTime.compareTo(start)>0)
			 end = new Date (endTime.getTime());
		 else if(endTime!=null && start ==null)
			 end = new Date (endTime.getTime());
		
	 }
	 
	 
	 public String getStart()
	 {
		if(start!=null)
			return start.toString();
		else
			 return  "";
	 }
	 
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
	//returns true if shift is 10 hours or 8 hours or 4 hors long and false otherwise
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
			 estimatedLength = (( dayOfArrival-dayOfDeparture)/(1000*3600));
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


