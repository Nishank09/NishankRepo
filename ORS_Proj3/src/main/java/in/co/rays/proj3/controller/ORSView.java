package in.co.rays.proj3.controller;


/**
 * Contains ORS View and Controller URI.
 * @author Iterator
 * @version 1.0
 */

public interface ORSView {
	/** The app context. */
	public String APP_CONTEXT = "/ORS_Proj3";
	/** The layout view. */
	public String LAYOUT= "/BaseLayout.jsp";
	/** The page folder. */
	public String PAGE_FOLDER= "/jsp";
	
	/** The java doc view. */
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";
	
	/** The error view. */
	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";
   
	/** The marksheet view. */
    public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";
    
    
    /** The marksheet list view. */
    public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
    
    /** The get marksheet view. */
    public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
    
    /** The user view. */
    public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
    
    /** The user list view. */
    public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
    
    /** The college view. */
    public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
    
    /** The college list view. */
    public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
    
    /** The student view. */
    public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
    
    /** The student list view. */
    public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
    
    /** The role view. */
    public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
    
    /** The role list view. */
    public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
    
    /** The user registration view. */
    public String USER_REGISTRATION_VIEW = PAGE_FOLDER   	
            + "/UserRegistrationView.jsp";
    
    /** The login view. */
    public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
    
    /** The welcome view. */
    public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
    
    /** The change password view. */
    public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER
            + "/ChangePasswordView.jsp";
    
    /** The my profile view. */
    public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyprofileView.jsp";
    
    /** The forget password view. */
    public String FORGET_PASSWORD_VIEW = PAGE_FOLDER
            + "/ForgetPasswordView.jsp";
    
    /** The marksheet merit list view. */
    public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER
            + "/MarksheetMeritListView.jsp";
    /** The course view. */
    public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
    
    /** The course list view. */
    public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
    
    /** The faculty view. */
    public String FACULTY_VIEW= PAGE_FOLDER + "/FacultyView.jsp";
    
    /** The faculty list view. */
    public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
            
    /** The subject view. */
    public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
    
    /** The subject list view. */
    public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
    
    /** The time table view. */
    public String TIME_TABLE_VIEW = PAGE_FOLDER + "/TimeTableView.jsp";
    
    /** The time table list view. */
    public String TIME_TABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableListView.jsp";

    /** The error ctl. */
    public String ERROR_CTL = "/ctl/ErrorCtl";
    
    /** The marksheet ctl. */
    public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
    
    /** The marksheet list ctl. */
    public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
    
    /** The user ctl. */
    public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
    
    /** The user list ctl. */
    public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
    
    /** The college ctl. */
    public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
    
    /** The college list ctl. */
    public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
    
    /** The student ctl. */
    public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
    
    /** The student list ctl. */
    public String STUDENT_LIST_CTL =APP_CONTEXT + "/ctl/StudentListCtl";
    
    /** The role ctl. */
    public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
    
    /** The role list ctl. */
    public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
    
    /** The user registration ctl. */
    public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
    
    /** The login ctl. */
    public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
    
    /** The Welcome ctl. */
    public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
    
    /** The Logout ctl. */
    public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
    
    /** The get marksheet ctl. */
    public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
    
    /** The Change password ctl. */
    public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
    
    /** The my profile ctl. */
    public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyprofileCtl";
    
    /** The forgot password ctl. */
    public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
    
    /** The marksheet merit list ctl. */
    public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT
            + "/ctl/MarksheetMeritListCtl";
    /** The course ctl. */
    public String COURSE_CTL=APP_CONTEXT+"/ctl/CourseCtl";
    
    /** The subject ctl. */
    public String SUBJECT_CTL=APP_CONTEXT+"/ctl/SubjectCtl";
    
    /** The feculty ctl. */
    public String FACULTY_CTL=APP_CONTEXT+"/ctl/FecultyCtl";
    
    /** The time table ctl. */
    public String TIME_TABLE_CTL=APP_CONTEXT+"/ctl/TimeTableCtl";
    
    /** The course list ctl. */
    public String COURSE_LIST_CTL=APP_CONTEXT+"/ctl/CourseListCtl";
    
    /** The subject list ctl. */
    public String SUBJECT_LIST_CTL=APP_CONTEXT+"/ctl/SubjectListCtl";
    
    /** The faculty list ctl. */
    public String FACULTY_LIST_CTL=APP_CONTEXT+"/ctl/FacultyListCtl";
    
    /** The time table list ctl. */
    public String TIME_TABLE_LIST_CTL=APP_CONTEXT+"/ctl/TimeTableListCtl";
    
    /** The Jasper  ctl. */
    public String JASPER_CTL = APP_CONTEXT + "/ctl/JasperCtl";

}
