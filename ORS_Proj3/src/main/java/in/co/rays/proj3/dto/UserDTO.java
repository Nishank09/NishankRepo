package in.co.rays.proj3.dto;

import java.sql.Timestamp;
import java.util.Date;



/**
 * User DTO class.
 * @author Iterator
 * @version 1.0
 */
/**
 * @author Nishank
 *this is our bean class
 */
public class UserDTO extends BaseDTO {
	/** First name of user. */
	private String firstname;
	
	/** Last name of user. */
	private String lastname;
	
	/** Login ID of user. */
	private String login;
	
	/** Password of user. */
	private String password;
	
	/** confirmpassword of user. */
	private String confirmpassword;
	
	/** dob of user. */
	private Date dob;
	
	/** mobileno of user. */
	private String mobileno;
	
	/** roleid of user. */
	private long roleid;
	
	/** unsucessful Login. */
	private int unsucessfulLogin;
	
	/** gender of user. */
	private String gender;
	
	/** lastLogin of user. */
	private Timestamp lastLogin;
	
	/** lock of user. */
	private String lock;
	
	/** registeredIP of user. */
	private String registeredIP;
	
	/** lastLoginIP of user. */
	private String lastLoginIP;
	
	//private String roleName;

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
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * Sets the login id.
	 *
	 * @param loginId the new login id
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the confirm password.
	 *
	 * @return the confirm password
	 */
	public String getconfirmpassword() {
		return confirmpassword;
	}
	/**
	 * Sets the confirm password.
	 *
	 * @param confirmPassword the new confirm password
	 */
	public void setconfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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
	public String getMobileno() {
		return mobileno;
	}
	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public long getRoleid() {
		return roleid;
	}
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	/**
	 * Gets the unsuccessfull login.
	 *
	 * @return the unsuccessfull login
	 */
	public int getUnsucessfulLogin() {
		return unsucessfulLogin;
	}
	/**
	 * Sets the unsuccessfull login.
	 *
	 * @param unsuccessfullLogin the new unsuccessfull login
	 */
	public void setUnsucessfulLogin(int unsucessfulLogin) {
		this.unsucessfulLogin = unsucessfulLogin;
	}
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * Gets the last login.
	 *
	 * @return the last login
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	/**
	 * Sets the last login.
	 *
	 * @param lastLogin the new last login
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * Gets the user lock.
	 *
	 * @return the user lock
	 */
	public String getLock() {
		return lock;
	}
	/**
	 * Sets the user lock.
	 *
	 * @param userLock the new user lock
	 */
	public void setLock(String lock) {
		this.lock = lock;
	}
	/**
	 * Gets the registered IP.
	 *
	 * @return the registered IP
	 */
	public String getRegisteredIP() {
		return registeredIP;
	}
	/**
	 * Sets the registered IP.
	 *
	 * @param registeredIP the new registered IP
	 */
	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}
	/**
	 * Gets the last login IP.
	 *
	 * @return the last login IP
	 */
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	/**
	 * Sets the last login IP.
	 *
	 * @param lastLoginIP the new last login IP
	 */
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
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
