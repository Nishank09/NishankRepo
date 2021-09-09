package in.co.rays.proj3.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.TimetableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.model.CourseModelINT;
import in.co.rays.proj3.model.ModelFactory;
import in.co.rays.proj3.model.SubjectModelHBI;
import in.co.rays.proj3.model.SubjectModelINT;
import in.co.rays.proj3.model.TimetableModelINT;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Serviletutility;

/**
 * The Class TimeTableCtl.
 * @author Iterator
 * @version 1.0
 */
@WebServlet(name="TimeTableCtl" ,urlPatterns={"/ctl/TimeTableCtl"})
public class TimeTableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;



	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("in timetable validate");

		boolean pass =true;
		
		String  courseId =  request.getParameter("course");
		String  subjectId =  request.getParameter("subject");
		String  semester =  request.getParameter("semester");
		String  Exdate =  request.getParameter("examdate");
		String  examTime =  request.getParameter("examTime");
		String  description =  request.getParameter("description");
		
		if(Datavalidator.isnull(courseId))
		{
		  request.setAttribute("course", "Please Select Course");
		  pass=false;
		}
		if(Datavalidator.isnull(subjectId))
		{
		  request.setAttribute("subject", "Please Select Subject");
		  pass=false;
		}
		if(Datavalidator.isnull(semester))
		{
		  request.setAttribute("semester", "Please Select Semester");
		  pass=false;
		}
		if(Datavalidator.isnull(Exdate))
		{
		  request.setAttribute("examdate", "Please Select Exam Date");
		  pass=false;
		}
		if(Datavalidator.isnull(examTime))
		{
		  request.setAttribute("examTime", "Please Select Exam Time");
		  pass=false;
		}
		if(Datavalidator.isnull(description))
		{
		  request.setAttribute("description", "Please Select desciption");
		  pass=false;
		}
		
		return pass;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("in timetable populate");
		   TimetableDTO dto = new TimetableDTO();
		   
		    String  courseId =  request.getParameter("course");
			String  subjectId =  request.getParameter("subject");
			String  semester =  request.getParameter("semester");
			System.out.println("Dateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+request.getParameter("examdate"));
			Date  Exdate =  Datautility.getdate(request.getParameter("examdate"));
			
			String  examTime =  request.getParameter("examTime");
			String  description =  request.getParameter("description");
			
			dto.setId(Datautility.getLong(request.getParameter("id")));
			dto.setcourseid(Datautility.getLong(courseId));
			dto.setsubjectid(Datautility.getInt(subjectId));
			dto.setsemester(Datautility.getstring(semester));
			dto.setDiscription(Datautility.getstring(description));
			
			System.out.println(Exdate);
			if(!Exdate.equals(""))
			{
				dto.setexamdate(Exdate);	
			}
		    	
			dto.setexamtime(Datautility.getstring(examTime));
		    
			return dto;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.proj3.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest req) {
		SubjectModelINT model1= ModelFactory.getInstance().getSubjectModel();
		CourseModelINT model2 = ModelFactory.getInstance().getCourseModel();
		try {
			List subjectlist= model1.list();
			List coursetlist= model2.list();
			req.setAttribute("subjectlist", subjectlist);
			req.setAttribute("coursetlist", coursetlist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in timetablectl doget method");
		long id = Datautility.getLong(request.getParameter("id"));
		TimetableDTO dto=null;
		TimetableModelINT model=ModelFactory.getInstance().getTimeTableModel();
		if(id>0)
		{
		  try {
			dto = model.findByPK(id);
			System.out.println("findbypk id se"+dto.getcoursename());
			Serviletutility.setdto(dto, request);
		  } catch (Exception e) {
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
		System.out.println("in timetable dopost");
		String op = request.getParameter("operation");
		long id = Datautility.getLong(request.getParameter("id"));
		
		TimetableDTO bean = (TimetableDTO)populateDTO(request);
		TimetableModelINT model = ModelFactory.getInstance().getTimeTableModel();
		
		if(OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)){
			
			System.out.println("shivam1");
		

		
				if(bean.getId()>0){
					System.out.println("inside id>0 to update");
					try {
						model.update(bean);
						Serviletutility.setdto(bean, request);
						Serviletutility.setsuccessmessage("Timetable Updated Successfully", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				
				else{
					System.out.println("inside  to add");
					try {
						System.out.println("courseID..........."+bean.getcourseid());
						System.out.println("SubjectID..........."+bean.getsubjectid());
						System.out.println("Examdate..........."+bean.getexamdate());
						model.add(bean);
						Serviletutility.setsuccessmessage("Timetable is successfully saved", request);
					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DuplicateRecordException e) {
						Serviletutility.setdto(bean, request);
						Serviletutility.seterromessage("Timetable Already exist", request);
						e.printStackTrace();
					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} 
				
				
	
		}
		else if(OP_CANCEL.equalsIgnoreCase(op))
		{
			System.out.println("inside  to cancel");
			Serviletutility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op))
		{
			System.out.println("inside  to reset");
			  Serviletutility.redirect(ORSView.TIME_TABLE_CTL, request, response);	
			  return;
		}
		System.out.println("inside  to forward to getview");
		  Serviletutility.forward(getview(), request, response);
	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getview() {
		// TODO Auto-generated method stub
		return ORSView.TIME_TABLE_VIEW;
	}

}
