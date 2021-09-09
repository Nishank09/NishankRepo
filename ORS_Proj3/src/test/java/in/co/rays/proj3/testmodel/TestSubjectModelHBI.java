package in.co.rays.proj3.testmodel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.SubjectModelHBI;


public class TestSubjectModelHBI {
	
	public static void testadd()
	{   
		SimpleDateFormat formater =new SimpleDateFormat("yyyy/dd/mm hh:mm:ss");
		Date date = new Date(0);
		formater.format(date);
		SubjectDTO dto= new SubjectDTO();
		dto.setCourseId(1);
		dto.setSubjectname("Math1");
		dto.setDescription("Bachelor in Computer application");
        dto.setCreatedBy("nishank@gmail.com");
        dto.setModifiedBy("nishank@gmail.com");
        //dto.setCreatedDateTime();

        SubjectModelHBI obj = new SubjectModelHBI();
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
	public static void testupdate()
	{   


		SubjectDTO dto= new SubjectDTO();
		dto.setId(2);
		dto.setCourseId(1);
		dto.setSubjectname("Math1");
		dto.setDescription("Master in Computer application");
        dto.setCreatedBy("nishank@gmail.com");
        dto.setModifiedBy("nishank@gmail.com");
        //dto.setCreatedDateTime();

        SubjectModelHBI obj = new SubjectModelHBI();
        try {
			obj.update(dto);
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testfindbypk()
	{   


		SubjectDTO dto= new SubjectDTO();
	


        SubjectModelHBI obj = new SubjectModelHBI();
        try {
			SubjectDTO dt= obj.findByPK(1);
			System.out.println(dt.getSubjectname());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testfindbyname()
	{   


		SubjectDTO dto= new SubjectDTO();
	      dto.setSubjectname("Math1");


        SubjectModelHBI obj = new SubjectModelHBI();
        try {
			SubjectDTO dt= obj.findByName("Math1");
			System.out.println(dt.getCourseId());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testdelete()
	{   


		SubjectDTO dto= new SubjectDTO();
	      dto.setId(3);


        SubjectModelHBI obj = new SubjectModelHBI();
        try {
			obj.delete(dto);
		
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testlist()
	{   


		SubjectDTO dto= new SubjectDTO();
	      


        SubjectModelHBI obj = (SubjectModelHBI) ModelFactory.getInstance().getSubjectModel();
        try {
			List list= obj.list(0,0);
			Iterator it= list.iterator();
			while(it.hasNext()){
				dto= (SubjectDTO) it.next();
				System.out.println(dto.getSubjectname());
				System.out.println(dto.getDescription());
			}
		
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testsearch()
	{   


		SubjectDTO dto= new SubjectDTO();
	    dto.setCourseId(1);


        SubjectModelHBI obj = new SubjectModelHBI();
        try {
			List list= obj.search(dto,0,10);
			Iterator it= list.iterator();
			while(it.hasNext()){
				dto= (SubjectDTO) it.next();
				System.out.println(dto);
				System.out.println(dto.getDescription());
			}
		
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testadd();
		//testupdate();
		//testfindbypk();
		//testfindbyname();
		//testdelete();
		testlist();
		//testsearch();
	}

}
