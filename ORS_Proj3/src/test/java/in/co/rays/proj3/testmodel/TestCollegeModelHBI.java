package in.co.rays.proj3.testmodel;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.CollegeModelHBI;
import in.co.rays.proj3.model.ModelFactory;

public class TestCollegeModelHBI {
     
	public static void testadd()
	{
		CollegeDTO dto= new CollegeDTO();
		//dto.setId(6);
		dto.setName("mahakal indore");
		dto.setCity("Indore");
		dto.setState("MP");
        dto.setPhoneno("123346457");
        CollegeModelHBI obj = (CollegeModelHBI) ModelFactory.getInstance().getCollegeModel();
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
		CollegeDTO dto= new CollegeDTO();
		dto.setId(6);
        CollegeModelHBI obj = new CollegeModelHBI();
        try {
			obj.delete(dto);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testupdate() throws DuplicateRecordException
	{
		CollegeDTO dto= new CollegeDTO();
		dto.setId(3);
		dto.setName("Medicaps Indore");
        CollegeModelHBI obj = new CollegeModelHBI();
        
			try {
				obj.update(dto);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void testfindbypk()
	{

        CollegeModelHBI obj = new CollegeModelHBI();
        int i =1;
			try {
				 CollegeDTO dto =obj.findByPk(i);
				 System.out.println(dto.getName());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void testfindbyname()
	{

        CollegeModelHBI obj = new CollegeModelHBI();
        int i =1;
			try {
				 CollegeDTO dto =obj.findByName("Medicaps Indore");
				 System.out.println(dto.getName());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}	
	public static void testsearch()
	{
         CollegeDTO dto=new CollegeDTO();
         dto.setState("mp");
        CollegeModelHBI obj = (CollegeModelHBI) ModelFactory.getInstance().getCollegeModel();
        int i =1;
			try {
				  List list = obj.search(dto,0,10);
				  Iterator it= list.iterator();
				  while(it.hasNext()){
					dto=  (CollegeDTO) it.next();
					 System.out.println(dto.getName());
				  }

			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}		
	
	public static void main(String[] args) 
	{
		
		testadd();
		//testdelete();
		//testupdate();
		//testfindbypk();
		//testfindbyname();
		//testsearch();
	}
}
