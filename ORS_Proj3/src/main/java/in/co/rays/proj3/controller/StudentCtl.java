package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CollegeModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelINT;
import in.co.rays.proj3.model.StudentModelHBI;
import in.co.rays.proj3.model.StudentModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class StudentCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="StudentCtl" ,urlPatterns={"/ctl/StudentCtl"})
public class StudentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		boolean pass= true;
		String fname= req.getParameter("fname");
		String lname= req.getParameter("lname");
		String email= req.getParameter("email");
		String mobileno= req.getParameter("mobileno");
		String dob= req.getParameter("dob");
		System.out.println("this is ="+ dob);
		String cname= req.getParameter("collegeid");
		if(Datavalidator.isnull(fname)){
			req.setAttribute("fname", Propertyreader.getvalue("error.require", "firstname"));
			pass=false;
		}
		if(Datavalidator.isnull(lname)){
			req.setAttribute("lname", Propertyreader.getvalue("error.require", "lastname"));
			pass=false;
		}
		if(Datavalidator.isnull(email)){
			req.setAttribute("email", Propertyreader.getvalue("error.require", "Email"));
			pass=false;
		}
		if(Datavalidator.isnull(mobileno)){
			req.setAttribute("mobileno", Propertyreader.getvalue("error.require", "MobileNo"));
			pass=false;
		}
		if(Datavalidator.isnull(dob)){
			req.setAttribute("dob", Propertyreader.getvalue("error.require", "Date of Birth"));
			pass=false;
		}
		if(Datavalidator.isnull(cname)){
			req.setAttribute("cname", Propertyreader.getvalue("error.require", "College Name"));
			pass=false;
		}
		return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CollegeModelHBI cmodel = new CollegeModelHBI();
		List list;
		try {
			list = cmodel.list();
			req.setAttribute("collegelist", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		StudentDTO dto = new StudentDTO();
        dto.setId(Datautility.getLong(req.getParameter("id")));
		dto.setcollegeid(Datautility.getLong(req.getParameter("collegeid")));
		dto.setfirstname(req.getParameter("fname"));
		dto.setlastname(req.getParameter("lname"));
		dto.setemail(req.getParameter("email"));
		dto.setmobileno(req.getParameter("mobileno"));
		dto.setdob(Datautility.getdate(req.getParameter("dob")));
		System.out.println(req.getParameter("Dateeeeeeee......................"+"dob"));
		
		return dto;
		
	}

	  /* (non-Javadoc)
  	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
  	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("studentctl doget called");
		String op= request.getParameter("operation");
		StudentModelINT ob=  ModelFactory.getInstance().getStudentModel();
		long id= Datautility.getLong(request.getParameter("id"));
		StudentDTO dto;
		if(id >0 || op != null ){
			try {
				 dto=ob.findByPK(id);
				 System.out.println("THIS IS DOB="+ dto.getdob());
				 Serviletutility.setdto(dto, request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		   Serviletutility.forward(getview(), request, response);
	}

	 /* (non-Javadoc)
  	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
  	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside Studentctl dopost method");
		String op= request.getParameter("operation");
		StudentModelINT umodel = ModelFactory.getInstance().getStudentModel();
		long id= Datautility.getLong(request.getParameter("id"));
		System.out.println("this is id="+ id);
		StudentDTO dto= (StudentDTO) populateDTO(request);
		if(OP_SAVE.equals(op) || OP_UPDATE.equalsIgnoreCase(op)){
      	  System.out.println("inside operation update check condition");
      	  System.out.println("this is id at "+id);
				if(id>0){
		              try {
		            	  System.out.println("inside id>0 condition");
		            	  System.out.println(dto.getdob());
						umodel.update(dto);
						Serviletutility.setdto(dto, request);
						Serviletutility.setsuccessmessage("Record Successfully Updated", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						Serviletutility.handleException(e, request, response);
						e.printStackTrace();
						return;
					} catch (DuplicateRecordException e) {
						Serviletutility.seterromessage("Record Already Exist", request);
						e.printStackTrace();
					}
				} 
					else{
						try {
							umodel.add(dto);
							Serviletutility.setsuccessmessage("Record Successfully Added", request);
						} catch (ApplicationException e) {
							Serviletutility.handleException(e, request, response);
							e.printStackTrace();
							return;
						} catch (DuplicateRecordException e) {
							// TODO Auto-generated catch block
							Serviletutility.seterromessage("Error: Record Already Exist", request);
							Serviletutility.setdto(dto, request);
							e.printStackTrace();
						}
						
						}	
					
			} else if (OP_CANCEL.equalsIgnoreCase(op)) {

				Serviletutility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
				return;
			}
		Serviletutility.forward(getview(), request, response);
		

		

		
	}
	 /* (non-Javadoc)
  	 * @see in.co.rays.controller.BaseCtl#getView()
  	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.STUDENT_VIEW;
	}

}
