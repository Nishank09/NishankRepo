package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.MarksheetDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;

public interface MarksheetModelINT {
	/**
	 * Adds a Marksheet
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when Roll No already exists
	 *
	 */
	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Finds Marksheet by PK
	 *
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public MarksheetDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Finds Marksheet by Roll No
	 *
	 * @param rollNo : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException;

	/**
	 * Deletes a Marksheet
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(MarksheetDTO dto) throws ApplicationException;

	/**
	 * Updates a Marksheet
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Searches Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 *
	 * @throws ApplicationException
	 */
	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Searches Marksheet
	 *
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(MarksheetDTO dto) throws ApplicationException;

	/**
	 * get List of Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Gets List of Marksheet
	 *
	 * @return list : List of Marksheets
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * get Merit List of Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List getMeritList(int pageNo, int PageSize) throws ApplicationException;
}
