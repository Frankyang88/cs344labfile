public class AreaData {

   private int code;
   private double size;
   private String name; 

    public AreaData(String Areaname,double Areasize,int Rcode) {
	this.name=Areaname;
	this.size=Areasize;
	this.code= Rcode;
    }

    public double getAreasize(){
	return this.size;
	}

    public String getAreaname(){
	   return this.name;
	}
    public int getrestrictedcode(){
		return this.code;
	}

    public double setAreaSize(double size){
	return this.size=size;
	}

    public String setAreaname(String name){
	 return  this.name=name;
	}

    public int setrestrictedcode(int code){
	  return this.code=code;
	}

    public String toString() {
	return this.name+ "  " + this.size+ "  " + this.code;
    }


} 
