package in.co.rays.proj3.testmodel;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj3.dto.TimetableDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.TimeTableModelHBI;
import in.co.rays.proj3.model.UserModelHBI;

public class TestTimetablemodelHNBI {
	public static void testadd()
	{   
		TimetableDTO dto= new TimetableDTO();
		Date d= new Date();
         dto.setsubjectid(8);
         dto.setcourseid(3);
         dto.setsemester("5th");
         //dto.setexamdate(d);
         dto.setexamtime("08:00PM to 11:00PM");
         
        //dto.setCreatedDateTime();

        TimeTableModelHBI obj= (TimeTableModelHBI) ModelFactory.getInstance().getTimeTableModel();
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

	public static void testupdate(){
		TimetableDTO dto= new TimetableDTO();
		     dto.setId(2);
		     dto.setsubjectname("Data file Structure");
	         dto.setcoursename("MCA");;
	         dto.setsemester("5th");
	         //dto.setexamdate(d);
	         dto.setexamtime("08:00PM to 11:00PM");
	         TimeTableModelHBI obj= (TimeTableModelHBI) ModelFactory.getInstance().getTimeTableModel();
	         try {
				obj.update(dto);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	public static void testfindbypk(){
		TimetableDTO dto= new TimetableDTO();
	     dto.setId(2);
	     TimeTableModelHBI obj= (TimeTableModelHBI) ModelFactory.getInstance().getTimeTableModel();
	     try {
			dto= obj.findByPK(2);
			System.out.println(dto.getcoursename());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//testadd();
		testupdate();
		//testfindbypk();
	}

}
