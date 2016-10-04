/*
  File: Test.java
  August 2002
*/

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * The purpose of this program is to show the use of various data types
 * and the use of wasNull().  It uses a table created with the following
 * SQL command:
 *
 * CREATE TABLE test_x (I INT, 
 *                      N NUMBER,
 *                      R NUMBER(8,2), 
 *                      C VARCHAR2(10),
 *                      D DATE);
 * 
 * @author Paul Werstein
 */

public class Test {

    public static void main (String[] args) {
	new Test().go();
    }

    private void go() {

	// Read pass.dat
	UserPass login = new UserPass();
	String user = login.getUserName();
	String pass = login.getPassWord();
	String host = "silver";

	Connection con = null;
	try {

	    //Register the driver and connect to Oracle
	    DriverManager.registerDriver 
		(new oracle.jdbc.driver.OracleDriver());
	    String url = "jdbc:oracle:thin:@" + host + ":1527:cosc344";
	    con = DriverManager.getConnection(url, user, pass);
	    System.out.println("Connected to Oracle");
	    // Get a Statement and execute the query
	    Statement stmt = con.createStatement();
	    ResultSet result = stmt.executeQuery("SELECT * FROM test_x");
	    // Loop through the result set and output the data
	    while (result.next()) {

		// Get the first column which is an INTEGER.
		// Uses the column name.
		System.out.print(result.getInt("I") + "  ");

		// Get the second column which is a NUMBER.
		// Sometimes it is null.  Uses the column index.
		// If the attribute is null, print NULL.  Otherwise
		// Java will print zero which is misleading.
		int n = result.getInt(2);
		if (result.wasNull()) {
		    System.out.print("NULL  ");
		} else {
		    System.out.print(n + "    "); 

		}

		// Get the third column which is NUMBER(8,2)
		System.out.print(result.getDouble(3) + "  ");

		// Get the fourth column which is VARCHAR2(10)
		System.out.print("x" + result.getString(4) + "x  ");

		// Get the fifth column which is DATE
		System.out.print(result.getDate(5) + "  ");
		System.out.print(result.getTime(5) + "  ");

		System.out.println();
	    }
	    con.close();

	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
    }  // end go()


    private void quit(String message) {
	System.err.println(message);
	System.exit(1);
    }

} // end class Test


