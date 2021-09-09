package in.co.rays.proj3.exception;


/**
 * The Class RecordNotFoundException.
 * @author Iterator
 * @version 1.0
 */
public class RecordNotFoundException extends Exception {
	
	/**
	 * Instantiates a new record not found exception.
	 *
	 * @param msg error message
	 */
 public RecordNotFoundException(String msg ){
	 super(msg);
 }
}
