package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.CollegeModelHBI;
import in.co.rays.proj3.model.CollegeModelINT;
import in.co.rays.proj3.model.MarksheetModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class CollegeListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name = "CollegeListCtl", urlPatterns = { "/ctl/CollegeListCtl" })
public class CollegeListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	 /* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CollegeModelHBI cmodel = new CollegeModelHBI();
		CollegeDTO dto = new CollegeDTO();
		try {
			List list = cmodel.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {

				dto = (CollegeDTO) it.next();
				System.out.println("college list:-" + dto.getName());
			}

			req.setAttribute("collegeList", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    /* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CollegeDTO dto = new CollegeDTO();
		dto.setId(Datautility.getLong(req.getParameter("collegeid")));
		dto.setCity(req.getParameter("City"));
		return dto;

	}

    /* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageno = 1;
		CollegeDTO dto = new CollegeDTO();
		int pagesize = Datautility.getInt(Propertyreader.getvalue("page.size"));
		CollegeModelHBI model = new CollegeModelHBI();
		try {
			List list = model.search(dto, pageno, pagesize);
			if (list == null || list.size() == 0) {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CollegeModelINT model = ModelFactory.getInstance().getCollegeModel();
		CollegeDTO dto = (CollegeDTO) populateDTO(request);
		String op = request.getParameter("operation");
		String ids[] = request.getParameterValues("ids");
		int pageno = Datautility.getInt(request.getParameter("pageno"));
		int pagesize = Datautility.getInt(request.getParameter("pagesize"));
		pageno = (pageno == 0) ? 1 : pageno;
		pagesize = (pagesize == 0) ? Datautility.getInt(Propertyreader.getvalue("page.size")) : pagesize;
		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {
			if (OP_SEARCH.equalsIgnoreCase(op)) {
				System.out.println("inside search condition" + op);
				pageno = 1;
			} else if (OP_NEXT.equalsIgnoreCase(op)) {
				System.out.println("next condition" + pageno);
				pageno++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
				System.out.println("previous condition" + pageno);
				pageno--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			Serviletutility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			Serviletutility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageno = 1;
			System.out.println("inside delete operation condition");
			if (ids != null && ids.length > 0) {
				System.out.println("inside delete operation if condition");
				CollegeDTO deletedto = new CollegeDTO();
				for (String id : ids) {
					Long newid = Datautility.getLong(id);
					deletedto.setId(newid);
					try {
						model.delete(deletedto);
						Serviletutility.setsuccessmessage("Data Deleated Successfully", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Serviletutility.handleException(e, request, response);
						return;
					}

				}
			} else {
				System.out.println("inside delete operation else condition");
				Serviletutility.seterromessage(" Select at least one record", request);
			}
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			Serviletutility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		}
		try {

			List searchlist = model.search(dto, pageno, pagesize);
			System.out.println("List=" + searchlist.size());
			if (searchlist == null) {
				Serviletutility.seterromessage("No Records found", request);
			} else {
				Serviletutility.setlist(searchlist, request);
				Serviletutility.setpageno(pageno, request);
				Serviletutility.setpagesize(pagesize, request);
				Serviletutility.forward(getview(), request, response);
			}
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			System.out.println("getting exception while getting list");
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
		return ORSView.COLLEGE_LIST_VIEW;
	}

}
