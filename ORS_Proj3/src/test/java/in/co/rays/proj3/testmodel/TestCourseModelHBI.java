package in.co.rays.proj3.testmodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.CollegeModelHBI;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.ModelFactory;

public class TestCourseModelHBI {


	public static void testadd()
	{   
		SimpleDateFormat formater =new SimpleDateFormat("yyyy/dd/mm hh:mm:ss");
		Date date = new Date(0);
		formater.format(date);
		CourseDTO dto= new CourseDTO();
		dto.setCoursename("test2");
		dto.setDuration("3");
		dto.setDescription("Test in application");
        dto.setCreatedBy("nishank@gmail.com");
        dto.setModifiedBy("nishank@gmail.com");
        //dto.setCreatedDateTime();

        CourseModelHBI obj = (CourseModelHBI) ModelFactory.getInstance().getCourseModel();
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
	public static void testdelete()
	{
		CourseDTO dto= new CourseDTO();
		dto.setId(2);
        CourseModelHBI obj = new CourseModelHBI();
        try {
			obj.delete(dto);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testupdate() 
	{
		CourseDTO dto= new CourseDTO();
		dto.setId(2);
		dto.setCoursename("BE");
		dto.setDescription("Bachelor in Engineering");
        CourseModelHBI obj = new CourseModelHBI();
        
			try {
				obj.update(dto);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void testfindbypk()
	{
     
		//CourseDTO dto= new CourseDTO();
        CourseModelHBI obj = new CourseModelHBI();
        int i =3;
			try {
				 CourseDTO dto =obj.findByPK(i);
				 System.out.println(dto.getCoursename());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void testfindbyname()
	{

        CourseModelHBI obj = new CourseModelHBI();
        int i =1;
			try {
				 CourseDTO dto =obj.findByName("BE");
				 System.out.println(dto.getDescription());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}	
	public static void testsearch()
	{
		CourseDTO dto= new CourseDTO();
         dto.setCoursename("BCA");
         CourseModelHBI obj = new CourseModelHBI();
        int i =1;
			try {
				  List list = obj.search(dto,0,10);
				  Iterator it= list.iterator();
				  while(it.hasNext()){
					dto=  (CourseDTO) it.next();
					 System.out.println(dto.getCoursename()+":"+dto.getDescription());
					 
				  }

			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}	
	public static void testlist()
	{
		CourseDTO dto= new CourseDTO();
   
         CourseModelHBI obj = new CourseModelHBI();

			try {
				  List list = obj.list();
				  Iterator it= list.iterator();
				  while(it.hasNext()){
					dto=  (CourseDTO) it.next();
					 System.out.println(dto.getCoursename()+":"+dto.getDescription());
				  }

			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}	


	
	public static void main(String[] args) {
		//testadd();
		//testdelete();
		//testupdate();
		testfindbypk();
		//testfindbyname();
		//testsearch();
		//testlist();
	}

}
