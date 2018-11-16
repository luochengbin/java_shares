package testJava.bean;

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
