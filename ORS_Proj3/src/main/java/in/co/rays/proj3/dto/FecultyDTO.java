package in.co.rays.proj3.dto;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The Class FacultyDTO.
 * @author Iterator
 * @version 1.0
 */
public class FecultyDTO extends BaseDTO {
	/** The subject id. */
	private long subjectId;
	
	/** The subject name. */
	private String subjectName;
	
	/** The course id. */
	private long courseId;
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/** The course name. */
	private String courseName;
	
	/** Course ID. */
	private long collegeId;
	
	/** College Name. */
	private String collegeName;
	
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The dob. */
	private Date dob;
	
	/** The mobile no. */
	private String mobileNo;
	/** Faculty Gender. */
	private String gender;
	/** The email. */
	private String email;
	
	/** Qualification of Faculty. */
	private String qualification;
	
	/** Joiningdate of Faculty. */
	private Date joiningdate; 
	
	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the joiningdate
	 */
	public Date getJoiningdate() {
		return joiningdate;
	}

	/**
	 * @param joiningdate the joiningdate to set
	 */
	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}


	/**
	 * @return the subjectId
	 */
	public long getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return the courseId
	 */
	public long getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * Gets the college id.
	 *
	 * @return the college id
	 */
	public long getCollegeId() {
		return collegeId;
	}
	/**
	 * Sets the college id.
	 *
	 * @param collegeId the new college id
	 */
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the college name.
	 *
	 * @return the college name
	 */
	public String getCollegeName() {
		return collegeName;
	}
	/**
	 * Sets the college name.
	 *
	 * @param collegeName the new college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmail(String email) {
		this.email = email;
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
		return id+"";
	}
	/* (non-Javadoc)
	 * @see in.co.rays.dto.DropdownList#getValue()
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}

}
