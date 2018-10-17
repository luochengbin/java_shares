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
import java.util.HashMap;
import java.util.Map;

import testJava.bean.DFCF_F10_Bean;

public class SQLiteJDBC {
	public static Map<String,String> sharesMap;
	
	public static void updateCWFX(ArrayList<DFCF_F10_Bean> list) {
	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      // create a database connection
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
//	      Statement statement = connection.createStatement();
//	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      
	      //insert or replace into Book (ID, Name, TypeID, Level, Seen) values((select ID from Book where Name = "SearchName"), "SearchName", ...);
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert or replace into cwfx values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
	      
	      for(DFCF_F10_Bean bean : list) {
	    	  for(DFCF_F10_Bean.ZyzbAbgqBean subBean : bean.getZyzb_abgq()) {
			      prep.setString(1, bean.getId());
			      prep.setString(2, subBean.getRq());
			      prep.setString(3, subBean.getJbmgsy());
			      prep.setString(4, subBean.getKfmgsy());
			      prep.setString(5, subBean.getXsmgsy());
			      prep.setString(6, subBean.getGsjlr());
			      prep.setString(7, subBean.getGsjlrtbzz());
			      prep.setString(8, subBean.getGsjlrgdhbzz());
			      prep.setString(9, subBean.getJqjzcsyl());
			      prep.setString(10, subBean.getTbjzcsyl());
			      prep.setString(11, subBean.getSjsl());
			      prep.setString(12, subBean.getZzczzl());
			      prep.setString(13, subBean.getZcfzl());
			      prep.setString(14, subBean.getLdfz());
			      prep.addBatch();
		    	  System.out.println("updateCWFX "+bean.getId());
	    	  }
	      }
	      
		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    
  	      //insert or replace into Book (ID, Name, TypeID, Level, Seen) values((select ID from Book where Name = "SearchName"), "SearchName", ...);
  	      PreparedStatement prep1 = connection.prepareStatement(
  	    	      "insert or replace into detail values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
  	      
  	      for(DFCF_F10_Bean bean : list) {
  	    	prep1.setString(1, bean.getId());
		      prep1.setString(3, bean.getHxtc().get(0).getYdnr());
		      prep1.addBatch();
  	      }
  	    prep1.executeBatch();
    	    
    	    connection.setAutoCommit(true);
    	    connection.close();
    	    System.out.println("updateCWFX step2 done");
	    }catch(Exception e) {
			e.printStackTrace();
	    }
	}
	
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
	      statement.executeUpdate("create table if not exists cwfx ("
		      		+ "id text,"
		      		+ "rq text, " //季度
		      		+ "jbmgsy text, " //基本每股收益
		      		+ "kfmgsy text, "//扣非每股收益
		      		+ "xsmgsy text, "//稀释每股收益
		      		+ "gsjlr text,"//归属净利润(元)
		      		+ "gsjlrtbzz text,"//归属净利润同比增长(%)
		      		+ "gsjlrgdhbzz text,"//归属净利润滚动环比增长(%)
		      		+ "jqjzcsyl text,"//加权净资产收益率(%)
		      		+ "tbjzcsyl text,"//摊薄净资产收益率(%)
		      		+ "sjsl text,"//实际税率(%)
		      		+ "zzczzl text,"//总资产周转率(次)
		      		+ "zcfzl text,"//资产负债率(%)
		      		+ "ldfz text,"//流动负债/总负债(%)
		      		+ "constraint pk_t2 primary key (id,rq)) ");
	      
	      statement.executeUpdate("create table if not exists detail ("
		      		+ "id text primary key,"
		      		+ "name text, "
		      		+ "bk text, "
		      		+ "zx text, "
		      		+ "zf text, "
		      		+ "zdie text, "
		      		+ "zongshou text, "
		      		+ "xs text,"
		      		+ "mrj text,"
		      		+ "mcj text,"
		      		+ "hs text,"
		      		+ "je text,"
		      		+ "syl text,"
		      		+ "zg text,"
		      		+ "zuidi text,"
		      		+ "kp text,"
		      		+ "zuoshou text)");
	      
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
	      
		    File file = new File(BaseConfig.dataPath);
		    sharesMap = new HashMap<>();
		    for(File subFile : file.listFiles()) {
				sharesMap.put(subFile.getName().substring(3, 9), null);
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
