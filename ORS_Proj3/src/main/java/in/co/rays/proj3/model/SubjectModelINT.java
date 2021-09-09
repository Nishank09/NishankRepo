package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.SubjectDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface SubjectModelINT {
	/**
	 * Add a Student
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when Student already exists
	 */
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Find Student by PK
	 *
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public SubjectDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Find Student by email
	 *
	 * @param name : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public SubjectDTO findByName(String email) throws ApplicationException;

	/**
	 * Update a Student
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : if updated user record is already exist
	 */
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Delete a Student
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(SubjectDTO dto) throws ApplicationException;

	/**
	 * Gets List of College
	 *
	 * @return list : List of College
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * get List of College with pagination
	 *
	 * @return list : List of College
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search Student with pagination
	 *
	 * @return list : List of Student
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List search(SubjectDTO dto, int pageNo, int PageSize) throws ApplicationException;

	/**
	 * Search Student
	 *
	 * @return list : List of Student
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(SubjectDTO dto) throws ApplicationException;

}


