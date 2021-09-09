package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.dto.StudentDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HIBdatasource;
import org.hibernate.Query;


/**
 * Hibernate Implementation of Marksheet Model.
 * @author Iterator
 * @version 1.0
 */
public class MarksheetModelHBI implements MarksheetModelINT {

	
	 /**
     * Adds a Marksheet.
     *
     * @param dto the dto
     * @return the long
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException             : throws when Roll No already exists
     */
	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s=HIBdatasource.getsession();
		Transaction tx=null;
		StudentModelINT smodel= ModelFactory.getInstance().getStudentModel();
		StudentDTO sdto= smodel.findByPK(dto.getStudentid());
		dto.setname(sdto.getfirstname() + " " + sdto.getlastname());
		MarksheetDTO dtoexist = findByRollNo(dto.getrollno());
		if(dtoexist !=null){
			throw new DuplicateRecordException("Record already exist");
		}
		try{
			tx = s.beginTransaction();
			s.save(dto);
			tx.commit();
		}
		catch(HibernateException e){
			if(tx !=null){
				tx.rollback();
			}
			throw new ApplicationException("getting error to add record");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		
		return 0;
	}

	/**
     * Finds Marksheet by PK.
     *
     * @param pk            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
	public MarksheetDTO findByPK(long pk) throws ApplicationException {
		Session s=HIBdatasource.getsession();
		MarksheetDTO dto= null;
		try{
			dto= s.get(MarksheetDTO.class, pk);
		}
		catch(HibernateException e){
			throw new ApplicationException("Getting error findbypk");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		
		return dto;
	}

	
	/**
     * Finds Marksheet by Roll No.
     *
     * @param rollNo            : get parameter
     * @return dto
     * @throws ApplicationException the application exception
     */
	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {
		Session s=HIBdatasource.getsession();
		Criteria cr= s.createCriteria(MarksheetDTO.class);
		MarksheetDTO dto= null;		
		
		try{
			cr.add(Restrictions.eq("rollno", rollNo));
			List list= cr.list();
			
			if(list.size()==1){
				dto=(MarksheetDTO) list.get(0);
			}else{
				dto = null;
			}
			
		}
		catch(HibernateException e){
			throw new ApplicationException("exception to get record by rollno");
		}
		finally{
			HIBdatasource.closesession(s);
		}
		return dto;
	}

	/**
     * Deletes a Marksheet.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     */
	public void delete(MarksheetDTO dto) throws ApplicationException {
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
	}
	/**
     * Updates a Marksheet.
     *
     * @param dto the dto
     * @throws ApplicationException the application exception
     * @throws DuplicateRecordException the duplicate record exception
     */
	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		MarksheetDTO dtoexist = findByRollNo(dto.getrollno());
		dto.getId();
		
		StudentModelINT smodel= ModelFactory.getInstance().getStudentModel();
		StudentDTO sdto= smodel.findByPK(dto.getStudentid());
		dto.setname(sdto.getfirstname() +"  "+ sdto.getlastname());  

        //dto.setname(studentDTO.getfirstname()+" "+studentDTO.getlastname());
		
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
			e.printStackTrace();
			throw new ApplicationException("Error to update record");
		}
	}

	
	 /**
     * Searches Marksheet with pagination.
     *
     * @param dto            : Search Parameters
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session s=HIBdatasource.getsession();	
		Criteria cr=s.createCriteria(MarksheetDTO.class);
		List list =null;
		try{
			if(dto !=null){
				if(dto.getrollno() !=null){
					cr.add(Restrictions.like("rollno", dto.getrollno()+"%"));
				}
				if(dto.getStudentid() != 0){
					cr.add(Restrictions.eq("studentid", dto.getStudentid()));
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

	
	 /**
     * Searches Marksheet.
     *
     * @param dto            : Search Parameters
     * @return the list
     * @throws ApplicationException the application exception
     */
	public List search(MarksheetDTO dto) throws ApplicationException {
		return search(dto,0,0);
	}

	
	/**
     * get List of Marksheet with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session s=HIBdatasource.getsession();
		Criteria cr= s.createCriteria(MarksheetDTO.class);
		if(pageSize>0){
			cr.setFirstResult(((pageNo-1)*pageSize));
			cr.setMaxResults(pageSize);
			
		}
		List list =cr.list();
		return list;
	}

	/**
     * Gets List of Marksheet.
     *
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}

	
    /**
     * get Merit List of Marksheet with pagination.
     *
     * @param pageNo            : Current Page No.
     * @param pageSize            : Size of Page
     * @return list : List of Marksheets
     * @throws ApplicationException the application exception
     */
	public List getMeritList(int pageNo, int PageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
        List list = null;
        try {
            session = HIBdatasource.getsession();

            StringBuffer hql = new StringBuffer(
                    "from MarksheetDTO order by (physics + chemistry + math) desc");

            Query query = session.createQuery(hql.toString());
            // if page size is greater than zero then apply pagination
            if (PageSize > 0) {
                /*pageNo = ((pageNo - 1) * pageSize);
                hql.append(" limit " + pageNo + "," + pageSize);*/
            
            query.setFirstResult((pageNo - 1) * PageSize);
            query.setMaxResults(PageSize);
            
            }

//            System.out.println(hql.toString());
           

            list = query.list();

        } catch (Exception e) {
            //log.error(e);
            throw new ApplicationException("Exception in  marksheet list"
                    + e.getMessage());
        } finally {
            session.close();
        }

        //log.debug("Model getMeritList End");
        return list;

	}

}
