
import java.util.LinkedList;
import java.util.Date;

public class Patient extends Person implements Comparable<Patient>{
	protected Date admissionDate;
	protected Integer age;
	protected LinkedList< Visit > visits;

	public Patient( int id, String name, int age ){
            super(id, name);
            this.age=age;
            visits = new LinkedList<Visit>();
	}
	
	public void addVisit( Visit v ){
            visits.add(v);
	}
		
	public void setAdmissionDate( Date d ){
            admissionDate=d;
	}
	
	public Date getAdmissionDate(){
            return admissionDate;
	}
	
	public void setAge( Integer age ){
            this.age=age;
	}
	
	public Integer getAge(){
            return age;
	}

	public int compareTo( Patient p ){
            if(age < p.getAge()){
                return -1;
            }else if(age > p.getAge()){
                return 1;
            }
            return 0;
	}
	
	public String toString(){
            return(super.toString()+", age "+age+")");
	}
}
