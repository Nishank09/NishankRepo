package in.co.rays.proj3.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.controller.BaseCtl;
import in.co.rays.proj3.controller.ORSView;
import in.co.rays.proj3.dto.BaseDTO;


/**
 * The Class ServletUtility.
 * 
 * @author Iterator
 * @version 1.0
 */
public class Serviletutility {

	
	/**
	 * Forward to given JSP/Servlet.
	 *
	 * @param page the page
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public static void forward(String page, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		RequestDispatcher rd= req.getRequestDispatcher(page);
		rd.forward(req, res);
	}
	
	/**
     * Redirect to given JSP/Servlet.
     *
     * @param page the page
     * @param request the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
	public static void redirect(String page,HttpServletRequest req, HttpServletResponse res) throws IOException{
		res.sendRedirect(page);
		
		
	}
	
	/**
     * Sets success message to request.
     *
     * @param msg the msg
     * @param request the request
     */
	public static void setsuccessmessage(String message,HttpServletRequest request){
		
		request.setAttribute("message",message);
	}
	
	/**
     * Gets success message from request.
     *
     * @param request the request
     * @return the success message
     */
	public static String  getsuccessmessage(HttpServletRequest request){
		
		String message=(String) request.getAttribute("message");
		if(message==null){
			return "";
		}
		else{
			return message;
		}
	}
	
	/**
     * Sets default DTO to request.
     *
     * @param dto the dto
     * @param request the request
     */
	public static void setdto(BaseDTO dto, HttpServletRequest request){
		request.setAttribute("dto", dto);
	}
	
	/**
     * Sets error message to request.
     *
     * @param msg the msg
     * @param request the request
     */
	public static void seterromessage(String message, HttpServletRequest request) {
		request.setAttribute(BaseCtl.MSG_ERROR, message);
		
	}
	
	/**
     * Gets error message from request.
     *
     * @param property the property
     * @param request the request
     * @return the error message
     */
	public static String geterrormessage(String key,HttpServletRequest request ){
		String message= (String) request.getAttribute(key);
		if (message==null){
			return "";
		}
		else{
			return message;
		}
		
	}
	
	/**
     * Gets error message from request.
     *
     * @param request the request
     * @return the error message
     */
	public static String geterrormessage(HttpServletRequest request ){
		String message= (String) request.getAttribute(BaseCtl.MSG_ERROR);
		if (message==null){
			return "";
		}
		else{
			return message;
		}
	}
	
	 /**
     * Sets default List to request.
     *
     * @param list the list
     * @param request the request
     */
	public static void setlist(List list, HttpServletRequest request){
		request.setAttribute("list", list);
	}
	
	/**
     * Gets default list from request.
     *
     * @param request the request
     * @return the list
     */
	public static List getlist(HttpServletRequest request){
		List list= (List) request.getAttribute("list");
		return list;
	}
	
	/**
     * Sets Page Number for List pages.
     *
     * @param pageNo the page no
     * @param request the request
     */
	public static void setpageno(int pageno, HttpServletRequest request){
		request.setAttribute("pageno", pageno);
	}
	
	  /**
     * Gets Page Number for List pages.
     *
     * @param request the request
     * @return the page no
     */
	public static int getpageno(HttpServletRequest request){
		
		return (Integer) request.getAttribute("pageno");
		
	}
	
	/**
     * Sets Page Size for list pages.
     *
     * @param pageSize the page size
     * @param request the request
     */
	public static void setpagesize(int pagesize, HttpServletRequest request){
		request.setAttribute("pagesize", pagesize);
	}
	
	 /**
     * Gets Page Size for List pages.
     *
     * @param request the request
     * @return the page size
     */
	public static int getpagesize(HttpServletRequest request){
		
		return (Integer) request.getAttribute("pagesize");
		
	}
	
	/**
     * Get request parameter to display. If value is null then return empty
     * string
     *
     * @param property the property
     * @param request the request
     * @return the parameter
     */
	public static String getparameter(String property,
            HttpServletRequest request){
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
	}
    /**
     * Redirect to Application Error Handler Page.
     *
     * @param e the e
     * @param request the request
     * @param response the response
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    public static void handleException(Exception e, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("exception", e);
        response.sendRedirect(ORSView.ERROR_CTL);

    }


	public static void main(String[] args) {

	}
}
