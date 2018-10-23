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
	      
	      statement.executeUpdate("create table if not exists bk ("
		      		+ "id text primary key,"
		      		+ "name text, " 
		      		+ "type double," 
		      		+ "level double," 
		      		+"comment text)");
	      
	      statement.executeUpdate("create table if not exists dp ("
		      		+ "id text primary key,"
		      		+ "name text, "
		      		+"comment text)");
	      
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert into data values (?,?,?,?,?,?,?);");
	      
		    for(String id : BaseConfig.dfcf_share_map.keySet()) {
		    	String name = BaseConfig.dfcf_share_map.get(id).getName();
				prep.setString(1, id);  
				prep.setString(2, name);
				prep.addBatch();
		    }
		    
		      PreparedStatement prep1 = connection.prepareStatement(
		    	      "insert into bk values (?,?,?);");
		      
			    for(String id : BaseConfig.dfcf_bk_map.keySet()) {
			    	String name = BaseConfig.dfcf_bk_map.get(id).getName();
			    	double type = BaseConfig.dfcf_bk_map.get(id).getType();
			    	prep1.setString(1, id);  
			    	prep1.setString(2, name);
			    	prep1.setDouble(3, type);
			    	prep1.addBatch();
			    }

		    connection.setAutoCommit(false);
    	    prep1.executeBatch();
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
