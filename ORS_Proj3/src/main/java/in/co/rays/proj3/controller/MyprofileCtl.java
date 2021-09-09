package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj3.dto.BaseDTO;
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
 * The Class MyProfileCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="MyprofileCtl" ,urlPatterns={"/ctl/MyprofileCtl"})
public class MyprofileCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	/** The Constant OP_CHANGE_MY_PASSWORD. */
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword" ;


	
	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		boolean pass = true;

        String op = Datautility.getstring(request.getParameter("operation"));

        if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

            return pass;
        }
        if (Datavalidator.isnull(request.getParameter("email"))) {
            System.out.println("email" + request.getParameter("email"));
            request.setAttribute("email",
                    Propertyreader.getvalue("error.require", "Email"));
            pass = false;
        }

        if (Datavalidator.isnull(request.getParameter("firstName"))) {
            System.out.println("firstName" + request.getParameter("firstName"));
            request.setAttribute("firstName",
                    Propertyreader.getvalue("error.require", "First Name"));
            pass = false;
        }

        if (Datavalidator.isnull(request.getParameter("lastName"))) {
            request.setAttribute("lastName",
            		Propertyreader.getvalue("error.require", "Last Name"));
            pass = false;
        }

        if (Datavalidator.isnull(request.getParameter("gender"))) {
            request.setAttribute("gender",
            		Propertyreader.getvalue("error.require", "Gender"));
            pass = false;
        }
       
        if (Datavalidator.isnull(request.getParameter("mobileNo"))) {
            request.setAttribute("mobileNo",
            		Propertyreader.getvalue("error.require", "MobileNo"));
            pass = false;
        }

        if (Datavalidator.isnull(request.getParameter("dob"))) {
            request.setAttribute("dob",
            		Propertyreader.getvalue("error.require", "Date Of Birth"));
            pass = false;
        }

        //log.debug("MyProfileCtl Method validate Ended");

        return pass;


	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 UserDTO dto = new UserDTO();

	        dto.setId(Datautility.getLong(request.getParameter("id")));
	        dto.setLogin(Datautility.getstring(request.getParameter("login")));
	        dto.setfirstname(Datautility.getstring(request.getParameter("firstName")));
	        dto.setlastname(Datautility.getstring(request.getParameter("lastName")));
	        dto.setMobileno(Datautility.getstring(request.getParameter("mobileNo")));
	        dto.setGender(Datautility.getstring(request.getParameter("gender")));
	        dto.setDob(Datautility.getdate(request.getParameter("dob")));
//			dto.setRoleId(DataUtility.getLong(request.getParameter("role")));

	        return dto;

	}

	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
        //log.debug("MyprofileCtl Method doGet Started");

        UserDTO UserBean = (UserDTO) session.getAttribute("user");
        long id = UserBean.getId();
        System.out.println("thisssssssssssssssss is ID"+ id );
        String op = Datautility.getstring(request.getParameter("operation"));

        // get model
        UserModelINT model =ModelFactory.getInstance().getUserModel();
        if (id > 0 || op != null) {
            System.out.println("in id > 0  condition");
            UserDTO dto;
            try {
                dto = model.findbypk(id);
                Serviletutility.setdto(dto, request);
            } catch (ApplicationException e) {
                //log.error(e);
                Serviletutility.handleException(e, request, response);
                return;
            }
        }

		Serviletutility.forward(getview(), request, response);
	}

	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
        //log.debug("MyprofileCtl Method doPost Started");

        UserDTO UserBean = (UserDTO) session.getAttribute("user");
        long id = UserBean.getId();
        String op = Datautility.getstring(request.getParameter("operation"));

        // get model
        UserModelINT model = ModelFactory.getInstance().getUserModel();

        if (OP_SAVE.equalsIgnoreCase(op)) {
            UserDTO dto = (UserDTO) populateDTO(request);
            try {
                if (id > 0) {
                    UserBean.setfirstname(dto.getfirstname());
                    UserBean.setlastname(dto.getlastname());
                    UserBean.setGender(dto.getGender());
                    UserBean.setMobileno(dto.getMobileno());
                    UserBean.setDob(dto.getDob());
                    
                    model.update(UserBean);

                }
                Serviletutility.setdto(dto, request);
                Serviletutility.setsuccessmessage(
                        "Profile has been updated Successfully. ", request);
            } catch (ApplicationException e) {
                //log.error(e);
                Serviletutility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
            	Serviletutility.setdto(dto, request);
            	Serviletutility.seterromessage("Login id already exists",
                        request);
            }
        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

        	Serviletutility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,response);
            return;

        }

        Serviletutility.forward(getview(), request, response);

        //log.debug("MyProfileCtl Method doPost Ended");


	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.MY_PROFILE_VIEW;
	}

}
