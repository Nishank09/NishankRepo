package in.co.rays.proj3.testmodel;


import java.util.Iterator;
import java.util.List;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.CollegeModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelHBI;

public class TestRoleModelHBI {

	public static void testadd()
	{
		RoleDTO dto= new RoleDTO();
	    
		dto.setName("TEST");
		dto.setDiscription("test access");
        RoleModelHBI obj = (RoleModelHBI) ModelFactory.getInstance().getRoleModel();
        try {
			obj.add(dto);
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			System.out.println("exception to add object");
			e.printStackTrace();
		} 
	}
	public static void testdelete()
	{
		RoleDTO dto= new RoleDTO();
		dto.setId(2);
        RoleModelHBI obj1 = new RoleModelHBI();
        try {
			obj1.delete(dto);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testupdate()
	{
		RoleDTO dto= new RoleDTO();
		dto.setId(4);
		dto.setName("Feculty");
		dto.setDiscription("discription updated");
        RoleModelHBI obj1 = new RoleModelHBI();
        
			try {
				try {
					obj1.update(dto);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void testfindbypk()
	{

		RoleModelHBI obj1 = new RoleModelHBI();
        int i =1;
			try {
				 RoleDTO dto =obj1.findByPK(i);
				 System.out.println(dto.getName());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void testfindbyName()
	{

		RoleModelHBI obj1 = new RoleModelHBI();
        String name ="Admin";
			try {
				 RoleDTO dto =obj1.findByName(name);
				 System.out.println(dto.getName());
				 System.out.println(dto.getDiscription());
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void testsearch()
	{
		RoleModelHBI obj = new RoleModelHBI();
		RoleDTO dto= new RoleDTO();
         dto.setName("Admin");;
        int i =1;
			try {
				  List list = obj.search(dto,0,10);
				  Iterator it= list.iterator();
				  while(it.hasNext()){
					dto=  (RoleDTO) it.next();
					 System.out.println(dto.getName());
				  }

			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		
		//testadd();
        //testdelete();
		testupdate();
		//testfindbypk();
		//testfindbyName();
		//testsearch();
	}

}
