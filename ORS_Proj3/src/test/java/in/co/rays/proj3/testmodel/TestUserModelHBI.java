package in.co.rays.proj3.testmodel;

import java.sql.Date;
import java.text.SimpleDateFormat;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.UserModelHBI;

public class TestUserModelHBI {
	public static void testadd()
	{   
		UserDTO dto= new UserDTO();
        dto.setfirstname("ajay");
        dto.setlastname("agnihotri");
        dto.setLogin("v@gmail.com");
        dto.setPassword("234567	");
        dto.setRoleid(1);
        //dto.setCreatedDateTime();

        UserModelHBI obj= (UserModelHBI) ModelFactory.getInstance().getUserModel();
        try {
			obj.add(dto);
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void testauthenticate() 
	{
		UserDTO dto= new UserDTO();
		dto.setId(2);
		dto.setLogin("a@gmail.com");;
		dto.setPassword("Password123@8910");
        UserModelHBI obj = new UserModelHBI();
        
			try {
		         dto =	obj.authenticate(dto.getLogin(), dto.getPassword());
		        if(dto != null){
		        	System.out.println("dto have something");
		        	}
		        else{
		        	System.out.println("dto return null");
		        }

			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
	}

	public static void main(String[] args) {
        		//testadd();
		testauthenticate();


	}

}
