package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.model.CollegeModelINT;
import in.co.rays.proj3.model.MarksheetModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class CollegeCtl.
 * @author Iterator
 * @version 1.0
 */

@WebServlet(name="CollegeCtl" ,urlPatterns={"/ctl/CollegeCtl"})
public class CollegeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	
	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest req) {
		boolean pass= true;

		if(Datavalidator.isnull(req.getParameter("cname"))){
			req.setAttribute("cname", Propertyreader.getvalue("error.require", "College Name"));
			pass= false;
		}
		if(Datavalidator.isnull(req.getParameter("address"))){
			req.setAttribute("address", Propertyreader.getvalue("error.require", "Address"));
			pass= false;
		}

		if(Datavalidator.isnull(req.getParameter("city"))){
			req.setAttribute("city", Propertyreader.getvalue("error.require", "City"));
			pass= false;
		}
		if(Datavalidator.isnull(req.getParameter("state"))){
			req.setAttribute("state", Propertyreader.getvalue("error.require", "state"));
			pass= false;
		}
		if(Datavalidator.isnull(req.getParameter("phoneno"))){
			req.setAttribute("phoneno", Propertyreader.getvalue("error.require", "Phone No"));
			pass= false;
		}
		return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		super.preload(req);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		CollegeDTO dto = new CollegeDTO();
		dto.setId(Datautility.getLong(req.getParameter("id")));
		dto.setName(req.getParameter("cname"));
		dto.setAddress(req.getParameter("address"));
		dto.setCity(req.getParameter("city"));
		dto.setState(req.getParameter("state"));
		dto.setPhoneno(req.getParameter("phoneno"));
		return dto;
		
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        System.out.println("CollegeCtl doget called");
		CollegeModelINT model = ModelFactory.getInstance().getCollegeModel();
		 String op=request.getParameter("operation");
		long id= Datautility.getLong(request.getParameter("id"));
       CollegeDTO dto;
       if(id>0 || op != null){
       	try {
				 dto=model.findByPk(id);
				 Serviletutility.setdto(dto, request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	
       }
		Serviletutility.forward(getview(), request, response);
	}



	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op= request.getParameter("operation");
		System.out.println("this is operation:" +op);
		CollegeModelINT umodel =  ModelFactory.getInstance().getCollegeModel();
		long id= Datautility.getLong(request.getParameter("id"));
		System.out.println(" this id is"+ id);
		CollegeDTO dto= (CollegeDTO) populateDTO(request);
		System.out.println("after the dto");
		if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)){
			System.out.println("inside update condition check");
			if(id > 0){
	              try {
	            	  System.out.println("inside update condition");
					umodel.update(dto);
					Serviletutility.setdto(dto, request);
					Serviletutility.setsuccessmessage("Record Successfully Updated", request);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					Serviletutility.handleException(e, request, response);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
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
					} catch (DuplicateRecordException e) {
						// TODO Auto-generated catch block
						Serviletutility.seterromessage("Error: Record Already Exist", request);;
					}
					
					}	
				
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			Serviletutility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		}
	Serviletutility.forward(getview(), request, response);
	

	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		return ORSView.COLLEGE_VIEW;
		
	}
}
