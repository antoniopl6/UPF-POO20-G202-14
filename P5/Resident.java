public class Resident extends Patient
{
	private Room room;
	private Bed bed;

	public Resident ( int id, String name, int age ){
            super(id,name,age);
	}
	
	public void assignRoom( Room r ){
            room=r;
	}
	
	public void assignBed( Bed b ){
            bed=b;
	}
	
	public Doctor getDoctor(){
            if(visits.isEmpty()){
                return null;
            }
            return this.visits.get(0).doctor;
	}
	
	public String toString(){
            String str="Resident "+super.toString();
            if((bed==null) && (room==null)){
                str=(str+" and has no room neither bed");
            }else{
                str=(str+" is assigned to "+room.toString()+" "+bed.toString());
            }
            if(getDoctor()==null){
                str=(str+" and has no doctor\n");
            }else{
                str=(str+" and "+getDoctor().toString()+ "\n");
            }
            return str;
	}
}
