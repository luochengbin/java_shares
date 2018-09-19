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
	      statement.executeUpdate("create table if not exists data (id text, date text, open double, high double, low double, close double, amount double,turnover double,constraint pk_t2 primary key (id,date)) ");
		  statement.executeUpdate("create table if not exists setting (name primary key,value text)");
		    
		  long dateUpdateTime = -1;
	      ResultSet rs = statement.executeQuery("select * from setting");
	      while(rs.next())
	      {
	    	  String name = rs.getString("name");
	    	  String value = rs.getString("value");
	    	  if(name != null && name.equals("data_update_time")){
	    		  dateUpdateTime = Long.valueOf(value); 
	    	  }
	      }
		  
	      ArrayList<Bean> list = new ArrayList<>();
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert into data values (?,?,?,?,?,?,?,?);");
	      
		    File file = new File(Main.dataPath);
		    for(File subFile : file.listFiles()) {
				list = dateUpdateTime > 0 ? Main.readFileByLines(subFile.getAbsolutePath(),dateUpdateTime):Main.readFileByLines(subFile.getAbsolutePath());
				for(Bean bean : list){
//				      statement.executeUpdate("insert into data values("+subFile.getName().substring(3, 9)+", '',"+bean.date+","+bean.open+","+bean.high+","+bean.low+","+bean.close+","+bean.amount+","+bean.turnover+")");
					String name = subFile.getName().substring(3, 9);
					prep.setString(1, name);  
					prep.setString(2, bean.date);  
					prep.setDouble(3, bean.open); 
					prep.setDouble(4, bean.high); 
					prep.setDouble(5, bean.low); 
					prep.setDouble(6, bean.close); 
					prep.setDouble(7, bean.amount); 
					prep.setDouble(8, bean.turnover); 
		    	    prep.addBatch();
					System.out.println("insert "+subFile.getName());
				}
		    }
		    

		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    
    	    if(list.size()>0) {
    		    statement.executeUpdate("replace into setting(name,value)values('data_update_time',"+System.currentTimeMillis()+")");
    	    }
    	    
    	    connection.setAutoCommit(true);
    	    connection.close();
			System.out.println("init done ");
//	      ResultSet rs = statement.executeQuery("select * from data");
//	      while(rs.next())
//	      {
//	        // read the result set
//	        System.out.println("name = " + rs.getString("name"));
//	        System.out.println("id = " + rs.getInt("id"));
//	      }
	    }
	    catch(Exception e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	    	try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	public static ArrayList<Bean> getAllList(){
		ArrayList<Bean> list = new ArrayList<>();

	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * from data");
	      while(rs.next())
	      {
	    	  Bean bean = new Bean();
	    	  bean.id = rs.getString("id");
	    	  bean.date = rs.getString("date");
	    	  bean.open = rs.getDouble("open");
	    	  bean.high = rs.getDouble("high");
	    	  bean.low = rs.getDouble("low");
	    	  bean.close = rs.getDouble("close");
	    	  bean.amount = rs.getDouble("amount");
	    	  bean.turnover = rs.getDouble("turnover");
	    	  
	    	  list.add(bean);
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
		return list;
	}

}
