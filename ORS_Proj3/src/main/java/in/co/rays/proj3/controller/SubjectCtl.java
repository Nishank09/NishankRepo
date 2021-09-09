package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelINT;
import in.co.rays.proj3.model.SubjectModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

//TODO: Auto-generated Javadoc
/**
* The Class SubjectCtl.
* @author Iterator
* @version 1.0
*/
@WebServlet(name="SubjectCtl" ,urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;


	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
	      boolean pass= true;
	      if(Datavalidator.isnull(req.getParameter("subject"))){
	    	  req.setAttribute("subject", Propertyreader.getvalue("error.require", "Subject Name"));
	    	  pass = false;
	      }
	      if(Datavalidator.isnull(req.getParameter("description"))){
	    	  req.setAttribute("description", Propertyreader.getvalue("error.require", "Discription"));
	    	  pass = false;
	      }
	      if(Datavalidator.isnull(req.getParameter("courseid"))){
	    	  req.setAttribute("courseid", Propertyreader.getvalue("error.require", "Course"));
	    	  pass = false;
	      }
	      return pass;
	      
		}
	

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		// TODO Auto-generated method stub
		SubjectDTO dto = new SubjectDTO();
		dto.setId(Datautility.getLong(req.getParameter("id")));
		dto.setSubjectname(req.getParameter("subject"));
		dto.setDescription(req.getParameter("description"));
		dto.setCourseId(Datautility.getLong(req.getParameter("courseid")));;
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		CourseModelHBI cmodel = new CourseModelHBI();
		 try {
			List list=cmodel.list();
			System.out.println("this is list:"+ list);
			req.setAttribute("list", list);
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
		System.out.println("subjectctl doget called");
		String op= request.getParameter("operation");
		SubjectModelINT ob=  ModelFactory.getInstance().getSubjectModel();
		long id= Datautility.getLong(request.getParameter("id"));
		SubjectDTO dto;
		if(id >0 || op != null ){
			try {
				 dto=ob.findByPK(id);
				 System.out.println("THIS IS Coursse ID="+ dto.getCourseId());
				 Serviletutility.setdto(dto, request);
			} catch (ApplicationException e) {
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
		System.out.println("inside Subjectctl dopost method");
		String op= request.getParameter("operation");
		SubjectModelINT umodel = ModelFactory.getInstance().getSubjectModel();
		long id= Datautility.getLong(request.getParameter("id"));
		System.out.println("this is id="+ id);
		SubjectDTO dto= (SubjectDTO) populateDTO(request);
		if(OP_SAVE.equals(op) || OP_UPDATE.equalsIgnoreCase(op)){
      	  System.out.println("inside operation update check condition");
      	  System.out.println("this is id at "+id);
				if(id>0){
		              try {
		            	  System.out.println("This is course nameeeeeeeeeeeee="+dto.getCourseName());
						umodel.update(dto);
						
						Serviletutility.setsuccessmessage("Record Successfully Updated", request);
					} catch (ApplicationException e) {
						Serviletutility.handleException(e, request, response);
						e.printStackTrace();
						return;
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						Serviletutility.seterromessage("Record Already Exist", request);
						Serviletutility.setdto(dto, request);
						e.printStackTrace();
					}
				} 
					else{
						try {
							umodel.add(dto);
							Serviletutility.setsuccessmessage("Record Successfully Added", request);
						} catch (ApplicationException e) {
							// TODO Auto-generated catch block
							Serviletutility.handleException(e, request, response);
							e.printStackTrace();
							return;
						} catch (DuplicateRecordException e) {
							// TODO Auto-generated catch block
							Serviletutility.seterromessage("Record Already Exist", request);
							Serviletutility.setdto(dto, request);
							e.printStackTrace();
						}
						
						}	
					
			} else if (OP_CANCEL.equalsIgnoreCase(op)) {

				Serviletutility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
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
		return ORSView.SUBJECT_VIEW;
	}

}
