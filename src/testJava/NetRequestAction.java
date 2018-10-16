package testJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NetRequestAction {
	
	
	/**
	 * �ɺ������������ͣ������
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
	 * �ɺ�������ͣ�����ݶ���
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
