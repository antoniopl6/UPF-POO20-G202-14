public class Bed{
	private int bedID;
	private Room room;
	private Resident resident;
		
	public Bed( int id, Room r ){
		bedID=id;
                room=r;
	}
	
	public void assignRoom( Room r ){
		room=r;
	}
	
	public void assignResident( Resident r ){
		resident=r;
	}
	
	public void release(){
		resident=null;
	}
	
	public int getBedID(){
		return bedID;
	}

	public boolean isAvailable(){
            return resident == null;
	}	
	
	public String toString(){
            return ("Bed "+bedID);
	}
}
