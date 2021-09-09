package in.co.rays.proj3.testmodel;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.FecultyDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.FecultyModelHBI;
import in.co.rays.proj3.model.ModelFactory;

public class TestFecultyMobelHBI {

	public static void testlist(){
		
		FecultyModelHBI m = (FecultyModelHBI) ModelFactory.getInstance().getFacultyModel();
				;
		FecultyDTO dto = new FecultyDTO();
		try {
			List list= m.list();
			Iterator it = list.iterator();
			while(it.hasNext())
			{
				dto= (FecultyDTO) it.next();
				System.out.println(dto.getFirstName());
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testSearch(){
		
		FecultyModelHBI m = (FecultyModelHBI) ModelFactory.getInstance().getFacultyModel();
				
		
		try {
			FecultyDTO dto=null;
			List list= m.search(dto, 0, 10);
			Iterator it = list.iterator();
			while(it.hasNext())
			{
				dto= (FecultyDTO) it.next();
				System.out.println(dto.getFirstName()+"::"+dto.getCollegeName()+"::"+dto.getCourseId());
				
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		
		//testlist();
		testSearch();
	}

}
