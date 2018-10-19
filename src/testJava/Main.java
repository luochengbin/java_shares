package testJava;

public class Main {
	public static void main(String[] args){
//		SQLiteJDBC.createTable();
//		NetRequestAction.DFCF_F10();
//		NetRequestAction.DFCF_GET_Bean();
//		NetRequestAction.DFCF_BK_GET_Bean();
		
		BaseConfig.dfcf_bk_map = SQLiteJDBC.getAllBKDataList();
		BaseConfig.dfcf_share_map = SQLiteJDBC.getAllSharesDataList();
		BaseConfig.dfcf_f10_map = SQLiteJDBC.getAllF10DataList();
		BaseConfig.tdx_share_map = SQLiteJDBC.getAllTDXDataList();
		
		long size =0;
		for(String key: BaseConfig.tdx_share_map.keySet()) {
			size += BaseConfig.tdx_share_map.get(key).size();
		}
		System.out.println("size "+BaseConfig.dfcf_bk_map.size()+" "+BaseConfig.dfcf_f10_map.size()+" "+BaseConfig.dfcf_share_map.size()+" "+BaseConfig.tdx_share_map.size()+" "+size);
	}
}