public class Person{
	public int id;
	public String name;
	
	public Person( int id, String name ){
            this.id=id;
            this.name=name;
	}
	
	public int getID(){
            return id;
	}
	public String getName(){
            return name;
	}
	public void setID( int id ){
            this.id=id;
	}
	public void setName( String name ){
		this.name=name;
	}
	public String toString(){
            return(name+" (ID "+id);
	}
}
