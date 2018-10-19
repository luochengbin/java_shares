package testJava;

public class Util {
	public static double valueOf(String value) {
		if(null == value || value.trim().equals("") || value.trim().equals("-") || value.trim().equals("--")) {
			return 0;
		}
		return Double.valueOf(value);
	}
}
