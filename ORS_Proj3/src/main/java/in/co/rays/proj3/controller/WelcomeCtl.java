package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.util.Serviletutility;

/**
 * Welcome functionality Controller. Performs operation for Show Welcome page
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="WelcomeCtl", urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl {

	   /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       System.out.println(" Welcomectl doget method called");
		Serviletutility.forward(getview(), req, res);      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#getView()
     */
	@Override
	protected String getview() {
		System.out.println(" Welcomectl getview method called");
		return ORSView.WELCOME_VIEW;
	}

}
