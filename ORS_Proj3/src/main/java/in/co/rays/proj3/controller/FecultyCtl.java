package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.FecultyDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.CollegeModelINT;
import in.co.rays.proj3.model.CourseModelINT;
import in.co.rays.proj3.model.FecultyModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.SubjectModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

//TODO: Auto-generated Javadoc
/**
* The Class FacultyCtl.
* @author Iterator
* @version 1.0
*/
@WebServlet(name="FecultyCtl" ,urlPatterns={"/ctl/FecultyCtl"})
public class FecultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;


	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	
	
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CourseModelINT smo= ModelFactory.getInstance().getCourseModel();
		CollegeModelINT cmo= ModelFactory.getInstance().getCollegeModel();
		try {
			List list= smo.list();
			List list2 =cmo.list();
			req.setAttribute("list", list);
			req.setAttribute("list2", list2);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
		boolean pass= true;
		String cname= req.getParameter("CourseList");
		if(Datavalidator.isnull(req.getParameter("fname"))){
			req.setAttribute("fname", Propertyreader.getvalue("error.require", "Firstname"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("lname"))){
			req.setAttribute("lname", Propertyreader.getvalue("error.require", "lastname"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("gender"))){
			req.setAttribute("gender", Propertyreader.getvalue("error.require", "gender"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("dateofjoining"))){
			req.setAttribute("dateofjoining", Propertyreader.getvalue("error.require", "Date of Joining"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("collegelist"))){
			req.setAttribute("college", Propertyreader.getvalue("error.require", "college"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("email"))){
			req.setAttribute("email", Propertyreader.getvalue("error.require", "Email"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("mobileno"))){
			req.setAttribute("mobileno", Propertyreader.getvalue("error.require", "MobileNo"));
			pass=false;
		}
		
		if(Datavalidator.isnull(req.getParameter("CourseList"))){
			req.setAttribute("CourseList", Propertyreader.getvalue("error.require", "Course Name"));
			pass=false;
		}
		return pass;
	}
		

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		// TODO Auto-generated method stub
		FecultyDTO dto = new FecultyDTO();
		dto.setId(Datautility.getLong(req.getParameter("id")));
		dto.setFirstName(req.getParameter("fname"));
		dto.setLastName(req.getParameter("lname"));
		dto.setGender(req.getParameter("gender"));
		dto.setJoiningdate(Datautility.getdate(req.getParameter("dateofjoining")));
		dto.setCollegeId(Datautility.getInt(req.getParameter("collegelist")));;
		dto.setEmail(req.getParameter("email"));
		dto.setMobileNo(req.getParameter("mobileno"));
		dto.setCourseId(Datautility.getInt(req.getParameter("CourseList")));
		return dto;
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("FecultyCtl doget called");
		FecultyModelINT model = ModelFactory.getInstance().getFacultyModel();
		 String op=request.getParameter("operation");
		long id= Datautility.getLong(request.getParameter("id"));
       FecultyDTO dto;
       if(id>0 || op != null){
    	   try {
			dto=model.findByPK(id);
			Serviletutility.setdto(dto, request);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
       	
       }
          Serviletutility.forward(getview(), request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op= request.getParameter("operation");
		long id= Datautility.getLong(request.getParameter("id"));
		FecultyDTO dto= (FecultyDTO) populateDTO(request);
		FecultyModelINT model= ModelFactory.getInstance().getFacultyModel();
		if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)){
			try {
				if(id>0){
					model.update(dto);
					Serviletutility.setdto(dto, request);
					Serviletutility.setsuccessmessage("Record Successfully Updated", request);
				}else{
					model.add(dto);
					Serviletutility.setsuccessmessage("Record Successfully Added", request);
				}
				
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				Serviletutility.seterromessage("Record Already Exist", request);
				Serviletutility.setdto(dto, request);
				e.printStackTrace();
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				Serviletutility.handleException(e, request, response);
				e.printStackTrace();
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {

			Serviletutility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		Serviletutility.forward(getview(), request, response);
	}

	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_VIEW;
	}

}
