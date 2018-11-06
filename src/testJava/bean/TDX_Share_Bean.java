package testJava.bean;

public class TDX_Share_Bean {
	public String id;
	public String date;
	public Double open;
	public Double high;
	public Double low;
	public Double close;
	public Double amount;
	public Double turnover;
	
	public Double flag1;
	public Double flag2;
	public Double flag3;
	
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

	@Override
	public String toString() {
		return "Bean [id=" + id + ", date=" + date + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", amount=" + amount + ", turnover=" + turnover + "]";
	}
}
