package testJava;

import java.util.ArrayList;
import java.util.HashMap;

import testJava.bean.TDX_Share_Bean;

public class Main {
	public static void main(String[] args){
		SQLiteJDBC.createTable();
//		NetRequestAction.DFCF_F10();
//		NetRequestAction.DFCF_GET_Bean();
//		NetRequestAction.DFCF_BK_GET_Bean();
//		
//		BaseConfig.dfcf_bk_map = SQLiteJDBC.getAllBKDataList();
//		BaseConfig.dfcf_share_map = SQLiteJDBC.getAllSharesDataList();
//		BaseConfig.dfcf_f10_map = SQLiteJDBC.getAllF10DataList();
		BaseConfig.tdx_share_map = SQLiteJDBC.getAllTDXDataList();

		HashMap<Integer,Result> countMap = new HashMap<>();
		

		for(String mapKey : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(mapKey);
			for(int beanIndex = 0; beanIndex < list.size()-1;beanIndex++) {
				TDX_Share_Bean bean = list.get(beanIndex);
				TDX_Share_Bean nextBean = list.get(beanIndex+1);
				if(bean.MACD < 0 && nextBean.MACD >0 && bean.DIF>-0.2 && bean.DIF<0.2){
					System.out.println("jx: "+nextBean.id+" "+nextBean.date);
					for(int i =2 ;i< 42 && beanIndex+i<list.size();i++) {
						TDX_Share_Bean curBean = list.get(beanIndex+i);
						if(curBean.MACD<0) {
							break;
						}
						Result result = new Result();
						if(countMap.get(i-1) == null) {
							result.price  = curBean.close/nextBean.close;
							countMap.put(i-1, result);
						}else {
							result = countMap.get(i-1);
							result.price += curBean.close/nextBean.close;
						}
						if(curBean.close > nextBean.close){
							result.priceUp++;
							result.priceUpDif += nextBean.DIF;
						}else {
							result.priceDown++;
							result.priceDownDif += nextBean.DIF;
							break;
						}
					}
				}
			}
		}
		
		for(Integer key : countMap.keySet()) {
			Result result = countMap.get(key);
			double count = result.priceUp+result.priceDown;
			System.out.println("result "+key+
					"	"+String.format("%.3f", result.price/count)+
					"	"+result.priceUp+"/"+result.priceDown+
					"	"+String.format("%.3f",(result.priceUp/count)) +
					"	"+String.format("%.3f",result.priceUpDif/result.priceUp)+
					"	"+String.format("%.3f",result.priceDownDif/result.priceDown));
		}
		
//		StrategyAction.zhichengxian(100);
//		StrategyAction.xiayingxian(100);
//		StrategyAction.huimaqiang(500000);
//		StrategyAction.lowAmountIn100Day();
//		SQLiteJDBC_CST.createTable();
	}
	
	static class Result{
		double price;
		double priceUp;
		double priceDown;
		double priceUpDif;
		double priceDownDif;
	}
}