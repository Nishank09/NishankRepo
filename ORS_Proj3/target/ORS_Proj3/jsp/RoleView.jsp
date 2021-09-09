<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.controller.RoleCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css" >
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} </style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.RoleDTO"></jsp:useBean>
<%@ include file="Testheader.jsp"%>
<div class="container" style="max-width: 700px;">
<form  action="<%=ORSView.ROLE_CTL%>" method="post">
<%long id=Datautility.getLong(request.getParameter("id")); %> 


<div class="card bg-secondary text-white mx-auto" style="max-width: 700px; ">
<h2 class="text-center  font-italic" ><%=id>0?"Update Role":"Add Role" %></h2>
        <article class="card-body mx-auto" style="max-width: 400px;">
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
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">
         <h6 style="color: #040e1c" class="font-italic">Role<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-university"></i> </span>
         	   </div>
         	        <input name="role" class="form-control" placeholder="Enter Role Name" type="text" value="<%=Datautility.getstringdata(dto.getName())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("role",request) %> </font>
          </div>
          
          <h6 style="color: #040e1c" class="font-italic">Discription<font color="red">*</font></h6>
                     <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-university"></i> </span>
         	   </div>
         	        <input name="discription" class="form-control" placeholder=""  value="<%=Datautility.getstringdata(dto.getDiscription())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("discription",request) %> </font>
          </div>
                                  
            <%if (id>0){ %>                                   
                      <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=RoleCtl.OP_UPDATE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=RoleCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
                   <%}else{ %> 
                     <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=RoleCtl.OP_SAVE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=RoleCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>
            
            
                     
             </div> 
          </div>   
</form>
<%@ include file="Footer.jsp"%>
</body>
</html>