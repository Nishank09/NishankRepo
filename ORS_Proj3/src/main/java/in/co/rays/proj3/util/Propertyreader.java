package in.co.rays.proj3.util;

import java.util.ResourceBundle;


/**
 * The Class PropertyReader.
 * @author Iterator
 * @version 1.0
 */
public class Propertyreader {

	private static final String Filename= "in.co.rays.proj3.bundle.system";
	
	/** The rb. */
	public static ResourceBundle rb = ResourceBundle.getBundle(Filename);
	
	/**
 	 * Return value of key.
 	 *
 	 * @param key the key
 	 * @return the value
 	 */
	public static String getvalue(String key){
		String value= rb.getString(key);
		return value;
		
	}
	
	/**
	 * Gets String after placing param values.
	 *
	 * @param key the key
	 * @param param the param
	 * @return String
	 */
	public static String getvalue(String key, String param){
		
		String msg= getvalue(key);
		msg= msg.replace("{0}",param);
		return msg;
	}
	
	public static void main(String[] args) {
		System.out.println(getvalue("error.require", "login"));
	}
}
