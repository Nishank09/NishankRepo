package in.co.rays.proj3.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The Class BaseDTO.
 * @author Iterator
 * @version 1.0
 */
public abstract class BaseDTO implements Serializable,Comparable<BaseDTO>,DropDownList {
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Non-Business Primary key
	 */
	protected long id;
	/**
     * Contains USER ID who created this database record
     */
	private String createdBy;
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Gets the created date time.
	 *
	 * @return the created date time
	 */
	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}
	
	
	/**
	 * Sets the created date time.
	 *
	 * @param createdDatetime the new created date time
	 */
	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	
	/**
	 * Gets the modified date time.
	 *
	 * @return the modified date time
	 */
	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}
	
	/**
	 * Sets the modified date time.
	 *
	 * @param modifiedDatetime the new modified date time
	 */
	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}
	/**
     * Contains USER ID who modified this database record
     */
	private String modifiedBy;
	/**
     * Contains Created Timestamp of database record
     */
	private Timestamp createdDateTime;
	/**
     * Contains modified Timestamp of database record
     */
	private Timestamp modifiedDateTime;
	
}
