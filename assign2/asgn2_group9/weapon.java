public class weapon{
private int wid;
private String wname;
private int pdamage;
private int mdamage;
private String rarity;
private int value;
private int ownerid;
private String bname;
private String qname;

public class weapon(int wid,String wname, int pdamage, int mdamage, String rarity,int value,int ownerid,String bname,String qname){
	this.wid=wid;
 	this.wname=wname;
	this.pdamage=pdamage;
	this.mdamage=mdamage;
	this.rarity=rarity;
	this.value=value;
	this.ownerid=ownerid;
	this.bname=bname;
	this.qname=qname;
}

 public String getweaponbackpack(){
	   return this.bname;
	}
 public String getweaponname(){
	   return this.wname;
	}

 public int getpdamage(){
		return this.pdamage;
	}
 public int getmdamage(){
		return this.mdamage;
	}
 public int getweaponid(){
		return this.wid;
	}
 public int getvalue(){
		return this.value;
	}
 public int getowner(){
		return this.ownerid;
	}

 public String getrarity(){
		return this.rarity;
	}

 public String setweapononQuest(String qname){
		return this.qname=qname;
	}

 public String setweaponbackpack(String bname){
	   return this.bname=bname;
	}
 public String setweaponname(String wname){
	   return this.wname=wname;
	}

 public int setpdamage(int damage){
		return this.pdamage=damage;
	}
 public int setmdamage(int damage){
		return this.mdamage=damage;
	}
 public int setweaponid(int id){
		return this.wid=id;
	}
 public int setvalue(int value){
		return this.value=value;
	}
 public int setowner(int id){
		return this.ownerid=id;
	}

 public String setrarity(String rarity){
		return this.rarity=rarity;
	}

 public String setweapononQuest(String name){
		return this.qname=name;
	}











 public String toString() {
	return this.wid+" "+this.wname+" "+this.pdamage+"  "+this.mdamage+ " " +this.rarity+" "+this.value+" "
		this.ownerid+ "  " + this.bname+ "  " + this.qname;
    }

}
