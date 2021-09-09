package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.RoleModelHBI;
import in.co.rays.proj3.model.UserModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

//TODO: Auto-generated Javadoc
/**
* Login functionality Controller. Performs operation for Login
* @author Iterator
* @version 1.0
*/
@WebServlet(name="LoginCtl", urlPatterns={"/LoginCtl"})
public class LoginCtl extends BaseCtl {
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("loginctl doget method called");      
		HttpSession session= request.getSession();
		String op= request.getParameter("operation");
		if(OP_LOG_OUT.equalsIgnoreCase(op)){
		 session=	request.getSession();
		 session.invalidate();
		 Serviletutility.setsuccessmessage(" Logged Out Successfully !!", request);
		 Serviletutility.forward(getview(), request, response);
			return;
		}
        Serviletutility.forward(getview(), request, response);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
		boolean pass= true;
		String op= req.getParameter("operation");
		if(OP_SIGN_UP.equalsIgnoreCase(op) || OP_LOG_OUT.equalsIgnoreCase(op)){
			return pass;
		}
		    System.out.println("loginctl validate method called");
			String login= req.getParameter("login");
			String password= req.getParameter("password");
			if(Datavalidator.isnull(login)){
				req.setAttribute("login" , Propertyreader.getvalue("error.require", "loginID"));
				System.out.println(req.getAttribute("login"));
				pass=false;
			}else if(!Datavalidator.isEmailId(login)){
				req.setAttribute("login", Propertyreader.getvalue("error.email", "LoginID"));
				pass=false;
			}
			if(Datavalidator.isnull(password)){
				req.setAttribute("password", Propertyreader.getvalue("error.require", "password"));
				pass=false;
			}else if(!Datavalidator.isPassword(password)){
				req.setAttribute("password", Propertyreader.getvalue("error.password", "password"));
				pass=false;
				
			}
	    return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(in.co.rays.proj3.dto.BaseDTO, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		 UserDTO userdto= new UserDTO();
		 userdto.setLogin(Datautility.getstring(req.getParameter("login")));
		 userdto.setPassword(Datautility.getstring(req.getParameter("password")));
		 return userdto;
	}

	 /**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("loginctl dopost called");
		HttpSession session = request.getSession(true);

		String op= request.getParameter("operation");
		String uri= request.getParameter("uri");
		if(OP_SIGN_IN.equalsIgnoreCase(op)){
			System.out.println("loginctl dopost signin condition true called");
		UserDTO dto = (UserDTO) populateDTO(request);
		System.out.println(dto.getLogin()+dto.getPassword());
		UserModelHBI usermodel = new UserModelHBI();
		RoleModelHBI rolemodel = new RoleModelHBI();
		try {
			dto=usermodel.authenticate(dto.getLogin(), dto.getPassword());
			if(dto!=null){
				session.setAttribute("user", dto);

				long id= dto.getRoleid();
				RoleDTO roledto= rolemodel.findByPK(id);
				if(roledto!=null){
					session.setAttribute("role", roledto.getName());
				}
				if(uri.equals("") || uri=="" || uri.equals(null) || "null".equalsIgnoreCase(uri) ){
					Serviletutility.forward(ORSView.WELCOME_VIEW, request, response);
				}
				else{
					Serviletutility.redirect(uri, request, response);
				}
				return;

			}
			else{
				dto= (UserDTO) populateDTO(request);
				Serviletutility.setdto(dto, request);
				Serviletutility.seterromessage(" Invalid LoginId And Password", request);
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else if(OP_SIGN_UP.equalsIgnoreCase(op)){
	    	Serviletutility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
	    	return;
	    }
		
		Serviletutility.forward(getview(), request, response);
	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
         return ORSView.LOGIN_VIEW;
	}

}
