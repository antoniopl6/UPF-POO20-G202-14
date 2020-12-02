
import java.util.LinkedList;
import java.util.Date;

public class Administrative extends Person
{

	private Hospital hospital;	
	
	public Administrative( int id, String name, Hospital hospital ){
		super(id, name);
                this.hospital = hospital;
	}
	
	public void addVisit( Date d, String s, Doctor doc, Patient p ){
            hospital.addVisit(new Visit(d, s, doc, p ));
	}

	public boolean assignBed( Resident resident  ){
            LinkedList<Room> rooms = hospital.getRooms();
            for(int i=0; i<rooms.size();i++){
                Bed b = rooms.get(i).getAvailableBed();
                if(b!=null){
                    resident.assignBed(b);
                    resident.assignRoom(rooms.get(i));
                    System.out.println(toString()+" has asigned bed to\n"+resident.toString());
                    return true;
                }
            }
            System.out.println(toString()+" has not found bed for\n"+resident.toString());
            return false;
	}
	
	public String toString(){
            return ("Administrative "+super.toString()+")");
 	}
}
