package testJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLiteJDBC {

	public static String dataPath = "D:\\new_tdx\\code\\data\\date";
	
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
	      statement.executeUpdate("create table if not exists data ("
	      		+ "id text,"
	      		+ " date text, "
	      		+ "open double, "
	      		+ "high double, "
	      		+ "low double, "
	      		+ "close double, "
	      		+ "amount double,"
	      		+ "turnover double,"
	      		+ "constraint pk_t2 primary key (id,date)) ");
	      
		  statement.executeUpdate("create table if not exists setting ("
		  		+ "name primary key,"
		  		+ "value text)");
		    
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
	      

			SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy/MM/dd");
			Date d = new Date();
			d.setTime(dateUpdateTime);
			String date = sDateFormat.format(d);
			statement.execute("delete from data where date like '"+date+"'");
		  
	      ArrayList<Bean> list = new ArrayList<>();
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert into data values (?,?,?,?,?,?,?,?);");
	      
		    File file = new File(dataPath);
		    for(File subFile : file.listFiles()) {
				list = dateUpdateTime > 0 ? readFileByLines(subFile.getAbsolutePath(),dateUpdateTime):readFileByLines(subFile.getAbsolutePath());
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
//					System.out.println("insert "+subFile.getName());
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
	    	System.err.println(e.getMessage());
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	    	try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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


	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static ArrayList<Bean> readFileByLines(String fileName) {
		ArrayList<Bean> list = new ArrayList<>();
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // 一次读入一行，直到读入null为文件结束
	        while ((tempString = reader.readLine()) != null ) {
	        	String[] array = tempString.split(",");
	        	if(array.length == 7) {
//		            System.out.println("line " + line + ": " + tempString);
	        		Bean bean = new Bean(array);
	        		list.add(bean);
	        	}
	            line++;
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            }
	        }
	    }
	    return list;
	}
	
	public static ArrayList<Bean> readFileByLines(String fileName,long time) {
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy/MM/dd");
		Date d = new Date();
		d.setTime(time);
		String date = sDateFormat.format(d);
		
		ArrayList<Bean> list = new ArrayList<>();
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // 一次读入一行，直到读入null为文件结束
	        while ((tempString = reader.readLine()) != null ) {
	        	String[] array = tempString.split(",");
	        	if(array.length == 7) {
//		            System.out.println("line " + line + ": " + tempString);
	        		Bean bean = new Bean(array);
	        		if(bean.date.compareTo(date) >= 0) {
		        		list.add(bean);
	        		}
	        	}
	            line++;
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            }
	        }
	    }
	    return list;
	}
	
	static class Bean {
		String id;
		String date;
		Double open;
		Double high;
		Double low;
		Double close;
		Double amount;
		Double turnover;
		
		Bean(){}
		
		Bean(String[] array) {
			date = array[0];
			open = Double.valueOf(array[1]);
			high = Double.valueOf(array[2]);
			low = Double.valueOf(array[3]);
			close = Double.valueOf(array[4]);
			amount = Double.valueOf(array[5]);
			turnover = Double.valueOf(array[6]);
		}

		@Override
		public String toString() {
			return "Bean [id=" + id + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
					+ ", close=" + close + ", amount=" + amount + ", turnover=" + turnover + "]";
		}
	}
}
