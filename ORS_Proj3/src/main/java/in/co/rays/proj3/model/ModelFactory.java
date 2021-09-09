package in.co.rays.proj3.model;

import java.util.HashMap;
import java.util.ResourceBundle;


//TODO: Auto-generated Javadoc
/**
* Factory of Model classes.
* @author Iterator
* @version 1.0
*/

public class ModelFactory {
	 /** The bundle. */
    private static ResourceBundle bundle = ResourceBundle.getBundle("in.co.rays.proj3.bundle.system");

    /** The Constant DATABASE. */
    private static final String DATABASE = bundle.getString("DATABASE");
    
    /** The m factory. */
    private static ModelFactory mFactory = null;
    
    /** Cache of Model classes. */
    private static HashMap<String, Object> modelCache = new HashMap<String, Object>();

    /**
     * Constructor is private so no other class can create instance of Model
     * Locator.
     */
    private ModelFactory() {

    }

    /**
     * Get the instance of Model Locator.
     *
     * @return mFactory
     */
    public static ModelFactory getInstance() {
        if (mFactory == null) {
            mFactory = new ModelFactory();
        }
        return mFactory;
    }
    
    
    /**
     * Get instance of Marksheet Model.
     *
     * @return marksheetModel
     */
    public FecultyModelINT getFacultyModel() {
    	FecultyModelINT facultyModel = (FecultyModelINT)modelCache.get("facultyModel");
        if (facultyModel == null) {
            if ("Hibernate".equals(DATABASE)) {
            	facultyModel = new FecultyModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }*/
            modelCache.put("facultyModel", facultyModel);
        }

        return facultyModel;
    }

    
    /**
     * Gets the time table model.
     *
     * @return the time table model
     */
    public TimetableModelINT getTimeTableModel() {
    	TimetableModelINT timeTableModel = (TimetableModelINT) modelCache
                .get("timeTableModel");
        if (timeTableModel == null) {
            if ("Hibernate".equals(DATABASE)) {
            	timeTableModel = new TimeTableModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }*/
            modelCache.put("timeTableModel", timeTableModel);
        }

        return timeTableModel;
    }
    
    

    /**
     * Get instance of Marksheet Model.
     *
     * @return marksheetModel
     */
    public MarksheetModelINT getMarksheetModel() {
        MarksheetModelINT marksheetModel = (MarksheetModelINT) modelCache
                .get("marksheetModel");
        if (marksheetModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                marksheetModel = new MarksheetModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }*/
            modelCache.put("marksheetModel", marksheetModel);
        }

        return marksheetModel;
    }
    
    /**
     * Get instance of Marksheet Model.
     *
     * @return marksheetModel
     */
   
    public CourseModelINT getCourseModel() {
    	CourseModelINT CourseModel = (CourseModelINT) modelCache.get("CourseModel");
        if (CourseModel == null) {
            if ("Hibernate".equals(DATABASE)) {
            	CourseModel = new CourseModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }*/
            modelCache.put("CourseModel", CourseModel);
        }

        return CourseModel;
    }

    
    /**
     * Gets the subject model.
     *
     * @return the subject model
     */
    public SubjectModelINT getSubjectModel() {
    	SubjectModelINT SubjectModel = (SubjectModelINT)modelCache.get("SubjectModel");
        if (SubjectModel == null) {
            if ("Hibernate".equals(DATABASE)) {
            	SubjectModel = new SubjectModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }*/
            modelCache.put("SubjectModel", SubjectModel);
        }

        return SubjectModel;
    }

    /**
     * Get instance of User Model.
     *
     * @return userModel
     */
    public UserModelINT getUserModel() {
        UserModelINT userModel = (UserModelINT) modelCache.get("userModel");
        if (userModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                userModel = new UserModelHBI();
            }
           /* if ("JDBC".equals(DATABASE)) {
                userModel = new UserModelJDBCImpl();
            }*/
            modelCache.put("userModel", userModel);
        }

        return userModel;
    }

    /**
     * Get instance of Collage Model.
     *
     * @return collage
     */
    public CollegeModelINT getCollegeModel() {
        CollegeModelINT collegeModel = (CollegeModelINT) modelCache
                .get("collegeModel");
        if (collegeModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                collegeModel = new CollegeModelHBI();
            }
           /* if ("JDBC".equals(DATABASE)) {
                collegeModel = new CollegeModelJDBCImpl();
            }*/
            modelCache.put("collegeModel", collegeModel);
        }

        return collegeModel;
    }

    /**
     * Get instance of Student Model.
     *
     * @return Student
     */
    public StudentModelINT getStudentModel() {
        StudentModelINT studentModel = (StudentModelINT) modelCache
                .get("StudentModel");
        if (studentModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                studentModel = new StudentModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                studentModel = new StudentModelJDBCImpl();
            }*/
            modelCache.put("studentModel", studentModel);
        }

        return studentModel;
    }

    /**
     * Get instance of Role Model.
     *
     * @return Student
     */
    public RoleModelINT getRoleModel() {
        RoleModelINT roleModel = (RoleModelINT) modelCache
                .get("roleModel");
        if (roleModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                roleModel = new RoleModelHBI();
            }
            /*if ("JDBC".equals(DATABASE)) {
                roleModel = new RoleModelJDBCImpl();
            }*/
            modelCache.put("roleModel", roleModel);
        }

        return roleModel;

    }

}
