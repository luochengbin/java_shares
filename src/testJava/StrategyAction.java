package testJava;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import testJava.bean.TDX_Share_Bean;

public class StrategyAction {
	
	public static class CheckBean{
		TDX_Share_Bean bean;
		HashMap<Integer,Double> priceMap;
		HashMap<Integer,Double> amountMap;
		
	}
	
	public static void MACD_CHECK2(){
		double up = 0;
		double down = 0;
		long upCount = 0;
		long downCount = 0;
		for(String mapKey : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(mapKey);
			int index = -1;
			for(TDX_Share_Bean bean : list){
				index++;
				if(index >0 && index <list.size()-50 
						&& bean.amount/bean.getBean(-1).amount > 2
						) {
					for(int i =1;i<list.size()-index-5;i++) {
						if(bean.amount/bean.getBean(i).amount > 5 && bean.getBean(i).close != 0) {
							if(bean.getBean(i+1).close > bean.getBean(i).close) {
								up += bean.getBean(i+1).close / bean.getBean(i).close;
								upCount++;
							}else {
								down += bean.getBean(i+1).close / bean.getBean(i).close;
								downCount++;
							}
						}
					}
				}
				
			}
		}
		System.out.println("down amount "+up/upCount+" "+down/downCount+" "+upCount/downCount);
	}
	
	public static void MACD_CHECK1(){
		ArrayList<TDX_Share_Bean> uplist = new ArrayList<>();
		ArrayList<TDX_Share_Bean> downlist = new ArrayList<>();
		for(String mapKey : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(mapKey);
			int index = 0;
			for(TDX_Share_Bean bean : list){
				index++;
				if(index > 50 && bean.getBean(1) != null){
					if(bean.getBean(1).close > bean.close) {
						uplist.add(bean);
					}else {
						downlist.add(bean);
					}
				}
			}
		}

		BigDecimal macd_up = new BigDecimal(0);
		BigDecimal macd_down = new BigDecimal(0);
		HashMap<Double,Double> macd_up_map = new HashMap<>();
		HashMap<Double,Double> macd_down_map = new HashMap<>();
		for(TDX_Share_Bean bean : uplist){
			macd_up = macd_up.add(new BigDecimal(bean.amount/bean.getAmountAverage(-10)));
			double temp_macd = Double.valueOf(String.format("%.1f",bean.amount/bean.getAmountAverage(-10)));
			macd_up_map.put(temp_macd, macd_up_map.get(temp_macd) == null ? 1 : macd_up_map.get(temp_macd)+1);
		}
		for(TDX_Share_Bean bean : downlist){
			macd_down = macd_down.add(new BigDecimal(bean.amount/bean.getAmountAverage(-10)));
			double temp_macd = Double.valueOf(String.format("%.1f",bean.amount/bean.getAmountAverage(-10)));
			macd_down_map.put(temp_macd, macd_down_map.get(temp_macd) == null ? 1 : macd_down_map.get(temp_macd)+1);
		}

		System.out.println("count up "+uplist.size()+" "+macd_up.divide(new BigDecimal(uplist.size()),3,RoundingMode.HALF_UP));
		System.out.println("count down "+downlist.size()+" "+macd_down.divide(new BigDecimal(downlist.size()),3,RoundingMode.HALF_UP));
		
		for(double key : macd_up_map.keySet()){
//			if(Double.valueOf(String.format("%.3f",macd_up_map.get(key)/uplist.size())) > 0.001)
//			System.out.println("count up each "+key+" "+macd_up_map.get(key)+" "+String.format("%.3f",macd_up_map.get(key)/uplist.size()));
		}
		for(double key : macd_down_map.keySet()){
//			if(Double.valueOf(String.format("%.3f",macd_down_map.get(key)/downlist.size())) > 0.001)
//			System.out.println("count down each "+key+" "+macd_down_map.get(key)+" "+String.format("%.3f",macd_down_map.get(key)/downlist.size()));
		}
		
		for(Double i = 0.1 ; i<10;i+=0.1) {
			double index = Double.valueOf(String.format("%.1f",i));
			double up = macd_up_map.get(index) == null ? 0:macd_up_map.get(index);
			double down = macd_down_map.get(index) == null ? 0:macd_down_map.get(index);
			System.out.println("count "+index+" "+(up/down)+"	"+String.format("%.4f",((up-down)/(uplist.size()+downlist.size()))));
			
		}
	}
	
	public static void MACD_CHECK() {
		final int CYCLE = 1500;
		final int COUNT_CYCLE = 20;
		final double DIF_RANGE_UP = 10;
		final double DIF_RANGE_DOWN = -10;
		HashMap<Integer,Result> countMap = new HashMap<>();

		double fundsCount = 0;
		double cc = 0;
		for(String mapKey : BaseConfig.tdx_share_map.keySet()) {
			ArrayList<TDX_Share_Bean> list = BaseConfig.tdx_share_map.get(mapKey);


			double ff = 1 ;
			double funds = 1;
			for(int beanIndex = ((CYCLE == 0) ? 0 : ((list.size()-CYCLE) < 0? 0 : (list.size()-CYCLE))); beanIndex < list.size()-1;beanIndex++) {
				TDX_Share_Bean bean = list.get(beanIndex);
				TDX_Share_Bean nextBean = list.get(beanIndex+1);
				if(bean.MACD < 0 && nextBean.MACD >0 && bean.DIF>DIF_RANGE_DOWN && bean.DIF <DIF_RANGE_UP
//					&& nextBean.amount/nextBean.getAmountAverage(-5) > 2
						){
//					System.out.println("jx: "+nextBean.id+" "+nextBean.date);
					for(int i =2 ;i< COUNT_CYCLE && beanIndex+i<list.size();i++) {
						TDX_Share_Bean curBean = list.get(beanIndex+i);
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
//							result.amountUpPer += nextBean.amount/nextBean.getAmountAverage(-5);
						}else {
							result.priceDown++;
							result.priceDownDif += nextBean.DIF;
//							result.amountDownPer += nextBean.amount/nextBean.getAmountAverage(-5);
//							System.out.println("jx: "+nextBean.id+" "+nextBean.date);
						}
						
						if(i == 2) {
							TDX_Share_Bean preBean = nextBean.getBean(-4);
							if(preBean != null) {
								double per = preBean.getBean(1).high/preBean.close;
								if(per > 1.015){
									per = 1.015;
								}else {
									per = preBean.getBean(1).close/preBean.close;
								}
								funds *= per;
								ff++;
							}
						}
						
//						if(i == (COUNT_CYCLE -1) || (beanIndex+i+1) == list.size()) {
//							double per = curBean.close/nextBean.getBean(1).close;
//							funds *= per;
//							if(per <1) {
//								System.out.println("jx: "+nextBean.id+" "+nextBean.date+" "+String.format("%.3f",(funds)));
//							}
//						}
						
//						if(curBean.close < nextBean.open || curBean.MACD<0 || (curBean.MACD < list.get(beanIndex+i-1).MACD && curBean.MACD>1)){
//							if(i >2) {
//								double per = curBean.close/nextBean.close;
//								funds *= per;
//								if(per <1) {
//									System.out.println("jx: "+nextBean.id+" "+nextBean.date+" "+String.format("%.3f",(funds)));
//								}
//							}
//							break;
//						}
					}
				}
			}
			double da = Math.pow(funds,1d/ff);
			fundsCount += da;
			cc++;
		}
		
		System.out.println("xxyy "+String.format("%.3f",(fundsCount/cc)));
		
		
		double total = 0;
		for(Integer key : countMap.keySet()) {
			Result result = countMap.get(key);
			double count = result.priceUp+result.priceDown;
			total += count;
			System.out.println("result "+key+
					"	"+String.format("%.3f", result.price/count)+
					"	"+result.priceUp+"/"+result.priceDown+
					"		"+String.format("%.3f",(result.priceUp/count)) +
					"	"+String.format("%.3f",result.priceUpDif/result.priceUp)+
					"	"+String.format("%.3f",result.priceDownDif/result.priceDown)+
					"	"+String.format("%.3f",result.amountDownPer/result.priceDown)+
					"	"+String.format("%.3f",result.amountUpPer/result.priceUp));
		}
	}
	
	static class Result{
		double price;
		double priceUp;
		double priceDown;
		double priceUpDif;
		double priceDownDif;
		double amountUpPer;
		double amountDownPer;
	}
	
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
