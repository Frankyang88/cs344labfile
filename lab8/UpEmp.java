/*
  File: UpEmp.java  
  November 2001
*/

import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * Updates the salary field for a specified employee.
 * Asks for the SSN of an employee and a new salary and
 * then updates the salary.
 * Based on <code>upemp.pc</code> by Paul Werstein.
 *
 * @author Iain Hewson and Paul Werstein
 */

public class UpEmp {
    
   public static void main(String[] args) {
      new UpEmp().go();
   }

    // This function does all the work
   public void go() {

       // Read pass.dat
       UserPass login = new UserPass();
       String user = login.getUserName();
       String pass = login.getPassWord();
       String host = "silver";

       Connection con = null;
       try {
	   // Register the driver and connect to Oracle
	   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	   String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";
	   con = DriverManager.getConnection(url, user, pass);
	   // Turn off autocommit
	   con.setAutoCommit(false);
	   System.out.println("Connected to Oracle");
	   // Get the SSN and new salary
	   int ssn = getInteger("Which employee is to be updated? ");
	   int salary = getInteger("What is the new salary? ");
	   // Get a Statement and execute the appropriate UPDATE
	   Statement stmt = con.createStatement();
	   String command = "UPDATE employee " + "SET salary = " + salary +
			    " WHERE ssn = " + ssn;
           System.out.println("command: " + command);
	   int rowsUpdated = stmt.executeUpdate(command);
	   // State how many rows where updated.
	   System.out.println(rowsUpdated + " row" +
			      (rowsUpdated == 1 ? "" : "s")
			      + " updated.");
	   // Commit the change (done automatically if autocommit is off
	   if (1 == rowsUpdated) {
	       con.commit();
	   }

       } catch (SQLException e) {
	   quit(e.getMessage());

       } finally {
	   if (con != null) {
	       try {
		   con.close();
	       } catch (SQLException e) {
		   quit(e.getMessage());
	       }
	   }
       }
   }  // end go()
           
    // Outputs a message and asks the user for an integer
    private int getInteger(String message) {
	String line = null;
	int number = 0;
	System.out.print(message);
	try {
	    BufferedReader buf = 
		new BufferedReader(new InputStreamReader(System.in));
	    line = buf.readLine();
	    number = Integer.parseInt(line);
	} catch (IOException e) {
	    quit(e.getMessage());
	} catch (NumberFormatException e) {
	    quit("'" + line + "' is not a valid integer.");
	}
	return number;
    }

    // Used to output an error message and exit
    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class UpEmp
