package testJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import testJava.bean.DFCF_BK_Bean;
import testJava.bean.DFCF_F10_Bean;
import testJava.bean.DFCF_Share_Bean;

public class NetRequestAction {

	public static void DFCF_BK_GET_Bean() {
		ArrayList<DFCF_BK_Bean> list = new ArrayList<>();
		{
			String url1 = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112408974231813341811_1539918575415&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FPGBKI&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd=C._BKGN&st=(ChangePercent)&sr=-1&p=1&ps=300&_=1539918575420";
			String rawresponse = HttpManager.sendGet(url1);
			String response = rawresponse.substring(rawresponse.indexOf("data:[\""), rawresponse.lastIndexOf("\"],"));
			String[] subResponse = response.split("\",\"");
	        for(String sub : subResponse) {
	        	DFCF_BK_Bean bean = new DFCF_BK_Bean();
		        bean.setData(1,sub);
		        list.add(bean);
		        System.out.println("DFCF_GET_Bean "+list.size());
	        }
		}
		{
			String url1 = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cb=jQuery112408974231813341811_1539918575415&type=CT&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FPGBKI&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)&cmd=C._BKHY&st=(ChangePercent)&sr=-1&p=1&ps=300&_=1539918575420";
			String rawresponse = HttpManager.sendGet(url1);
			String response = rawresponse.substring(rawresponse.indexOf("data:[\""), rawresponse.lastIndexOf("\"],"));
			String[] subResponse = response.split("\",\"");
	        for(String sub : subResponse) {
	        	DFCF_BK_Bean bean = new DFCF_BK_Bean();
		        bean.setData(0,sub);
		        list.add(bean);
		        System.out.println("DFCF_GET_Bean "+list.size());
	        }
		}
		SQLiteJDBC.updateBK(list);
	}
	
	
	public static void DFCF_GET_Bean() {
		Gson gson = new Gson();
		ArrayList<DFCF_Share_Bean> list = new ArrayList<>();
		int index = 0;
		int step = 1;
		String ids = "";
		for(String key : BaseConfig.sharesMap.keySet()){
			if(index >200 || step == BaseConfig.sharesMap.size()) {
				ids += key.startsWith("6")?(key+"1"):(key+"2");
				String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?ps=500&token=580f8e855db9d9de7cb332b3990b61a3&type=CT&cmd="+ids+"&sty=CTALL&cb=getStockFullInfo&js=([(x)],true)&0.2644922395264093";
				String rawresponse = HttpManager.sendGet(url);
		        String response = rawresponse.replaceAll("getStockFullInfo\\(\\[\"", "").replaceAll("\"\\]\\)", "");
		        String[] subResponse = response.split("\",\"");
		        for(String sub : subResponse) {
			        DFCF_Share_Bean bean = new DFCF_Share_Bean();
			        bean.setData(sub);
			        list.add(bean);
			        System.out.println("DFCF_GET_Bean "+list.size()+"/"+BaseConfig.sharesMap.size());
		        }
		        
				ids = "";
				index = 0;
			}else {
				ids += key.startsWith("6")?(key+"1")+",":(key+"2")+",";
			}
			step++;
			index++;
		}
		SQLiteJDBC.updateCWFXDetail(list);
	}
	
	public static void DFCF_F10() {
		Gson gson = new Gson();
		ArrayList<DFCF_F10_Bean> list = new ArrayList<>();
		int index = 0;
		for(String key : BaseConfig.sharesMap.keySet()){
	        String url = "http://emweb.securities.eastmoney.com/PC_HSF10/OperationsRequired/OperationsRequiredAjax?times=1&code="+(key.startsWith("6")?"SH"+key : "SZ"+key  );
	        String response = HttpManager.sendGet(url);
	        DFCF_F10_Bean bean = gson.fromJson(response,DFCF_F10_Bean.class);
	        bean.setId(key);
	        list.add(bean);
	        index++;
	        System.out.println("DFCF_F10 "+index+"/"+BaseConfig.sharesMap.size()+" "+key);
//	        if(index >5){
//	        	break;
//	        }
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
