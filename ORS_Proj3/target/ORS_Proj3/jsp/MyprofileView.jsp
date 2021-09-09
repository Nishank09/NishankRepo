<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.controller.MyprofileCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>my profile</title>
<style type="text/css">
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
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
</head>
<body>
<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.UserDTO" />
<%@include file="Testheader.jsp" %>
<br>
<div class="container">

<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; " >
<article class="card-body mx-auto" style="max-width: 400px;">
	
	
	<form action="<%=ORSView.MY_PROFILE_CTL %>" method="post">
	<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>
	
	
                        <%long id=Datautility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3">My Profile: </h3>
                            
                          
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
       
        
     <h6 style="color: #20B2AA">Email<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Enter Email address" type="email" value="<%=Datautility.getstringdata(dto.getLogin())%>">
    </div> <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("email",request) %> </font>
    </div>
    <!-- form-group// -->
    
         
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
    
    <h6 style="color: #20B2AA">Last Name<font color="red">*</font></h6>
        
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="lastName" class="form-control" placeholder="Enter last name" type="text" value="<%=Datautility.getstringdata(dto.getlastname())%>" >
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("lastName",request) %> </font>
</div>     <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Mobile Number<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
     <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<input name="mobileNo" class="form-control" placeholder="Enter mobile number" type="text"value="<%=Datautility.getstringdata(dto.getMobileno())%>" >
    </div><font color="red" class="ml-5"><%=Serviletutility.geterrormessage("mobileNo",request) %> </font> 
    </div>
    <!-- form-group// -->
       
    <%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("Male", "male");
                           map.put("Female","female");
                           String gender=Htmlutility.getlist("gender",dto.getGender(), map);
                           %>
                           
    <h6 style="color: #20B2AA">Gender<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i  class="fa fa-venus-mars"></i> </span>
		</div>
		
		<%=gender%>
	</div> <font color="red" class="ml-5"> <%=Serviletutility.geterrormessage("gender", request) %></font>
	</div>
	<!-- form-group end.// -->
	
	<%-- 
     <%String role=HTMLUtility.getList("role",String.valueOf(dto.getRoleId()),list); %>
    
    <h6 style="color: #20B2AA">Role<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-venus"></i> </span>
		 </div>
<%=role%>
    </div> 
    <font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("role", request) %></font>
</div> 
        <!-- form-group// --> --%>
    
    <h6 style="color: #20B2AA">Date Of Birth<font color="red">*</font></h6>
       
       <div class="input-group mb-3">
       <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
		 </div>
		 <%System.out.print(dto.getDob()); %>
        <input name="dob" id="udate4" class="form-control"      value="<%=Datautility.getDateString(dto.getDob())%>">
    </div> 
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("dob",request) %> </font>
</div>
        <!-- form-group// -->
    
    
                                        
   
   
   <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=MyprofileCtl.OP_SAVE%>">   
                <input type="submit" class="bg-success text-white"  name="operation" value="<%=MyprofileCtl.OP_CHANGE_MY_PASSWORD%>">
     </div> <!-- form-group// -->  
   
       
</form>
</article>
</div> <!-- card.// -->

</div> 
<!--container end.//-->
<br><br><br>
<%@include file="Footer.jsp" %>
</body>
</html>