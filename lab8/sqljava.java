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

public class sqljava { 
    private ArrayList<String> fnamelist = new ArrayList<String>(); 
    private ArrayList<String> lnamelist = new ArrayList<String>(); 
    private ArrayList<Integer> salarylist= new ArrayList<Integer>(); 
    
    public sqljava(){
	go();
	
	}	

    public static void main (String[] args) {
	sqljava test=new sqljava();
        test.print_info();
  	test.sort();
	test.print_info();
    }

    public void print_info(){
	System.out.println(String.format("%-20s %-20s %s","fname", "lname","salary"));
	for(int i=0; i< fnamelist.size();i++)
        System.out.println(String.format("%-20s %-20s %s",fnamelist.get(i) ,lnamelist.get(i),salarylist.get(i)));
//System.out.printf(salarylist.get(i));

	}

    public void sort(){
	String fname_tmp;
	String lname_tmp;
        int salary_tmp;    
		for(int i =0;i< salarylist.size();i++){
		 salary_tmp =salarylist.get(i);
                 fname_tmp  =fnamelist.get(i);
                 lname_tmp  =lnamelist.get(i);
 		 int j;
                 for( j = i -1; j>=0 && salary_tmp < salarylist.get(j); j--){
		
				salarylist.set(j+1,salarylist.get(j));
				fnamelist.set(j+1,fnamelist.get(j));
				lnamelist.set(j+1,lnamelist.get(j));


			}	
		 salarylist.set(j+1,salary_tmp);
		 lnamelist.set(j+1,lname_tmp);
		 fnamelist.set(j+1,fname_tmp);
		  
		}    	
			
	}
    // This is the function that does all the work
    private void go() {

	// Read pass.dat
	UserPass login = new UserPass();
	String user = login.getUserName();
	String pass = login.getPassWord();
	String host = "silver";

	Connection con = null;
	try {
	     // Register the driver and connect to Oracle
	    DriverManager.registerDriver 
		(new oracle.jdbc.driver.OracleDriver());
	    String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";
            System.out.println("url: " + url);
	    con = DriverManager.getConnection(url, user, pass);
	    System.out.println("Connected to Oracle");

	    Statement stmt = con.createStatement();
	    ResultSet results=stmt.executeQuery("select fname,lname,salary from employee");	
	    
           // System.out.println("fname      lname      salary");
	    while(results.next() != false){
	    String fname   = results.getNString("fname");
	    int salary    = results.getInt("salary");
	    String lname  = results.getNString("lname"); 
	    fnamelist.add(fname);
            lnamelist.add(lname);
	    salarylist.add(salary);  
	    //System.out.println(fname+" "+lname+"   "+salary);
	}

	    
	    
	    results.close();
	    stmt.close();
	
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

	} finally {
	    if (con != null) {
		try {
		    con.close();
                    System.out.println("Database connection closed");

		} catch (SQLException e) {
		    quit(e.getMessage());
		}
	    }
	}
    }  // end go()

    // Used to output an error message and exit
    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class TestLogin


