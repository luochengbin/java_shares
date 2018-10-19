package testJava.bean;

import testJava.Util;

public class DFCF_BK_Bean {
	private String id;
	private String name;
	private double type;
	private double zdf;
	private double zsz;
	private double hsl;
	private double szjs;
	private double xdjs;
	private double zxj;
	private double zde;
	
	public void setData(double type,String data) {
		String[] list = data.split(",");
		id = list[1];
		name = list[2];
		this.type = type;
		zdf = Util.valueOf(list[3]);
		zsz = Util.valueOf(list[4]);
		hsl = Util.valueOf(list[5]);
		szjs = Util.valueOf((list[6].split("\\|"))[0]);
		xdjs = Util.valueOf((list[6].split("\\|"))[1]);
		zxj = Util.valueOf(list[18]);
		zde = Util.valueOf(list[19]);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getType() {
		return type;
	}

	public void setType(double type) {
		this.type = type;
	}

	public double getZdf() {
		return zdf;
	}

	public void setZdf(double zdf) {
		this.zdf = zdf;
	}

	public double getZsz() {
		return zsz;
	}

	public void setZsz(double zsz) {
		this.zsz = zsz;
	}

	public double getHsl() {
		return hsl;
	}

	public void setHsl(double hsl) {
		this.hsl = hsl;
	}

	public double getSzjs() {
		return szjs;
	}

	public void setSzjs(double szjs) {
		this.szjs = szjs;
	}

	public double getXdjs() {
		return xdjs;
	}

	public void setXdjs(double xdjs) {
		this.xdjs = xdjs;
	}

	public double getZxj() {
		return zxj;
	}

	public void setZxj(double zxj) {
		this.zxj = zxj;
	}

	public double getZde() {
		return zde;
	}

	public void setZde(double zde) {
		this.zde = zde;
	}

	@Override
	public String toString() {
		return "DFCF_BK_Bean [id=" + id + ", name=" + name + ", type=" + type + ", zdf=" + zdf + ", zsz=" + zsz
				+ ", hsl=" + hsl + ", szjs=" + szjs + ", xdjs=" + xdjs + ", zxj=" + zxj + ", zde=" + zde + "]";
	}
	
	
}
