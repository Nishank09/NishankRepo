package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CourseDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HIBdatasource;
/**
 * Hibernate Implementation of CourseModel.
 * @author Iterator
 * @version 1.0
 */
public class CourseModelHBI implements CourseModelINT {
	 /**
     * Add a Course.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s= HIBdatasource.getsession();
		Transaction tx = null;
		CourseDTO dtoexist = findByName(dto.getCoursename());
		if(dtoexist !=null){
			throw new DuplicateRecordException("Record already Exist");
		}
		dto.setDuration("3");
		try{
			tx =s.beginTransaction();
			s.save(dto);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx!=null){
				tx.rollback();
			}
			throw new ApplicationException("Error to add record");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		return 0;
	}
	/**
 	 * Delete a Course.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 */
	public void delete(CourseDTO dto) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Transaction tx = null;
		try{
		     tx=s.beginTransaction();
		     s.delete(dto);
		     tx.commit();
		}
		catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			throw new ApplicationException("Error to delete record");
		}
		finally{
			HIBdatasource.closesession(s);
		}
	}
	/**
 	 * Update a Course.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 * @throws DuplicateRecordException the duplicate record exception
 	 */
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s= HIBdatasource.getsession();
		Transaction tx = null;
		CourseDTO dtoexist= findByName(dto.getCoursename());
		if(dtoexist!=null && dtoexist.getId()!=dto.getId()){
			throw new DuplicateRecordException("Record already exist");
		}
		try{
			tx=s.beginTransaction();
			s.update(dto);
			tx.commit();
		}
		catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			
		}
		finally{
			HIBdatasource.closesession(s);
		}
		
	}
	/**
	 * Find course by Course Name.
	 *
	 * @param name the name
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public CourseDTO findByName(String Name) throws ApplicationException {
		CourseDTO dto=null;
		Session s= HIBdatasource.getsession();
		try{
			Criteria cr= s.createCriteria(CourseDTO.class);
			
			cr.add(Restrictions.eq("coursename", Name));
			List list= cr.list();
			Iterator it= list.iterator();
			while(it.hasNext()){
				dto = (CourseDTO) it.next();
		}
	}
	    catch(HibernateException e){
	    	e.printStackTrace();
	    	throw new ApplicationException("Error to search by name");
	    }
		finally{
			HIBdatasource.closesession(s);
		}
	    
		
		return dto;
	}
	/**
 	 * Find Course by PK.
 	 *
 	 * @param pk            : get parameter
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public CourseDTO findByPK(long pk) throws ApplicationException {
		CourseDTO dto =null;
		Session s= HIBdatasource.getsession();
		try{
		  dto =  s.get(CourseDTO.class, pk);
		}
		catch(HibernateException e){
			e.printStackTrace();
			
		}
		finally{
			HIBdatasource.closesession(s);
		}
		return dto;
	}
	/**
	 * Searches Course with pagination.
	 *
	 * @param dto            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Course
	 * @throws ApplicationException the application exception
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Session s= HIBdatasource.getsession();
		 Criteria cr=s.createCriteria(CourseDTO.class);
		try{
			if(dto !=null){
				if(dto.getId() > 0){
					 cr.add(Restrictions.eq("id", dto.getId()));
				}

			}
			if(pageSize >0){
				cr.setFirstResult(((pageNo-1)*pageSize));
				cr.setMaxResults(pageSize);
			}
			List list= cr.list();
			return list;
		}
		catch(HibernateException e){
			e.printStackTrace();
			throw new ApplicationException("Error to in search operation");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		
		
	}
	/**
	 * Search Course.
	 *
	 * @param dto            : Search Parameters
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}
	/**
	 * get List of Course with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of College
	 * @throws ApplicationException the application exception
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		List list = null;
		Session s = HIBdatasource.getsession();
		try{

			Criteria cr= s.createCriteria(CourseDTO.class);
			if(pageSize>0){
				cr.setFirstResult((pageNo-1)*pageSize);
				cr.setMaxResults(pageSize);
			}
			list=cr.list();
         }
		catch(HibernateException e){
 			e.printStackTrace();
 			throw new ApplicationException("Error to in search operation");
 		}
		finally{
			HIBdatasource.closesession(s);
		}
		
		return list;
	}
	/**
	 * Gets List of Course.
	 *
	 * @return list : List of Course
	 * @throws ApplicationException the application exception
	 */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}

}
