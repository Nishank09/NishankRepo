package in.co.rays.proj3.dto;



//TODO: Auto-generated Javadoc
/**
* The Class CollegeDTO.
* @author Iterator
* @version 1.0
*/

public class CollegeDTO extends BaseDTO {
	
	/** Name of College. */
	private String name;
	
	/** Address of college. */
	private String address;
	
	/** State of college. */
	private String state;
	
	/** city of college. */
	private String city;
	
	/** Phone Number of college. */
	private String phoneno;
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
	public String getPhoneno() {
		return phoneno;
	}
	
	/**
	 * Sets the phone no.
	 *
	 * @param phoneNo the new phone no
	 */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
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
		return name;
	}

}
