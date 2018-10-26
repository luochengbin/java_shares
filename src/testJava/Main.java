package testJava;

public class Main {
	public static void main(String[] args){
		SQLiteJDBC.createTable();
//		NetRequestAction.DFCF_F10();
//		NetRequestAction.DFCF_GET_Bean();
//		NetRequestAction.DFCF_BK_GET_Bean();
		
		BaseConfig.dfcf_bk_map = SQLiteJDBC.getAllBKDataList();
		BaseConfig.dfcf_share_map = SQLiteJDBC.getAllSharesDataList();
		BaseConfig.dfcf_f10_map = SQLiteJDBC.getAllF10DataList();
		BaseConfig.tdx_share_map = SQLiteJDBC.getAllTDXDataList();

//		StrategyAction.lowAmountIn100Day();
//		SQLiteJDBC_CST.createTable();
	}
}