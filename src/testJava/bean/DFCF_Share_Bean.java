package testJava.bean;

import testJava.Util;

public class DFCF_Share_Bean {
	//[0]	String	2,300760,ÂõÈðÒ½ÁÆ,81.30,5.17%,4.00,337908,28,81.29,81.30,0.32%,27.79%,2694792112,13.20,85.03,73.38,85.03,77.30,15.07%,495.24,-85.78%,-277,79.75,184597,153311,1.20,13,110,7.43,1215691266,98835703636,121600000,9886080371,-,-,28.29%,28.29%,1
	//"1,600600,ÇàµºÆ¡¾Æ,31.35,0.00,0.00%,31.35,31.36,56,26014,81215737,31.62,31.35,31.90,30.52,0.37%,16.26"
	private String id;
	private String name;
	private double zxj;
	private double zdf;
	private double zde;
	private double zhongshou;
	private double xianshou;
	private double mrj;
	private double mcj;
	private double zhangsu;
	private double huanshou;
	private double jine;
	private double syl;
	private double zuigao;
	private double zuidi;
	private double kaipan;
	private double zuoshou;
	private double zhenfu;
	private double liangbi;
	private double weibi;
	private double weicha;
	private double junjia;
	private double neipan;
	private double waipan;
	private double neiwaibi;
	private double buy1liang;
	private double sell1liang;
	private double sjl;
	private double zgb;
	private double zsz;
	private double ltgb;
	private double ltsz;
	private double zf3;
	private double zf6;
	private double hs3;
	private double hs6;
	
	private String bk;


	public void setData(String values) {
		String[] array = values.split(",");
		if(array.length < 36){
			return;
		}
		id = array[1];
		name = array[2];
		zxj =Util.valueOf(array[3]);
		zdf = Util.valueOf(array[4].replaceAll("%", ""));
		zde = Util.valueOf(array[5]);
		zhongshou = Util.valueOf(array[6]);
		xianshou = Util.valueOf(array[7]);
		mrj = Util.valueOf(array[8]);
		mcj = Util.valueOf(array[9]);
		zhangsu = Util.valueOf(array[10].replaceAll("%", ""));
		huanshou = Util.valueOf(array[11].replaceAll("%", ""));
		jine = Util.valueOf(array[12]);
		syl = Util.valueOf(array[13]);
		zuigao = Util.valueOf(array[14]);
		zuidi = Util.valueOf(array[15]);
		kaipan = Util.valueOf(array[16]);
		zuoshou = Util.valueOf(array[17]);
		zhenfu = Util.valueOf(array[18].replaceAll("%", ""));
		liangbi = Util.valueOf(array[19]);
		weibi = Util.valueOf(array[20].replaceAll("%", ""));
		weicha = Util.valueOf(array[21]);
		junjia = Util.valueOf(array[22]);
		neipan = Util.valueOf(array[23]);
		waipan = Util.valueOf(array[24]);
		neiwaibi = Util.valueOf(array[25]);
		buy1liang = Util.valueOf(array[26]);
		sell1liang = Util.valueOf(array[27]);
		sjl = Util.valueOf(array[28]);
		zgb = Util.valueOf(array[29]);
		zsz = Util.valueOf(array[30]);
		ltgb = Util.valueOf(array[31]);
		ltsz = Util.valueOf(array[32]);
		zf3 = Util.valueOf(array[33].replaceAll("%", ""));
		zf6 = Util.valueOf(array[34].replaceAll("%", ""));
		hs3 = Util.valueOf(array[35].replaceAll("%", ""));
		hs6 = Util.valueOf(array[36].replaceAll("%", ""));
	}
	
	public String getBk() {
		return bk;
	}

	public void setBk(String bk) {
		this.bk = bk;
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

	public double getZxj() {
		return zxj;
	}

	public void setZxj(double zxj) {
		this.zxj = zxj;
	}

	public double getZdf() {
		return zdf;
	}

	public void setZdf(double zdf) {
		this.zdf = zdf;
	}

	public double getZde() {
		return zde;
	}

	public void setZde(double zde) {
		this.zde = zde;
	}

	public double getZhongshou() {
		return zhongshou;
	}

	public void setZhongshou(double zhongshou) {
		this.zhongshou = zhongshou;
	}

	public double getXianshou() {
		return xianshou;
	}

	public void setXianshou(double xianshou) {
		this.xianshou = xianshou;
	}

	public double getMrj() {
		return mrj;
	}

	public void setMrj(double mrj) {
		this.mrj = mrj;
	}

	public double getMcj() {
		return mcj;
	}

	public void setMcj(double mcj) {
		this.mcj = mcj;
	}

	public double getZhangsu() {
		return zhangsu;
	}

	public void setZhangsu(double zhangsu) {
		this.zhangsu = zhangsu;
	}

	public double getHuanshou() {
		return huanshou;
	}

	public void setHuanshou(double huanshou) {
		this.huanshou = huanshou;
	}

	public double getJine() {
		return jine;
	}

	public void setJine(double jine) {
		this.jine = jine;
	}

	public double getSyl() {
		return syl;
	}

	public void setSyl(double syl) {
		this.syl = syl;
	}

	public double getZuigao() {
		return zuigao;
	}

	public void setZuigao(double zuigao) {
		this.zuigao = zuigao;
	}

	public double getZuidi() {
		return zuidi;
	}

	public void setZuidi(double zuidi) {
		this.zuidi = zuidi;
	}

	public double getKaipan() {
		return kaipan;
	}

	public void setKaipan(double kaipan) {
		this.kaipan = kaipan;
	}

	public double getZuoshou() {
		return zuoshou;
	}

	public void setZuoshou(double zuoshou) {
		this.zuoshou = zuoshou;
	}

	public double getZhenfu() {
		return zhenfu;
	}

	public void setZhenfu(double zhenfu) {
		this.zhenfu = zhenfu;
	}

	public double getLiangbi() {
		return liangbi;
	}

	public void setLiangbi(double liangbi) {
		this.liangbi = liangbi;
	}

	public double getWeibi() {
		return weibi;
	}

	public void setWeibi(double weibi) {
		this.weibi = weibi;
	}

	public double getWeicha() {
		return weicha;
	}

	public void setWeicha(double weicha) {
		this.weicha = weicha;
	}

	public double getJunjia() {
		return junjia;
	}

	public void setJunjia(double junjia) {
		this.junjia = junjia;
	}

	public double getNeipan() {
		return neipan;
	}

	public void setNeipan(double neipan) {
		this.neipan = neipan;
	}

	public double getWaipan() {
		return waipan;
	}

	public void setWaipan(double waipan) {
		this.waipan = waipan;
	}

	public double getNeiwaibi() {
		return neiwaibi;
	}

	public void setNeiwaibi(double neiwaibi) {
		this.neiwaibi = neiwaibi;
	}

	public double getBuy1liang() {
		return buy1liang;
	}

	public void setBuy1liang(double buy1liang) {
		this.buy1liang = buy1liang;
	}

	public double getSell1liang() {
		return sell1liang;
	}

	public void setSell1liang(double sell1liang) {
		this.sell1liang = sell1liang;
	}

	public double getSjl() {
		return sjl;
	}

	public void setSjl(double sjl) {
		this.sjl = sjl;
	}

	public double getZgb() {
		return zgb;
	}

	public void setZgb(double zgb) {
		this.zgb = zgb;
	}

	public double getZsz() {
		return zsz;
	}

	public void setZsz(double zsz) {
		this.zsz = zsz;
	}

	public double getLtgb() {
		return ltgb;
	}

	public void setLtgb(double ltgb) {
		this.ltgb = ltgb;
	}

	public double getLtsz() {
		return ltsz;
	}

	public void setLtsz(double ltsz) {
		this.ltsz = ltsz;
	}

	public double getZf3() {
		return zf3;
	}

	public void setZf3(double zf3) {
		this.zf3 = zf3;
	}

	public double getZf6() {
		return zf6;
	}

	public void setZf6(double zf6) {
		this.zf6 = zf6;
	}

	public double getHs3() {
		return hs3;
	}

	public void setHs3(double hs3) {
		this.hs3 = hs3;
	}

	public double getHs6() {
		return hs6;
	}

	public void setHs6(double hs6) {
		this.hs6 = hs6;
	}

	@Override
	public String toString() {
		return "DFCF_Share_Bean [id=" + id + ", name=" + name + ", zxj=" + zxj + ", zdf=" + zdf + ", zde=" + zde
				+ ", zhongshou=" + zhongshou + ", xianshou=" + xianshou + ", mrj=" + mrj + ", mcj=" + mcj + ", zhangsu="
				+ zhangsu + ", huanshou=" + huanshou + ", jine=" + jine + ", syl=" + syl + ", zuigao=" + zuigao
				+ ", zuidi=" + zuidi + ", kaipan=" + kaipan + ", zuoshou=" + zuoshou + ", zhenfu=" + zhenfu
				+ ", liangbi=" + liangbi + ", weibi=" + weibi + ", weicha=" + weicha + ", junjia=" + junjia
				+ ", neipan=" + neipan + ", waipan=" + waipan + ", neiwaibi=" + neiwaibi + ", buy1liang=" + buy1liang
				+ ", sell1liang=" + sell1liang + ", sjl=" + sjl + ", zgb=" + zgb + ", zsz=" + zsz + ", ltgb=" + ltgb
				+ ", ltsz=" + ltsz + ", zf3=" + zf3 + ", zf6=" + zf6 + ", hs3=" + hs3 + ", hs6=" + hs6 + "]";
	}

	
}
