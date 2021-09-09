package in.co.rays.proj3.testutilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.DropDownList;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.StudentModelHBI;
import in.co.rays.proj3.model.SubjectModelHBI;
import in.co.rays.proj3.util.Htmlutility;
import in.co.rays.proj3.util.Serviletutility;

public class TestHtmlutilities {

	public static void testgetlist(){
		Htmlutility test= new Htmlutility();
		StudentDTO dto= new StudentDTO();
		StudentModelHBI cm = new StudentModelHBI();
	
		try {
			List l = cm.list();
			
            System.out.println(l);
            Collections.sort(l);
            Iterator it= l.iterator();
            
            while(it.hasNext()){
            	 dto= (StudentDTO) it.next();
            	 System.out.println(dto.getfirstname());
            }
            	
            List <DropDownList> dd = (List<DropDownList>) l;
         //String li= test.getlist("subjectlist", "courselist", l);
            for(DropDownList obj: dd){
            	System.out.println(obj.getKey()+":"+obj.getValue());
            }
         
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	 
	}
	
	public static void main(String[] args) {
		
		testgetlist();
		

	}

}
