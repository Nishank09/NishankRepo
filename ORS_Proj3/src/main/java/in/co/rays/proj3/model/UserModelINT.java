package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;


public interface UserModelINT {
	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws Application Exception 
	 * @throws Duplication Record Exception: When User already exist
	 */   
	
	public long add(UserDTO dto) throws ApplicationException,DuplicateRecordException;
   /**
    * 
    * Delete a User
	* 
	* @param dto
	* @throws Application Exception 
    */
	public void delete(UserDTO dto)throws ApplicationException;
	/**
    * Update a User
	* 
	* @param dto
	 * @throws DuplicateRecordException 
	* @throws Application Exception 
	* @throws Duplicate Record Exception
	 */
	public void update(UserDTO dto)throws ApplicationException, DuplicateRecordException;
	/**
	 * Find User by PK
	 * @param pk
	 * @return dto
	 * @throws Application Exception 
	 */
	
	public UserDTO authenticate(String login, String pass) throws ApplicationException;
	
	public UserDTO findbypk(long pk)throws ApplicationException;
	/**
	 * Find User by Name
	 *
	 * @param name : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public UserDTO findByLogin(String Name) throws ApplicationException;

	/**
	 * Search User with pagination
	 *
	 * @return list : List of User
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List search(UserDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search User
	 *
	 * @return list : List of User
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(UserDTO dto) throws ApplicationException;

	/**
	 * get List of User with pagination
	 *
	 * @return list : List of User
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Gets List of User
	 *
	 * @return list : List of User
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException;
	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException;
	public boolean forgetPassword(String login) throws ApplicationException;
			
}
