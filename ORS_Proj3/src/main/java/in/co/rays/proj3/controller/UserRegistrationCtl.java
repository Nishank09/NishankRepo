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
import in.co.rays.proj3.model.UserModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class UserRegistrationCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="UserRegistrationCtl" ,urlPatterns={"/UserRegistrationCtl"})
public class UserRegistrationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	/** The Constant OP_SIGN_UP. */
	public static final String OP_SIGN_UP="SignUp";


	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean pass=true;
		String fname= request.getParameter("firstName");
		String lname=request.getParameter("lastName");
		String mobile=request.getParameter("mobileNumber");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		if(Datavalidator.isnull(fname)){
			request.setAttribute("firstName",Propertyreader.getvalue("error.require", "First Name"));
		pass=false;
		}else if (Datavalidator.isnotnull(fname)&&!Datavalidator.isName(fname)){
			request.setAttribute("firstName","please enter correct name");
			pass=false;
		}
		
		
		
		
		if(Datavalidator.isnull(lname)){
			request.setAttribute("lastName",Propertyreader.getvalue("error.require", "Last Name"));
			pass=false;
		}else if(Datavalidator.isnotnull(lname)&&!Datavalidator.isName(lname)){
			request.setAttribute("lastName","please enter correct name");
			pass=false;
		}
		
		
		
		
		
		
		if(Datavalidator.isnull(mobile)){
			request.setAttribute("mobileNumber",Propertyreader.getvalue("error.require", "Mobile Number"));
			pass=false;
		}/*else if (Datavalidator.isnotnull(mobile)&&!Datavalidator.isPhoneNo(mobile)) {
			request.setAttribute("mobileNumber", "please enter valid MobileNumber");
			pass=false;
		}*/
		
		
		
		
		
		if(Datavalidator.isnull(email)){
			request.setAttribute("email",Propertyreader.getvalue("error.require", "Email"));
			pass=false;
		}else  if(!Datavalidator.isEmailId(email)){
			request.setAttribute("email", Propertyreader.getvalue("error.email", "Email"));
		}

		
		
		if(Datavalidator.isnull(password)){
			request.setAttribute("password",Propertyreader.getvalue("error.require", "Password"));
			pass=false;
		}else if (!Datavalidator.isPassword(password)) {
			request.setAttribute("password","Password contain Upper case,lower case and special character");
			pass=false;
		}
		
		
		
		
		if(Datavalidator.isnull(confirmPassword)){
			request.setAttribute("confirmPassword",Propertyreader.getvalue("error.require", "ConfirmPassword"));
			pass=false;
		}
		
		if(Datavalidator.isnull(gender)){
			request.setAttribute("gender",Propertyreader.getvalue("error.require", "Gender"));
			pass=false;
		}
		
		
		if(Datavalidator.isnull(dob)){
			request.setAttribute("dob",Propertyreader.getvalue("error.require", "Date of Birth"));
			pass=false;
		}/*else if(Datavalidator.isnotnull(dob)&&!Datavalidator.isValidAge(dob)){
			request.setAttribute("dob","age must be above 18");
			pass=false;
			
		}*/
	    
		
		if (!password.equals(confirmPassword)
	            && !"".equals(confirmPassword)) {
	        Serviletutility.seterromessage("Confirm  Password  should not be matched.", request);

	        pass = false;
	    }
	    //log.debug("UserRegistrationCtl Method validate Ended");

	    return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// TODO Auto-generated method stub
		UserDTO dto=new UserDTO();
		String fname= request.getParameter("firstName");
		String lname=request.getParameter("lastName");
		String mobile=request.getParameter("mobileNumber");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
	    
		dto.setId(Datautility.getLong(request.getParameter("id")));
		dto.setRoleid(RoleDTO.Student);
		dto.setfirstname(Datautility.getstring(fname));
		dto.setlastname(Datautility.getstring(lname));
		dto.setMobileno(Datautility.getstring(mobile));
		dto.setLogin(Datautility.getstring(email));
		dto.setPassword(Datautility.getstring(password));
		dto.setconfirmpassword(Datautility.getstring(confirmPassword));
		dto.setGender(Datautility.getstring(gender));
		dto.setDob(Datautility.getdate(dob));
		return dto;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Serviletutility.forward(getview(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=Datautility.getstring(request.getParameter("operation"));
		   UserModelINT model=ModelFactory.getInstance().getUserModel();
		   long id=Datautility.getLong(request.getParameter("id"));
		   if(OP_SIGN_UP.equalsIgnoreCase(op)){
			   UserDTO dto=(UserDTO)populateDTO(request);
			   try {
	          long pk=model.registerUser(dto);
	          dto.setId(pk);
	          request.getSession().setAttribute("UserDTO",dto);
	          Serviletutility.redirect(ORSView.LOGIN_CTL, request, response);
	          return;
			} catch (ApplicationException e) {
	          //log.error(e);
	          Serviletutility.handleException(e, request, response);
	          return;
			} catch (DuplicateRecordException e) {
	          //log.error(e);
	          Serviletutility.setdto(dto, request);
	          Serviletutility.seterromessage("User already Exist", request);
	          Serviletutility.forward(ORSView.USER_REGISTRATION_VIEW, request, response);
	          return;
	}
		   }else if(OP_RESET.equalsIgnoreCase(op)){
	       	Serviletutility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
	        return;
	    }
	   // log.debug("UserRegistrationCtl Method doPost Ended");

	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.USER_REGISTRATION_VIEW;
	}

}
