package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.FecultyDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.CollegeModelHBI;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.FecultyModelHBI;
import in.co.rays.proj3.model.FecultyModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class FacultyListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="FacultyListCtl" ,urlPatterns={"/ctl/FacultyListCtl"})
public class FacultyListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

 

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	FecultyDTO dto=new FecultyDTO();
   	 dto.setCourseId(Datautility.getLong(request.getParameter("course")));
   	 return dto;
	}


	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		CollegeModelHBI a = (CollegeModelHBI) ModelFactory.getInstance().getCollegeModel();
		CourseModelHBI b = (CourseModelHBI) ModelFactory.getInstance().getCourseModel();
		try {
			List collegelist =a.list();
			List courselist  =b.list();
			req.setAttribute("collegelist", collegelist);
			req.setAttribute("courselist", courselist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageno=1;
		int pagesize= Datautility.getInt(Propertyreader.getvalue("page.size"));
		FecultyModelINT model = ModelFactory.getInstance().getFacultyModel(); 
		FecultyDTO dto = (FecultyDTO) populateDTO(request);
		try {
			List list= model.search(dto, pageno, pagesize);
			if(list ==null || list.size()==0){
				Serviletutility.seterromessage("No Records Found", request);
			}
			Serviletutility.setpageno(pageno, request);
			Serviletutility.setpagesize(pagesize, request);
			Serviletutility.setlist(list, request);
			Serviletutility.forward(getview(), request, response);
		}
		catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.println("Fecultylist view Dopost method called");
		
		FecultyModelINT umodel =  ModelFactory.getInstance().getFacultyModel();
		FecultyDTO dto= (FecultyDTO) populateDTO(request);
		
		String op= request.getParameter("operation");
		String ids[]= request.getParameterValues("ids");
		int pageno= Datautility.getInt(request.getParameter("pageno"));
		int pagesize= Datautility.getInt(request.getParameter("pagesize"));
        pageno = (pageno == 0) ? 1 : pageno;
        pagesize = (pagesize == 0) ? Datautility.getInt(Propertyreader
                .getvalue("page.size")) : pagesize;
		if(OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)){
			if(OP_SEARCH.equalsIgnoreCase(op)){
				pageno=1;
			}
			else if(OP_NEXT.equalsIgnoreCase(op)){
				pageno++;
			}
			else if(OP_PREVIOUS.equalsIgnoreCase(op)){
				pageno--;
			}
		}
		else if(OP_NEW.equalsIgnoreCase(op)){
			System.out.println("inside new operation condition");
			Serviletutility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			pageno=1;
			System.out.println("inside delete operation condition");
			if(ids !=null && ids.length >0){
				System.out.println("inside delete operation if condition");
				FecultyDTO deletedto = new FecultyDTO();
				for(String id : ids){
					Long newid= Datautility.getLong(id);
					deletedto.setId(newid);
					try {
						umodel.delete(deletedto);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Serviletutility.setsuccessmessage("Data Deleated Successfully", request);;
				}
			}
			else{
				System.out.println("inside delete operation else condition");
				Serviletutility.seterromessage(" Select at least one record", request);
			}
		}
		else if(OP_BACK.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		
			try {
/*				 System.out.println("This is feculty id = "+ dto.getId());
				 System.out.println("This is College id = "+ dto.getCollegeId());
				 System.out.println("This is course id = "+ dto.getCourseId());*/
				 List list=umodel.search(dto,pageno,pagesize);
				 Serviletutility.setdto(dto, request);
				 
				 if(list==null){
					 Serviletutility.seterromessage("No Records found", request);
				 }
				 else{
					 Serviletutility.setlist(list, request);
					 Serviletutility.setpageno(pageno, request);
					 Serviletutility.setpagesize(pagesize, request);
					 Serviletutility.forward(getview(), request, response);
				 }
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				System.out.println("getting exception while getting list");
				e.printStackTrace();
			}
			



	}



	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_LIST_VIEW;
	}

}
