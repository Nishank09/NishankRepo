package in.co.rays.proj3.controller;
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

//TODO: Auto-generated Javadoc
/**
* The Class ForgetPasswordCtl.
* @author Iterator
* @version 1.0
*/
@WebServlet(name="ForgetPasswordCtl" ,urlPatterns={"/ForgetPasswordCtl"})
public class ForgetPasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 //log.debug("ForgetPasswordCtl Method validate Started");

	        boolean pass = true;

	        String login = request.getParameter("login");

	        if (Datavalidator.isnull(login)) {
	            request.setAttribute("login",Propertyreader.getvalue("error.require", "Email Id"));
	            pass = false;
	        } else if (!Datavalidator.isEmailId(login)) {
	            request.setAttribute("login",
	                    Propertyreader.getvalue("error.email", "Login "));
	            pass = false;
	        }
	        //log.debug("ForgetPasswordCtl Method validate Ended");

	        return pass;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//log.debug("ForgetPasswordCtl Method populatebean Started");

        UserDTO dto = new UserDTO();

        dto.setLogin(Datautility.getstring(request.getParameter("login")));

        //log.debug("ForgetPasswordCtl Method populatebean Ended");

        return dto;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Serviletutility.forward(getview(), request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//log.debug("ForgetPasswordCtl Method doPost Started");

        String op = Datautility.getstring(request.getParameter("operation"));

        UserDTO dto = (UserDTO) populateDTO(request);

        // get model
        UserModelINT model=ModelFactory.getInstance().getUserModel();

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
                model.forgetPassword(dto.getLogin());
                Serviletutility.setsuccessmessage(
                        "Password has been sent to your email id.", request);
            } catch (ApplicationException e) {
               // log.error(e);
                Serviletutility.handleException(e, request, response);
                return;
            }
            
Serviletutility.setdto(dto, request);
            Serviletutility.forward(getview(), request, response);
        }

        //log.debug("ForgetPasswordCtl Method doPost Ended");

	

	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.FORGET_PASSWORD_VIEW;
	}



}
