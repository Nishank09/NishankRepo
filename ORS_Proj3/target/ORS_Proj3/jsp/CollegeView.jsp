<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.controller.CollegeCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
label {
    font-weight: 600;
    color: #555;
}

body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} 

</style>


<title></title>
</head>
<body >
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.CollegeDTO"></jsp:useBean>
<%@ include file="Testheader.jsp"%>
<div class="container">
<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; ">
<div class="card-body mx-auto" align="center" style="max-width: 400px;">

<form  action="<%=ORSView.COLLEGE_CTL%>" method="post">
<%long id=Datautility.getLong(request.getParameter("id")); %>


<h2 class="text-center default-text py-3" ><%=id==0?"Add College":"Update College" %></h2>
           
           
           <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">


        
        					<% if (!Serviletutility.getsuccessmessage(request).equals("")) {%>
				
				<div class="alert alert-success alert-dismissible ">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong> <%=Serviletutility.getsuccessmessage(request)%></strong>
				</div>
				<%
					}
					if (!Serviletutility.geterrormessage(request).equals("")) {
				%>
				<div class="alert alert-danger alert-dismissible ">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong> <%=Serviletutility.geterrormessage(request)%></strong>
				</div>
				<%
					}
				%>
            <h6 style="color: #20B2AA" align="left">College Name<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-university"></i> </span>
         	   </div>
         	        <input name="cname" class="form-control" placeholder="Enter your College name" type="text" value="<%=Datautility.getstring(dto.getName()) %>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("cname",request) %> </font>
          </div>
            
            <h6 style="color: #20B2AA" align="left">Address<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-address-card"></i> </span>
         	   </div>
         	        <input name="address" class="form-control" placeholder="Enter Address" type="text" value="<%=Datautility.getstring(dto.getAddress()) %>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("address",request) %> </font>
          </div>
            
           <h6 style="color: #20B2AA" align="left">City<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-address-card"></i> </span>
         	   </div>
         	        <input name="city" class="form-control" placeholder="Enter city" type="text" value="<%=Datautility.getstring(dto.getCity()) %>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("city",request) %> </font>
          </div>   
               
           <h6 style="color: #20B2AA" align="left">State<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-address-card"></i> </span>
         	   </div>
         	        <input name="state" class="form-control" placeholder="Enter State" type="text" value="<%=Datautility.getstring(dto.getState()) %>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("state",request) %> </font>
          </div> 
            
            <h6 style="color: #20B2AA" align="left">Phone No<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fas fa-phone"></i> </span>
         	   </div>
         	        <input name="phoneno" class="form-control" placeholder="Enter State" type="text" value="<%=Datautility.getstring(dto.getPhoneno()) %>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("phoneno",request) %> </font>
          </div> 
              
            <br>
 <%if (id>0){ %>                                   
                      <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=CollegeCtl.OP_UPDATE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=CollegeCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
                   <%}else{ %> 
                     <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=CollegeCtl.OP_SAVE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=CollegeCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>

            
            
                     
             
</form>
</div>
</div>
<%@ include file="Footer.jsp"%>
</body>

</html>