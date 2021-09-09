package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.CourseModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class CourseListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="CourseListCtl" ,urlPatterns={"/ctl/CourseListCtl"})
public class CourseListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;



	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CourseDTO dto = new CourseDTO();
		dto.setId(Datautility.getLong(req.getParameter("courseId")));
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CourseModelHBI ob = (CourseModelHBI) ModelFactory.getInstance().getCourseModel(); 
		try {
			List courselist= ob.list();
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
		CourseModelINT ob =  ModelFactory.getInstance().getCourseModel();
		CourseDTO dto = new CourseDTO();
		try {
			List list= ob.search(dto, pageno, pagesize);
			Serviletutility.setpageno(pageno, request);
			Serviletutility.setpagesize(pagesize, request);
			Serviletutility.setlist(list, request);
			Serviletutility.forward(getview(), request, response);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        System.out.println("Courselist view Dopost method called");
		
		CourseModelINT umodel =  ModelFactory.getInstance().getCourseModel();
		CourseDTO dto= (CourseDTO) populateDTO(request);
		
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
			Serviletutility.redirect(ORSView.COURSE_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			pageno=1;
			System.out.println("inside delete operation condition");
			if(ids !=null && ids.length >0){
				System.out.println("inside delete operation if condition");
				CourseDTO deletedto = new CourseDTO();
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
			Serviletutility.redirect(ORSView.COURSE_LIST_CTL, request, response);
		}
		
			try {
				//System.out.println("this is college id="+ dto.getCoursename());
				//System.out.println("this is college id="+ dto.getDescription());
				//System.out.println("this is college id="+ dto.getDuration());
				 List list=umodel.search(dto,pageno,pagesize);
				 System.out.println("List="+list.size());
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
				Serviletutility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
			




	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_LIST_VIEW;
	}

}
