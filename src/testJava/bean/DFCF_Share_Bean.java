package testJava.bean;

public class DFCF_Share_Bean {
	//"1,600600,ÇàµºÆ¡¾Æ,31.35,0.00,0.00%,31.35,31.36,56,26014,81215737,31.62,31.35,31.90,30.52,0.37%,16.26"
	private String id;
	private String name;
	private String zx;
	private String zhangdie;
	private String zf;
	private String mrj;
	private String mcj;
	private String xs;
	private String zhongshou;
	private String je;
	private String kp;
	private String zuoshou;
	private String zg;
	private String zuidi;
	private String hs;
	private String sy;
	
	public void setData(String values) {
		String[] array = values.split("\",\"");
		id = array[1];
		name = array[2];
		zhangdie = array[3];
		zf = array[4];
		mrj = array[5];
		mcj = array[6];
		xs = array[7];
		zhongshou = array[8];
		je = array[9];
		kp = array[10];
		zuoshou = array[11];
		zg = array[12];
		zuidi = array[13];
		hs = array[14];
		sy = array[15];
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
	public String getZx() {
		return zx;
	}
	public void setZx(String zx) {
		this.zx = zx;
	}
	public String getZhangdie() {
		return zhangdie;
	}
	public void setZhangdie(String zhangdie) {
		this.zhangdie = zhangdie;
	}
	public String getZf() {
		return zf;
	}
	public void setZf(String zf) {
		this.zf = zf;
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
	public String getXs() {
		return xs;
	}
	public void setXs(String xs) {
		this.xs = xs;
	}
	public String getZhongshou() {
		return zhongshou;
	}
	public void setZhongshou(String zhongshou) {
		this.zhongshou = zhongshou;
	}
	public String getJe() {
		return je;
	}
	public void setJe(String je) {
		this.je = je;
	}
	public String getKp() {
		return kp;
	}
	public void setKp(String kp) {
		this.kp = kp;
	}
	public String getZuoshou() {
		return zuoshou;
	}
	public void setZuoshou(String zuoshou) {
		this.zuoshou = zuoshou;
	}
	public String getZg() {
		return zg;
	}
	public void setZg(String zg) {
		this.zg = zg;
	}
	public String getZuidi() {
		return zuidi;
	}
	public void setZuidi(String zuidi) {
		this.zuidi = zuidi;
	}
	public String getHs() {
		return hs;
	}
	public void setHs(String hs) {
		this.hs = hs;
	}
	public String getSy() {
		return sy;
	}
	public void setSy(String sy) {
		this.sy = sy;
	}

	@Override
	public String toString() {
		return "DFCF_Share_Bean [id=" + id + ", name=" + name + ", zx=" + zx + ", zhangdie=" + zhangdie + ", zf=" + zf
				+ ", mrj=" + mrj + ", mcj=" + mcj + ", xs=" + xs + ", zhongshou=" + zhongshou + ", je=" + je + ", kp="
				+ kp + ", zuoshou=" + zuoshou + ", zg=" + zg + ", zuidi=" + zuidi + ", hs=" + hs + ", sy=" + sy + "]";
	}
	
	
	
}
