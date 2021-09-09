<%@page import="in.co.rays.proj3.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
<style type="text/css">
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} 
</style>
</head>
<body>
<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.UserDTO" />
<%@include file="Testheader.jsp" %>
<br>
<div class="container">

<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; " >
<article class="card-body mx-auto" style="max-width: 400px;">
	
	
	<form action="<%=ORSView.FORGET_PASSWORD_CTL %>" method="post">
	<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>
	
	
                        <%long id=Datautility.getLong(request.getParameter("id")); %>
                       <br>
							<h3 class="text-center default-text py-3">Forget Password:</h3>
                            
                          
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


         <h6 >Please Enter Your Email:<font color="red">*</font></h6>           
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="login" class="form-control" placeholder="Enter Your Password" type="text" value="<%=Datautility.getstringdata(dto.getLogin())%>">
    </div>
    <font color="red" class="ml-5"><%=Serviletutility.geterrormessage("login",request) %> </font>
    </div>
     <!-- form-group// --><br>
    
                                       
    <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>">   
<%--                 <input type="submit" class="bg-success text-white"  name="operation" value="<%=ForgetPasswordCtl.OP_RESET%>">
 --%>     </div> <!-- form-group// -->  
   
   <br>
</form>
</article>
</div> <!-- card.// -->

</div> 
<!--container end.//-->
<br>


</body>
<br><br><br><br><br><br><br>
<%@ include file="Footer.jsp"%>
</html>