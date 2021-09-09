package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.MarksheetModelHBI;
import in.co.rays.proj3.model.MarksheetModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelHBI;
import in.co.rays.proj3.model.StudentModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class MarksheetListCtl.
 * @author Iterator
 * @version 1.0
 */

@WebServlet(name="MarksheetListCtl",urlPatterns={"/ctl/MarksheetListCtl"})
public class MarksheetListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		StudentModelINT smodel= ModelFactory.getInstance().getStudentModel();
		try {
			List list= smodel.list();
			req.setAttribute("studentlist", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		// TODO Auto-generated method stub
		MarksheetDTO dto = new MarksheetDTO();
		   dto.setStudentid(Datautility.getInt(req.getParameter("studentId")));;
		   dto.setrollno(Datautility.getstring(req.getParameter("rollNo"))); 
		   return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  int pageno=1;
		  int pagesize= Datautility.getInt(Propertyreader.getvalue("page.size"));
	      MarksheetModelHBI model = new MarksheetModelHBI();
	      MarksheetDTO dto= (MarksheetDTO) populateDTO(request);
		  try {
			List list= model.search(dto, pageno, pagesize);
           if(list == null || list.size() == 0 ){
        	   Serviletutility.seterromessage("No Records Found", request);
           }
           Serviletutility.setlist(list, request);
           Serviletutility.setpageno(pageno, request);
           Serviletutility.setpagesize(pagesize, request);
           Serviletutility.forward(getview(), request ,response);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MarksheetModelINT model = ModelFactory.getInstance().getMarksheetModel();
	    MarksheetDTO dto= (MarksheetDTO) populateDTO(request);
		String op= request.getParameter("operation");
		String ids[]= request.getParameterValues("ids");
		int pageno= Datautility.getInt(request.getParameter("pageno"));
		int pagesize= Datautility.getInt(request.getParameter("pagesize"));
        pageno = (pageno == 0) ? 1 : pageno;
        pagesize = (pagesize == 0) ? Datautility.getInt(Propertyreader
                .getvalue("page.size")) : pagesize;
		if(OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)){
			if(OP_SEARCH.equalsIgnoreCase(op)){
				System.out.println("inside search condition"+op);
				pageno=1;
			}
			else if(OP_NEXT.equalsIgnoreCase(op)){
				System.out.println("next condition"+ pageno);
				pageno++;
			}
			else if(OP_PREVIOUS.equalsIgnoreCase(op)){
				System.out.println("previous condition"+ pageno);
				pageno--;
			}
		}
		else if(OP_NEW.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			pageno=1;
			System.out.println("inside delete operation condition");
			if(ids !=null && ids.length >0){
				System.out.println("inside delete operation if condition");
				MarksheetDTO deletedto = new MarksheetDTO();
				for(String id : ids){
					Long newid= Datautility.getLong(id);
					deletedto.setId(newid);
					try {
						model.delete(deletedto);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Serviletutility.setsuccessmessage("Data Deleated Successfully", request);;
				}
			}
			else{
				System.out.println("inside delete operation else condition");
				Serviletutility.seterromessage(" Select at least one record", request);
			}
		}
		else if(OP_BACK.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		}
		try {
			
			 List searchlist= model.search(dto,pageno,pagesize);
			 System.out.println("List="+searchlist.size());
			 if(searchlist==null){
				 Serviletutility.seterromessage("No Records found", request);
			 }
			 else{
				 Serviletutility.setlist(searchlist, request);
				 Serviletutility.setpageno(pageno, request);
				 Serviletutility.setpagesize(pagesize, request);
				Serviletutility.forward(getview(), request, response);
			 }
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			Serviletutility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		
		
	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
