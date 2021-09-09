<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.controller.SubjectCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<style type="text/css" >
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} </style>
</head>
<body>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.SubjectDTO"></jsp:useBean>
<%@ include file="Testheader.jsp"%>
<div class="container" style="max-width: 700px;">
<form  action="<%=ORSView.SUBJECT_CTL%>" method="post">
<%long id=Datautility.getLong(request.getParameter("id")); %>


<div class="card bg-secondary text-white mx-auto" style="max-width: 700px; ">
<h2 class="text-center  font-italic" ><%=dto.getId()==0?"Add Subject":"Update Subject" %></h2>
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
            					<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=dto.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">
         <h6 style="color: #040e1c" class="font-italic">Subject Name<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-book"></i> </span>
         	   </div>
         	        <input name="subject" class="form-control" placeholder="Enter Subject Name" type="text" value="<%=Datautility.getstringdata(dto.getSubjectname())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("subject",request) %> </font>
          </div>
       <h6 style="color: #040e1c" class="font-italic">Description<font color="red">*</font></h6>
                     <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-bars"></i> </span>
         	   </div>
         	        <input name="description" class="form-control" placeholder="" type="textarea" value="<%=Datautility.getstringdata(dto.getDescription())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("description",request) %> </font>
          </div>    
          
          
          <h6 style="color: #040e1c" class="font-italic">Course<font color="red">*</font></h6>
                   <% List l = (List)request.getAttribute("list");
                   String coursename= Htmlutility.getlist("courseid", String.valueOf(dto.getCourseId()), l); %>
        
         			    <%= coursename %>
         	   
         	       
           

         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("courseid",request) %> </font>
          
          
         
             <br><br>                     
                       <%if (id>0){ %>                                   
                      <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=SubjectCtl.OP_UPDATE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
                   <%}else{ %> 
                     <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=SubjectCtl.OP_SAVE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=SubjectCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>
 
            
            
                     
             </div> 
          </div>   
</form>
</body>
<br><br><br><br>
<%@include file="Footer.jsp" %>
</html>