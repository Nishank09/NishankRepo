package in.co.rays.proj3.model;

import java.util.Date;
import java.util.List;

import in.co.rays.proj3.dto.TimetableDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;
import in.co.rays.proj3.exception.RecordNotFoundException;

public interface TimetableModelINT {
	/**
	 * Add a TimeTable
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when user already exists
	 * @throws RecordNotFoundException
	 */
	public long add(TimetableDTO dto) throws ApplicationException, DuplicateRecordException, RecordNotFoundException;

	/**
	 * Find TimeTable by PK
	 *
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public TimetableDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Update a Timetable
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : if updated user record is already exist
	 */
	public void update(TimetableDTO dto) throws ApplicationException;

	/**
	 * Delete a TimeTable
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(TimetableDTO dto) throws ApplicationException;

	/**
	 * Get List of TimeTable
	 *
	 * @return list : List of Timetable
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * Get List of Timetable with pagination
	 *
	 * @return list : List of TimeTable
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int PageSize) throws ApplicationException;

	/**
	 * Search TimeTable with pagination
	 *
	 * @return list : List of TimeTable
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search TimeTable
	 *
	 * @return list : List of TimeTable
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(TimetableDTO dto) throws ApplicationException;

	public TimetableDTO checkByCourseName(long getcourseid, Date getexamdate) throws ApplicationException;

	public TimetableDTO checkBySubjectName(long getcourseid, long getsubjectid, Date getexamdate) throws ApplicationException;

	public TimetableDTO checkBysemester(long getcourseid, long getsubjectid, String getsemester, Date getexamdate) throws ApplicationException;

}
