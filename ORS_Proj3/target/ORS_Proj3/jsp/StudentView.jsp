<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.controller.StudentCtl"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
			yearRange : '1980:2020',
			dateFormat : 'dd-mm-yy'
		});
	});
</script>

<title></title>
</head>
<body>
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.proj3.dto.StudentDTO"></jsp:useBean>
	<%@ include file="Testheader.jsp"%>
	<div class="container">
	<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; ">
<article class="card-body mx-auto" style="max-width: 400px;">
		<form action="<%=ORSView.STUDENT_CTL%>" method="post">
			<%
				long id = Datautility.getLong(request.getParameter("id"));
			%>
			<h2 class="text-center  font-italic"><%=dto.getId() == 0 ? "Add Student" : "Update Student"%></h2>
			
				
					<%
						if (!Serviletutility.getsuccessmessage(request).equals("")) {
					%>

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

<h6 style="color: #20B2AA">First Name<font color="red">*</font></h6>
<div class="input-group mb-3" >
<div class="form-group input-group">
<div class="input-group-prepend">
<span class="input-group-text"> <i class="fa fa-user"></i> </span>
</div>
<input name="fname" class="form-control" placeholder="Enter first name" type="text" value="<%=Datautility.getstring(dto.getfirstname())%>">
</div>
<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("fname", request)%>
</div>

<h6 style="color: #20B2AA">Last Name<font color="red">*</font></h6>
<div class="input-group mb-3" >
<div class="form-group input-group">
<div class="input-group-prepend">
<span class="input-group-text"> <i class="fa fa-user"></i> </span>
</div>
<input name="lname" class="form-control" placeholder="Enter last name" type="text" value="<%=Datautility.getstring(dto.getlastname())%>">
</div>
<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("lname", request)%>
</div>

<h6 style="color: #20B2AA">Email<font color="red">*</font></h6>
<div class="input-group mb-3" >
<div class="form-group input-group">
<div class="input-group-prepend">
<span class="input-group-text"> <i class="far fa-envelope"></i> </span>
</div>
<input name="email" class="form-control" placeholder="Enter Email" type="text" value="<%=Datautility.getstring(dto.getemail())%>">
</div>
<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("email", request)%>
</div>


<h6 style="color: #20B2AA">Phone No<font color="red">*</font></h6>
<div class="input-group mb-3" >
<div class="form-group input-group">
<div class="input-group-prepend">
<span class="input-group-text"> <i class="fas fa-phone"></i> </span>
</div>
<input name="mobileno" class="form-control" placeholder="Enter Mobile No" type="text" value="<%=Datautility.getstring(dto.getmobileno())%>">
</div>
<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("mobileno", request)%>
</div>

<h6 style="color: #20B2AA">Date of Birth<font color="red">*</font></h6>
<div class="input-group mb-3" >
<div class="form-group input-group">
<div class="input-group-prepend">
<span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
</div>
<input name="dob" readonly="readonly" id="udate4" class="form-control"  type="text" value="<%=Datautility.getDateString(dto.getdob())%>">
</div>
<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("dob", request)%>
</div>

<%
							List l = (List) request.getAttribute("collegelist");
							String collegename = Htmlutility.getlist("collegeid", String.valueOf(dto.getcollegeid()), l);
						%>
<h6 style="color: #20B2AA">College Name<font color="red">*</font></h6>
<div class="input-group mb-3" >
<div class="form-group input-group">
<div class="input-group-prepend">
<span class="input-group-text"> <i class="fa fa-university"></i> </span>
</div>
<%=collegename%>
</div>
<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("cname", request)%>
</div>


					


					<%
						if (id > 0) {
					%>
					<div class="form-group " align="center">
						<input type="submit" class="bg-success text-white"
							name="operation" value="<%=StudentCtl.OP_UPDATE%>"> <input
							type="submit" class="bg-warning text-white" name="operation"
							value="<%=StudentCtl.OP_CANCEL%>">
					</div>
					<!-- form-group// -->

					<%
						} else {
					%>
					<div class="form-group " align="center">
						<input type="submit" class="bg-success text-white"
							name="operation" value="<%=StudentCtl.OP_SAVE%>"> <input
							type="submit" class="bg-warning text-white" name="operation"
							value="<%=StudentCtl.OP_RESET%>">
					</div>
					<!-- form-group// -->
					<%
						}
					%>




				</div>
			
		</form>
		</div>
<%@ include file="Footer.jsp"%>
</body>

</html>