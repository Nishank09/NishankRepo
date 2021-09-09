package in.co.rays.proj3.dto;


/**
 * Role DTO class.
 * @author Iterator
 * @version 1.0
 */
public class RoleDTO extends BaseDTO {
	/** Role name. */
	private String name;
	/** Role description. */
	private String discription;
	/** The Constant ADMIN. */
	public static final long Admin=1;
	/** The Constant STUDENT. */
	public static final long Student=2;
	/** The Constant COLLEGE. */
	public static final long College=3;
	
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
		System.out.println("inside Roledto");
	}
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDiscription() {
		return discription;
	}
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDiscription(String discription) {
		this.discription = discription;
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
