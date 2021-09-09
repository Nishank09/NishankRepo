package in.co.rays.proj3.testmodel;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelHBI;

public class testStudentMobelHBI {

	
	
	public static void testlist(){
		StudentModelHBI o = (StudentModelHBI) ModelFactory.getInstance().getStudentModel();
		StudentDTO dto= new StudentDTO();
		try {
			List list= o.list();
			Iterator it= list.iterator();
			while(it.hasNext()){
				dto= (StudentDTO) it.next();
				System.out.println(dto.getfirstname() + dto.getlastname());
				
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		testlist();

	}

}
