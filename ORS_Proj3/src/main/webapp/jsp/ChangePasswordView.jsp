<%@page import="in.co.rays.proj3.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>


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
<title>Change Passeword</title>
</head>
<body>

<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.UserDTO" />
<%@include file="Testheader.jsp" %>
<br>
<div class="container">

<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; " >
<article class="card-body mx-auto" style="max-width: 400px;">
	
	
	<form action="<%=ORSView.CHANGE_PASSWORD_CTL %>" method="post">
	<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>
	
	
                        <%long id=Datautility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3">Change Password</h3>
                            
                          
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
    <strong>Error!</strong><font><%=Serviletutility.geterrormessage(request) %></font>
  </div>
<%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">


         <h6 >Old Password:<font color="red">*</font></h6>           
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="oldPassword" class="form-control" placeholder="Enter Your Password" type="password" value="<%=Datautility.getstring(request.getParameter("oldPassword") == null ? "" : Datautility.getstring(request.getParameter("oldPassword")))%>">
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("oldPassword",request) %> </font>
    </div>
     <!-- form-group// -->
    

    <h6 >New Password:<font color="red">*</font></h6>  
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
        <input name="newPassword" class="form-control" placeholder="Enter New Password" type="password" value="<%=Datautility.getstring(request.getParameter("newPassword") == null ? "" : Datautility.getstring(request.getParameter("newPassword")))%>">
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("newPassword",request) %> </font>
</div>     <!-- form-group// -->


    <h6 >Confirm Password:<font color="red">*</font></h6>  
    <div class="input-group mb-3">
     <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
    	<input name="confirmPassword" class="form-control" placeholder="Re-Enter Password" type="password" value="<%=Datautility.getstring(request.getParameter("confirmPassword") == null ? "" : Datautility.getstring(request.getParameter("confirmPassword")))%>">
    </div><font color="red" class="ml-5"><%=Serviletutility.geterrormessage("confirmPassword",request) %> </font> 
    </div>
    <!-- form-group// -->
    
    
                                       
    <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">   
                <input type="submit" class="bg-success text-white"  name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
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