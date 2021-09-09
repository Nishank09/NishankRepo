package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelHBI;
import in.co.rays.proj3.model.UserModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;


/**
 * The Class UserCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

     
	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		RoleModelHBI rmodel = new RoleModelHBI();
		try {
			List list= rmodel.list();
			
			req.setAttribute("roleList", list);
			System.out.println(list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		 UserDTO dto= new UserDTO();
			dto.setId(Datautility.getLong(req.getParameter("id")));

			dto.setfirstname(Datautility.getstring(req.getParameter("firstName")));

			dto.setlastname(Datautility.getstring(req.getParameter("lastName")));

			dto.setMobileno(Datautility.getstring(req.getParameter("mobileNumber")));

			dto.setLogin(Datautility.getstring(req.getParameter("email")));

			dto.setPassword(Datautility.getstring(req.getParameter("password")));

			dto.setconfirmpassword(Datautility.getstring(req.getParameter("confirmPassword")));

			dto.setGender(Datautility.getstring(req.getParameter("gender")));

			dto.setDob(Datautility.getdate(req.getParameter("dob")));
	      /*  System.out.println(dto.getDob()+"pda");*/
			dto.setRoleid(Datautility.getLong(req.getParameter("role")));
		 
		   return dto; 
	}
	

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
		System.out.println("inside UserCtl validate method");
		boolean pass = true;
		String fname = req.getParameter("firstName");
		System.out.println("This is firstname"+fname);
		String lname = req.getParameter("lastName");
		System.out.println("This is lastname"+lname);
		String mobile = req.getParameter("mobileNumber");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmpassword");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		String role = req.getParameter("role");
		if (Datavalidator.isnull(fname)) {
			System.out.println("inside isnull fname");
			req.setAttribute("firstName", Propertyreader.getvalue("error.require", "First Name"));
			pass = false;
		}else if(!Datavalidator.isName(fname)){
			System.out.println("inside isname fname");
			req.setAttribute("firstName", "Please Enter Proper Name");
            pass=false;
		}
		if (Datavalidator.isnull(lname)) {
			System.out.println("inside isnull lname");
			req.setAttribute("lastName", Propertyreader.getvalue("error.require", "Last Name"));
			pass = false;
		}else if(!Datavalidator.isName(lname)){
			System.out.println("inside isname lname");
			req.setAttribute("lastname", "Please Enter Proper Name");
			pass=false;
		}
		if (Datavalidator.isnull(mobile)) {
			System.out.println("inside isnull mobile");
			req.setAttribute("mobileNumber", Propertyreader.getvalue("error.require", "Mobile Number"));
			pass = false;
		}
			
		
		if (Datavalidator.isnull(email)) {
			System.out.println("inside isnull email");
			req.setAttribute("email", Propertyreader.getvalue("error.require", "Email"));
			pass = false;
		} else if (!Datavalidator.isEmailId(email)) {
			System.out.println("inside isemail email");
			req.setAttribute("email", Propertyreader.getvalue("error.email", "Email"));
		}
		if (Datavalidator.isnull(password)) {
			System.out.println("inside isnull password");
			req.setAttribute("password", Propertyreader.getvalue("error.require", "Password"));
			pass = false;
		}else if (!Datavalidator.isPassword(password)) {
			System.out.println("inside ispassword");
			req.setAttribute("password", "Password should contain 8 character");
			pass = false;
		}else if (!Datavalidator.isPassword(password)) {
			System.out.println("inside ispassword second error mas");
			req.setAttribute("password", "Password Contain upper case,lower case,number and special char. ");
			pass = false;
		}
		if (Datavalidator.isnull(confirmPassword)) {
			System.out.println("inside confirmpassword is null");
			req.setAttribute("confirmPassword", Propertyreader.getvalue("error.require", "confirmPassword"));
			pass = false;
		}
		if (Datavalidator.isnull(gender)) {
			System.out.println("inside gender is null");
			req.setAttribute("gender", Propertyreader.getvalue("error.require", "Gender"));
			pass = false;
		}
		if (Datavalidator.isnull(dob)) {
			System.out.println("inside dob is null");
			req.setAttribute("dob", Propertyreader.getvalue("error.require", "Date of Birth"));
			pass = false;
		}else if (Datavalidator.isValidAge(dob)) {
			System.out.println("inside valid age");
			req.setAttribute("dob", "Age Must Be Above 18");
			pass = false;
		}
		if (Datavalidator.isnull(role)) {
			System.out.println("inside role is null");
			req.setAttribute("role", Propertyreader.getvalue("error.require", "Role"));
			pass = false;
		}

		if (!password.equals(confirmPassword) && !"".equals(confirmPassword)) {
			System.out.println("inside password match with c p");
			Serviletutility.seterromessage("Confirm  Password  should be matched.", req);

			pass = false;
		}
        
		return pass;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op= request.getParameter("operation");
		UserModelHBI ob= (UserModelHBI) ModelFactory.getInstance().getUserModel();
		long id= Datautility.getLong(request.getParameter("id"));
		UserDTO dto;
		if(id >0 || op != null ){
			try {
				 dto=ob.findbypk(id);
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
		System.out.println("inside userctl dopost method");
		String op= request.getParameter("operation");
		UserModelHBI umodel = (UserModelHBI) ModelFactory.getInstance().getUserModel();
		long id= Datautility.getLong(request.getParameter("id"));
		UserDTO dto= (UserDTO) populateDTO(request);
		if(OP_SAVE.equals(op) || OP_UPDATE.equalsIgnoreCase(op)){
		
				if(id>0){
		              try {
						umodel.update(dto);
						Serviletutility.setdto(dto, request);
						Serviletutility.setsuccessmessage("Record Successfully Updated", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						Serviletutility.handleException(e, request, response);
						e.printStackTrace();
						return;
					} catch (DuplicateRecordException e) {
						Serviletutility.seterromessage("Record already exist", request);
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
							Serviletutility.seterromessage("Record already exist", request);
							Serviletutility.setdto(dto, request);
							e.printStackTrace();
						}
						
						}	
					
			} else if (OP_CANCEL.equalsIgnoreCase(op)) {

				Serviletutility.redirect(ORSView.USER_LIST_CTL, request, response);
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
		return ORSView.USER_VIEW;
	}

}
