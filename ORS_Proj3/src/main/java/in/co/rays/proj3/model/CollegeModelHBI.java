package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.util.HIBdatasource;

/**
 * Hibernate Implementation of CollegeModel.
 * @author Iterator
 * @version 1.0
 */
public class CollegeModelHBI implements CollegeModelINT {

	 /**
     * Add a College.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
	public long add(CollegeDTO dto) throws ApplicationException, DuplicateRecordException 
	{
		System.out.println("inside CollegeModel");
		long pk= 0;
		Session s= HIBdatasource.getsession();
		System.out.println("Session object recieved");
		Transaction tx = null;
         try{
		        CollegeDTO dtoexist= findByName(dto.getName());
		        if(dtoexist!=null)
		        {
			        throw new DuplicateRecordException("College Name Already Exist");
		        }
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
     * Delete a College.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
	public void delete(CollegeDTO dto) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Transaction tx=null;
		try{
		   tx= s.beginTransaction();
		s.delete(dto);
		tx.commit();
		  }
		catch(Exception e)
		{
			e.printStackTrace();
			if(tx !=null)
 	       {
 		       tx.rollback();
 	       }
			throw new ApplicationException("Exception to delete");
			
		}
		finally
		{
			HIBdatasource.closesession(s);
		}
	}
	 /**
     * Update a Collage.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
	public void update(CollegeDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s= HIBdatasource.getsession();
		Transaction tx =null;
/*		CollegeDTO dtoexist= findByName(dto.getName());
		if(dtoexist!=null && dtoexist.getId()!=dto.getId()){
			throw new DuplicateRecordException("College Already exist");
		}*/
	try{	
		tx= s.beginTransaction();
		s.update(dto);
		tx.commit();
		
	   }
	catch(HibernateException hb)
	   {
		hb.printStackTrace();
		if(tx !=null)
		 {
		 tx.rollback();
		 }
		throw new ApplicationException("Exception in update operation");
	   }
	finally
	  {
		HIBdatasource.closesession(s);
	  }
	}
	 /**
     * Find Collage by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
	public CollegeDTO findByPk(long pk) throws ApplicationException {
		CollegeDTO dto=null;
		Session s=null;
	try{	
		 s= HIBdatasource.getsession();
		 dto= (CollegeDTO) s.get(CollegeDTO.class, pk);
		
	   }
	catch(HibernateException hb)
	  {
		hb.printStackTrace();
		throw new ApplicationException("Exception in findbypk operation");		
	  }
	finally{
		HIBdatasource.closesession(s);
	       }
		return dto;
		
	}
	/**
     * Find User by College Name.
     *
     * @param name the name
     * @return dto
     * @throws ApplicationException the application exception
     */
	public CollegeDTO findByName(String Name) throws ApplicationException {
		CollegeDTO dto=null;
		
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(CollegeDTO.class);
		cr.add(Restrictions.eq("name", Name));
        List list= cr.list(); 
        Iterator it=list.iterator();
        while(it.hasNext()){
        	dto= (CollegeDTO) it.next();
        	System.out.println("this is name"+dto.getName());
        }
        return dto;
	}
	 /**
     * Searches Colleges with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Colleges
     * @throws ApplicationException the application exception
     */
	public List search(CollegeDTO dto, int pageNo, int pageSize) throws ApplicationException 
{
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(CollegeDTO.class);
		if(dto!=null)
	{

		  if(dto.getId()>0)
		  {
		    cr.add(Restrictions.eq("id",dto.getId()));
		  }
		  if(dto.getName()!=null)
		  {
		    cr.add(Restrictions.like("name",dto.getName()+"%"));
		  }
		  if(dto.getCity()!=null)
		  {
		    cr.add(Restrictions.like("city",dto.getCity()+"%"));
		  }
		  if(dto.getState()!=null)
		  {
		    cr.add(Restrictions.like("state",dto.getState()+"%"));
		  }
	}
		if(pageSize>0){
			cr.setFirstResult(((pageNo-1)*pageSize));
			cr.setMaxResults(pageSize);
		}
		List list= cr.list();
		return list;
}
	 /**
     * Search College.
     *
     * @param dto            : Search Parameters
     * @return the list
     * @throws ApplicationException the application exception
     */
	public List search(CollegeDTO dto) throws ApplicationException {
		
		return search(dto,0,0);
	}
	 /**
     * get List of College with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(CollegeDTO.class);
		if(pageSize>0){
		cr.setFirstResult(((pageNo-1)*pageSize));
		cr.setMaxResults(pageSize);
		   }
		List list= cr.list();
		return list;
		}
	 /**
     * Gets List of College.
     *
     * @return list : List of College
     * @throws ApplicationException the application exception
     */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}
     
	
}
