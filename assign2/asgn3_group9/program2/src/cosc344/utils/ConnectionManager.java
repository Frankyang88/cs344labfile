package cosc344.utils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * We will use simple singleton pattern to ensure only one connection is created
 */
public class ConnectionManager {
    private static Connection cn = null;
    
    /**
     * Establishes connection
     * @return Connection the established connection
     */
    public static Connection getSQLConnection() throws SQLException {
        if (cn == null) {
            ArrayList<String> list = new ArrayList<>();
            readUserAndPassword(list);
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String dbPath = list.get(0);
            String username = list.get(1);
            String password = list.get(2);
            cn = DriverManager.getConnection(dbPath, username, password);
        }
        return cn;
    }

    /**
     * To connect to the database, you just need to create a plain txt file in this folder
     * enter the host address
     * your username and password
     * one item a line
     *
     * FILENAME:password.txt
     *
     * AND MAKE SURE NOT ADD THIS FILE TO GIT!!!
     */
    public static void readUserAndPassword(ArrayList<String> pws){
        Path path = Paths.get("cosc344/utils/password.txt");
        try {
            Files.lines(path).forEach((x)->{
                pws.add(x);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Closes connection
     */
    public static void closeConnection() throws SQLException {
        cn.close();
    }
}
