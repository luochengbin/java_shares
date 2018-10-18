package testJava.bean;

public class DFCF_Share_Bean {
	//[0]	String	2,300760,ÂõÈðÒ½ÁÆ,81.30,5.17%,4.00,337908,28,81.29,81.30,0.32%,27.79%,2694792112,13.20,85.03,73.38,85.03,77.30,15.07%,495.24,-85.78%,-277,79.75,184597,153311,1.20,13,110,7.43,1215691266,98835703636,121600000,9886080371,-,-,28.29%,28.29%,1
	//"1,600600,ÇàµºÆ¡¾Æ,31.35,0.00,0.00%,31.35,31.36,56,26014,81215737,31.62,31.35,31.90,30.52,0.37%,16.26"
	private String id;
	private String name;
	private String zxj;
	private String zdf;
	private String zde;
	private String zhongshou;
	private String xianshou;
	private String mrj;
	private String mcj;
	private String zhangsu;
	private String huanshou;
	private String jine;
	private String syl;
	private String zuigao;
	private String zuidi;
	private String kaipan;
	private String zuoshou;
	private String zhenfu;
	private String liangbi;
	private String weibi;
	private String weicha;
	private String junjia;
	private String neipan;
	private String waipan;
	private String neiwaibi;
	private String buy1liang;
	private String sell1liang;
	private String sjl;
	private String zgb;
	private String zsz;
	private String ltgb;
	private String ltsz;
	private String zf3;
	private String zf6;
	private String hs3;
	private String hs6;
	
	public void setData(String values) {
		String[] array = values.split(",");
		if(array.length < 36){
			return;
		}
		id = array[1];
		name = array[2];
		zxj = array[3];
		zdf = array[4];
		zde = array[5];
		zhongshou = array[6];
		xianshou = array[7];
		mrj = array[8];
		mcj = array[9];
		zhangsu = array[10];
		huanshou = array[11];
		zuoshou = array[12];
		jine = array[13];
		syl = array[14];
		zuigao = array[15];
		kaipan = array[16];
		zuoshou = array[17];
		zhenfu = array[18];
		liangbi = array[19];
		weibi = array[20];
		weicha = array[21];
		junjia = array[22];
		neipan = array[23];
		waipan = array[24];
		neiwaibi = array[25];
		buy1liang = array[26];
		sell1liang = array[27];
		sjl = array[28];
		zgb = array[29];
		zsz = array[30];
		ltgb = array[31];
		ltsz = array[32];
		zf3 = array[33];
		zf6 = array[34];
		hs3 = array[35];
		hs6 = array[36];
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

	public String getZxj() {
		return zxj;
	}

	public void setZxj(String zxj) {
		this.zxj = zxj;
	}

	public String getZdf() {
		return zdf;
	}

	public void setZdf(String zdf) {
		this.zdf = zdf;
	}

	public String getZde() {
		return zde;
	}

	public void setZde(String zde) {
		this.zde = zde;
	}

	public String getZhongshou() {
		return zhongshou;
	}

	public void setZhongshou(String zhongshou) {
		this.zhongshou = zhongshou;
	}

	public String getXianshou() {
		return xianshou;
	}

	public void setXianshou(String xianshou) {
		this.xianshou = xianshou;
	}

	public String getMrj() {
		return mrj;
	}

	public void setMrj(String mrj) {
		this.mrj = mrj;
	}

	public String getMcj() {
		return mcj;
	}

	public void setMcj(String mcj) {
		this.mcj = mcj;
	}

	public String getZhangsu() {
		return zhangsu;
	}

	public void setZhangsu(String zhangsu) {
		this.zhangsu = zhangsu;
	}

	public String getHuanshou() {
		return huanshou;
	}

	public void setHuanshou(String huanshou) {
		this.huanshou = huanshou;
	}

	public String getJine() {
		return jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}

	public String getSyl() {
		return syl;
	}

	public void setSyl(String syl) {
		this.syl = syl;
	}

	public String getZuigao() {
		return zuigao;
	}

	public void setZuigao(String zuigao) {
		this.zuigao = zuigao;
	}

	public String getZuidi() {
		return zuidi;
	}

	public void setZuidi(String zuidi) {
		this.zuidi = zuidi;
	}

	public String getKaipan() {
		return kaipan;
	}

	public void setKaipan(String kaipan) {
		this.kaipan = kaipan;
	}

	public String getZuoshou() {
		return zuoshou;
	}

	public void setZuoshou(String zuoshou) {
		this.zuoshou = zuoshou;
	}

	public String getZhenfu() {
		return zhenfu;
	}

	public void setZhenfu(String zhenfu) {
		this.zhenfu = zhenfu;
	}

	public String getLiangbi() {
		return liangbi;
	}

	public void setLiangbi(String liangbi) {
		this.liangbi = liangbi;
	}

	public String getWeibi() {
		return weibi;
	}

	public void setWeibi(String weibi) {
		this.weibi = weibi;
	}

	public String getWeicha() {
		return weicha;
	}

	public void setWeicha(String weicha) {
		this.weicha = weicha;
	}

	public String getJunjia() {
		return junjia;
	}

	public void setJunjia(String junjia) {
		this.junjia = junjia;
	}

	public String getNeipan() {
		return neipan;
	}

	public void setNeipan(String neipan) {
		this.neipan = neipan;
	}

	public String getWaipan() {
		return waipan;
	}

	public void setWaipan(String waipan) {
		this.waipan = waipan;
	}

	public String getNeiwaibi() {
		return neiwaibi;
	}

	public void setNeiwaibi(String neiwaibi) {
		this.neiwaibi = neiwaibi;
	}

	public String getBuy1liang() {
		return buy1liang;
	}

	public void setBuy1liang(String buy1liang) {
		this.buy1liang = buy1liang;
	}

	public String getSell1liang() {
		return sell1liang;
	}

	public void setSell1liang(String sell1liang) {
		this.sell1liang = sell1liang;
	}

	public String getSjl() {
		return sjl;
	}

	public void setSjl(String sjl) {
		this.sjl = sjl;
	}

	public String getZgb() {
		return zgb;
	}

	public void setZgb(String zgb) {
		this.zgb = zgb;
	}

	public String getZsz() {
		return zsz;
	}

	public void setZsz(String zsz) {
		this.zsz = zsz;
	}

	public String getLtgb() {
		return ltgb;
	}

	public void setLtgb(String ltgb) {
		this.ltgb = ltgb;
	}

	public String getLtsz() {
		return ltsz;
	}

	public void setLtsz(String ltsz) {
		this.ltsz = ltsz;
	}

	public String getZf3() {
		return zf3;
	}

	public void setZf3(String zf3) {
		this.zf3 = zf3;
	}

	public String getZf6() {
		return zf6;
	}

	public void setZf6(String zf6) {
		this.zf6 = zf6;
	}

	public String getHs3() {
		return hs3;
	}

	public void setHs3(String hs3) {
		this.hs3 = hs3;
	}

	public String getHs6() {
		return hs6;
	}

	public void setHs6(String hs6) {
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
