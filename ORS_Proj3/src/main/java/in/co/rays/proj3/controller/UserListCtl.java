package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelHBI;
import in.co.rays.proj3.model.UserModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class UserListCtl.
 * @author Iterator
 * @version 1.0
 */

@WebServlet(name="UserListCtl", urlPatterns={"/ctl/UserListCtl"})
public class UserListCtl extends BaseCtl {

	

	

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		RoleModelHBI rmodel = new RoleModelHBI();
		try {
			List list= rmodel.list();
			req.setAttribute("roleList", list);
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
		UserDTO dto= new UserDTO();
		dto.setRoleid(Datautility.getLong(req.getParameter("roleid")));
		 dto.setfirstname(req.getParameter("firstname"));
		 dto.setLogin(req.getParameter("email"));
		 //dto.setRoleid(Datautility.getLong(req.getParameter("role")));
		 return dto;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModelHBI umodel = (UserModelHBI) ModelFactory.getInstance().getUserModel();
		int pagesize= Datautility.getInt(Propertyreader.getvalue("page.size"));
		UserDTO dto= (UserDTO)populateDTO(request);
		int pageno=1;
		try {
			List list =umodel.search(dto, pageno, pagesize);
			if(list==null){
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
		// TODO Auto-generated method stub
		System.out.println("Userlist view Dopost method called");
		
		UserModelHBI umodel = (UserModelHBI) ModelFactory.getInstance().getUserModel();
		UserDTO dto= (UserDTO) populateDTO(request);
		
		String op= request.getParameter("operation");
		String ids[]= request.getParameterValues("ids");
		int pageNo= Datautility.getInt(request.getParameter("pageno"));
		int pageSize= Datautility.getInt(request.getParameter("pagesize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? Datautility.getInt(Propertyreader
                .getvalue("page.size")) : pageSize;
		if(OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)){
			if(OP_SEARCH.equalsIgnoreCase(op)){
				pageNo=1;
			}
			else if(OP_NEXT.equalsIgnoreCase(op)){
				pageNo++;
			}
			else if(OP_PREVIOUS.equalsIgnoreCase(op)){
				pageNo--;
			}
		}
		else if(OP_NEW.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.USER_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			Serviletutility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			pageNo=1;
			System.out.println("inside delete operation condition");
			if(ids !=null && ids.length >0){
				System.out.println("inside delete operation if condition");
				UserDTO deletedto = new UserDTO();
				for(String id : ids){
					Long newid= Datautility.getLong(id);
					deletedto.setId(newid);
					umodel.delete(deletedto);
					Serviletutility.setsuccessmessage("Data Deleated Successfully", request);;
				}
			}
			else{
				System.out.println("inside delete operation else condition");
				Serviletutility.seterromessage(" Select at least one record", request);
			}
		}
		else if(OP_BACK.equalsIgnoreCase(op)){
			System.out.println("this is op_BACK condition op"+ op);
			Serviletutility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}
		
			try {
				 System.out.println("Name = "+ dto.getfirstname());
				 List list=umodel.search(dto,pageNo,pageSize);
				 
				 System.out.println("List="+list.size());
				 if(list==null){
					 Serviletutility.seterromessage("No Records found", request);
					
				 }

				 else{
					 Serviletutility.setlist(list, request);
					 Serviletutility.setpageno(pageNo, request);
					 Serviletutility.setpagesize(pageSize, request);
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
		return ORSView.USER_LIST_VIEW;
	}

}
