package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelHBI;
import in.co.rays.proj3.model.RoleModelINT;
import in.co.rays.proj3.model.UserModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class RoleListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="RoleListCtl",urlPatterns={"/ctl/RoleListCtl"})
public class RoleListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		RoleModelHBI model = new RoleModelHBI();
		try {
			List list= model.list();
			if(list==null || list.size() == 0){
				Serviletutility.seterromessage("No Record Found", req);
				}
			req.setAttribute("rolelist", list);
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
		RoleDTO dto = new RoleDTO();
		dto.setId(Datautility.getLong(req.getParameter("roleid")));
		return dto;
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageno=1;
		RoleDTO dto = new RoleDTO();
		RoleModelHBI rmodel = new RoleModelHBI();
		int pagesize= Datautility.getInt(Propertyreader.getvalue("page.size"));
		try {
			List list = rmodel.search(dto, pageno, pagesize);
			if(list ==null || list.size()==0){
				Serviletutility.seterromessage("No Records Found", request);
			  }
			Serviletutility.setpageno(pageno, request);
			Serviletutility.setpagesize(pagesize, request);
			Serviletutility.setlist(list, request);
			Serviletutility.forward(getview(), request, response);
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Rolelist view Dopost method called");
		
		RoleModelINT umodel =  ModelFactory.getInstance().getRoleModel();
		RoleDTO dto= (RoleDTO) populateDTO(request);
		
		String op= request.getParameter("operation");
		String ids[]= request.getParameterValues("ids");
		int pageno= Datautility.getInt(request.getParameter("pageno"));
		int pagesize= Datautility.getInt(request.getParameter("pagesize"));
        pageno = (pageno == 0) ? 1 : pageno;
        pagesize = (pagesize == 0) ? Datautility.getInt(Propertyreader
                .getvalue("page.size")) : pagesize;
		if(OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)){
			if(OP_SEARCH.equalsIgnoreCase(op)){
				pageno=1;
			}
			else if(OP_NEXT.equalsIgnoreCase(op)){
				pageno++;
			}
			else if(OP_PREVIOUS.equalsIgnoreCase(op)){
				pageno--;
			}
		}
		else if(OP_NEW.equalsIgnoreCase(op)){
			System.out.println("inside new operation condition");
			Serviletutility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			pageno=1;
			System.out.println("inside delete operation condition");
			if(ids !=null && ids.length >0){
				System.out.println("inside delete operation if condition");
				RoleDTO deletedto = new RoleDTO();
				for(String id : ids){
					Long newid= Datautility.getLong(id);
					deletedto.setId(newid);
					try {
						umodel.delete(deletedto);
						Serviletutility.setsuccessmessage("Data Deleated Successfully", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						Serviletutility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				
				}
			}
			else{
				System.out.println("inside delete operation else condition");
				Serviletutility.seterromessage(" Select at least one record", request);
			}
		}
		else if(OP_BACK.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.ROLE_LIST_CTL, request, response);
		}
		
			try {
				 System.out.println("Name = "+ dto.getId());
				 List list=umodel.search(dto,pageno,pagesize);
				 System.out.println("List="+list.size());
				 if(list==null){
					 Serviletutility.seterromessage("No Records found", request);
				 }
				 else{
					 Serviletutility.setlist(list, request);
					 Serviletutility.setpageno(pageno, request);
					 Serviletutility.setpagesize(pagesize, request);
					 Serviletutility.forward(getview(), request, response);
				 }
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				System.out.println("getting exception while getting list");
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
		return ORSView.ROLE_LIST_VIEW;
	}

}
