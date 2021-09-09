<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.controller.FecultyCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
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
<title>Insert title here</title>
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
<script>

	$(function() {
		$("#udate4").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2021',
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
</head>
<body>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.FecultyDTO"></jsp:useBean>
<%@ include file="Testheader.jsp"%>
<div class="container">
<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; ">
<article class="card-body mx-auto" style="max-width: 400px;">

<form  action="<%=ORSView.FACULTY_CTL%>" method="post">
<%long id=Datautility.getLong(request.getParameter("id")); %> 
<h2 class="text-center  font-italic" ><%=dto.getId()==0?"Add Faculty":"Update Faculty" %></h2>

       
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
				
				
            <h6 style="color: #20B2AA">First Name<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
         	   </div>
         	        <input name="fname" class="form-control" placeholder="Enter first name" type="text" value="<%=Datautility.getstring(dto.getFirstName())%>">
           </div>
           
           
           
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("fname",request) %> </font>
          </div>
            
                      <h6 style="color: #20B2AA">Last Name<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
         	   </div>
         	        <input name="lname" class="form-control" placeholder="Enter last name" type="text" value="<%=Datautility.getstring(dto.getLastName())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("lname",request) %> </font>
          </div>      
            
            <%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("male", "male");
                           map.put("female","female");
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
            
            <h6 style="color: #20B2AA"> Joining Date <font color="red">*</font></h6>
            
               <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
         	   </div>
         	   
         	        <input name="dateofjoining"  readonly="readonly" id="udate4" class="form-control" placeholder=""  value="<%=Datautility.getDateString(dto.getJoiningdate())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("dateofjoining",request) %> </font>
          </div>
                           <% List l2 = (List)request.getAttribute("list2");
                          String collegelist= Htmlutility.getlist("collegelist", String.valueOf(dto.getCollegeId()), l2);%>
            <h6 style="color: #20B2AA">College<font color="red">*</font></h6>
                <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user-tie"></i> </span>
		 </div>
        <%=collegelist %>
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("college",request) %> </font>
    </div>
            
            
                           <h6 style="color: #20B2AA">Email<font color="red">*</font></h6>
         
           <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="far fa-envelope"></i> </span>
         	   </div>
         	        <input name="email" class="form-control" placeholder="Enter Email Address" type="text" value="<%=Datautility.getstring(dto.getEmail())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("email",request) %> </font>
          </div>      
          
          <h6 style="color: #20B2AA">Phone No<font color="red">*</font></h6>
            
               <div class="input-group mb-3" >                   
         	 <div class="form-group input-group">
         	   <div class="input-group-prepend">
         			    <span class="input-group-text"> <i class="fas fa-phone"></i> </span>
         	   </div>
         	        
         	        <input name="mobileno" class="form-control" placeholder="Enter your Phone No." type="text" value="<%=Datautility.getstring(dto.getMobileNo())%>">
           </div>
         	    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("mobileno",request) %> </font>
          </div>   
    
                 
               <% List l = (List)request.getAttribute("list");
               String Courselist= Htmlutility.getlist("CourseList",String.valueOf(dto.getCourseId()), l);%>
               <h6 style="color: #20B2AA">Course<font color="red">*</font></h6>
               <div class="input-group mb-3">
          <div class="form-group input-group">
		    <div class="input-group-prepend">
		     <span class="input-group-text"> <i class="fa fa-venus"></i> </span>
		    </div>
           <%=Courselist%>
          </div> 
               <font color="red" class="ml-5"> <%=Serviletutility.geterrormessage("CourseList",request) %></font>
              </div>
    
    
          
            
                             <%if (id>0){ %>                                   
                      <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=FecultyCtl.OP_UPDATE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=FecultyCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
                   <%}else{ %> 
                     <div class="form-group " align="center">
                          <input type="submit" class="bg-success text-white" name="operation" value="<%=FecultyCtl.OP_SAVE%>">   
                          <input type="submit" class="bg-warning text-white"  name="operation" value="<%=FecultyCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>

            
             
                    
                   
             </div> 
             </div>
            
</form>
 </article>
</body>
<br><br>
<%@include file="Footer.jsp" %>
</html>