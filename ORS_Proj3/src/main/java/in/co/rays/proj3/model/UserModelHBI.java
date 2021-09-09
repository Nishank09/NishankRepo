package in.co.rays.proj3.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;
import in.co.rays.proj3.util.EmailBuiler;
import in.co.rays.proj3.util.EmailMessage;
import in.co.rays.proj3.util.EmailUtility;
import in.co.rays.proj3.util.HIBdatasource;

/**
 * Hibernate Implementation of UserModel.
 * @author Iterator
 * @version 1.0
 */
public class UserModelHBI implements UserModelINT {

	

	/**
	 * Add a User.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk= 0;
		System.out.println("Usermodel add method called");
		Session s= HIBdatasource.getsession();
		Transaction tx = null;
        try{
            UserDTO dtoexist= findByLogin(dto.getLogin());
            if(dtoexist!=null)
            {
            	throw new DuplicateRecordException("Login Already Exist");
            }
        	
		            tx= s.beginTransaction();
		            pk= (Long) s.save(dto);
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
        finally {
			HIBdatasource.closesession(s);
		}
        return pk;
	}

	/**
 	 * Delete a User.
 	 *
 	 * @param dto the dto
 	 * @throws ApplicationException the application exception
 	 */
	public void delete(UserDTO dto)  {
		Session s= HIBdatasource.getsession();
		Transaction tx= null;
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
				
				
			}
			finally
			{
				HIBdatasource.closesession(s);
			}
		
	}

	/**
	 * Update a User.
	 *
	 * @param dto the dto
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		Session s= HIBdatasource.getsession();
		Transaction tx =null;
        
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
 	 * Find User by PK.
 	 *
 	 * @param pk            : get parameter
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public UserDTO findbypk(long pk) throws ApplicationException {
          UserDTO dto = null;
  		Session s=null;
  		try{	
  			 s= HIBdatasource.getsession();
  			 dto= (UserDTO) s.get(UserDTO.class, pk);
  			
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
 	 * Find User by PK.
 	 *
 	 * @param login the login
 	 * @return dto
 	 * @throws ApplicationException the application exception
 	 */
	public UserDTO findByLogin(String Login) throws ApplicationException {
		UserDTO dto=null;
		
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(UserDTO.class);
		cr.add(Restrictions.eq("login", Login));
        List list= cr.list(); 
        Iterator it=list.iterator();
        while(it.hasNext()){
        	dto= (UserDTO) it.next();
        }
        return dto;
	}
	

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#changePassword(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {
		boolean flag = false;
		UserDTO dtoExist = null;

		dtoExist = findbypk(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			try {
				update(dtoExist);
			} catch (Exception e) {

				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("loginId", dtoExist.getLogin());
		map.put("password", dtoExist.getPassword());
		map.put("firstName", dtoExist.getfirstname());
		map.put("lastName", dtoExist.getlastname());

		String message = EmailBuiler.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExist.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return flag;
	}


	/**
	 * Searches Users with pagination.
	 *
	 * @param dto            : Search Parameters
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Users
	 * @throws ApplicationException the application exception
	 */

	public List search(UserDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(UserDTO.class);
		if(dto!=null)
	{

		  if(dto.getRoleid()>0)
		  {
		    cr.add(Restrictions.eq("roleid",dto.getRoleid()));
		  }
		  if(dto.getfirstname()!=null)
		  {
		    cr.add(Restrictions.like("firstname",dto.getfirstname()+"%"));
		  }
		  if(dto.getlastname()!=null)
		  {
		    cr.add(Restrictions.like("lastname",dto.getlastname()+"%"));
		  }
		  if(dto.getLogin()!=null)
		  {
		    cr.add(Restrictions.like("login",dto.getLogin()+"%"));
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
 	 * Searches User.
 	 *
 	 * @param dto            : Search Parameters
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
	public List search(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}
	/**
	 * get List of User with pagination.
	 *
	 * @param pageNo            : Current Page No.
	 * @param pageSize            : Size of Page
	 * @return list : List of Users
	 * @throws ApplicationException the application exception
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session s= HIBdatasource.getsession();
		Criteria cr= s.createCriteria(UserDTO.class);
		if(pageSize>0){
		cr.setFirstResult(((pageNo-1)*pageSize));
		cr.setMaxResults(pageSize);
		   }
		List list= cr.list();
		return list;
		}
	
	 /**
 	 * Gets List of user.
 	 *
 	 * @return list : List of Users
 	 * @throws ApplicationException the application exception
 	 */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#authenticate(java.lang.String, java.lang.String)
	 */
	public UserDTO authenticate(String login, String pass) throws ApplicationException {
		System.out.println("-----------user model called");
		UserDTO dto = null;
		Session s= null;
		try{
		 s = HIBdatasource.getsession();
		Criteria cr = s.createCriteria(UserDTO.class);
		System.out.println(cr.list());
		cr.add(Restrictions.eq("login", login));
		cr.add(Restrictions.eq("password", pass));
		List list = cr.list();
		Iterator it= list.iterator();
		while(it.hasNext()){
		 	dto =  (UserDTO) it.next();
		 	
		}
		}
	 catch(HibernateException hb) {
		hb.printStackTrace();
		throw new ApplicationException("Invalid Login ID and Password");
	} finally {
		HIBdatasource.closesession(s);
	}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#registerUser(in.co.rays.dto.UserDTO)
	 */
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = add(dto);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());

        String message = EmailBuiler.getUserRegistrationMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return pk;

	}
	/* (non-Javadoc)
	 * @see in.co.rays.model.UserModelInt#forgetPassword(java.lang.String)
	 */
	public boolean forgetPassword(String login) throws ApplicationException {
		 UserDTO userData = findByLogin(login);
	        boolean flag = false;

	        try {
				if (userData == null) {
				    try {
						throw new RecordNotFoundException("Email Id Does not matched.");
					} catch (RecordNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        HashMap<String, String> map = new HashMap<String, String>();
	        map.put("login", userData.getLogin());
	        map.put("password", userData.getPassword());
	        map.put("firstName", userData.getfirstname());
	        map.put("lastName", userData.getlastname());
	        String message = EmailBuiler.getForgetPasswordMessage(map);
	        EmailMessage msg = new EmailMessage();
	        msg.setTo(login);
	        msg.setSubject("SUNARYS ORS Password reset");
	        msg.setMessage(message);
	        msg.setMessageType(EmailMessage.HTML_MSG);
	        EmailUtility.sendMail(msg);
	        flag = true;

	        return flag;
		
	}

}
