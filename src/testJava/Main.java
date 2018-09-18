package testJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static void main(String[] args){
		SQLiteJDBC.createTable();
//		ArrayList<Bean> list = SQLiteJDBC.getAllList();
//		for(Bean bean : list) {
//			System.out.println(bean.toString());
//		}
//		checkXiaYingXian();
//		zhangtingban();
	}
	
	public static void zhangtingban() {
		ArrayList<ZTBBean> list = new ArrayList<>();
        
        for(int pageNo = 1; pageNo <= 1000 ; pageNo++) {
            String url = "http://www.178448.com/fjzt-2.html?page="+pageNo;
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements =  doc.select("div.datalist").select("tbody").select("tr");
                for(Element element : elements){
                	ZTBBean bean = new ZTBBean(element.select("td").eachText());
                	list.add(bean);
                    System.out.println("xxyy "+bean);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("xxyy "+list);
	}
	
	public static void checkHuimaqiang() {
	    File file = new File("D:\\new_tdx\\code\\data\\date");
		int goldCount = 0;
		
	    for(File subFile : file.listFiles()) {
			ArrayList<Bean> list = readFileByLines(subFile.getAbsolutePath());
			if(list.size() >100){
				for(int beanIndex = list.size()-50 ; beanIndex < list.size();beanIndex ++){
					if(list.get(beanIndex).close < list.get(beanIndex).open) {
						continue;
					}
					boolean isGold = true;
					for(int i = beanIndex-10; i < beanIndex ;i++) {
						if(list.get(i).amount*1.5 > list.get(beanIndex).amount){
							isGold = false;
						}
					}
					if(isGold){
						goldCount++;
						System.out.println("checkBeiliangzhu "+subFile.getName()+" "+list.get(beanIndex).date);
					}
				}
			}
	    }
	    
		System.out.println("checkBeiliangzhu gold count "+goldCount);
	}
	
	public static void checkXiaYingXian() {
	    File file = new File("D:\\new_tdx\\code\\data\\date");
		int goldCount = 0;
		int upCount = 0;
		int downCount = 0;
		
	    for(File subFile : file.listFiles()) {
			ArrayList<Bean> list = readFileByLines(subFile.getAbsolutePath());
			if(list.size() >100){
				for(int beanIndex =  list.size()-5 ; beanIndex < list.size()-4;beanIndex ++){
					boolean isGold = true;
					if(list.get(beanIndex-1).close > list.get(beanIndex-1).open
						|| list.get(beanIndex).low == list.get(beanIndex).open	
						|| list.get(beanIndex+1).amount == 0){
						continue;
					}
					if(list.get(beanIndex).close > list.get(beanIndex).open){
						double shiti = list.get(beanIndex).close - list.get(beanIndex).open;
						double xiayingxian = list.get(beanIndex).open - list.get(beanIndex).low;
						double shangyingxian = list.get(beanIndex).high - list.get(beanIndex).close;
						if(shiti*1.2 > xiayingxian || xiayingxian == 0 || shangyingxian*1.5 > xiayingxian){
							isGold = false;
						}
					}else {
						double shiti = list.get(beanIndex).open - list.get(beanIndex).close;
						double xiayingxian = list.get(beanIndex).close - list.get(beanIndex).low;
						double shangyingxian = list.get(beanIndex).high - list.get(beanIndex).open;
						if(shiti*1.2 > xiayingxian || xiayingxian == 0 || shangyingxian*1.5 > xiayingxian){
							isGold = false;
						}
					}
					if(isGold){
						goldCount++;
						if(list.get(beanIndex+1).close > list.get(beanIndex).close*1){
							upCount++;
							System.out.println("checkBeiliangzhu up "+subFile.getName()+" "+list.get(beanIndex).date);
						}else {
							System.out.println("checkBeiliangzhu down "+subFile.getName()+" "+list.get(beanIndex).date);
						}
					}
				}
			}
	    }
	    
		System.out.println("checkBeiliangzhu gold count "+goldCount +" upCountPer: "+(double)upCount/goldCount);
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
	        		if(bean.date.compareTo(date) >0) {
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
	
	static class ZTBBean {
		String id;
		String bzt;
		String cgl;
		String stockName;
		String reson;
		String date;
		String selectPrice;
		String bztNum;
		String bztDate;
		
		public ZTBBean(List<String> list) {
			id = list.get(0);
			bzt= list.get(1);
			cgl= list.get(2);
			stockName= list.get(3);
			reson= list.get(4);
			date= list.get(5);
			selectPrice= list.get(6);
			bztNum= list.get(7);
			bztDate= list.get(8);
		}

		@Override
		public String toString() {
			return "ZTBBean [id=" + id + ", bzt=" + bzt + ", cgl=" + cgl + ", stockName=" + stockName + ", reson="
					+ reson + ", date=" + date + ", selectPrice=" + selectPrice + ", bztNum=" + bztNum + ", bztDate="
					+ bztDate + "]";
		}
		
		
	}
}