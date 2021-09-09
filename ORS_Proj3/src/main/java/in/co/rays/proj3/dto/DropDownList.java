package in.co.rays.proj3.dto;


/**
 * DropdownList interface is implemented by DTOs those are used to create drop
 * down list on HTML pages.
 * @author Iterator
 * @version 1.0
 */
public interface DropDownList{
	/**
	 * Returns key of list elements
	 * @return key
	 */
	public String getKey();

	/**
	 * Returns value of list element
	 * @return value
	 */
	public String getValue();
}