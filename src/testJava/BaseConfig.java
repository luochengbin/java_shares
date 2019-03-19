package testJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import testJava.bean.DFCF_BK_Bean;
import testJava.bean.DFCF_F10_Bean;
import testJava.bean.DFCF_Share_Bean;
import testJava.bean.TDX_Share_Bean;

public class BaseConfig {
	public static String dataPath = "D:\\ben\\workspace\\stockdata";
	public static Map<String,String> sharesMap;
	public static HashMap<String,DFCF_Share_Bean> dfcf_share_map;
	public static HashMap<String,DFCF_F10_Bean.ZyzbAbgqBean> dfcf_f10_map;
	public static HashMap<String,DFCF_BK_Bean> dfcf_bk_map;
	public static HashMap<String,ArrayList<TDX_Share_Bean>> tdx_share_map;
}
