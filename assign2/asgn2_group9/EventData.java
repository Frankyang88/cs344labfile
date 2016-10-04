public class EventData{
private int heroid;
private String Mname;
private String Qname;
private String Aname;

public EventData(int id,String mname,String qname,String aname){
	this.heroid=id;
	this.Mname=mname;
	this.Qname=qname;
	this.Aname=aname;
}

    public int  getheroid(){
	return this.heroid;
	}


	
    public String getmonstername(){
	return this.Mname;
	}

    public String getquestname(){
	   return this.Qname;
	}


    public String getareaname(){
	 return  this.Aname;
	}

    public int setheroid(int id){
	  return this.heroid=id;
	}

    public String setmonstername(String name){
	  return this.Mname=name;
	}

    public String setquestname(String name){
	  return this.Qname=name;
	}
    public String setmonstername(String name){
	  return this.Aname=name;
	}

    public String toString() {
	return this.heroid+ "  " + this.Qname+ "  " + this.Mname+ " "+this.Aname;
    }




}
