package testJava;

import java.util.ArrayList;
import java.util.HashMap;

import testJava.bean.TDX_Share_Bean;

public class StrategyAction {
	
	public static void lowAmountIn100Day() {
		int range = 10;
		int longDateAgo = 100;
		HashMap<String,TDX_Share_Bean> low20Map = new HashMap<>();
		HashMap<String,TDX_Share_Bean> low40Map = new HashMap<>();
		HashMap<String,TDX_Share_Bean> low60Map = new HashMap<>();
		HashMap<String,TDX_Share_Bean> low80Map = new HashMap<>();
		HashMap<String,TDX_Share_Bean> low100Map = new HashMap<>();
		
		for(String id : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(id);
			for(int i = list.size() -range-1 ; i > 0 && i < list.size()-1  ; i++){
				TDX_Share_Bean bean = list.get(i);
				for(int i1 = i-1 ; i1 >=0 && (i - i1)<=longDateAgo  ; i1--){
					TDX_Share_Bean tempBean = list.get(i1);
					if(bean.amount > tempBean.amount) {
						break;
					}
						
					if(i-i1 == 20){
						low20Map.put(bean.id, bean);
					}
					if(i-i1 == 40){
						low40Map.put(bean.id, bean);
					}
					if(i-i1 == 60){
						low60Map.put(bean.id, bean);
					}
					if(i-i1 == 80){
						low80Map.put(bean.id, bean);
					}
					if(i-i1 == 100){
						low100Map.put(bean.id, bean);
					}
				}
			}
		}
		

		for(String id : low20Map.keySet()) {
			TDX_Share_Bean bean = low20Map.get(id);
			System.out.println("low20Map "+bean.id+" "+bean.date);
		}
		for(String id : low40Map.keySet()) {
			TDX_Share_Bean bean = low40Map.get(id);
			System.out.println("low40Map "+bean.id+" "+bean.date);
		}
		for(String id : low60Map.keySet()) {
			TDX_Share_Bean bean = low60Map.get(id);
			System.out.println("low60Map "+bean.id+" "+bean.date);
		}
		for(String id : low80Map.keySet()) {
			TDX_Share_Bean bean = low80Map.get(id);
			System.out.println("low80Map "+bean.id+" "+bean.date);
		}
		for(String id : low100Map.keySet()) {
			TDX_Share_Bean bean = low100Map.get(id);
			System.out.println("low100Map "+bean.id+" "+bean.date);
		}
	}
}
