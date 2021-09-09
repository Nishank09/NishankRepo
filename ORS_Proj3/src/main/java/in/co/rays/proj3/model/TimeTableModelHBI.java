package in.co.rays.proj3.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.dto.TimetableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.HIBdatasource;


/**
 * The Class TimeTableModelHibImpl.
 * 
 * @author Iterator
 * @version 1.0
 */
public class TimeTableModelHBI implements TimetableModelINT {

	/** The log. 
	 * @throws ApplicationException */
	//private static Logger log = Logger.getLogger(MarksheetModelHibImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#checkByCourseName(long,
	 * java.util.Date)
	 */
	public TimetableDTO checkByCourseName(long CourseId, Date ExamDate) throws ApplicationException {
        String examdate= Datautility.getstringdata(ExamDate);
		Session session = null;
		TimetableDTO dto = null;
		try {
			session = HIBdatasource.getsession();

			Criteria criteria = session.createCriteria(TimetableDTO.class);

			criteria.add(Restrictions.eq("courseid", CourseId));
			criteria.add(Restrictions.eq("examdate", ExamDate));

			List list = criteria.list();
			if (list.size() == 1) {

				dto = (TimetableDTO) list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			HIBdatasource.closesession(session);
		}

		//log.debug("Model getMeritList End");
		return dto;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#checkBySubjectName(long, long,
	 * java.util.Date)
	 */
	public TimetableDTO checkBySubjectName(long CourseId, long SubjectId, Date ExamDate) throws ApplicationException {
		Session session = null;
		TimetableDTO dto = null;
		try {
			session = HIBdatasource.getsession();

			Criteria criteria = session.createCriteria(TimetableDTO.class);

			// if page size is greater than zero then apply pagination
			criteria.add(Restrictions.eq("courseid", CourseId));
			criteria.add(Restrictions.eq("subjectid", SubjectId));

			criteria.add(Restrictions.like("examdate", ExamDate));

			List list = criteria.list();
			if (list.size() == 1) {

				dto = (TimetableDTO) list.get(0);
			}

		} catch (Exception e) {
			//log.error(e);
			e.printStackTrace();
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		//log.debug("Model getMeritList End");
		return dto;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#checkBysemester(long, long,
	 * java.lang.String, java.util.Date)
	 */
	public TimetableDTO checkBysemester(long CourseId, long SubjectId, String semester, Date ExamDate)
			throws ApplicationException {
		Session session = null;
		TimetableDTO dto=null;
		
		try {
			session = HIBdatasource.getsession();

			Criteria criteria = session.createCriteria(TimetableDTO.class);

			// if page size is greater than zero then apply pagination
			System.out.println("CourseId---------------------------------"+CourseId);
			criteria.add(Restrictions.eq("courseid", CourseId));
			criteria.add(Restrictions.eq("subjectid", SubjectId));
			criteria.add(Restrictions.like("semester",semester +"%"));

			criteria.add(Restrictions.like("examdate", ExamDate));

			List list = criteria.list();
			if (list.size() == 1) {

				dto = (TimetableDTO) list.get(0);
			}

		} catch (Exception e) {
		//	log.error(e);
			e.printStackTrace();
			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		//log.debug("Model getMeritList End");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.model.TimeTableModelInt#add(in.co.rays.dto.TimeTableDTO)
	 */
	public long add(TimetableDTO dto) throws ApplicationException, DuplicateRecordException {
		System.out.println("Timetable model add method called");
		
		Session s=HIBdatasource.getsession();
		Transaction tx=null;
		SubjectModelINT subjectModel = ModelFactory.getInstance().getSubjectModel();
		CourseModelINT courseModel = ModelFactory.getInstance().getCourseModel();

		SubjectDTO subjectdto = subjectModel.findByPK(dto.getsubjectid());
		CourseDTO coursedto = courseModel.findByPK(dto.getcourseid());
       
		dto.setsubjectname(subjectdto.getSubjectname());
		dto.setcoursename(coursedto.getCoursename());
		Session session = HIBdatasource.getsession();
		Transaction transaction = null;
		try {
			TimetableDTO duplicatename = checkByCourseName(dto.getcourseid(), dto.getexamdate());

			TimetableDTO duplicatename1 = checkBysemester(dto.getcourseid(), dto.getsubjectid(), dto.getsemester(),
					dto.getexamdate());

			TimetableDTO duplicatename2 = checkBySubjectName(dto.getcourseid(), dto.getsubjectid(), dto.getexamdate());
			if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
				throw new DuplicateRecordException("Time Table already exist");

			}
			tx = s.beginTransaction();
			System.out.println("session created");
			s.save(dto);
			System.out.println("save done");
			tx.commit();
		}
		catch(HibernateException e){
			if(tx !=null){
				tx.rollback();
				throw new ApplicationException("getting error to add record");
			}
			
		}
		finally{
			HIBdatasource.closesession(s);
		}
		
		return 0;
	}

	public TimetableDTO findByPK(long pk) throws ApplicationException {
		Session s=HIBdatasource.getsession();
		TimetableDTO dto= null;
		try{
			dto= s.get(TimetableDTO.class, pk);
		}
		catch(HibernateException e){
			e.printStackTrace();
			throw new ApplicationException("Getting error findbypk");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		
		return dto;
	}

	public void update(TimetableDTO dto)
			throws ApplicationException {
		SubjectModelINT subjectModel = ModelFactory.getInstance().getSubjectModel();

		SubjectDTO subjectdto = subjectModel.findByPK(dto.getsubjectid());

		dto.setsubjectname(subjectdto.getSubjectname());

		CourseModelINT courseModel = ModelFactory.getInstance().getCourseModel();

		CourseDTO coursedto = courseModel.findByPK(dto.getcourseid());

		dto.setcoursename(coursedto.getCoursename());


		Session s=HIBdatasource.getsession();
		Transaction tx=null;
		try{
			tx=s.beginTransaction();
			s.update(dto);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx !=null){
				tx.rollback();
			}
			throw new ApplicationException("Error to update record");
		}
		finally {
			HIBdatasource.closesession(s);
		}


	}

	public void delete(TimetableDTO dto) throws ApplicationException {
		Session s=HIBdatasource.getsession();
		Transaction tx=null;
		try{
			tx=s.beginTransaction();
			s.delete(dto);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx !=null){
				tx.rollback();
			}
			throw new ApplicationException("Getting error to delete record");
		}
		finally {
			HIBdatasource.closesession(s);
		}


	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session s=HIBdatasource.getsession();
		Criteria cr= s.createCriteria(TimetableDTO.class);
		if(pageSize>0){
			cr.setFirstResult(((pageNo-1)*pageSize));
			cr.setMaxResults(pageSize);
		}
		List list =cr.list();
		return list;

	}

	public List search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session s=HIBdatasource.getsession();	
		Criteria cr=s.createCriteria(TimetableDTO.class);
		List list =null;
		try{
			if(dto !=null){
				if(dto.getcourseid() > 0){
					cr.add(Restrictions.eq("courseid", dto.getcourseid()));
				}
				if(dto.getsubjectid() >0){
					cr.add(Restrictions.eq("subjectid", dto.getsubjectid()));
				}
				if(dto.getexamdate() !=null){
					cr.add(Restrictions.like("examdate", dto.getexamdate()+"%"));
				}
				if(dto.getexamtime() !=null){
					cr.add(Restrictions.like("examtime", dto.getexamtime()+"%"));
				}
			}
			if(pageSize>0){
				cr.setFirstResult(((pageNo-1)*pageSize));
				cr.setMaxResults(pageSize);
			}
			list = cr.list();

		}
		catch(HibernateException e){
			throw new ApplicationException("error in search");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		return list;
	}

	public List search(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

}
