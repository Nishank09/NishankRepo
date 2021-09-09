package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.MarksheetModelHBI;
import in.co.rays.proj3.model.MarksheetModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelHBI;
import in.co.rays.proj3.model.UserModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class MarksheetCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="MarksheetCtl" ,urlPatterns={"/ctl/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;


	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
		boolean pass = true;
		req.getParameter("rollno");
		req.getParameter("studentId");
		req.getParameter("physics");
		req.getParameter("chemistry");
		req.getParameter("math");
		if(Datavalidator.isnull(req.getParameter("rollno"))){
			req.setAttribute("rollno", Propertyreader.getvalue("error.require", "Roll NO"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("studentId"))){
			req.setAttribute("list", Propertyreader.getvalue("error.require", "Student Name"));
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("physics"))){
			System.out.println("inside null condition");
			req.setAttribute("physics", Propertyreader.getvalue("error.require", "Physics No."));
			pass=false;
		}else if (Datavalidator.isnotnull(req.getParameter("physics"))&&!Datavalidator.isInteger(req.getParameter("physics"))) 
		{   
			System.out.println("inside notnull condition");
			req.setAttribute("physics", Propertyreader.getvalue("error.integer", "Marks"));
			pass = false;
		}else if (Datavalidator.isnotnull(req.getParameter("physics"))&&Datautility.getInt(req.getParameter("physics")) > 100) {
			System.out.println("inside can not be greater than 100 condition");
			req.setAttribute("physics", "Marks can not be greater than 100");
			pass = false;
		} 
		else if(Datautility.getInt(req.getParameter("physics"))< 0){
			System.out.println("inside can not be negative condition");
			req.setAttribute("physics", "Marks can not be negative");
			pass = false;
		}

		if(Datavalidator.isnull(req.getParameter("chemistry"))){
			req.setAttribute("chemistry", Propertyreader.getvalue("error.require", "chemistry"));
			pass=false;
		}else if (Datavalidator.isnotnull(req.getParameter("chemistry")) && !Datavalidator.isInteger(req.getParameter("chemistry"))) 
		{
			req.setAttribute("chemistry", Propertyreader.getvalue("error.integer", "Marks"));
			pass = false;
		}else if (Datavalidator.isnotnull(req.getParameter("chemistry"))&&Datautility.getInt(req.getParameter("chemistry")) > 100) {
			req.setAttribute("chemistry", "Marks can not be greater than 100");
			pass = false;
		} 
		else if(Datautility.getInt(req.getParameter("chemistry"))< 0){
			req.setAttribute("chemistry", "Marks can not be negative");
			pass=false;
		}
		if(Datavalidator.isnull(req.getParameter("math"))){
			req.setAttribute("math", Propertyreader.getvalue("error.require", "math"));
			pass=false;
		}else if (Datavalidator.isnotnull(req.getParameter("math"))&&!Datavalidator.isInteger(req.getParameter("math"))) 
		{
			req.setAttribute("math", Propertyreader.getvalue("error.integer", "Marks"));
			pass = false;
		}else if (Datavalidator.isnotnull(req.getParameter("math"))&&Datautility.getInt(req.getParameter("math")) > 100 ) {
			req.setAttribute("math", "Marks can not be greater than 100");
			pass = false;
		} 
		else if(Datautility.getInt(req.getParameter("math"))< 0){
			req.setAttribute("math", "Marks can not be negative");
			pass = false;
		}
		
		
		return pass;
		
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	
	
	@Override
	protected void preload(HttpServletRequest req) {
        StudentModelHBI smodel = new StudentModelHBI();
        try {
			List slist=  smodel.list();
			if( slist != null){
				req.setAttribute("slist", slist);
			}
			else{
				System.out.println("list is null");
			}
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
     MarksheetDTO dto= new MarksheetDTO();

     dto.setrollno((Datautility.getstring(req.getParameter("rollno"))));
     dto.setId(Datautility.getLong(req.getParameter("id")));
     dto.setStudentid(Datautility.getLong(req.getParameter("studentId")));;
     dto.setphysics(Datautility.getstring(req.getParameter("physics")));
     dto.setchemistry(Datautility.getstring(req.getParameter("chemistry")));
     dto.setmath(Datautility.getstring(req.getParameter("math")));
     
		return dto;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MarksheetModelHBI model = (MarksheetModelHBI) ModelFactory.getInstance().getMarksheetModel();
		 String op=request.getParameter("operation");
		long id= Datautility.getLong(request.getParameter("id"));
        MarksheetDTO dto;
        if(id>0 || op != null){
        	try {
				 dto=model.findByPK(id);
				 Serviletutility.setdto(dto, request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
		
	    Serviletutility.forward(getview(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post method called");
		String op= request.getParameter("operation");
		MarksheetModelINT umodel =  ModelFactory.getInstance().getMarksheetModel();
		long id= Datautility.getLong(request.getParameter("id"));
		System.out.println("id is"+ id);
		MarksheetDTO dto= (MarksheetDTO) populateDTO(request);
		if(OP_SAVE.equals(op) || OP_UPDATE.equalsIgnoreCase(op)){
			
			if(id>0){
	              try {
	            	  System.out.println("inside update condition");
					umodel.update(dto);
					Serviletutility.setdto(dto, request);
					Serviletutility.setsuccessmessage("Record Successfully Updated", request);
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

			Serviletutility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
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
		return ORSView.MARKSHEET_VIEW;
	}

}
