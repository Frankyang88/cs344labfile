package cosc344.services;

import cosc344.models.Monster;
import cosc344.utils.NotFoundException;

import java.sql.*;
import java.util.*;

public class MonsterService {
    private Connection conn;
    
    /**
     * 1-arg MonsterService constructor
     * @param connection to help with the database
     */
    public MonsterService(Connection connection) throws SQLException {
        this.conn = connection;
    }
    /**
     * getObject-method. This will create and load valueObject contents from database
     * using given Primary-Key as identifier. This method is just a convenience method
     * for the real load-method which accepts the valueObject as a parameter. Returned
     * valueObject will be created using the createValueObject() method.
     * 
     * @param name - the name of the Monster to create a valueObject with
     * @return valueObject - the created class instance to be passed to the real load method
     */
    public Monster getObject(String name) throws NotFoundException, SQLException {

        Monster valueObject = new Monster();
        valueObject.setName(name);
        load( valueObject);
        return valueObject;
    }


    /**
     * load-method. This will load valueObject contents from database using
     * Primary-Key as identifier. Upper layer should use this so that valueObject
     * instance is created and only primary-key should be specified. Then call
     * this method to complete other persistent information. This method will
     * overwrite all other fields except primary-key and possible runtime variables.
     * If load can not find matching row, NotFoundException will be thrown.
     *
     * @param valueObject  This parameter contains the class instance to be loaded.
     *                     Primary-key field must be set for this to work properly.
     */
    public void load(Monster valueObject) throws NotFoundException, SQLException {

        if (valueObject.getName() == null) {
            System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }

        String sql = "SELECT * FROM monster WHERE (mname = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getName());

            singleQuery( stmt, valueObject);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }
    
    /**
     * Loads all monsters by areaname
     * @param areaname that the monsters will be loaded from
     * @return searchResults of monster objects related to the query.
     */
    public ArrayList<Monster> loadAllByAreaName(String areaname) throws SQLException {

        String sql = "SELECT * FROM monster WHERE (aname = ? ) ORDER BY mname ASC ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, areaname);
        ArrayList<Monster> searchResults = listQuery( stmt);

        return searchResults;
    }

    /**
     * LoadAll-method. This will read all contents from database table and
     * build a List containing valueObjects. Please note, that this method
     * will consume huge amounts of resources if table has lot's of rows.
     * This should only be used when target tables have only small amounts
     * of data.
     * @return searchResults - List of Monster Objects related to the query.
     */
    public ArrayList<Monster> loadAll() throws SQLException {

        String sql = "SELECT * FROM monster ORDER BY mname ASC ";
        ArrayList<Monster> searchResults = listQuery( conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * LoadAll-method. This will read all contents from database table and
     * build a List containing valueObjects. Please note, that this method
     * will consume huge amounts of resources if table has lot's of rows.
     * This should only be used when target tables have only small amounts
     * of data.
     *
     * @return count of rows affected by the query.
     */
    public int loadAllByGroupByAreaName(String areaname) throws NotFoundException, SQLException {

        String sql = "SELECT aname, count(aname) AS num FROM monster WHERE (aname = ?) GROUP BY aname";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, areaname);
        //ArrayList<Monster> searchResults = listQuery( conn.prepareStatement(sql));
        int count = 0;
        
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {
             count = result.getInt("num");

            } else {
                System.out.println("No this area!");
                throw new NotFoundException("Haven't found this area!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
        
        return count;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * valueObject contents. Make sure that values for all NOT NULL columns are
     * correctly specified. Also, if this table does not use automatic surrogate-keys
     * the primary-key must be specified. After INSERT command this method will
     * read the generated primary-key back to valueObject if automatic surrogate-keys
     * were used.
     *
     * @param valueObject  This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key
     *                     field must be set for this to work properly.
     */
    public synchronized void create(Monster valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO monster ( mname, mlevel, aname) VALUES (?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getName());
            stmt.setInt(2, valueObject.getLevel());
            stmt.setString(3, valueObject.getAreaname());

            int rowcount = databaseUpdate( stmt);
            if (rowcount != 1) {
                System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null)
                stmt.close();
        }


    }


    /**
     * save-method. This method will save the current state of valueObject to database.
     * Save can not be used to create new instances in database, so upper layer must
     * make sure that the primary-key is correctly specified. Primary-key will indicate
     * which instance is going to be updated in database. If save can not find matching
     * row, NotFoundException will be thrown.
     *
     * @param valueObject  This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    public void save(Monster valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE monster SET mlevel = ?, aname = ? WHERE (mname = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valueObject.getLevel());
            stmt.setString(2, valueObject.getAreaname());

            stmt.setString(3, valueObject.getName());

            int rowcount = databaseUpdate( stmt);
            if (rowcount == 0) {
                System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * delete-method. This method will remove the information from database as identified by
     * by primary-key in supplied valueObject. Once valueObject has been deleted it can not
     * be restored by calling save. Restoring can only be done using create method but if
     * database is using automatic surrogate-keys, the resulting object will have different
     * primary-key than what it was in the deleted object. If delete can not find matching row,
     * NotFoundException will be thrown.
     *
     * @param valueObject  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete(Monster valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getName() == null) {
            System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Can not delete without Primary-Key!");
        }

        String sql = "DELETE FROM monster WHERE (mname = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getName());

            int rowcount = databaseUpdate( stmt);
            if (rowcount == 0) {
                System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * deleteAll-method. This method will remove all information from the table that matches
     * this Dao and ValueObject couple. This should be the most efficient way to clear table.
     * Once deleteAll has been called, no valueObject that has been created before can be
     * restored by calling save. Restoring can only be done using create method but if database
     * is using automatic surrogate-keys, the resulting object will have different primary-key
     * than what it was in the deleted object. (Note, the implementation of this method should
     * be different with different DB backends.)
     *
     */
    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM monster";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            int rowcount = databaseUpdate( stmt);
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * coutAll-method. This method will return the number of all rows from table that matches
     * this Dao. The implementation will simply execute "select count(primarykey) from table".
     * If table is empty, the return value is 0. This method should be used before calling
     * loadAll, to make sure table has not too many rows.
     *
     * @return allRows - The count of rows affected by the query.
     */
    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM monster";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next())
                allRows = result.getInt(1);
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
        return allRows;
    }


    /**
     * searchMatching-Method. This method provides searching capability to
     * get matching valueObjects from database. It works by searching all
     * objects that match permanent instance variables of given object.
     * Upper layer should use this by setting some parameters in valueObject
     * and then  call searchMatching. The result will be 0-N objects in a List,
     * all matching those criteria you specified. Those instance-variables that
     * have NULL values are excluded in search-criteria.
     *
     * @param valueObject  This parameter contains the class instance where search will be based.
     *                     Primary-key field should not be set.
     * @return searchResults of Monster objects related to the query.
     */
    public ArrayList<Monster> searchMatching(Monster valueObject) throws SQLException {

        ArrayList<Monster> searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM monster WHERE 1=1 ");

        if (valueObject.getName() != null) {
            if (first) { first = false; }
            sql.append("AND mname LIKE '").append(valueObject.getName()).append("%' ");
        }

        if (valueObject.getLevel() != 0) {
            if (first) { first = false; }
            sql.append("AND mlevel = ").append(valueObject.getLevel()).append(" ");
        }

        if (valueObject.getAreaname() != null) {
            if (first) { first = false; }
            sql.append("AND aname LIKE '").append(valueObject.getAreaname()).append("%' ");
        }

        sql.append("ORDER BY mname ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList<Monster>();
        else
            searchResults = listQuery( conn.prepareStatement(sql.toString()));

        return searchResults;
    }


    /**
     * databaseUpdate-method. This method is a helper method for internal use. It will execute
     * all database handling that will change the information in tables. SELECT queries will
     * not be executed here however. The return value indicates how many rows were affected.
     * This method will also make sure that if cache is used, it will reset when data changes.
     *
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @return result      The number of rows affected by the query.
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }



    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return only one row. The result set will be converted
     * to valueObject. If no rows were found, NotFoundException will be thrown.
     *
     * @param stmt         This parameter contains the SQL statement to be executed.
     * @param valueObject  Class-instance where resulting data will be stored.
     */
    protected void singleQuery(PreparedStatement stmt, Monster valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setName(result.getString("mname"));
                valueObject.setLevel(result.getInt("mlevel"));
                valueObject.setAreaname(result.getString("aname"));
                valueObject.setHitpoints(this.levelToHitpoints(result.getInt("mlevel")));

            } else {
                System.out.println("Monster Object Not Found!");
                throw new NotFoundException("Monster Object Not Found!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of valueObjects. If no rows were found, an empty List will be returned.
     *
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @return searchResults of Monster objects related to the query.
     */
    protected ArrayList<Monster> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<Monster> searchResults = new ArrayList<>();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                Monster temp = new Monster();

                temp.setName(result.getString("mname"));
                temp.setLevel(result.getInt("mlevel"));
                temp.setAreaname(result.getString("aname"));
                temp.setHitpoints(this.levelToHitpoints(result.getInt("mlevel")));

                searchResults.add(temp);
            }

        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }

        return searchResults;
    }

    private int levelToHitpoints(int level){
     return level * 5;
    }
}