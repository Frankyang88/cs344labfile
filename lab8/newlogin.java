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

public class newlogin {


    public static void main (String[] args) {
	new newlogin().go();
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
	    ResultSet results=stmt.executeQuery("select * from department where mgrssn = 333445555");	
	    if(results.next() != false){
	    String dname   = results.getNString("dname");
	    int dnumber    = results.getInt("dnumber");
	    String mgrssn  = results.getNString("mgrssn");
            java.sql.Date startDate = results.getDate("mgrstartdate");  
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
            String showDate = df.format(startDate);  
	    System.out.println("dname:"+dname+"\ndnumber:"+dnumber+"\nmgrssn:"+mgrssn+"\nstartdate:"+showDate);
		}

	    int rows = stmt.executeUpdate("update employee set salary=salary*1.1");
	    if(rows>0){
		System.out.println("update successfully,"+rows+" rows updates");
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


