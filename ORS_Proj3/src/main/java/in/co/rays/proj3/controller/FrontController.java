package in.co.rays.proj3.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class FrontController.
 * @author Iterator
 * @version 1.0
 */
@WebFilter(urlPatterns={"/ctl/*","/doc/*"})
public class FrontController implements Filter {
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	  * @see javax.servlet.Filter#destroy()
	  */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	 /* (non-Javadoc)
	  * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	  */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
	     HttpServletRequest request = (HttpServletRequest) req;
	     HttpServletResponse response = (HttpServletResponse) resp;
	     String uri = request.getRequestURI();
	     request.setAttribute("uri", uri);
	     HttpSession session = request.getSession(true);
	     if (session.getAttribute("user") == null) {
	         request.setAttribute("error",
	                 "Your session has been expired. Please re-Login.");
	         Serviletutility.forward(ORSView.LOGIN_VIEW, request, response);
	         return;
	     } else {
	         chain.doFilter(req, resp);
	     }

		
	}
	
	
	/* (non-Javadoc)
 	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
 	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
       


}
