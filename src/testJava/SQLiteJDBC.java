package testJava;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import testJava.Main.Bean;

public class SQLiteJDBC {
	
	public static void createTable() {

	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

//	      statement.executeUpdate("drop table if exists data");
	      statement.executeUpdate("create table if not exists data (id integer, name string , date string, open double, high double, low double, close double, amount double,turnover double) ");
	      
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert into data values (?, ?,?,?,?,?,?,?,?);");
	      
		    File file = new File("D:\\new_tdx\\code\\data\\date");
		    for(File subFile : file.listFiles()) {
				ArrayList<Bean> list = Main.readFileByLines(subFile.getAbsolutePath());
				for(Bean bean : list){
//				      statement.executeUpdate("insert into data values("+subFile.getName().substring(3, 9)+", '',"+bean.date+","+bean.open+","+bean.high+","+bean.low+","+bean.close+","+bean.amount+","+bean.turnover+")");
					prep.setString(1, subFile.getName().substring(3, 9));  
					prep.setString(2, "");  
					prep.setString(3, bean.date);  
					prep.setDouble(4, bean.open); 
					prep.setDouble(5, bean.high); 
					prep.setDouble(6, bean.low); 
					prep.setDouble(7, bean.close); 
					prep.setDouble(8, bean.amount); 
					prep.setDouble(9, bean.turnover); 
		    	    prep.addBatch();
					System.out.println("insert "+subFile.getName());
				}
		    }
		    

		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    connection.setAutoCommit(true);
		    
	      ResultSet rs = statement.executeQuery("select * from data");
	      while(rs.next())
	      {
	        // read the result set
	        System.out.println("name = " + rs.getString("name"));
	        System.out.println("id = " + rs.getInt("id"));
	      }
	    }
	    catch(Exception e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
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
