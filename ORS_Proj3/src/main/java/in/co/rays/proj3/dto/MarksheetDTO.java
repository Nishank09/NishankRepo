package in.co.rays.proj3.dto;


/**
 * Marksheet DTO class.
 * @author Iterator
 * @version 1.0
 */
public class MarksheetDTO extends BaseDTO {

	 /**
     * Rollno of Student
     */
    private String rollno;
    /**
     * ID of Student
     */
    private long studentid;
    /**
     * Name of Student
     */
    private String name;
    /**
     * Physics marks of Student
     */
    private String physics;
    /**
     * Chemistry marks of Student
     */
    private String chemistry;
    /**
     * Mathematics marks of Student
     */
    private String math;
     
    
	
    /**
	 * accessors.
	 *
	 * @return the roll no
	 */
	public String getrollno() {
		return rollno;
	}
	/**
	 * Sets the roll no.
	 *
	 * @param rollNo the new roll no
	 */
	public void setrollno(String rollno) {
		this.rollno = rollno;
	}
	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public long getStudentid() {
		return studentid;
	}
	/**
	 * Sets the student id.
	 *
	 * @param studentId the new student id
	 */
	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getname() {
		return name;
	}
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setname(String name) {
		this.name = name;
	}
	/**
	 * Gets the physics.
	 *
	 * @return the physics
	 */
	public String getphysics() {
		return physics;
	}
	/**
	 * Sets the physics.
	 *
	 * @param physics the new physics
	 */
	public void setphysics(String physics) {
		this.physics = physics;
	}
	/**
	 * Gets the chemistry.
	 *
	 * @return the chemistry
	 */
	public String getchemistry() {
		return chemistry;
	}
	/**
	 * Sets the chemistry.
	 *
	 * @param chemistry the new chemistry
	 */
	public void setchemistry(String chemistry) {
		this.chemistry = chemistry;
	}
	/**
	 * Gets the maths.
	 *
	 * @return the maths
	 */
	public String getmath() {
		return math;
	}
	/**
	 * Sets the maths.
	 *
	 * @param maths the new maths
	 */
	public void setmath(String math) {
		this.math = math;
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
