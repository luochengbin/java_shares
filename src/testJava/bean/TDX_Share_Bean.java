package testJava.bean;

import testJava.BaseConfig;

public class TDX_Share_Bean {
	public String id;
	public String date;
	public double open;
	public double high;
	public double low;
	public double close;
	public double amount;
	public double turnover;
	
	public double flag1;
	public double flag2;
	public double flag3;
	
	public double EMA1;
	public double EMA2;
	public double DIF;
	public double DEA;
	public double MACD;
	public double RSI6;
	public double RSI6_up;
	public double RSI6_down;
	
	
	public TDX_Share_Bean(){}
	
	public TDX_Share_Bean(String[] array) {
		date = array[0];
		open = Double.valueOf(array[1]);
		high = Double.valueOf(array[2]);
		low = Double.valueOf(array[3]);
		close = Double.valueOf(array[4]);
		amount = Double.valueOf(array[5]);
		turnover = Double.valueOf(array[6]);
	}

	public TDX_Share_Bean getBean(int cursor) {
		int newIndex = BaseConfig.tdx_share_map.get(id).indexOf(this)+cursor;
		if(newIndex<0 || newIndex>BaseConfig.tdx_share_map.get(id).size()-1){
			return null;
		}
		return BaseConfig.tdx_share_map.get(id).get(newIndex);
	}
	
	public double getAmountAverage(int offset) {
		TDX_Share_Bean bean = getBean(offset);
		double amount = 0;
		if(bean != null) {
			amount = bean.amount;	
		}
		for(int i = 1;i< Math.abs(offset);i++){
			TDX_Share_Bean subBean = bean.getBean(i);
			if(subBean != null) {
				amount += subBean.amount;
			}
		}
		return amount/Math.abs(offset);
	}
	
	@Override
	public String toString() {
		return "Bean [id=" + id + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", amount=" + amount + ", turnover=" + turnover + "]";
	}
}
