package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.FecultyDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;

public interface FecultyModelINT {
	/**
	 * Add a faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when faculty already exists
	 * @throws RecordNotFoundException 
	 */
	public long add(FecultyDTO dto) throws DuplicateRecordException, ApplicationException, RecordNotFoundException;

	/**
	 * Delete a faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(FecultyDTO dto) throws ApplicationException;

	/**
	 * Update a faculty
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : if updated faculty record is already exist
	 */
	public void update(FecultyDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Find faculty by PK
	 *
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 * @throws RecordNotFoundException 
	 */
	public FecultyDTO findByPK(long pk) throws ApplicationException, RecordNotFoundException;

	/**
	 * Find faculty by login
	 *
	 * @param login : get parameter
	 * @return dto
	 * @throws ApplicationException
	 * @throws RecordNotFoundException 
	 * @throws DuplicateRecordException
	 */
	public FecultyDTO findByEmail(String Email) throws ApplicationException, RecordNotFoundException;

	/**
	 * Search Faculty with pagination
	 *
	 * @return list : List of Faculty
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List search(FecultyDTO dto, int pageNo, int PageSize) throws ApplicationException;

	/**
	 * Search Faculty
	 *
	 * @return list : List of Faculty
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(FecultyDTO dto) throws ApplicationException;

	/**
	 * Get List of Faculty with pagination
	 *
	 * @return list : List of Faculty
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Get List of Faculty
	 *
	 * @return list : List of Faculty
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;
}
