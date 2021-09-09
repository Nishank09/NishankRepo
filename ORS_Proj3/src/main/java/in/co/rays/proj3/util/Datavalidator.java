package in.co.rays.proj3.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The Class DataValidator.
 * @author Iterator
 * @version 1.0
 */
public class Datavalidator {

	
	/**
	 * Checks if is null.
	 *
	 * @param val the val
	 * @return true, if is null
	 */
	public static boolean isnull(String value){
		if(value==null || value.trim().length()==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Checks if is not null.
	 *
	 * @param val the val
	 * @return true, if is not null
	 */
	public static boolean isnotnull(String value){
		return !isnull(value);
	}

	/**
     * Checks if is integer.
     *
     * @param val the val
     * @return true, if is integer
     */
	public static boolean isInteger(String val) {
		
		if(isnotnull(val)){
			try{
			int i = Integer.parseInt(val);
			return true;
			}
			catch(NumberFormatException e){
				return false;
			}
		}else{
			return false;
		}
		
	}


	/**
     * Checks if is long.
     *
     * @param val the val
     * @return true, if is long
     */
	public static boolean isLong(String val) {
		if(isnotnull(val)){
		try{
			long l = Long.parseLong(val);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
		}else{
			return false;
		}
		
	}
	
	/**
     * Checks if is email.
     *
     * @param email the email
     * @return true, if is email
     */
	public static boolean isEmailId(String email)
	{
		String emailreg="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isnotnull(email))
		{
		try 
		{

			return email.matches(emailreg);

		} 
		catch (NumberFormatException e)
		{
			return false;
		}		
		} 
		else
		{
        return false;
		}
	}
    /**
     * Checks if value is Date
     * 
     * @param val
     * @return
     */
    public static boolean isDate(String val) {

        Date d = null;
        if (isnotnull(val)) {
            d = Datautility.getdate(val);
        }
        return d != null;
    }


	
	 /**
		 * Checks if value is Phone No
		 * 
		 * @param val
		 * @return boolean
		 */
		public static boolean isPhoneNo(String val) 
		{
			String regex ="^[6-9]\\d{9}$";
			if (!val.matches(regex)) 
			{

				return true;
			} 
			else
			{	
				return false;
			}
		}
		
		
		
		
		/**
		 * Checks if value is name
		 * 
		 * @param val
		 * @return
		 */
		public static boolean isName(String val) {

		String name = "^[a-zA-Z]{3,15}$";
		System.out.println("isName method run");
			
			if (!val.matches(name)) 
			{
				return false;
			} else
			{

				return true;

			}
			
		}

		/**
		 * check value is in range 
		 * @param val
		 * @return
		 */
		public static boolean isRange(String val) {

			System.out.println("isRange method run");
			
			String name ="^[a-zA-Z]{3,15}$";
			if (!val.matches(name)) {
				return true;
			} else {
				return false;
			}
		}
		
		public static boolean isRollNO(String val) {
		
			String passregex = "^([0-9]{2}[a-zA-Z]{2}[0-9]{2})$";
			if (!val.matches(passregex)) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Checks if value is Password
		 * 
		 * @param val
		 * @return boolean
		 */
		/*This regex will enforce these rules:

			At least one upper case English letter, (?=.*?[A-Z])
			At least one lower case English letter, (?=.*?[a-z])
			At least one digit, (?=.*?[0-9])
			At least one special character, (?=.*?[#?!@$%^&*-])
			Minimum eight in length .{8,} (with the anchors)
*/
		public static boolean isPassword(String val) {
			String passregex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
			if (val.matches(passregex))
			{

				return true;
			} 
			else
			{
				return false;
			}
		}
		
		
		public static boolean isMarks(String value)
		{
			System.out.println("is marks called");
			int i=Integer.parseInt(value);
			System.out.println("int value------------>"+i);
		    boolean pass=false;
			
			if(i<100 && i>=0 )
			{
		    pass=true;
			}
			return pass;
			
			
		}

		public static boolean isValidAge(String dob) {
				boolean pass = false;
				if (isDate(dob)) {
					Date cdate = new Date();
					try {
						SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
						Date userdate = format.parse(dob);
						int age = cdate.getYear()-userdate.getYear();
						System.out.println("final age  "+age);
						if(age>=18){

							pass=true;
						}
					} catch (java.text.ParseException e) {
						
					}
				}
				
				return pass;
			}	
			
		

}
