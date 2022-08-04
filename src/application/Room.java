package application;

public class Room {

	private int roomNumber;
	private int roomCapacity;
	
	public Room(int roomNum, int roomCap) {
		roomNumber= roomNum;
		roomCapacity= roomCap;
		
	}
	
	public Room(Room toCopy) {
		this.roomNumber=toCopy.roomNumber ;
		this.roomCapacity= toCopy.roomCapacity;
		
	}
	public int getRoomNumber()
	{
		return roomNumber;
		
	}
	
	public int getRoomCapacity(int roomNum)
	{
		if(roomNum== roomNumber)
			return roomCapacity;
		else
			return 0;
		
	}
	
}
