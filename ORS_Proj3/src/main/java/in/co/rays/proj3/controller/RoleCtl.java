package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelINT;
import in.co.rays.proj3.model.UserModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class RoleCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="RoleCtl" ,urlPatterns={"/ctl/RoleCtl"})
public class RoleCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;



	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
      boolean pass= true;
      if(Datavalidator.isnull(req.getParameter("role"))){
    	  req.setAttribute("role", Propertyreader.getvalue("error.require", "Role"));
    	  pass = false;
      }
      if(Datavalidator.isnull(req.getParameter("discription"))){
    	  req.setAttribute("discription", Propertyreader.getvalue("error.require", "Discription"));
    	  pass = false;
      }
      return pass;
      
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
          RoleDTO dto = new RoleDTO();
          dto.setId(Datautility.getLong(req.getParameter("id")));
          dto.setName(req.getParameter("role"));
          dto.setDiscription(req.getParameter("discription"));
          return dto;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("rolectl doget called");
		String op= request.getParameter("operation");
		RoleModelINT ob=  ModelFactory.getInstance().getRoleModel();
		long id= Datautility.getLong(request.getParameter("id"));
		RoleDTO dto;
		if(id >0 || op != null ){
			try {
				 dto=ob.findByPK(id);
				 System.out.println("THIS IS DESCRIPTION="+ dto.getDiscription());
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
		System.out.println("inside Rolectl dopost method");
		String op= request.getParameter("operation");
		RoleModelINT umodel = ModelFactory.getInstance().getRoleModel();
		long id= Datautility.getLong(request.getParameter("id"));
		System.out.println("this is id="+ id);
		RoleDTO dto= (RoleDTO) populateDTO(request);
		if(OP_SAVE.equals(op) || OP_UPDATE.equalsIgnoreCase(op)){
      	  System.out.println("inside operation update check condition");
      	  System.out.println("this is id at "+id);
				if(id>0){
		              try {
		            	  System.out.println("inside id>0 condition");
						umodel.update(dto);
						Serviletutility.setdto(dto, request);
						Serviletutility.setsuccessmessage("Record Successfully Updated", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						Serviletutility.handleException(e, request, response);
						e.printStackTrace();
						return;
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						Serviletutility.seterromessage("", request);
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
							Serviletutility.setdto(dto, request);
							Serviletutility.seterromessage("Role already exists", request);
							e.printStackTrace();
						}
						
						}	
					
			} else if (OP_CANCEL.equalsIgnoreCase(op)) {

				Serviletutility.redirect(ORSView.ROLE_LIST_CTL, request, response);
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
		return ORSView.ROLE_VIEW;
	}

}
