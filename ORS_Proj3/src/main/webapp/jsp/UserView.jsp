<%@page import="in.co.rays.proj3.controller.UserCtl"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<style  >
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} 
.divider-text {
    position: relative;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 15px;
}
.divider-text span {
    padding: 7px;
    font-size: 12px;
    position: relative;   
    z-index: 2;
}
.divider-text:after {
    content: "";
    position: absolute;
    width: 100%;
    border-bottom: 1px solid #ddd;
    top: 55%;
    left: 0;
    z-index: 1;
}

.btn-facebook {
    background-color: #405D9D;
    color: #fff;
}
.btn-twitter {
    background-color: #42AEEC;
    color: #fff;
}


</style>


<script>
	$(function() {
		$("#udate4").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
<title>UserView</title>
</head>
<body>

<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.UserDTO" />
<%@ include file="Testheader.jsp"%> 
<div class="container">
<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; ">
<article class="card-body mx-auto" style="max-width: 400px;">
<form action="<%=ORSView.USER_CTL %>" method="post">
     <%long id=Datautility.getLong(request.getParameter("id")); %>       
     <%List list= (List)request.getAttribute("roleList"); %>      		
     <h3 class="text-center default-text py-3"><%=(id==0)? "Add User": "Update User" %></h3>
                  		
                  		
                  		<%if(!Serviletutility.getsuccessmessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success! </strong><%=Serviletutility.getsuccessmessage(request) %>
        </div>
    <%} %>
    
    <%if(!Serviletutility.geterrormessage(request).equals("")){ %>
     <div class="alert alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font><%=Serviletutility.geterrormessage(request) %></font>
    </div>
    <%} %>  
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">
    
         <h6 style="color: #20B2AA">First Name<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
         	   </div>
         	        <input name="firstName" class="form-control" placeholder="Enter first name" type="text" value="<%=Datautility.getstringdata(dto.getfirstname())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("firstName",request) %> </font>
          </div>
         <h6 style="color:#20B2AA">Last Name<font color="red">*</font></h6>
         
           <div class="input-group mb-3">
             <div class="form-group input-group">
               <div class="input-group-prepend">
                       <span class="input-group-text"> <i class="fa fa-user"></i> </span>
               </div>
                   <input name="lastName" class="form-control" placeholder="Enter last name" type="text" value="<%=Datautility.getstringdata(dto.getlastname())%>">        
             </div>
                   <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("lastName",request) %> </font> 
           </div> 
           <h6 style="color: #20B2AA">Mobile Number<font color="red">*</font></h6>
           
         <div class="input-group mb-3">
            <div class="form-group input-group">
    	      <div class="input-group-prepend">
		              <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		      </div>
    	         <input name="mobileNumber" class="form-control" placeholder="Enter mobile number" type="text"value="<%=Datautility.getstringdata(dto.getMobileno())%>" >
           </div>
                  <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("mobileNumber",request) %> </font> 
          </div>
          <h6 style="color: #20B2AA">Email<font color="red">*</font></h6>
    
           <div class="input-group mb-3">
            <div class="form-group input-group">
    	     <div class="input-group-prepend">       	
    	     <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
    	     </div>
    	        <input name="email" class="form-control" placeholder="Enter Email address" type="email" value="<%=Datautility.getstringdata(dto.getLogin())%>">
            </div> 
                 <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("email",request) %> </font>
           </div>
           
          <h6 style="color: #20B2AA">Password<font color="red">*</font></h6>
    
          <div class="input-group mb-3">
            <div class="form-group input-group">
    	      <div class="input-group-prepend">
		      <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		      </div>
                <input name="password" class="form-control" placeholder="Enter password" type="password" value="<%=Datautility.getstringdata(dto.getPassword())%>">
            </div> 
               <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("password",request) %> </font>
         </div>
         <h6 style="color: #20B2AA">Confirm Password<font color="red">*</font></h6>
             
          <div class="input-group mb-3">
            <div class="form-group input-group">
    	      <div class="input-group-prepend">
		      <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		      </div>
                <input name="confirmpassword" class="form-control" placeholder="Enter confirm password" type="password" value="<%=((id==0)?Datautility.getstringdata(dto.getconfirmpassword()):Datautility.getstringdata(dto.getPassword())) %>">
            </div> 
              <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("password",request) %> </font>
          </div>          
          
		 <%HashMap<String,String> map = new HashMap<String,String>();
		               map.put("Male", "male");
		               map.put("Female", "female");
		               String gender= Htmlutility.getlist("gender", dto.getGender(), map);
		 %>     
		     <h6 style="color: #20B2AA">Gender<font color="red">*</font></h6>
		  <div class="input-group mb-3">
           <div class="form-group input-group">
    	      <div class="input-group-prepend">
		      <span class="input-group-text"> <i  class="fa fa-venus-mars"></i> </span>
		      </div>	    
		       <%=gender %>
		   </div> <font color="red" class="ml-5"> <%=Serviletutility.geterrormessage("gender", request) %></font>
	     </div>
             <h6 style="color: #20B2AA">Date Of Birth<font color="red">*</font></h6>
       
           <div class="input-group mb-3">
             <div class="form-group input-group">
    	      <div class="input-group-prepend">
    	      <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
		      </div>
		      <input name="dob" class="form-control"  placeholder="Enter date of birth" readonly="readonly" type="text" id="udate4" value="<%=Datautility.getDateString(dto.getDob())%>">
             </div>
          </div> 
                <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("dob",request) %> </font>
           
           <%String role=Htmlutility.getlist("role",String.valueOf(dto.getRoleid()),list);%>
               <h6 style="color: #20B2AA">Role<font color="red">*</font></h6>
               <div class="input-group mb-3">
          <div class="form-group input-group">
		    <div class="input-group-prepend">
		     <span class="input-group-text"> <i class="fa fa-venus"></i> </span>
		    </div>
           <%=role%>
          </div> 
               <font color="red" class="ml-5"> <%=Serviletutility.geterrormessage("role", request) %></font>
              </div>
              
                    <%if (id>0){ %>                                   
                      <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=UserCtl.OP_UPDATE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=UserCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
                   <%}else{ %> 
                     <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=UserCtl.OP_SAVE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=UserCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>
   

</form>
</article>	
</div>
    	     
</div>


<%@include file="Footer.jsp" %>
</body>
</html>