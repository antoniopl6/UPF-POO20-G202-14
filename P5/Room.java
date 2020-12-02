
import java.util.LinkedList;

public class Room{
	private LinkedList< Bed > beds;
	private int roomID;
	
	public Room( int roomID ){
		this.roomID = roomID;
                beds = new LinkedList< Bed >();
	}
	
	public void addBed( int bedID ){
            beds.add(new Bed(bedID, this));
	}
	
	public Bed getBed( int idx ){
            return beds.get(idx);
	}
	
	public Bed getAvailableBed(){
            for(int i=0; i<beds.size();i++){
                if(beds.get(i).isAvailable()){
                    return beds.get(i);
                }
            }
            return null;
	}
	
	public boolean isAvailable(){
            for(int i=0; i<beds.size();i++){
                if(beds.get(i).isAvailable()){
                    return true;
                }
            }
            return false;
	}
	
	public String listBeds(){
            String str = "";
            for(int i=0; i<beds.size();i++){
                str = (str+beds.get(i).toString()+"\n");
            }
            return str;
	}

	public String toString(){ 
            return ("Room "+roomID+"\n");
	}
}
