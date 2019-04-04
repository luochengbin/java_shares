package testJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
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

import testJava.bean.DFCF_BK_Bean;
import testJava.bean.DFCF_F10_Bean;
import testJava.bean.DFCF_Share_Bean;
import testJava.bean.TDX_Share_Bean;

public class SQLiteJDBC {
	public final static int EMA1 = 12,EMA2 = 26,DIF = 9;
	
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
		      prep.setDouble(2, bean.getZxj());
		      prep.setDouble(3, bean.getZdf());
		      prep.setDouble(4, bean.getZde());
		      prep.setDouble(5, bean.getZhongshou());
		      prep.setDouble(6, bean.getXianshou());
		      prep.setDouble(7, bean.getMrj());
		      prep.setDouble(8, bean.getMcj());
		      prep.setDouble(9, bean.getZhangsu());
		      prep.setDouble(10, bean.getHuanshou());
		      prep.setDouble(11, bean.getJine());
		      prep.setDouble(12, bean.getSyl());
		      prep.setDouble(13, bean.getZuigao());
		      prep.setDouble(14, bean.getZuidi());
		      prep.setDouble(15, bean.getKaipan());
		      prep.setDouble(16, bean.getZuoshou());
		      prep.setDouble(17, bean.getZhenfu());
		      prep.setDouble(18, bean.getLiangbi());
		      prep.setDouble(19, bean.getWeibi());
		      prep.setDouble(20, bean.getWeicha());
		      prep.setDouble(21, bean.getJunjia());
		      prep.setDouble(22, bean.getNeipan());
		      prep.setDouble(23, bean.getWaipan());
		      prep.setDouble(24, bean.getNeiwaibi());
		      prep.setDouble(25, bean.getBuy1liang());
		      prep.setDouble(26, bean.getSell1liang());
		      prep.setDouble(27, bean.getSjl());
		      prep.setDouble(28, bean.getZgb());
		      prep.setDouble(29, bean.getZsz());
		      prep.setDouble(30, bean.getLtgb());
		      prep.setDouble(31, bean.getLtsz());
		      prep.setDouble(32, bean.getZf3());
		      prep.setDouble(33, bean.getZf6());
		      prep.setDouble(34, bean.getHs3());
		      prep.setDouble(35, bean.getHs6());
		      prep.setString(36, bean.getId());
		      prep.addBatch();
	    	  System.out.println("updateCWFX_BEAN "+bean.getId());
	      }
	      
		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    
    	    connection.setAutoCommit(true);
    	    System.out.println("updateCWFX_BEAN done");
	    }catch(Exception e) {
			e.printStackTrace();
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
	
	public static void updateBK(ArrayList<DFCF_BK_Bean> list) {
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
	    	      "insert or replace into bk values (?,?,?,?,?,?,?,?,?,?);");
	     
    		
	      for(DFCF_BK_Bean bean : list) {
		      prep.setString(1, bean.getId());
		      prep.setString(2, bean.getName());
		      prep.setDouble(3, bean.getType());
		      prep.setDouble(4, bean.getZdf());
		      prep.setDouble(5, bean.getZsz());
		      prep.setDouble(6, bean.getHsl());
		      prep.setDouble(7, bean.getSzjs());
		      prep.setDouble(8, bean.getXdjs());
		      prep.setDouble(9, bean.getZxj());
		      prep.setDouble(10, bean.getZde());
		      prep.addBatch();
	    	  System.out.println("updateBK "+bean.getId());
	      }
	      
		    connection.setAutoCommit(false);
    	    prep.executeBatch();
    	    connection.setAutoCommit(true);
    	    System.out.println("updateBK done");
	    }catch(Exception e) {
			e.printStackTrace();
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
  	    	  if( bean.getHxtc() != null &&  bean.getHxtc().size() >0 ) {
  	    		prep1.setString(1, bean.getId());
  	    		prep1.setString(3, bean.getHxtc().get(0).getYdnr());
  	    	  }
//  	    	prep1.setString(1, bean.getId());
//		      prep1.setString(2, bean.getId());
//		      prep1.setString(3, bean.getHxtc().get(0).getYdnr());
//		      prep1.setString(4, bean.getId());
//		      prep1.setString(5, bean.getId());
//		      prep1.setString(6, bean.getId());
//		      prep1.setString(7, bean.getId());
//		      prep1.setString(8, bean.getId());
//		      prep1.setString(9, bean.getId());
//		      prep1.setString(10, bean.getId());
//		      prep1.setString(11, bean.getId());
//		      prep1.setString(12, bean.getId());
//		      prep1.setString(13, bean.getId());
//		      prep1.setString(14, bean.getId());
//		      prep1.setString(15, bean.getId());
//		      prep1.setString(16, bean.getId());
//		      prep1.setString(17, bean.getId());
//		      prep1.setString(18, bean.getId());
//		      prep1.setString(19, bean.getId());
//		      prep1.setString(20, bean.getId());
//		      prep1.setString(21, bean.getId());
//		      prep1.setString(22, bean.getId());
//		      prep1.setString(23, bean.getId());
//		      prep1.setString(24, bean.getId());
//		      prep1.setString(25, bean.getId());
//		      prep1.setString(26, bean.getId());
//		      prep1.setString(27, bean.getId());
//		      prep1.setString(28, bean.getId());
//		      prep1.setString(29, bean.getId());
//		      prep1.setString(30, bean.getId());
//		      prep1.setString(31, bean.getId());
//		      prep1.setString(32, bean.getId());
//		      prep1.setString(33, bean.getId());
//		      prep1.setString(34, bean.getId());
//		      prep1.setString(35, bean.getId());
//		      prep1.setString(36, bean.getId());
//		      prep1.setString(37, bean.getId());
		      prep1.addBatch();
  	      }
  	    prep1.executeBatch();
    	    
    	    connection.setAutoCommit(true);
    	    System.out.println("updateCWFX_F10 F10 done");
	    }catch(Exception e) {
			e.printStackTrace();
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
		      		+ "rq text, " 
		      		+ "jbmgsy text, " 
		      		+ "kfmgsy text, "
		      		+ "xsmgsy text, "
		      		+ "gsjlr text,"
		      		+ "gsjlrtbzz text,"
		      		+ "gsjlrgdhbzz text,"
		      		+ "jqjzcsyl text,"
		      		+ "tbjzcsyl text,"
		      		+ "sjsl text,"
		      		+ "zzczzl text,"
		      		+ "zcfzl text,"
		      		+ "ldfz text,"
		      		+ "constraint pk_t2 primary key (id,rq)) ");
	      
	      statement.executeUpdate("create table if not exists bk ("
		      		+ "id text primary key,"
		      		+ "name text, " 
		      		+ "type double, " 
		      		+ "zdf double, "
		      		+ "zsz double, "
		      		+ "hsl double,"
		      		+ "szjs double,"
		      		+ "xdjs double,"
		      		+ "zxj double,"
		      		+ "zde double)");
	      
	      statement.executeUpdate("create table if not exists detail ("
		      		+ "id text primary key,"
		      		+ "name text, "
		      		+ "bk text, "
		      		+ "zxj double, "
		      		+ "zdf double, "
		      		+ "zde double, "
		      		+ "zhongshou double, "
		      		+ "xianshou double,"
		      		+ "mrj double,"
		      		+ "mcj double,"
		      		+ "zhangsu double,"
		      		+ "huanshou double,"
		      		+ "jine double,"
		      		+ "syl double,"
		      		+ "zuigao double,"
		      		+ "zuidi double,"
		      		+ "kaipan double,"
		      		+ "zuoshou double,"
		      		+ "zhenfu double,"
		      		+ "liangbi double,"
		      		+ "weibi double,"
		      		+ "weicha double,"
		      		+ "junjia double,"
		      		+ "neipan double,"
		      		+ "waipan double,"
		      		+ "neiwaibi double,"
		      		+ "buy1liang double,"
		      		+ "sell1liang double,"
		      		+ "sjl double,"
		      		+ "zgb double,"
		      		+ "zsz double,"
		      		+ "ltgb double,"
		      		+ "ltsz double,"
		      		+ "zf3 double,"
		      		+ "zf6 double,"
		      		+ "hs3 double,"
		      		+ "hs6 double)");
	      
	      statement.executeUpdate("create table if not exists data ("
		      		+ "id text,"
		      		+ " date text, "
		      		+ "open double, "
		      		+ "high double, "
		      		+ "low double, "
		      		+ "close double, "
		      		+ "amount double,"
		      		+ "turnover double,"
		      		+ "ema1 double,"
		      		+ "ema2 double,"
		      		+ "dif double,"
		      		+ "dea double,"
		      		+ "macd double,"
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
		  
	      ArrayList<TDX_Share_Bean> list = new ArrayList<>();
	      PreparedStatement prep = connection.prepareStatement(
	    	      "insert into data values (?,?,?,?,?,?,?,?,?,?,?,?,?);");

		    long modifiedTime = 0;
		    File file = new File(BaseConfig.dataPath);
		    for(File sf : file.listFiles()) {
		    	if(sf.lastModified() > modifiedTime){
			    	modifiedTime = sf.lastModified();
		    	}
		    }

		    if(modifiedTime > dateUpdateTime) {
	    	    connection.setAutoCommit(true);
				statement.execute("delete from data where date like '"+date+"'");
			    BaseConfig.sharesMap = new HashMap<>();
			    int index =0;
			    for(File subFile : file.listFiles()) {
			    	String name = subFile.getName().substring(3, 9);
			    	BaseConfig.sharesMap.put(name, null);
					list = dateUpdateTime > 0 ? readFileByLines(subFile.getAbsolutePath(),dateUpdateTime):readFileByLines(subFile.getAbsolutePath());
					System.out.println("insert "+subFile.getName());
					
					HashMap<String,String> hh = new HashMap<>();
					
					boolean isFirst = true;
					double ema1 = 0;
					double ema2 = 0;
					double dif = 0;
					double dea = 0;
					double macd = 0;
				      ResultSet rs1 = statement.executeQuery("select * from data where id="+name+" order by date desc");
				      if(rs1 != null && rs1.next()){
				    	  isFirst = false;
				    	  ema1 = rs1.getDouble(rs1.findColumn("ema1"));
				    	  ema2 = rs1.getDouble(rs1.findColumn("ema2"));
				    	  dea = rs1.getDouble(rs1.findColumn("dea"));
				      }

					connection.setAutoCommit(false);
					for(TDX_Share_Bean bean : list){
//					      statement.executeUpdate("insert into data values("+subFile.getName().substring(3, 9)+", '',"+bean.date+","+bean.open+","+bean.high+","+bean.low+","+bean.close+","+bean.amount+","+bean.turnover+")");
						
			    	    if(hh.get(name+bean.date) != null) {
			    	    	continue;
			    	    }
						prep.setString(1, name);  
						prep.setString(2, bean.date);  
						prep.setDouble(3, bean.open); 
						prep.setDouble(4, bean.high); 
						prep.setDouble(5, bean.low); 
						prep.setDouble(6, bean.close); 
						prep.setDouble(7, bean.amount); 
						prep.setDouble(8, bean.turnover); 
						
						if(isFirst) {
							isFirst = false;
							prep.setDouble(9, bean.EMA1); 
							prep.setDouble(10, bean.EMA2); 
							prep.setDouble(11, bean.DIF); 
							prep.setDouble(12, bean.DEA); 
							prep.setDouble(13, bean.MACD); 
						}else {
							bean.EMA1 = ema1 = bean.close*2/(EMA1+1)+ema1*(EMA1-1)/(EMA1+1);
							bean.EMA2 = ema2 = bean.close*2/(EMA2+1)+ema2*(EMA2-1)/(EMA2+1);
						    bean.DIF = dif = bean.EMA1 - bean.EMA2;
						    bean.DEA = dea = bean.DIF*2/(DIF+1)+dea*(DIF-1)/(DIF+1);
						    bean.MACD = macd = (bean.DIF-bean.DEA)*2;
						    
						}

						prep.setDouble(9, bean.EMA1); 
						prep.setDouble(10, bean.EMA2); 
						prep.setDouble(11, bean.DIF); 
						prep.setDouble(12, bean.DEA); 
						prep.setDouble(13, bean.MACD); 
			    	    prep.addBatch();
			    	    hh.put(name+bean.date, name+bean.date);
					}
					
					index++;
					if(index == 100){
						index = 0;
			    	    prep.executeBatch();
			    	    connection.setAutoCommit(true);
					}
			    }
		    }else {
			    BaseConfig.sharesMap = new HashMap<>();
			    for(File subFile : file.listFiles()) {
			    	String name = subFile.getName().substring(3, 9);
			    	BaseConfig.sharesMap.put(name, null);
			    }
		    }

    	    prep.executeBatch();
    	    connection.setAutoCommit(true);

		    statement.executeUpdate("replace into setting(name,value)values('data_update_time',"+modifiedTime+")");
    	    
			System.out.println("SQLiteJDBC init done ");
	    }
	    catch(Exception e)
	    {
	    	System.err.println(e.getMessage());
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	    	try {
				connection.rollback();
			} catch (SQLException e1) {
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
	
	public static HashMap<String,DFCF_Share_Bean> getAllSharesDataList(){
		HashMap<String,DFCF_Share_Bean> list = new HashMap<String,DFCF_Share_Bean>();
	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * from detail");
	      while(rs.next())
	      {
	    	  DFCF_Share_Bean bean = new DFCF_Share_Bean();
	    	  bean.setId(rs.getString("id"));
	    	  bean.setName(rs.getString("name"));
	    	  bean.setBk(rs.getString("bk"));
	    	  bean.setZxj(rs.getDouble("zxj"));
	    	  bean.setZdf(rs.getDouble("zdf"));
	    	  bean.setZde(rs.getDouble("zde"));
	    	  bean.setZhongshou(rs.getDouble("zhongshou"));
	    	  bean.setXianshou(rs.getDouble("xianshou"));
	    	  bean.setMrj(rs.getDouble("mrj"));
	    	  bean.setMcj(rs.getDouble("mcj"));
	    	  bean.setZhangsu(rs.getDouble("zhangsu"));
	    	  bean.setHuanshou(rs.getDouble("huanshou"));
	    	  bean.setJine(rs.getDouble("jine"));
	    	  bean.setSyl(rs.getDouble("syl"));
	    	  bean.setZuigao(rs.getDouble("zuigao"));
	    	  bean.setZuidi(rs.getDouble("zuidi"));
	    	  bean.setKaipan(rs.getDouble("kaipan"));
	    	  bean.setZuoshou(rs.getDouble("zuoshou"));
	    	  bean.setZhenfu(rs.getDouble("zhenfu"));
	    	  bean.setLiangbi(rs.getDouble("liangbi"));
	    	  bean.setWeibi(rs.getDouble("weibi"));
	    	  bean.setWeicha(rs.getDouble("weicha"));
	    	  bean.setJunjia(rs.getDouble("junjia"));
	    	  bean.setNeipan(rs.getDouble("neipan"));
	    	  bean.setWaipan(rs.getDouble("waipan"));
	    	  bean.setNeiwaibi(rs.getDouble("neiwaibi"));
	    	  bean.setBuy1liang(rs.getDouble("buy1liang"));
	    	  bean.setSell1liang(rs.getDouble("sell1liang"));
	    	  bean.setSjl(rs.getDouble("sjl"));
	    	  bean.setZgb(rs.getDouble("zgb"));
	    	  bean.setZsz(rs.getDouble("zsz"));
	    	  bean.setLtgb(rs.getDouble("ltgb"));
	    	  bean.setLtsz(rs.getDouble("ltsz"));
	    	  bean.setZf3(rs.getDouble("zf3"));
	    	  bean.setZf6(rs.getDouble("zf6"));
	    	  bean.setHs3(rs.getDouble("hs3"));
	    	  bean.setHs6(rs.getDouble("hs6"));
	    	  
	    	  list.put(bean.getId(), bean);
	    	  
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
		System.out.println("getAllSharesDataList init done ");
		return list;
	}
	
	public static HashMap<String,DFCF_F10_Bean.ZyzbAbgqBean> getAllF10DataList(){
		HashMap<String,DFCF_F10_Bean.ZyzbAbgqBean> list = new HashMap<String,DFCF_F10_Bean.ZyzbAbgqBean>();
	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * from cwfx");
	      while(rs.next())
	      {
	    	  DFCF_F10_Bean.ZyzbAbgqBean bean = new DFCF_F10_Bean.ZyzbAbgqBean();
	    	  bean.setId(rs.getString("id"));
	    	  bean.setRq(rs.getString("rq"));
	    	  bean.setJbmgsy(rs.getString("jbmgsy"));
	    	  bean.setKfmgsy(rs.getString("kfmgsy"));
	    	  bean.setXsmgsy(rs.getString("xsmgsy"));
	    	  bean.setGsjlr(rs.getString("gsjlr"));
	    	  bean.setGsjlrtbzz(rs.getString("gsjlrtbzz"));
	    	  bean.setGsjlrgdhbzz(rs.getString("gsjlrgdhbzz"));
	    	  bean.setJqjzcsyl(rs.getString("jqjzcsyl"));
	    	  bean.setTbjzcsyl(rs.getString("tbjzcsyl"));
	    	  bean.setSjsl(rs.getString("sjsl"));
	    	  bean.setZzczzl(rs.getString("zzczzl"));
	    	  bean.setZcfzl(rs.getString("zcfzl"));
	    	  bean.setLdfz(rs.getString("ldfz"));
	    	  
	    	  list.put(bean.getId()+bean.getRq(), bean);
	    	  
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
		System.out.println("getAllF10DataList init done ");
		return list;
	}

	public static HashMap<String,DFCF_BK_Bean> getAllBKDataList(){
		HashMap<String,DFCF_BK_Bean> list = new HashMap<String,DFCF_BK_Bean>();
	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * from bk");
	      while(rs.next())
	      {
	    	  DFCF_BK_Bean bean = new DFCF_BK_Bean();
	    	  bean.setId(rs.getString("id"));
	    	  bean.setName(rs.getString("name"));
	    	  bean.setType(rs.getDouble("type"));
	    	  bean.setZdf(rs.getDouble("zdf"));
	    	  bean.setZsz(rs.getDouble("zsz"));
	    	  bean.setHsl(rs.getDouble("hsl"));
	    	  bean.setSzjs(rs.getDouble("szjs"));
	    	  bean.setXdjs(rs.getDouble("xdjs"));
	    	  bean.setZxj(rs.getDouble("zxj"));
	    	  bean.setZde(rs.getDouble("zde"));
	    	  
	    	  list.put(bean.getName(), bean);
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
		System.out.println("getAllBKDataList init done ");
		return list;
	}
	
	public static HashMap<String,ArrayList<TDX_Share_Bean>> getAllTDXDataList(){
		HashMap<String,ArrayList<TDX_Share_Bean>> list = new HashMap<String,ArrayList<TDX_Share_Bean>>();
		BaseConfig.tdx_share_map = list;
	    Connection connection = null;
	    try
	    {
			Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:shares.db");
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	      ResultSet rs = statement.executeQuery("select * from data");
	      ArrayList<TDX_Share_Bean> sublist = new ArrayList<TDX_Share_Bean>();
	      TDX_Share_Bean bean;
	      while(rs.next())
	      {
	    	  bean = new TDX_Share_Bean();
	    	  bean.id = rs.getString("id");
	    	  bean.date = rs.getString("date");
	    	  bean.open = rs.getDouble("open");
	    	  bean.high = rs.getDouble("high");
	    	  bean.low = rs.getDouble("low");
	    	  bean.close = rs.getDouble("close");
	    	  bean.amount = rs.getDouble("amount");
	    	  bean.turnover = rs.getDouble("turnover");
	    	  bean.EMA1 = rs.getDouble("ema1");
	    	  bean.EMA2 = rs.getDouble("ema2");
	    	  bean.DIF = rs.getDouble("dif");
	    	  bean.DEA = rs.getDouble("dea");
	    	  bean.MACD = rs.getDouble("macd");
	    	  if(list.get(bean.id) == null){
	    		  sublist = new ArrayList<TDX_Share_Bean>();
	    		  sublist.add(bean);
	    		  list.put(bean.id, sublist);
	    	  }else {
	    		  list.get(bean.id).add(bean);
	    		  
	    		  getRSI(list.get(bean.id).size()-1,bean);
	    		  
//	    		  bean.EMA1 = bean.close*2/(EMA1+1)+list.get(bean.id).get(sublist.size()-2).EMA1*(EMA1-1)/(EMA1+1);
//	    		  bean.EMA2 = bean.close*2/(EMA2+1)+list.get(bean.id).get(sublist.size()-2).EMA2*(EMA2-1)/(EMA2+1);
//				  bean.DIF = bean.EMA1 - bean.EMA2;
//				  bean.DEA = bean.DIF*2/(DIF+1)+sublist.get(list.get(bean.id).size()-2).DEA*(DIF-1)/(DIF+1);
//				  bean.MACD = (bean.DIF-bean.DEA)*2;
	    		  
//	    		  System.out.println("MACD "+bean.id+" "+bean.date+" "+bean.close+" "+bean.DIF+" "+bean.DEA+" "+bean.MACD);
	    		  
	    	  }
	      }
	    }
	    catch(Exception e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	    	e.printStackTrace();
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
		System.out.println("getAllTDXDataList init done ");
		return list;
	}
	
	public static void getRSI(int index ,TDX_Share_Bean bean) {
	  final int CYCLE = 6;
	  if(index+1 == CYCLE) {
		  double up = 0;
		  double down = 0;
		  for(int i =-(CYCLE-2);i<0;i++) {
			  TDX_Share_Bean preBean = bean.getBean(i);
			  double raise = preBean.close - preBean.getBean(-1).close;
			  if(raise >0) {
				  up += raise;
			  }else {
				  down += Math.abs(raise);
			  }
		  }
		  if(down == 0) {
			  bean.RSI6 = 100;
			  bean.RSI6_up = up/CYCLE;
		  }else {
			  double RS = up/down;
			  bean.RSI6 = (RS/(1+RS))*100;
			  bean.RSI6_up = up/CYCLE;
			  bean.RSI6_down = down/CYCLE;
		  }
	  }else if(index+1 >CYCLE) {
		  double up = 0;
		  double down = 0;
		  double RS=0;
		  TDX_Share_Bean preBean = bean.getBean(-1);
		  double raise = bean.close - preBean.close;
		  if(raise > 0) {
			  up = preBean.RSI6_up*(CYCLE-1)/CYCLE+raise/CYCLE;
			  down = preBean.RSI6_down*(CYCLE-1)/CYCLE;
		  }else {
			  up = preBean.RSI6_up*(CYCLE-1)/CYCLE;
			  down = preBean.RSI6_down*(CYCLE-1)/CYCLE+Math.abs(raise/CYCLE);
		  }
		  if(down == 0) {
			  bean.RSI6 = 100;
			  bean.RSI6_up = up;
		  }else {
			  RS = (up)/(down);
			  bean.RSI6 = 100*(RS/(1+RS));
			  bean.RSI6_up = up;
			  bean.RSI6_down = down;
		  }
	  }
//	  System.out.println("rsi "+index+" "+bean.id+" "+bean.date+" "+bean.RSI6+" "+bean.RSI6_up+" "+bean.RSI6_down);
	}
	
	/**
	 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
	 */
	public static ArrayList<TDX_Share_Bean> readFileByLines(String fileName) {
		ArrayList<TDX_Share_Bean> list = new ArrayList<>();
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	        while ((tempString = reader.readLine()) != null ) {
	        	String[] array = tempString.split(",");
	        	if(array.length == 7 && !array[0].trim().equals("")
	        			&& !array[1].trim().equals("")
	        			&& !array[2].trim().equals("")
	        			&& !array[3].trim().equals("")
	        			&& !array[4].trim().equals("")
	        			&& !array[5].trim().equals("")
	        			&& !array[6].trim().equals("")) {
//		            System.out.println("line " + line + ": " + tempString);
	        		TDX_Share_Bean bean = new TDX_Share_Bean(array);
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
	
	public static ArrayList<TDX_Share_Bean> readFileByLines(String fileName,long time) {
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy/MM/dd");
		Date d = new Date();
		d.setTime(time);
		String date = sDateFormat.format(d);
		
		ArrayList<TDX_Share_Bean> list = new ArrayList<>();
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        int line = 1;
	        // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	        while ((tempString = reader.readLine()) != null ) {
	        	String[] array = tempString.split(",");
	        	if(array.length == 7&& !array[0].trim().equals("")
	        			&& !array[1].trim().equals("")
	        			&& !array[2].trim().equals("")
	        			&& !array[3].trim().equals("")
	        			&& !array[4].trim().equals("")
	        			&& !array[5].trim().equals("")
	        			&& !array[6].trim().equals("")) {
//		            System.out.println("line " + line + ": " + tempString);
	        		TDX_Share_Bean bean = new TDX_Share_Bean(array);
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
}
