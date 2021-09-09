package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HIBdatasource;


/**
 * Hibernate Implementation of SubjectModel.
 * @author Iterator
 * @version 1.0
 */
public class SubjectModelHBI implements SubjectModelINT {
    /** The log. */


	
	/**
     * Add a Course.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk= 0;
		Session s= HIBdatasource.getsession();
		Transaction tx = null;
        SubjectDTO dtoexist= findByName(dto.getSubjectname());
        System.out.println("this is dtoexist :"+ dtoexist);
        if(dtoexist!=null)
        {
	        throw new DuplicateRecordException("Subject Name Already Exist");
        }
         try{
         	 CourseModelHBI cmodel= new CourseModelHBI();
 			CourseDTO crDto=cmodel.findByPK(dto.getCourseId());
 			dto.setCourseName(crDto.getCoursename());

		            tx= s.beginTransaction();
		            s.save(dto);
		    		System.out.println("Transection done");
		            tx.commit();
            }
         catch(HibernateException e)
            {
     		System.out.println("Hibernate Exception");
    	       e.printStackTrace();
    	        if(tx !=null)
    	       {
    		       tx.rollback();
    	       }
            }
         finally
            {
		       HIBdatasource.closesession(s);
	
            }
	    return pk;
	}

	
	/**
	 * Find Subject by PK.
	 *
	 * @param pk            : get parameter
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public SubjectDTO findByPK(long pk) throws ApplicationException {
		//log.debug("Model findByPK Started");
        Session session = null;
        SubjectDTO dto = null;
        try {
            session = HIBdatasource.getsession();
            dto = (SubjectDTO)session.get(SubjectDTO.class, pk);
        } catch (HibernateException e) {
           // log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by pk");
        } finally {
            session.close();
        }

        //log.debug("Model findByPK End");
		
	
		return dto;
	}

	
	/**
	 * Find course by Subject Name.
	 *
	 * @param name the name
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public SubjectDTO findByName(String name) throws ApplicationException {
		//log.debug("Model findByName Started");
		 Session session = null;
	     SubjectDTO dto = null;
	     try {
	            session = HIBdatasource.getsession();
	            Criteria criteria = session.createCriteria(SubjectDTO.class);
	            criteria.add(Restrictions.eq("Subjectname", name));
	            List list = criteria.list();
                Iterator it= list.iterator();
                while(it.hasNext()){
                	dto= (SubjectDTO) it.next();
                }

	        } catch (HibernateException e) {
	            //log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception in getting findbyname method " + e.getMessage());

	        } finally {
	            session.close();
	        }

	        //log.debug("Model findByName End");
		return dto;

	}

	
	/**
	 * Update a Subject.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		//log.debug("Model update Started");
		Long pk=0L;
        SubjectDTO dtoExist=findByName(dto.getSubjectname());
		if(dtoExist!=null){
			throw new DuplicateRecordException("Subject already exist");
		}
        Session session=null;
        Transaction transaction=null;
        try{
        	CourseModelHBI crModel= new CourseModelHBI();
			CourseDTO crDto=crModel.findByPK(dto.getCourseId());
			dto.setCourseName(crDto.getCoursename());
            
        	 session = HIBdatasource.getsession();
             transaction = session.beginTransaction();
             session.update(dto);
             transaction.commit();
        }catch(HibernateException e){
        	//log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();

            }
        } finally {
        	HIBdatasource.closesession(session);
        }
       // log.debug("Model update End");
		
	}

	
	/**
	 * Delete a Course.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */
	public void delete(SubjectDTO dto) throws ApplicationException {
		//log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HIBdatasource.getsession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            //log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Subject Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
       // log.debug("Model delete End");	

		
	}
	
	/**
	 * Gets List of Course.
	 *
	 * @return list : List of Course
	 * @throws ApplicationException the application exception
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
		
	}

	/**
	 * get List of Subject with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of subject
	 * @throws ApplicationException the application exception
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
        List list = null;
        try {
            session = HIBdatasource.getsession();
            Criteria criteria = session.createCriteria(SubjectDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize);
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            //log.error("Database Exception..", e);
             
        	e.printStackTrace();
        } finally {
            session.close();
        }

        //log.debug("Model list End");
        return list;
	}

	 
	

	/**
	 * Searches Subject with pagination.
	 *
	 * @param dto            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Subject
	 * @throws ApplicationException the application exception
	 */
	public List search(SubjectDTO dto, int pageNo, int PageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try{
			session=HIBdatasource.getsession();
			Criteria criteria=session.createCriteria(SubjectDTO.class);
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id",dto.getId()));
			}
			if(dto.getSubjectname()!=null && dto.getSubjectname().length()>0){
				criteria.add(Restrictions.like("subjectname", dto.getSubjectname()));
			}
			if(dto.getCourseName()!=null && dto.getCourseName().length()>0){
				criteria.add(Restrictions.like("courseName", dto.getCourseName()));
			}
			if(dto.getCourseId()>0){
				criteria.add(Restrictions.eq("courseId",dto.getCourseId()));
			}
		    if (PageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * PageSize));
                criteria.setMaxResults(PageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
           // log.error("Database Exception..", e);
            throw new ApplicationException("Exception in course search");
        } finally {
            session.close();

		return list;
	}
	}

	/**
	 * Search Subject.
	 *
	 * @param dto            : Search Parameters
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List search(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

	
	
}
