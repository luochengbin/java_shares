package testJava;

import java.util.ArrayList;
import java.util.HashMap;

import testJava.bean.TDX_Share_Bean;

public class StrategyAction {
	
	public static HashMap<String,TDX_Share_Bean> zhichengxian(double range) {
		double total = 0;
		double up = 0;
		double down = 0;
		int dayAgo = 50;
		int dayLater = 5;
		HashMap<String,TDX_Share_Bean> map = new HashMap<>();

		for(String id : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(id);
			for(int i = list.size()-dayLater ; i-dayAgo > 0 && i > list.size()-range-dayLater  ; i--){
				TDX_Share_Bean today = list.get(i);
				double syx = today.high - (today.open > today.close ? today.open : today.close);
				double xyx = (today.open > today.close ? today.close : today.open) - today.low;
				double st = Math.abs(today.close - today.open);
				double zf = (today.high-today.low)/today.low;

				boolean isNewLow = true;
				for(int agoIndex = 1 ; agoIndex < 10; agoIndex++) {
					if(list.get(i-agoIndex).low < today.low) {
						isNewLow = false;
						break;
					}
				}
				
				if(!isNewLow) {
					break;
				}
				
				
				int samePrice = 0;
				for(int agoIndex = 10 ; agoIndex < dayAgo-1; agoIndex++) {
					TDX_Share_Bean tempDay = list.get(i-agoIndex);
					if(tempDay.open == today.open 
							|| tempDay.close == today.open 
							|| tempDay.high == today.open
							|| tempDay.low == today.open
							|| tempDay.open == today.close
							|| tempDay.close == today.close
							|| tempDay.high == today.close
							|| tempDay.low == today.close 
							|| tempDay.open == today.high
							|| tempDay.close == today.high
							|| tempDay.high == today.high
							|| tempDay.low == today.high
							|| tempDay.open == today.low
							|| tempDay.close == today.low
							|| tempDay.high == today.low
							|| tempDay.low == today.low  ) {
						samePrice++;
					}
				}
				
//				if(isNewLow && 
//						zf>0.03 &&
//						xyx > st &&
////						st > 2*syx &&
//						today.open <= today.close) {
//					total += 1;
//					if(list.get(i+1).close > list.get(i).close
//							|| list.get(i+2).close > list.get(i).close
//							|| list.get(i+3).close > list.get(i).close
//							|| list.get(i+4).close > list.get(i).close
////							|| list.get(i+5).close > list.get(i).close
////							|| list.get(i+6).close > list.get(i).close
//							) {
//						up++;
//					}else {
//						down++;
//						System.out.println("xiayingxian "+today.id+" "+today.date);
//					}
//				}

				if(samePrice >3) {
					
					total += 1;
					if(list.get(i+1).close > list.get(i).close
							|| list.get(i+2).close > list.get(i).close
							|| list.get(i+3).close > list.get(i).close
							|| list.get(i+4).close > list.get(i).close
//							|| list.get(i+5).close > list.get(i).close
//							|| list.get(i+6).close > list.get(i).close
							) {
						up++;
					}else {
						down++;
						System.out.println("xiayingxian down"+today.id+" "+today.date);
					}
					
					System.out.println("zhichengxian "+today.id+" "+today.date);
					break;
				}
			}
		}


		System.out.println("zhichengxian "+total+" "+(up/total));
		
		return map;
	}

	public static HashMap<String,TDX_Share_Bean> xiayingxian(double range) {
		double total = 0;
		double up = 0;
		double down = 0;
		int dayAgo = 10;
		int dayLater = 5;
		HashMap<String,TDX_Share_Bean> map = new HashMap<>();

		for(String id : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(id);
			for(int i = list.size()-dayLater ; i-dayAgo > 0 && i > list.size()-range-dayLater  ; i--){
				TDX_Share_Bean today = list.get(i);
				double syx = today.high - (today.open > today.close ? today.open : today.close);
				double xyx = (today.open > today.close ? today.close : today.open) - today.low;
				double st = Math.abs(today.close - today.open);
				double zf = (today.high-today.low)/today.low;
				
				boolean isNewLow = true;
				for(int agoIndex = 1 ; agoIndex < dayAgo-1; agoIndex++) {
					if(list.get(i-agoIndex).low < today.low) {
						isNewLow = false;
						break;
					}
				}
				
				if(isNewLow && 
						zf>0.03 &&
						xyx > st &&
//						st > 2*syx &&
						today.open <= today.close) {
					total += 1;
					if(list.get(i+1).close > list.get(i).close
							|| list.get(i+2).close > list.get(i).close
							|| list.get(i+3).close > list.get(i).close
							|| list.get(i+4).close > list.get(i).close
//							|| list.get(i+5).close > list.get(i).close
//							|| list.get(i+6).close > list.get(i).close
							) {
						up++;
					}else {
						down++;
						System.out.println("xiayingxian "+today.id+" "+today.date);
					}
				}
			}
		}

		System.out.println("xiayingxian "+total+" "+(up/total));
		
		return map;
	}

	public static HashMap<String,TDX_Share_Bean> huimaqiang(double range) {
		double total = 0;
		double up = 0;
		double down = 0;
		int dayAgo = 20;
		int dayLater = 2;
		HashMap<String,TDX_Share_Bean> map = new HashMap<>();
		for(String id : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(id);
			TDX_Share_Bean latestDay = list.get(list.size()-1);
			for(int i = list.size()-dayLater ; i-dayAgo > 0 && i > list.size()-dayLater-range  ; i--){
				TDX_Share_Bean today = list.get(i);
				double syx = today.high - (today.open > today.close ? today.open : today.close);
				double xyx = (today.open > today.close ? today.close : today.open) - today.low;
				double st = Math.abs(today.close - today.open);
				if(
						st >0 &&
//						st>syx*3 &&
						today.close > list.get(i-1).close &&
//						today.close < list.get(i-dayAgo).close &&
						today.amount > list.get(i-1).amount*1.9 && 
						today.amount > list.get(i-2).amount*1.9 &&
						today.amount > list.get(i-3).amount*1.9
//						today.amount > list.get(i-4).amount*1.9 &&
//						today.amount > list.get(i-5).amount*1.9 &&
//						today.amount > list.get(i-6).amount*1.5 &&
//						today.amount > list.get(i-7).amount*1.5 &&
//						today.amount > list.get(i-8).amount*1.5 &&
//						today.amount > list.get(i-9).amount*1.5 &&
//						today.amount > list.get(i-10).amount*1.5  &&
//						list.get(i+1).amount < today.amount*0.9 &&
//						list.get(i+2).amount < today.amount*0.8 &&
//						list.get(i+3).amount < today.amount*0.7 &&
//						list.get(i+3).close > today.close*0.98
						){
					
					
						for(int fdayIndex = 1 ; fdayIndex <dayLater ; fdayIndex ++){
							double syx_f = list.get(i+fdayIndex).high - (list.get(i+fdayIndex).open > list.get(i+fdayIndex).close ? list.get(i+fdayIndex).open : list.get(i+fdayIndex).close);
							double xyx_f = (list.get(i+fdayIndex).open > list.get(i+fdayIndex).close ? list.get(i+fdayIndex).close : list.get(i+fdayIndex).open) - list.get(i+fdayIndex).low;
							double st_f = Math.abs(list.get(i+fdayIndex).close - list.get(i+fdayIndex).open);

							
							if(
//									st_f<syx_f*3 ||
									list.get(i+fdayIndex).amount > today.amount ||
									list.get(i+fdayIndex).close < today.open ) {
								break;
							}

							if(fdayIndex == (dayLater-1) && latestDay.close <= today.close*1.1 && latestDay.close > today.close) {
								System.out.println("huimaqiang "+today.id+" "+today.date);
							}
							
//							if(list.get(i+fdayIndex).amount < today.amount*0.5){
//								total++;
//								if(list.get(i+fdayIndex+1).close > list.get(i+fdayIndex).close
//										|| list.get(i+fdayIndex+2).close > list.get(i+fdayIndex).close
//										|| list.get(i+fdayIndex+3).close > list.get(i+fdayIndex).close
//										|| list.get(i+fdayIndex+4).close > list.get(i+fdayIndex).close
//										|| list.get(i+fdayIndex+5).close > list.get(i+fdayIndex).close
//										|| list.get(i+fdayIndex+6).close > list.get(i+fdayIndex).close) {
//									up++;
//								}else {
//									down++;
//									System.out.println("huimaqiang "+today.id+" "+today.date+" "+list.get(i+fdayIndex).date);
//								}
//								break;
//							}
							
						}

				}
			}
		}
		System.out.println("huimaqiang "+total+" "+(up/total)+" "+(down/total));
		return map;
	}

	public static HashMap<Integer,HashMap<String,TDX_Share_Bean>> lowAmountIn100Day() {
		int range = 1000000;
		int longDateAgo = 31;
		int minDateAgo = 20;
		return lowAmountIn100Day(range,minDateAgo,longDateAgo);
	}
	
	public static HashMap<Integer,HashMap<String,TDX_Share_Bean>> lowAmountIn100Day(int range,int minDateAgo,int longDateAgo) {
		HashMap<Integer,HashMap<String,TDX_Share_Bean>> map = new HashMap<>();

		double total = 0;
		HashMap<Double,Double> upMap = new HashMap<>();
		
		for(String id : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(id);
			boolean hasAdd = false;
			First_Flag:
			for(int i = list.size()-1 ; i > 0 && i > list.size()-1-range  ; i--){
				TDX_Share_Bean bean = list.get(i);
				for(int i1 = i-1 ; i1 >=0 && (i - i1)<=longDateAgo  ; i1--){
					TDX_Share_Bean tempBean = list.get(i1);
					if(bean.amount > tempBean.amount || i1 == 0 || (i - i1) == longDateAgo) {
						if(i-i1 >= minDateAgo && i-i1 < longDateAgo){
							HashMap<String,TDX_Share_Bean> tempMap = map.get(i-i1);
							if(tempMap == null){
								tempMap = new HashMap<>();
								map.put(i-i1, tempMap);
							}
							tempMap.put(bean.id, bean);
							hasAdd = true;

							if(i+10 < list.size()) {
								for(int fIndex = 1 ; fIndex <= 10 ;fIndex ++){
									if(list.get(i+fIndex).close > bean.close) {
										if(upMap.get(Double.valueOf(i-i1)) == null){
											upMap.put(Double.valueOf(i-i1), Double.valueOf(0));
										}
										upMap.put(Double.valueOf(i-i1), Double.valueOf(upMap.get(Double.valueOf(i-i1))+1));
										break;
									}
								}
								total += 1;
							}
						}
						if(hasAdd) {
							break First_Flag;
						}
						break;
					}
				}
			}
		}

		int count = 0;
		for(Integer day : map.keySet()) {
			count += map.get(day).size();
		}
		for(Integer day : map.keySet()) {
			System.out.println("lowAmount-"+day+" count:"+map.get(day).size()+" "+(map.get(day).size()/Double.valueOf(count)));
		}
		
		double upCount = 0;
		for(Double day : upMap.keySet()) {
			upCount += upMap.get(day);
		}
		for(Double day : upMap.keySet()) {
			System.out.println("lowAmount-up "+day+" count:"+upMap.get(day)+" total:"+total+" "+(upMap.get(day)/upCount)+" "+(upCount/total));
		}
		
		for(Integer day : map.keySet()) {
			for(String id : map.get(day).keySet()) {
				TDX_Share_Bean bean = map.get(day).get(id);
				System.out.println("lowAmount-"+day+" "+bean.id+" "+bean.date);
			}
		}
		
		return map;
	}
}
