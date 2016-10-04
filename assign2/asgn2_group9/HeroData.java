public class HeroData {

   private int playerid;
   private int heroexp;
   private String heroname; 
   private String BirthDay; 
   private int HeroLevel;

    public HeroData(int playerid, String heroname,String BirthDay,int heroexp,int HeroLevel) {
	this.playerid=playerid;
	this.heroexp=heroexp;
	this.heroname= heroname;
	this.BirthDay=BirthDay;
	this.HeroLevel=HeroLevel;
    }

    public int getplayerid(){
	return this.playerid;
	}

    public String getheroname(){
	   return this.heroname;
	}
    public String getbirthday(){
	return this.BirthDay;
	}
    public int getexp(){
		return this.heroexp;
	}

    public int setplayerid(int pid){
	return this.playerid=pid;
	}

    public String setheroname(String hname){
	 return  this.heroname=hname;
	}
    public String setbirthday(String bdate){
	 return this.BirthDay=bdate;
	}
    public int setexp(int exp){
	  return this.heroexp=exp;
	}
    public int getlevel(){
	  return this.HeroLevel;
	}
   public int setlevel(int level){
	  return this.HeroLevel=level;
	}

    public String toString() {
	return playerid+ "  " + heroname+ "  " + BirthDay+"  "+heroexp+" "+HeroLevel;
    }


} 
