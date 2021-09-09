<%@page import="in.co.rays.proj3.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.proj3.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
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
<title>User Registration</title>
</head>
<body>

<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.UserDTO" />
<%@include file="Testheader.jsp" %>
<br>
<div class="container">

<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; " >
<article class="card-body mx-auto" style="max-width: 400px;">
	
	
	<form action="<%=ORSView.USER_REGISTRATION_CTL %>" method="post">
	<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>
	
	
                        <%long id=Datautility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3">User Registration:</h3>
                            
                          
                            <!--Body-->
                            
                            		<%if(!Serviletutility.getsuccessmessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=Serviletutility.getsuccessmessage(request) %>
    </div>
    <%} %>                            
	
	<%if(!Serviletutility.geterrormessage(request).equals("")){ %>
<div class="alert alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error! </strong><font><%= Serviletutility.geterrormessage(request) %></font>
  </div>
<%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">
         
           <h6 style="color: #20B2AA">First Name<font color="red">*</font></h6>         
         
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="firstName" class="form-control" placeholder="Enter first name" type="text" value="<%=Datautility.getstringdata(dto.getfirstname())%>">
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("firstName",request) %> </font>
    </div>
     <!-- form-group// -->
    
      <h6 style="color: #20B2AA" class="paddingclass">Last Name<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="lastName" class="form-control" placeholder="Enter last name" value="<%=Datautility.getstringdata(dto.getlastname())%>" type="text">
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("lastName",request) %> </font>
</div>     <!-- form-group// -->
    
    <h6 style="color: #20B2AA" class="paddingclass">Mobile Number<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
     <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<input name="mobileNumber" class="form-control" placeholder="Enter mobile number" type="text" value="<%=Datautility.getstringdata(dto.getMobileno())%>">
    </div><font color="red" class="ml-5"><%=Serviletutility.geterrormessage("mobileNumber",request) %> </font> 
    </div>
    <!-- form-group// -->
    
        <h6 style="color: #20B2AA" class="paddingclass">Email<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Enter Email address" type="email" value="<%=Datautility.getstringdata(dto.getLogin())%>">
    </div> <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("email",request) %> </font>
    </div>
    <!-- form-group// -->
    
     <h6 style="color: #20B2AA" class="paddingclass">Password<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
   <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="password" class="form-control" placeholder="Enter password" type="password" value="<%=Datautility.getstringdata(dto.getPassword())%>">
    </div> <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("password",request) %> </font>
    </div>
    <!-- form-group// -->
    
     <h6 style="color: #20B2AA" class="paddingclass">Confirm Password<font color="red">*</font></h6>
  
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="confirmPassword" class="form-control" placeholder="Enter Confirm password" type="password" value="<%=((id==0)? Datautility.getstringdata(dto.getconfirmpassword()):Datautility.getstringdata(dto.getPassword()))%>">
    </div> <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("confirmPassword",request) %> </font>
    </div>
    <!-- form-group// --> 
    
    
    <%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("male", "male");
                           map.put("female","female");
                           String gender=Htmlutility.getlist("gender",dto.getGender(), map);
                           %>
         <h6 style="color: #20B2AA" class="paddingclass">Gender<font color="red">*</font></h6>
                           
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i  class="fa fa-venus-mars"></i> </span>
		</div>
		<%=gender%>
	</div> <font color="red" class="ml-5"> <%=Serviletutility.geterrormessage("gender", request) %></font>
	</div>
	<!-- form-group end.// -->
       
      <h6 style="color: #20B2AA" class="paddingclass">Date of Birth<font color="red">*</font></h6>
       
       <div class="input-group mb-3">
       <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
		 </div>
		 <%System.out.print(dto.getDob()); %>
        <input name="dob" class="form-control"  placeholder="Enter date of birth" readonly="readonly" type="text" id="udate4" value="<%=Datautility.getDateString(dto.getDob())%>">
    </div> 
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("dob",request) %> </font>
</div>
        <!-- form-group// -->
                                      
   
   
   <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">   
                <input type="submit" class="bg-warning text-white"  name="operation" value="<%=UserRegistrationCtl.OP_RESET%>">
                
     </div> <!-- form-group// -->  
  
     
</form>
</article>
</div> <!-- card.// -->

</div> 
<!--container end.//-->
<br>

</body>
<%@include file="Footer.jsp" %>
</html>