/*
  File: EmpName.java
  August 2002
*/

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * Prompts the user for the initial letter of an employee's first name
 * and then queries the EMPLOYEE table for the employee's first and
 * last name and department number.
 *
 * @author Iain Hewson and Paul Werstein
 */

public class EmpName {


    public static void main (String[] args) {
	new EmpName().go();
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
	    con = DriverManager.getConnection(url, user, pass);
	    System.out.println("Connected to Oracle");
	    // Get the first letter of the first name
	    char c = getInitialLetter();
	    // Get a Statement and execute the appropriate SELECT
	    Statement stmt = con.createStatement();
	    String command = "SELECT fname,lname,dno FROM employee " +
			     "WHERE fname LIKE '" + c + "%'";
	    ResultSet result = stmt.executeQuery(command);
	    // Get the ResultSet metadata and determine the number
	    // of columns
	    ResultSetMetaData metadata = result.getMetaData();
	    int columns = metadata.getColumnCount();
	    // Print a simple heading
	    System.out.println("\nFNAME          LNAME        DNO");
	    System.out.println("-------------------------------");
	    // Loop through the result set and print the columns
	    while (result.next()) {
		for (int i=1; i<=columns; i++) {
		    System.out.print(result.getString(i) +
				     spaces(15-result.getString(i).length()));
		}
		System.out.println();
	    }

	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);

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

    // Puts 'number' of spaces into a buffer and returns it
    private String spaces (int number) {
	StringBuffer buf = new StringBuffer();
	for (int i=0; i<number; i++) {
	    buf.append(' ');
	}
	return buf.toString();
    }

    // Asks the user for a letter and returns it
    private char getInitialLetter() {
	int c = 0;
	System.out.print("\nEnter the initial letter of a first name: ");
	try {
	    while((c = System.in.read()) == -1)
		;
	} catch (IOException e) {
	    quit(e.getMessage());
	}
	System.out.println();
	return (char) c;
    }

    // Used to output an error message and exit
    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class EmpName


