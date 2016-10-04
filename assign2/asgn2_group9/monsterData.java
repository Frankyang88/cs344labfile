public class monsterData{

   private int level;
   private String location;
   private String name; 
   private int HP;

    public monsterData(String monstername,int level,String areaname,int HP) {
	this.name=monstername;
	this.location=areaname;
	this.level= level;
	this.HP=HP;
    }

    public String getmonsterlocation(){
	return this.location;
	}

    public String getmonstername(){
	   return this.name;
	}
    public int getmonsterlevel(){
		return this.level;
	}
    public int getmonsterHP(){
		return this.HP;
	}

     	
    public String setmonsterlocation(String location){
	return this.location=location;
	}

    public String setmonstername(String name){
	 return  this.name=name;
	}

    public int setmonsterlevel(int level){
	  return this.level=level;
	}
    public int setmonsterHP(int HP){
	  return this.HP=HP;
	}

    public String toString() {
	return this.name+ "  " + this.level+ "  " + this.HP+ " "+this.location;
    }


}
