package in.co.rays.proj3.model;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.RoleDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.util.HIBdatasource;


/**
 * The Class RoleModelHibImpl.
 * @author Iterator
 * @version 1.0
 */
public class RoleModelHBI implements RoleModelINT {

	
	/**
	 * Add a Role.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public long add(RoleDTO dto) throws DuplicateRecordException, ApplicationException {
		long pk = 0;
		System.out.println("inside Role Model");
		Session s = HIBdatasource.getsession();
		Transaction ts = null;
		try {
			RoleDTO dtoexist = findByName(dto.getName());
			if (dtoexist != null) {
				throw new DuplicateRecordException("Record Already Exist");
			}

			ts = s.beginTransaction();
			System.out.println("Transaction begin");
			s.save(dto);
			System.out.println("Transaction Commit");
			ts.commit();
		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (ts != null) {
				ts.rollback();
			}
		} finally {
			HIBdatasource.closesession(s);
		}
		return pk;
	}

	
	/**
 	 * Delete a Role.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 */
	public void delete(RoleDTO dto) throws ApplicationException {
		Session s = HIBdatasource.getsession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Error to Delete Record");
		} finally {
			HIBdatasource.closesession(s);
		}
	}

	
	/**
 	 * Update a Role.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 * @throws DuplicateRecordException the duplicate record exception
 	 */
	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s = HIBdatasource.getsession();
		Transaction tx = null;
		try {

			tx = s.beginTransaction();
			s.update(dto);
			tx.commit();
			

		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Error to update Record");
		} finally {
			HIBdatasource.closesession(s);
		}

	}

	/**
	 * Find Role by PK.
	 *
	 * @param pk            : get parameter
	 * @return dto
	 * @throws ApplicationException the application exception
	 */
	public RoleDTO findByPK(long pk) throws ApplicationException {
		Session s = HIBdatasource.getsession();
		Transaction tx = null;
		RoleDTO dto = null;
		try {
			dto = s.get(RoleDTO.class, pk);
			
		} catch (HibernateException hb) {
			throw new ApplicationException("Error to get record by pk");

		} finally {
			HIBdatasource.closesession(s);
		}
		return dto;
	}

	/**
 	 * Find Role by Name.
 	 *
 	 * @param name            : get parameter
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public RoleDTO findByName(String name) throws ApplicationException {
		RoleDTO dtorecords = null;
		Session s = HIBdatasource.getsession();
		Criteria cr = s.createCriteria(RoleDTO.class);
		cr.add(Restrictions.eq("name", name));
		List list = cr.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dtorecords = (RoleDTO) it.next();
		}

		return dtorecords;
	}

	
	/**
	 * Searches Roles with pagination.
	 *
	 * @param dto            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Roles
	 * @throws ApplicationException the application exception
	 */
	public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session s = HIBdatasource.getsession();
		Criteria cr = s.createCriteria(RoleDTO.class);

		if (dto != null) {
			if (dto.getName() != null) {
				cr.add(Restrictions.eq("name", dto.getName()+"%"));
			}
			if (dto.getId() > 0) {
				cr.add(Restrictions.eq("id", dto.getId()));
			}
			
			
		}
		if (pageSize > 0) {
			cr.setFirstResult(((pageNo - 1) * pageSize));
			cr.setMaxResults(pageSize);
		}
		List list = cr.list();

		return list;
	}

	/**
	 * Searches Role.
	 *
	 * @param dto            : Search Parameters
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	public List search(RoleDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/**
	 * get List of Role with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Roles
	 * @throws ApplicationException the application exception
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session s = HIBdatasource.getsession();
		Criteria cr = s.createCriteria(RoleDTO.class);
		if (pageSize > 0) {
			cr.setFirstResult(((pageNo - 1) * pageSize));
			cr.setMaxResults(pageSize);
		}
		List list = cr.list();
		return list;

	}

	/**
	 * Gets List of Role.
	 *
	 * @return list : List of Roles
	 * @throws ApplicationException the application exception
	 */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}



}
