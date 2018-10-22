package testJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC_CST {
	
	
	public static void createTable() {

	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:custom.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

//	      statement.executeUpdate("drop table if exists data");
	      statement.executeUpdate("create table if not exists data ("
		      		+ "id text primary key,"
		      		+ "name text, " 
		      		+ "level double, " 
		      		+ "bug double, " 
		      		+ "sell double, " 
		      		+ "updown double, " 
		      		+ "comment text)");
	      
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert into data values (?,?,?,?,?,?,?);");
	      
		    for(String id : BaseConfig.dfcf_share_map.keySet()) {
		    	String name = BaseConfig.dfcf_share_map.get(id).getName();
				prep.setString(1, id);  
				prep.setString(2, name);
				prep.addBatch();
		    }
		    

		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    
    	    connection.setAutoCommit(true);
    	    connection.close();
			System.out.println("SQLiteJDBC_CST init done ");
	    }
	    catch(Exception e)
	    {
	    	System.err.println(e.getMessage());
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	    	try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("SQLiteJDBC_CST init done ");
			}
	    }
	    finally
	    {
	      try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
	}
	
}
