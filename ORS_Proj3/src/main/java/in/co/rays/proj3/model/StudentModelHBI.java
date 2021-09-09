package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HIBdatasource;


/**
 * Hibernate Implementation of StudentModel.
 * @author Iterator
 * @version 1.0
 */
public class StudentModelHBI implements StudentModelINT {

	
	   /**
   	 * add a Student.
   	 *
   	 * @param dto the dto
   	 * @return the long
   	 * @throws ApplicationException the application exception
   	 * @throws DuplicateRecordException the duplicate record exception
   	 */
	public long add(StudentDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk= 0;
		Session s= HIBdatasource.getsession();
		Transaction tx = null;
        StudentDTO dtoexist= findByEmail(dto.getemail());
        if(dtoexist!=null)
        {
	        throw new DuplicateRecordException("Student record Already Exist");
        }
         try{

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
	public StudentDTO findByEmail(String email) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Criteria cr = s.createCriteria(StudentDTO.class);
		StudentDTO dto=null;
		List list=null;
		try{
			cr.add(Restrictions.eq("email", email));
			list= cr.list();
			Iterator it=list.iterator();
			while(it.hasNext()){
				dto=(StudentDTO) it.next();
			}
		}
		catch(HibernateException e){
			throw new ApplicationException("error to find by email");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		return dto;

	}
	

	public StudentDTO findByPK(long pk) throws ApplicationException {
		StudentDTO dto=null;
		Session s=null;
	try{	
		 s= HIBdatasource.getsession();
		 dto= (StudentDTO) s.get(StudentDTO.class, pk);
		
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
   	 * Update a Student.
   	 *
   	 * @param dto the dto
   	 * @throws ApplicationException the application exception
   	 * @throws DuplicateRecordException the duplicate record exception
   	 */
	public void update(StudentDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s= HIBdatasource.getsession();
		Transaction tx =null;
		StudentDTO dtoexist= findByEmail(dto.getemail());
		if(dtoexist!=null && dtoexist.getId()!=dto.getId()){
			throw new DuplicateRecordException("Email Already Already exist");
		}
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
	 * Delete a Student.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 */
public void delete(StudentDTO dto) throws ApplicationException {
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



	public List list() throws ApplicationException {
		
		return list(0,0);
	}

	
	/**
 	 * get List of Student with pagination.
 	 *
 	 * @param pageNo the page no
 	 * @param pageSize the page size
 	 * @return list : List of Student
 	 * @throws ApplicationException the application exception
 	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(StudentDTO.class);
		if(pageSize>0){
		cr.setFirstResult(((pageNo-1)*pageSize));
		cr.setMaxResults(pageSize);
		   }
		List list= cr.list();
		return list;
	}

	
	/**
	 * Searches Student with pagination.
	 *
	 * @param dto the dto
	 * @param pageNo : Current Page No.
	 * @param pageSize the page size
	 * @return list : List of Student
	 * @throws ApplicationException the application exception
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(StudentDTO.class);
		if(dto!=null)
	{

		  if(dto.getcollegeid()>0)
		  {
		    cr.add(Restrictions.eq("collegeid",dto.getcollegeid()));
		  }
		  if(dto.getemail()!=null)
		  {
		    cr.add(Restrictions.like("email",dto.getemail()+"%"));
		  }
		  if(dto.getfirstname()!=null && dto.getfirstname().length() > 0)
		  {
		    cr.add(Restrictions.like("firstname",dto.getfirstname()+"%"));
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
 	 * Searches Student.
 	 *
 	 * @param dto the dto
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
	public List search(StudentDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

}
