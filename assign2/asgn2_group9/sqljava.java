/*
  File: TestLogin.java
  August 2002
*/

import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This program reads a user's pass.dat and connects to Oracle.
 *
 * @author Paul Werstein
 */
/*

   public ArrayList<HeroData> getallplayerinfo();
   public int createnewplayer(HeroData player);
   public int deletehero(int playerid);
   public HeroData getplayerinfo(String pid);
   public int updateheroExp(int playerid ,int exp);
  
   public ArrayList<AreaData> getareainfo();
   public int updateAreasize(String areaname,double size);
   public int updateAreacode(String areaname,int code);
   public int createArea(AreaData area);
   public AreaData getArea(String areaname);

   public ArrayList<monsterData> getallmonsterinfo();
   public int createmonster(monsterData monster);
   public int deletemonster(int mname);
   public monsterData getmonsterinfo(String mname);
   public int updatemonsterlocation(String Mname,String location);
   public int updatemonsterlevel(String Mname,int level);


   private void sql_connect();
   public void sql_close();



*/
public class sqljava { 
   
    private HeroData newhero;
   Connection con;
 	





   public sqljava(){
		sql_connect();
	}	

    public static void main (String[] args) {

	sqljava test=new sqljava();
	HeroData samplehero=new HeroData(111,"Frank","16/1/2017",9921299,-1);  
  	test.createnewplayer(samplehero);
	samplehero.setexp(12222210);
	test.updateheroExp(samplehero.getplayerid(),samplehero.getexp());
	HeroData tmp=test.getplayerinfo("111");
        System.out.println(tmp.getplayerid()+" "+tmp.getheroname()+"  "+tmp.getbirthday()+"  "+tmp.getexp());
	ArrayList<HeroData> wow= test.getallplayerinfo();
	for(int i=0;i<wow.size();i++){
	System.out.println(wow.get(i).toString());}

	test.deletehero(samplehero.getplayerid());

	AreaData samplearea=new AreaData("human kingdom",100.00,1);
	test.createArea(samplearea);
	test.updateAreasize(samplearea.getAreaname(),10.00);
	test.updateAreacode(samplearea.getAreaname(),0);
	AreaData tmpa=test.getArea(samplearea.getAreaname());

   	ArrayList<AreaData> wow2= test.getareainfo();
	for(int i=0;i<wow2.size();i++){
	System.out.println(wow2.get(i).toString());}

 	samplearea=new AreaData("human kingdom2",100.00,1);
	test.createArea(samplearea);   
        
	monsterData samplemonster=new monsterData("Frank T800",33,"human kingdom",0);
	test.createmonster(samplemonster);
	test.updatemonsterlocation(samplemonster.getmonstername(),"human kingdom2");
	test.updatemonsterlevel(samplemonster.getmonstername(),100);
	monsterData tmpm=test.getmonsterinfo(samplemonster.getmonstername());
	System.out.println(tmpm.toString());
        test.deletemonster(tmpm.getmonstername());
	ArrayList<monsterData> wow3= test.getallmonsterinfo();
	for(int i=0;i<wow3.size();i++){
	System.out.println(wow3.get(i).toString());}


	test.deleteArea(tmpa.getAreaname());
	test.deleteArea(samplearea.getAreaname());
         


	test.sql_close();	   
    }

   /*weapon operation*/
 
   /*event data operation*/

   /*quest operation*/

    /*backpack operation*/


    /*monster operation*/
   public int deletemonster(String Mname){
	 	try {	    
	        	String query = "Delete from monster where mname = ?";
	     	 	PreparedStatement prepSQL = con.prepareStatement(query);
	   	 	prepSQL.setString (1, Mname);
	   	 	ResultSet results = prepSQL.executeQuery();
	  		results.close();
	   	 	prepSQL.close();		

		} catch (SQLException e) {
	    		System.out.println(e.getMessage());
	    		System.exit(1);
		}	 
		return 0; 
	}
   public ArrayList<monsterData> getallmonsterinfo(){
	 ArrayList<monsterData> allmonster = new ArrayList<monsterData>();
	 try{
	    	 String query = "SELECT * FROM monster";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 ResultSet results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	   	 int mlevel    = results.getInt("mlevel");
	   	 String mname  = results.getNString("mname");
		 String aname  = results.getNString("aname");

	 	 monsterData monsterinfo= new monsterData(mname,mlevel,aname,-1);
		 allmonster.add(monsterinfo);
		}

		 query = "SELECT * FROM MHP";
	     	 prepSQL = con.prepareStatement(query);
	   	 results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int mlevel   = results.getInt("mlevel");
	   	 int mhp      = results.getInt("mhp");
		 for(int i=0;i<allmonster.size();i++){
			if(allmonster.get(i).getmonsterlevel()==mlevel){	
				allmonster.get(i).setmonsterHP(mhp);	
			}

		   }
		}
       		results.close();
	   	prepSQL.close();
		}
	catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 

		return allmonster;

	}

   public int createmonster(monsterData monster){
	try{
		 String query = "INSERT INTO monster(mname,mlevel,aname) VALUES (?,?,?)";
	   	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, monster.getmonstername());
		 prepSQL.setString (2, Integer.toString(monster.getmonsterlevel()));
		 prepSQL.setString (3, monster.getmonsterlocation());
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
       return 0;  

	}
   public monsterData getmonsterinfo(String mname){
	monsterData monsterinfo = null;

	try {	    
	   	 String query = "SELECT mname,mlevel,aname FROM monster WHERE mname = ? ";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, mname);
	   	 ResultSet results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int level=results.getInt("mlevel");
	   	 String aname  = results.getNString("aname");
		 String monname=results.getNString("mname");
         
	 	 monsterinfo= new monsterData(monname,level,aname,0);
		}
	   	 query = "SELECT  mhp FROM MHP WHERE mlevel = ? ";
	     	 prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, Integer.toString(monsterinfo.getmonsterlevel()));
	   	 results = prepSQL.executeQuery();

	   	 while(results.next() != false){
		 int mhp  = results.getInt("mhp");
		 monsterinfo.setmonsterHP(mhp);
		}

       		results.close();
	   	prepSQL.close();
	    
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
 
	  return monsterinfo;
		
		
		
	}
   public int updatemonsterlocation(String Mname,String location){

	try {	    
	         String query = "UPDATE monster SET  aname= ? where mname = ?";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, location);
                 prepSQL.setString (2, Mname);
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

		} 
	return 0; 
	}
   public int updatemonsterlevel(String Mname,int level){

	try {	    
	         String query = "UPDATE monster SET mlevel = ? where mname = ?";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, Integer.toString(level));
                 prepSQL.setString (2, Mname);
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

		} 
	return 0; 
	}

	/*area operation*/
    public int createArea(AreaData area){
		
	try{
		 String query = "INSERT INTO area(aname,asize,restricted) VALUES (?,?,?)";
	   	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, area.getAreaname());
		 prepSQL.setString (2, Double.toString(area.getAreasize()));
		 prepSQL.setString (3, Integer.toString(area.getrestrictedcode()));
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
       return 0;   
    }
    public AreaData getArea(String areaname){

	AreaData area = null;

	try {	    
	   	 String query = "SELECT * FROM area WHERE aname = ? ";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, areaname);
	   	 ResultSet results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int code   = results.getInt("restricted");
	   	 int size   = results.getInt("asize");
	   	 String name  = results.getNString("aname");
	 	 area= new AreaData(name,size,code);
		}
		
       		results.close();
	   	prepSQL.close();
	    
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

		} 
 
	  return area;
	}
    public int deleteArea(String areaname){
	 	try {	    
	        	String query = "Delete from area where aname = ?";
	     	 	PreparedStatement prepSQL = con.prepareStatement(query);
	   	 	prepSQL.setString (1, areaname);
	   	 	ResultSet results = prepSQL.executeQuery();
	  		results.close();
	   	 	prepSQL.close();		

		} catch (SQLException e) {
	    		System.out.println(e.getMessage());
	    		System.exit(1);

		}	 
		return 0; 
	}

    public int updateAreacode(String areaname,int code){
	
 	try {	    
	         String query = "UPDATE area SET restricted = ? where aname = ?";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, Integer.toString(code));
                 prepSQL.setString (2, areaname);
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
	return 0; 
	}

    public int updateAreasize(String areaname,double size){
	
 	try {	    
	         String query = "UPDATE area SET asize = ? where aname = ?";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, Double.toString(size));
                 prepSQL.setString (2, areaname);
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
	return 0; 
	}
    public ArrayList<AreaData> getareainfo(){
	 ArrayList<AreaData> allarea = new ArrayList<AreaData>();
	 try{
	    	 String query = "SELECT * FROM area";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 ResultSet results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int asize   = results.getInt("asize");
	   	 int code    = results.getInt("restricted");
	   	 String aname  = results.getNString("aname");
 		 
          	  
	 	 AreaData info= new AreaData(aname,asize,code);
		 allarea.add(info);
		}

       		results.close();
	   	prepSQL.close();
		}
	catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 

		return allarea;
}	
	/*hero data operation*/
    public int deletehero(int playerid){

	 	try {	    
	        	String query = "Delete from hero where pid = ?";
	     	 	PreparedStatement prepSQL = con.prepareStatement(query);
	   	 	prepSQL.setString (1, Integer.toString(playerid));
	   	 	ResultSet results = prepSQL.executeQuery();
	  		results.close();
	   	 	prepSQL.close();
		} catch (SQLException e) {
	    		System.out.println(e.getMessage());
	    		System.exit(1);

		}	 
		return 0; 
	}


    public int updateheroExp(int playerid ,int exp){
 	try {	    
	         String query = "UPDATE hero SET exp = ? where pid = ?";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (2, Integer.toString(playerid));
                 prepSQL.setString (1, Integer.toString(exp));
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
	return 0; 
   }

    public int createnewplayer(HeroData player)
    { 
	try{
		 String query = "INSERT INTO hero (pid,hname,bdate,exp) VALUES (?,?,TO_DATE(?,'dd/mm/yyyy'),?)";
	   	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, Integer.toString(player.getplayerid()));
		 prepSQL.setString (2, player.getheroname());
		 prepSQL.setString (3, player.getbirthday());
                 prepSQL.setString (4, Integer.toString(player.getexp()));
	   	 ResultSet results = prepSQL.executeQuery();
	  	 results.close();
	   	 prepSQL.close();
	
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
       return 0;    
    }

    public HeroData getplayerinfo(String pid){
	HeroData heroinfo = null;

	try {	    
	   	 String query = "SELECT pid,hname,bdate,exp FROM hero WHERE pid = ? ";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, pid);
	   	 ResultSet results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int playerid   = results.getInt("pid");
	   	 int heroexp    = results.getInt("exp");
	   	 String heroname  = results.getNString("hname");
 
          	 java.sql.Date BDate = results.getDate("bdate");  
	   	 SimpleDateFormat bdatetmp = new SimpleDateFormat("dd/MM/yyyy");  
           	 String BirthDay = bdatetmp.format(BDate);  
	 	 heroinfo= new HeroData(playerid,heroname,BirthDay,heroexp,-1);
 		
		}
	   	 query = "SELECT hlevel FROM HLEVEL WHERE hexp = ? ";
	     	 prepSQL = con.prepareStatement(query);
	   	 prepSQL.setString (1, Integer.toString(heroinfo.getexp()));
	   	 results = prepSQL.executeQuery();

	   	 while(results.next() != false){
		 int herolevel  = results.getInt("hlevel");
		 heroinfo.setlevel(herolevel);
		}

       		results.close();
	   	prepSQL.close();
	    
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 
 
	  return heroinfo;
     }
    public ArrayList<HeroData> getallplayerinfo(){
	 ArrayList<HeroData> allplayer = new ArrayList<HeroData>();
	 try{
	    	 String query = "SELECT * FROM hero";
	     	 PreparedStatement prepSQL = con.prepareStatement(query);
	   	 ResultSet results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int playerid   = results.getInt("pid");
	   	 int heroexp    = results.getInt("exp");
	   	 String heroname  = results.getNString("hname");
 
          	 java.sql.Date BDate = results.getDate("bdate");  
	   	 SimpleDateFormat bdatetmp = new SimpleDateFormat("dd/MM/yyyy");  
           	 String BirthDay = bdatetmp.format(BDate);  
	 	 HeroData heroinfo= new HeroData(playerid,heroname,BirthDay,heroexp,-1);
		 allplayer.add(heroinfo);
		}

		 query = "SELECT * FROM HLEVEL";
	     	 prepSQL = con.prepareStatement(query);
	   	 results = prepSQL.executeQuery();

	   	 while(results.next() != false){
	  	 int herolevel   = results.getInt("hlevel");
	   	 int heroexp    = results.getInt("hexp");
		 for(int i=0;i<allplayer.size();i++){
			if(allplayer.get(i).getexp()==heroexp){	
				allplayer.get(i).setlevel(herolevel);	
			
			}

		   }
		}



       		results.close();
	   	prepSQL.close();
		}
	catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} 

		return allplayer;
}	     

    private void sql_connect(){
		// Read pass.dat
	UserPass login = new UserPass();
	String user = login.getUserName();
	String pass = login.getPassWord();
	String host = "silver";

	con = null;
	try {
	     // Register the driver and connect to Oracle
	    DriverManager.registerDriver 
		(new oracle.jdbc.driver.OracleDriver());
	    String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";
            System.out.println("url: " + url);
	    con = DriverManager.getConnection(url, user, pass);
	    System.out.println("Connected to Oracle");
	    
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

		}

	}

    public void sql_close(){

	if (con != null) {
		try {
		    con.close();
                    System.out.println("Database connection closed");

		} catch (SQLException e) {
		    quit(e.getMessage());
		}
	    }
	}	

    // Used to output an error message and exit
    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class TestLogin


