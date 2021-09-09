package in.co.rays.proj3.dto;

import java.util.Date;


/**
 * Timetable DTO class.
 * @author Iterator
 * @version 1.0
 */
public class TimetableDTO extends BaseDTO {

	
	/** Course Id. */

	private long courseid;
	
	/** Course Name. */
	private String coursename;
	
	/** Subject Id. */

	private long subjectid;
	
	/** Subject Name. */
	private String subjectname;
	
	/** Exam Date. */
    private Date examdate;
    
    /** Exam Time. */
    private String examtime;
    
    /** Semester. */
    private String semester;
    
    /** Timetable Descripton. */
    private String discription;
    
    
	/**
	 * @return the discription
	 */
	public String getDiscription() {
		return discription;
	}

	/**
	 * @param discription the discription to set
	 */
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public long getcourseid() {
		return courseid;
	}
	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setcourseid(long courseid) {
		this.courseid = courseid;
	}
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getcoursename() {
		return coursename;
	}
	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setcoursename(String coursename) {
		this.coursename = coursename;
	}
	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public long getsubjectid() {
		return subjectid;
	}
	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setsubjectid(long subjectid) {
		this.subjectid = subjectid;
	}
	/**
	 * Gets the subject name.
	 *
	 * @return the subject name
	 */
	public String getsubjectname() {
		return subjectname;
	}
	/**
	 * Sets the subject name.
	 *
	 * @param subjectName the new subject name
	 */
	public void setsubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	/**
	 * Gets the exam date.
	 *
	 * @return the exam date
	 */
	public Date getexamdate() {
		return examdate;
	}
	/**
	 * Sets the exam date.
	 *
	 * @param examDate the new exam date
	 */
	public void setexamdate(Date examdate) {
		this.examdate = examdate;
	}
	/**
	 * Gets the exam time.
	 *
	 * @return the exam time
	 */
	public String getexamtime() {
		return examtime;
	}
	/**
	 * Sets the exam time.
	 *
	 * @param examTime the new exam time
	 */
	public void setexamtime(String examtime) {
		this.examtime = examtime;
	}
	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getsemester() {
		return semester;
	}
	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setsemester(String semester) {
		this.semester = semester;
	}
	/* (non-Javadoc)
	 * @see in.co.rays.dto.BaseDTO#compareTo(in.co.rays.dto.BaseDTO)
	 */
	public int compareTo(BaseDTO o) {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getKey()
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
