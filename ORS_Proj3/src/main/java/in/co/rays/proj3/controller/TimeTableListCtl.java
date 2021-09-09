package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.TimetableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.model.CourseModelHBI;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.RoleModelHBI;
import in.co.rays.proj3.model.SubjectModelHBI;
import in.co.rays.proj3.model.TimeTableModelHBI;
import in.co.rays.proj3.model.TimetableModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Propertyreader;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class TimeTableListCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="TimeTableListCtl" ,urlPatterns={"/ctl/TimeTableListCtl"})
public class TimeTableListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;



	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		CourseModelHBI ob = (CourseModelHBI) ModelFactory.getInstance().getCourseModel();
		SubjectModelHBI ob1= (SubjectModelHBI) ModelFactory.getInstance().getSubjectModel();
		
		


		try {
			List courselist= ob.list();
			List subjectlist= ob1.list();
			req.setAttribute("courselist", courselist);
			req.setAttribute("subjectlist", subjectlist);
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		TimetableDTO dto = new TimetableDTO();

		dto.setcourseid(Datautility.getLong(request.getParameter("courseId")));

		dto.setsubjectid(Datautility.getLong(request.getParameter("subjectId")));

		dto.setexamtime(Datautility.getstring(request.getParameter("examTime")));

		return dto;
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TimeTableModelHBI model= (TimeTableModelHBI) ModelFactory.getInstance().getTimeTableModel();
		TimetableDTO dto = new TimetableDTO();
		int pageno=1;
		int pagesize= Datautility.getInt(Propertyreader.getvalue("page.size"));
		List list;
		try {
			list = model.search(dto, pageno, pagesize);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List list = null;
        List next =null;
        int pageNo = Datautility.getInt(request.getParameter("pageNo"));
        int pageSize = Datautility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? Datautility.getInt(Propertyreader.getvalue("page.size")) : pageSize;
        TimetableDTO dto = (TimetableDTO) populateDTO(request);
        String op = Datautility.getstring(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        String ids[] = request.getParameterValues("ids");
        TimetableModelINT model = ModelFactory.getInstance().getTimeTableModel();
        try {

            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) 
            {

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }

            } else if (OP_NEW.equalsIgnoreCase(op)) {
                Serviletutility.redirect(ORSView.TIME_TABLE_CTL, request, response);
                return;
            
            }else if(OP_RESET.equalsIgnoreCase(op))
    		{
  			  Serviletutility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);	
  			  return;
  		}

            
            else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    TimetableDTO deleteDto = new TimetableDTO();
                    for (String id : ids) {
                    	
                    	Long idnew=Datautility.getLong(id);
                    	
                        deleteDto.setId(idnew);
                        
                        model.delete(deleteDto);
                        
                        Serviletutility.setsuccessmessage(
                                "Delete data Successfully", request);
                    }
                } else if(OP_BACK.equalsIgnoreCase(op)){
                	Serviletutility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
                	return;
                }else{
                    Serviletutility.seterromessage(
                            "Select at least one record", request);
                }
            }
            System.out.println("thsi is courseidsddddddddddddddd="+ dto.getcourseid());
            list = model.search(dto, pageNo, pageSize);
            next=model.search(dto, pageNo+1, pageSize);
            Serviletutility.setlist(list, request);
            if (list == null || list.size() == 0&&!OP_DELETE.equalsIgnoreCase(op)) {
                Serviletutility.seterromessage("No record found ", request);
            }
            if(next==null||next.size()==0){
            	request.setAttribute("nextlistsize", "0");
            }else{
            	request.setAttribute("nextlistsize", next.size());
            }
            Serviletutility.setdto(dto, request);
            Serviletutility.setlist(list, request);
            Serviletutility.setpageno(pageNo, request);
            Serviletutility.setpagesize(pageSize, request);
            Serviletutility.forward(getview(), request, response);

        } catch (ApplicationException e) {
            
            
            return;
        }
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.TIME_TABLE_LIST_VIEW;
	}

}
