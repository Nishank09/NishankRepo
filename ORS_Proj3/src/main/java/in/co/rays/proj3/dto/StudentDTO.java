package in.co.rays.proj3.dto;

import java.util.Date;


/**
 * Student DTO class.
 * @author Iterator
 * @version 1.0
 */
public class StudentDTO extends BaseDTO {

	
    /**
     * First Name of Student
     */
    private String firstname;
    /**
     * Last Name of Student
     */
    private String lastname;
    /**
     * Date of Birth of Student
     */
    private Date dob;
    /**
     * Mobileno of Student
     */
    private String mobileno;
    /**
     * Email of Student
     */
    private String email;
    /**
     * CollegeId of Student
     */
    private long collegeid;
    /**
     * College name of Student
     */
    private String collegename;
    
    
    /**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getfirstname() {
		return firstname;
	}
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getlastname() {
		return lastname;
	}
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getdob() {
		return dob;
	}
	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	public void setdob(Date dob) {
		this.dob = dob;
	}
	/**
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getmobileno() {
		return mobileno;
	}
	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setmobileno(String mobileNo) {
		this.mobileno = mobileNo;
	}
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getemail() {
		return email;
	}
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setemail(String email) {
		this.email = email;
	}
	/**
	 * Gets the college id.
	 *
	 * @return the college id
	 */
	public long getcollegeid() {
		return collegeid;
	}
	/**
	 * Sets the college id.
	 *
	 * @param collegeId the new college id
	 */
	public void setcollegeid(long collegeid) {
		this.collegeid = collegeid;
	}
	/**
	 * Gets the college name.
	 *
	 * @return the college name
	 */
	public String getcollegename() {
		return collegename;
	}
	/**
	 * Sets the college name.
	 *
	 * @param collegeName the new college name
	 */
	public void setcollegename(String collegename) {
		this.collegename = collegename;
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
		return firstname;
	}

}
