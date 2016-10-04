public class quest{
private String Qname;
private String Qtype;
private int mandatory;
private String difficulty;

public quest(String qname,String qtype,int mandatory,String difficulty){
	this.Qname=qname;
	this.Qtype=qtype;
	this.mandatory=mandatory;
	this.difficulty=difficulty;
}
 public String getQuestname(){
	   return this.Qname;
	}
 public int getmandatorytype(){
		return this.mandatory;
	}
 public String getQuesttype(){
		return this.Qtype;
	}

 public String getQuestdifficulty(){
		return this.difficulty;
	}




 public int setmandatory(int i){
	// return -1 means failure
		if(i==0 || i==1)
		return this.mandatory=i;
		else return -1;
	}

 public String setQuesttype(String type){
		return this.Qtype=type;
	}

 public String setQuestname(String name){
		return this.Qname=name;
	}
 public String setQuestdifficulty(String difficulty){
		return this.difficulty=difficulty;
	}
 public String toString() {
	return this.Qname+ "  " + this.Qtype+ "  " + this.mandatory+ " "+this.difficulty;
    }

}
