package in.co.rays.proj3.exception;


/**
 * The Class ApplicationException.
 * @author Iterator
 * @version 1.0
 */
public class ApplicationException extends Exception {
	
	/**
	 * Instantiates a new application exception.
	 *
	 * @param msg : Error message
	 */
   public ApplicationException(String msg){
	   super(msg);
   }
}
