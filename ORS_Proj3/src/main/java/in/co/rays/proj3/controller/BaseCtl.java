package in.co.rays.proj3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj3.dto.BaseDTO;
import in.co.rays.proj3.dto.UserDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.util.Datautility;
import in.co.rays.proj3.util.Datavalidator;
import in.co.rays.proj3.util.Serviletutility;

//TODO: Auto-generated Javadoc
/**
* The Class BaseCtl.
* @author Iterator
* @version 1.0

*/
public abstract class BaseCtl extends HttpServlet {
	/** The Constant OP_SAVE. */
	public static final String OP_BACK = "Back";
	/** The Constant OP_SAVE. */
    public static final String OP_SAVE = "Save";
    /** The Constant OP_CANCEL. */
    public static final String OP_CANCEL = "Cancel";
    /** The Constant OP_DELETE. */
    public static final String OP_DELETE = "Delete";
    /** The Constant OP_VIEW. */
    public static final String OP_VIEW = "View";
    /** The Constant OP_RESET. */
    public static final String OP_RESET = "Reset";
    /** The Constant OP_UPDATE. */
    public static final String OP_UPDATE = "Update";
    /** The Constant OP_SEARCH. */
    public static final String OP_SEARCH = "Searce";
    /** The Constant OP_NEW. */
    public static final String OP_NEW = "New";
    /** The Constant OP_PREVIOUS. */
    public static final String OP_PREVIOUS = "Previous";
    /** The Constant OP_NEXT. */
    public static final String OP_NEXT = "Next";
    /** The Constant OP_GO. */
    public static final String OP_GO = "Go";
    /** Error message key constant. */
    public static final String MSG_ERROR = "error";
	/** Success message key constant. */
	public static final String MSG_SUCCESS = "success";
      

    /**
     * Validates input data entered by User
     * 
     * @param request
     * @return
     */
	protected boolean validate(HttpServletRequest req){
		return true;
	}
	   /**
     * Loads list and other data required to display at HTML form
     * 
     * @param request
	 * @throws ApplicationException 
     */
	protected void preload(HttpServletRequest req){
		
	}

    /**
     * Populates bean object from request parameters
     * 
     * @param request
     * @return
     */
	protected BaseDTO populateDTO(HttpServletRequest req){
		
		return null;
		
	}
	/**
	 * Populates Generic attributes Date Time Objects in dto.	
	 * @param dto the dto
	 * @param request the request
	 * @return the base DTO
	 */
	protected BaseDTO populatedatetime(BaseDTO dto ,HttpServletRequest req){
		String createdby= req.getParameter("createdby");
	    String modifiedby=req.getParameter("modifiedby");
	    UserDTO userbean= (UserDTO) req.getSession().getAttribute("user");
	    if(userbean ==null){
	    	modifiedby = "root";
	    	createdby= "root";
	    	}
	    else{
	    	modifiedby=userbean.getLogin();
	    	if(createdby.equalsIgnoreCase(null) || Datavalidator.isnull(createdby)){
	    		createdby = modifiedby;
	    	}
	    }
	    dto.setCreatedBy(createdby);
	    dto.setModifiedBy(modifiedby);
	    long l = Datautility.getLong(req.getParameter("createdatetime"));
	    if(l>0){
	    	dto.setCreatedDateTime(Datautility.gettimestamp(l));
	    }
	    else{
	    	dto.setCreatedDateTime(Datautility.currenttimestamp());
	    }
	    dto.setModifiedDateTime(Datautility.currenttimestamp());
	    return dto;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		preload(req);
		
		String op= Datautility.getstringdata(req.getParameter("operation"));
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("inside service method Step 1 op="+ op);
		if(Datavalidator.isnotnull(op) && !OP_VIEW.equalsIgnoreCase(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op) && !OP_BACK.equalsIgnoreCase(op)){
		     System.out.println("step 2"+ 
		op);
                
			if(!validate(req)){

				System.out.println("inside validate condition");
				BaseDTO dto= populateDTO(req);
				Serviletutility.setdto(dto, req);
				Serviletutility.forward(getview(), req, res);

				return;
			}

	 }
        System.out.println("service--->"+req.getMethod());
		super.service(req, res);
	}
	/**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	protected abstract String getview();
}
