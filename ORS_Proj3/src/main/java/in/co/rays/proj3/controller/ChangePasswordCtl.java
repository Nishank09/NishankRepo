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
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.UserModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * Servlet implementation class ChangePasswordCtl
 */
@WebServlet(name="ChangePasswordCtl" ,urlPatterns={"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl {
	
	/** The Constant OP_CHANGE_MY_PROFILE. */
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";
	private static final long serialVersionUID = 1L;


    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
     */
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
        //log.debug("ChangePasswordCtl Method validate Started");

        boolean pass = true;

        String op = request.getParameter("operation");

        if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {

            return pass;
        }
        if (Datavalidator.isnull(request.getParameter("oldPassword"))) {
            request.setAttribute("oldPassword",Propertyreader.getvalue("error.require", "Old Password"));
            pass = false;
        }
        if (Datavalidator.isnull(request.getParameter("newPassword"))) {
            request.setAttribute("newPassword",
            		Propertyreader.getvalue("error.require", "New Password"));
            pass = false;
        }
        if (Datavalidator.isnull(request.getParameter("confirmPassword"))) {
            request.setAttribute("confirmPassword", Propertyreader.getvalue(
                    "error.require", "Confirm Password"));
            pass = false;
        }
        if (!request.getParameter("newPassword").equals(
                request.getParameter("confirmPassword"))
                && !"".equals(request.getParameter("confirmPassword"))) {
            Serviletutility.seterromessage(
                    "New and confirm passwords not matched", request);

            pass = false;
        }

        //log.debug("ChangePasswordCtl Method validate Ended");

        return pass;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//log.debug("ChangePasswordCtl Method populatebean Started");

        UserDTO dto = new UserDTO();

        dto.setPassword(Datautility.getstring(request.getParameter("oldPassword")));

        dto.setconfirmpassword(Datautility.getstring(request.getParameter("confirmPassword")));


        //log.debug("ChangePasswordCtl Method populatebean Ended");

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
		HttpSession session = request.getSession(true);

        //log.debug("ChangePasswordCtl Method doGet Started");

        String op = Datautility.getstring(request.getParameter("operation"));

        // get model
        UserModelINT model =ModelFactory.getInstance().getUserModel();

        UserDTO dto = (UserDTO) populateDTO(request);

        UserDTO UserBean = (UserDTO) session.getAttribute("user");

        String newPassword = (String) request.getParameter("newPassword");

        long id = UserBean.getId();

        if (OP_SAVE.equalsIgnoreCase(op)) {
            try {
                boolean flag = model.changePassword(id,dto.getPassword(),newPassword);
                if (flag == true) {
                    dto = model.findByLogin(UserBean.getLogin());
                    session.setAttribute("user",dto);
                    Serviletutility.setdto(dto, request);
                    Serviletutility.setsuccessmessage(
                            "Password has been changed Successfully.", request);
                }
            } catch (ApplicationException e) {
                //log.error(e);
                Serviletutility.handleException(e, request, response);
                return;

            } catch (RecordNotFoundException e) {
                Serviletutility.seterromessage("Old PassWord is Invalid",
                        request);
            }
        } else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
            Serviletutility.redirect(ORSView.MY_PROFILE_CTL, request, response);
            return;
        }

        Serviletutility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
       // log.debug("ChangePasswordCtl Method doGet Ended");


	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
