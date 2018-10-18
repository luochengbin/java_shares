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
import testJava.bean.DFCF_Share_Bean;

public class SQLiteJDBC {
	public static Map<String,String> sharesMap;
	
	public static void updateCWFXDetail(ArrayList<DFCF_Share_Bean> list) {
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
	    	      "update detail set "
	    	      + "name=?,"
	    	      + "zxj=?,"
	    	      + "zdf=?,"
	    	      + "zde=?,"
	    	      + "zhongshou=?,"
	    	      + "xianshou=?,"
	    	      + "mrj=?,"
	    	      + "mcj=?,"
	    	      + "zhangsu=?,"
	    	      + "huanshou=?,"
	    	      + "jine=?,"
	    	      + "syl=?,"
	    	      + "zuigao=?,"
	    	      + "zuidi=?,"
	    	      + "kaipan=?,"
	    	      + "zuoshou=?,"
	    	      + "zhenfu=?,"
	    	      + "liangbi=?,"
	    	      + "weibi=?,"
	    	      + "weicha=?,"
	    	      + "junjia=?,"
	    	      + "neipan=?,"
	    	      + "waipan=?,"
	    	      + "neiwaibi=?,"
	    	      + "buy1liang=?,"
	    	      + "sell1liang=?,"
	    	      + "sjl=?,"
	    	      + "zgb=?,"
	    	      + "zsz=?,"
	    	      + "ltgb=?,"
	    	      + "ltsz=?,"
	    	      + "zf3=?,"
	    	      + "zf6=?,"
	    	      + "hs3=?,"
	    	      + "hs6=? where id=?");
	      
	      for(DFCF_Share_Bean bean : list) {
		      prep.setString(1, bean.getName());
		      prep.setString(2, bean.getZxj());
		      prep.setString(3, bean.getZdf());
		      prep.setString(4, bean.getZde());
		      prep.setString(5, bean.getZhongshou());
		      prep.setString(6, bean.getXianshou());
		      prep.setString(7, bean.getMrj());
		      prep.setString(8, bean.getMcj());
		      prep.setString(9, bean.getZhangsu());
		      prep.setString(10, bean.getHuanshou());
		      prep.setString(11, bean.getJine());
		      prep.setString(12, bean.getSyl());
		      prep.setString(13, bean.getZuigao());
		      prep.setString(14, bean.getZuidi());
		      prep.setString(15, bean.getKaipan());
		      prep.setString(16, bean.getZuoshou());
		      prep.setString(17, bean.getZhenfu());
		      prep.setString(18, bean.getLiangbi());
		      prep.setString(19, bean.getWeibi());
		      prep.setString(20, bean.getWeicha());
		      prep.setString(21, bean.getJunjia());
		      prep.setString(22, bean.getNeipan());
		      prep.setString(23, bean.getWaipan());
		      prep.setString(24, bean.getNeiwaibi());
		      prep.setString(25, bean.getBuy1liang());
		      prep.setString(26, bean.getSell1liang());
		      prep.setString(27, bean.getSjl());
		      prep.setString(28, bean.getZgb());
		      prep.setString(29, bean.getZsz());
		      prep.setString(30, bean.getLtgb());
		      prep.setString(31, bean.getLtsz());
		      prep.setString(32, bean.getZf3());
		      prep.setString(33, bean.getZf6());
		      prep.setString(34, bean.getHs3());
		      prep.setString(35, bean.getHs6());
		      prep.setString(36, bean.getId());
		      prep.addBatch();
	    	  System.out.println("updateCWFX_BEAN "+bean.getId());
	      }
	      
		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    
    	    connection.setAutoCommit(true);
    	    connection.close();
    	    System.out.println("updateCWFX_BEAN done");
	    }catch(Exception e) {
			e.printStackTrace();
	    }
	}
	
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
		    	  System.out.println("updateCWFX_F10 "+bean.getId());
	    	  }
	      }
	      
		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    
  	      //insert or replace into Book (ID, Name, TypeID, Level, Seen) values((select ID from Book where Name = "SearchName"), "SearchName", ...);
  	      PreparedStatement prep1 = connection.prepareStatement(
  	    	      "insert or replace into detail values (?,(select name from detail where id =?),"
  	    	      + "?,"
  	    	      + "(select zxj from detail where id =?),"
  	    	      + "(select zdf from detail where id =?),"
  	    	      + "(select zde from detail where id =?),"
  	    	      + "(select zhongshou from detail where id =?),"
  	    	      + "(select xianshou from detail where id =?),"
  	    	      + "(select mrj from detail where id =?),"
  	    	      + "(select mcj from detail where id =?),"
  	    	      + "(select zhangsu from detail where id =?),"
  	    	      + "(select huanshou from detail where id =?),"
  	    	      + "(select jine from detail where id =?),"
  	    	      + "(select syl from detail where id =?),"
  	    	      + "(select zuigao from detail where id =?),"
  	    	      + "(select zuidi from detail where id =?),"
  	    	      + "(select kaipan from detail where id =?),"
  	    	      + "(select zuoshou from detail where id =?),"
  	    	      + "(select zhenfu from detail where id =?),"
  	    	      + "(select liangbi from detail where id =?),"
  	    	      + "(select weibi from detail where id =?),"
  	    	      + "(select weicha from detail where id =?),"
  	    	      + "(select junjia from detail where id =?),"
  	    	      + "(select neipan from detail where id =?),"
  	    	      + "(select waipan from detail where id =?),"
  	    	      + "(select neiwaibi from detail where id =?),"
  	    	      + "(select buy1liang from detail where id =?),"
  	    	      + "(select sell1liang from detail where id =?),"
  	    	      + "(select sjl from detail where id =?),"
  	    	      + "(select zgb from detail where id =?),"
  	    	      + "(select zsz from detail where id =?),"
  	    	      + "(select ltgb from detail where id =?),"
  	    	      + "(select ltsz from detail where id =?),"
  	    	      + "(select zf3 from detail where id =?),"
  	    	      + "(select zf6 from detail where id =?),"
  	    	      + "(select hs3 from detail where id =?),"
  	    	      + "(select hs6 from detail where id =?));");
  	      
  	      for(DFCF_F10_Bean bean : list) {
  	    	prep1.setString(1, bean.getId());
		      prep1.setString(2, bean.getId());
		      prep1.setString(3, bean.getHxtc().get(0).getYdnr());
		      prep1.setString(4, bean.getId());
		      prep1.setString(5, bean.getId());
		      prep1.setString(6, bean.getId());
		      prep1.setString(7, bean.getId());
		      prep1.setString(8, bean.getId());
		      prep1.setString(9, bean.getId());
		      prep1.setString(10, bean.getId());
		      prep1.setString(11, bean.getId());
		      prep1.setString(12, bean.getId());
		      prep1.setString(13, bean.getId());
		      prep1.setString(14, bean.getId());
		      prep1.setString(15, bean.getId());
		      prep1.setString(16, bean.getId());
		      prep1.setString(17, bean.getId());
		      prep1.setString(18, bean.getId());
		      prep1.setString(19, bean.getId());
		      prep1.setString(20, bean.getId());
		      prep1.setString(21, bean.getId());
		      prep1.setString(22, bean.getId());
		      prep1.setString(23, bean.getId());
		      prep1.setString(24, bean.getId());
		      prep1.setString(25, bean.getId());
		      prep1.setString(26, bean.getId());
		      prep1.setString(27, bean.getId());
		      prep1.setString(28, bean.getId());
		      prep1.setString(29, bean.getId());
		      prep1.setString(30, bean.getId());
		      prep1.setString(31, bean.getId());
		      prep1.setString(32, bean.getId());
		      prep1.setString(33, bean.getId());
		      prep1.setString(34, bean.getId());
		      prep1.setString(35, bean.getId());
		      prep1.setString(36, bean.getId());
		      prep1.setString(37, bean.getId());
		      prep1.addBatch();
  	      }
  	    prep1.executeBatch();
    	    
    	    connection.setAutoCommit(true);
    	    connection.close();
    	    System.out.println("updateCWFX_F10 F10 done");
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
		      		+ "zxj text, "
		      		+ "zdf text, "
		      		+ "zde text, "
		      		+ "zhongshou text, "
		      		+ "xianshou text,"
		      		+ "mrj text,"
		      		+ "mcj text,"
		      		+ "zhangsu text,"
		      		+ "huanshou text,"
		      		+ "jine text,"
		      		+ "syl text,"
		      		+ "zuigao text,"
		      		+ "zuidi text,"
		      		+ "kaipan text,"
		      		+ "zuoshou text,"
		      		+ "zhenfu text,"
		      		+ "liangbi text,"
		      		+ "weibi text,"
		      		+ "weicha text,"
		      		+ "junjia text,"
		      		+ "neipan text,"
		      		+ "waipan text,"
		      		+ "neiwaibi text,"
		      		+ "buy1liang text,"
		      		+ "sell1liang text,"
		      		+ "sjl text,"
		      		+ "zgb text,"
		      		+ "zsz text,"
		      		+ "ltgb text,"
		      		+ "ltsz text,"
		      		+ "zf3 text,"
		      		+ "zf6 text,"
		      		+ "hs3 text,"
		      		+ "hs6 text)");
	      
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
				System.out.println("insert "+subFile.getName());
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
