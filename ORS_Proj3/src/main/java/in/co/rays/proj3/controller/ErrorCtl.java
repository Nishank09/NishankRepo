package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Serviletutility;


/**
 * Servlet implementation class ErrorCtl
 */
@WebServlet(name="ErrorCtl", urlPatterns={"/ctl/ErrorCtl"})
public class ErrorCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_BACK = "Back";


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
		System.out.println("Dopossssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		String op = Datautility.getstring(request.getParameter("operation"));
		if (OP_BACK.equalsIgnoreCase(op)) {
			Serviletutility.redirect(ORSView.WELCOME_CTL, request, response);
			return;
		}
		Serviletutility.forward(getview(), request, response);
	}

	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.ERROR_VIEW;
	}

}
