package testJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import testJava.bean.DFCF_F10_Bean;
import testJava.bean.DFCF_Share_Bean;

public class NetRequestAction {
	
	public static void DFCF_GET_Bean() {
		Gson gson = new Gson();
		ArrayList<DFCF_Share_Bean> list = new ArrayList<>();
		int index = 0;
//		for(String key : SQLiteJDBC.sharesMap.keySet()){
	        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?ps=500&token=64a483cbad8b666efa51677820e6b21c&type=CT&cmd=3003002%2C6000001%2C6000041&sty=CTF&cb=getStockFullInfo&js=%28%5B%28x%29%5D%29&0.7869784760156245=";
	        String rawresponse = HttpManager.sendGet(url);
	        
	        System.out.println(rawresponse);
	        String response = rawresponse.replaceAll("getStockFullInfo\\(\\[\"", "").replaceAll("\"\\]\\)", "");
	        System.out.println(response);
	        String[] subResponse = response.split(",");
	        for(String sub : subResponse) {
		        DFCF_Share_Bean bean = new DFCF_Share_Bean();
		        bean.setData(sub);
		        list.add(bean);
		        System.out.println("DFCF_GET_Bean "+bean);
	        }
	        index++;
	        
//	        if(index>10) {
//	        	break;
//	        }
//		}
	}
	
	public static void DFCF_F10() {
		Gson gson = new Gson();
		ArrayList<DFCF_F10_Bean> list = new ArrayList<>();
		int index = 0;
		for(String key : SQLiteJDBC.sharesMap.keySet()){
	        String url = "http://emweb.securities.eastmoney.com/PC_HSF10/OperationsRequired/OperationsRequiredAjax?times=1&code="+(key.startsWith("6")?"SH"+key : "SZ"+key  );
	        String response = HttpManager.sendGet(url);
	        DFCF_F10_Bean bean = gson.fromJson(response,DFCF_F10_Bean.class);
	        bean.setId(key);
	        list.add(bean);
	        index++;
	        System.out.println("DFCF_F10 "+index+"/"+list.size()+" "+key);
	        
	        if(index>10) {
	        	break;
	        }
		}
		SQLiteJDBC.updateCWFX(list);
	}
	
	/**
	 * 股海明灯上面的涨停板数据
	 */
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
	

	
	/**
	 * 股海明灯涨停板数据对象
	 *
	 */
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
