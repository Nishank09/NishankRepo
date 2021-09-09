package in.co.rays.proj3.dto;


/**
 * The Class CourseDTO.
 * @author Iterator
 * @version 1.0
 */
public class CourseDTO extends BaseDTO {
	/** Name of Course. */
	private String coursename;
	
	/** description of Course. */
	private String description;
	
	/** duration of Course. */
	private String duration;
	
	
	
	
	/**
	 * @return the coursename
	 */
	public String getCoursename() {
		return coursename;
	}

	/**
	 * @param coursename the coursename to set
	 */
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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
		return coursename;
	}

}
