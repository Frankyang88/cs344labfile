public class backpack{
private int ownerid;
private String bname;
private int size;
private int money;
private ArrayList<String> consumable;

public backpack(int id,int name,int size,int money,ArrayList<String> consumable){
	this.ownerid=id;
	this.bname=name;
	this.money=money;
	this.size=size;
	this.consumable=consumable;
}

 public int getownerid(){
	   return this.ownerid;
	}
 public String getbackpackname(){
		return this.bname;
	}

 public int getsize(){
		return this.size;
	}
 public int getmoney(){
		return this.money;
	}

 public ArrayList<String> getconsumable(){
		return this.consumable;
	}

 public int setownerid(int id){
	  return this.ownerid=id;
	}

 public int setbackpacksize(int size){
	  return this.size=size;
	}

 public int setmoney(int money){
	  return this.money=money;
	}

 public String setbackpackname(String name){
	  return this.bname=name;
	}
 public ArrayList<String> setconsumable(ArrayList<String> consumable){
		return this.consumbale=consumable;
	}



 public String toString() {
	return this.ownerid+ "  " + this.bname+ "  " + this.size+ " "+this.money;
    }

}
