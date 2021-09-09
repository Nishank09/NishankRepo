package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.MarksheetModelHBI;
import in.co.rays.proj3.model.MarksheetModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.StudentModelHBI;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class MarksheetMeritListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="MarksheetMeritListCtl" ,urlPatterns={"/ctl/MarksheetMeritListCtl"})
public class MarksheetMeritListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pagesize= Datautility.getInt(Propertyreader.getvalue("page.size"));
		int pageno=1;
		MarksheetDTO dto = new MarksheetDTO();
		MarksheetModelINT smodel = ModelFactory.getInstance().getMarksheetModel();
		try {
			List list= smodel.getMeritList(pageno, pagesize);
			if(list==null || list.size() ==0){
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
		List list=null;
		List next=null;
		int pageNo = Datautility.getInt(request.getParameter("pageNo"));
		int pageSize = Datautility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? Datautility.getInt(Propertyreader.getvalue("page.size")) : pageSize;

		
		MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
		String op = Datautility.getstring(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");
        MarksheetModelINT model=ModelFactory.getInstance().getMarksheetModel();
        
        
        
        try{		
        	
        	if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {
				pageNo = 1;
			} else if (OP_NEXT.equalsIgnoreCase(op)) {
				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			Serviletutility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			Serviletutility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			Serviletutility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				MarksheetDTO deletedto = new MarksheetDTO();
				for (String id : ids) {
					deletedto.setId(Datautility.getLong(id));
					model.delete(deletedto);
					Serviletutility.seterromessage("Data Delete Successfully", request);
				}

			} else {
				Serviletutility.seterromessage("Select atleast one record", request);
			}
		}
		list = model.search(dto, pageNo, pageSize);
		 next = model.search(dto, pageNo + 1, pageSize);
		if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
			Serviletutility.seterromessage("No record found", request);
		}
		if (next == null || next.size() == 0) {
			request.setAttribute("nextListSize", "0");
		} else {
			request.setAttribute("nextListSize", next.size());
		}
		Serviletutility.setlist(list, request);
		Serviletutility.setpageno(pageNo, request);
		Serviletutility.setpagesize(pageSize, request);
		Serviletutility.forward(getview(), request, response);
	} catch (ApplicationException e) {
		//log.error(e);
		Serviletutility.handleException(e, request, response);
		return;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_MERIT_LIST_VIEW;
	}

}
