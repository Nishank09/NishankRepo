<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.controller.MarksheetCtl"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add Marksheet</title>

<style type="text/css">
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
}
</style>
</head>
<body>

	<%@ include file="Testheader.jsp"%>
	<div class="container">
		<div class="card bg-secondary text-white mx-auto"
			style="max-width: 400px;">
			<article class="card-body mx-auto" style="max-width: 400px;">
			<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
				<jsp:useBean id="dto" scope="request"
					class="in.co.rays.proj3.dto.MarksheetDTO"></jsp:useBean>
				<%
					long id = Datautility.getLong(request.getParameter("id"));
				%>
				<h2 class="text-center  font-italic"><%=id == 0 ? "Add Marksheet" : "Update Marksheet"%></h2>

<br>

				<%
					if (!Serviletutility.getsuccessmessage(request).equals("")) {
				%>
				<div class="alert alert-success alert-dismissible fade show">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>Success! </strong><%=Serviletutility.getsuccessmessage(request)%>
				</div>
				<%
					}
				%>

				<%
					if (!Serviletutility.geterrormessage(request).equals("")) {
				%>
				<div class="alert alert-danger">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>Error!</strong><font><%=Serviletutility.geterrormessage(request)%></font>
				</div>
				<%
					}
				%>

				<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
					type="hidden" name="sid" value="<%=dto.getStudentid()%>"> <input
					type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=dto.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">


				<h6 style="color: #20B2AA">
					Roll No<font color="red">*</font>
				</h6>

				<div class="input-group mb-3">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<input name="rollno" class="form-control"
							placeholder="Enter the Roll No" type="text"
							value="<%=Datautility.getstringdata(dto.getrollno())%>">
					</div>
					<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("rollno", request)%>
					</font>
				</div>

				<h6 style="color: #20B2AA">
					Student Name<font color="red">*</font>
				</h6>
				<div class="input-group mb-3">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i
								class="fa fa-user-graduate"></i>
							</span>
							<%
								List l = (List) request.getAttribute("slist");
								String coursename = Htmlutility.getlist("studentId", String.valueOf(dto.getStudentid()), l);
							%>
						</div>
						<%=coursename%>
					</div>
					<font color="red" class="ml-5"> <%=Serviletutility.geterrormessage("list", request)%></font>
				</div>

				<h6 style="color: #20B2AA">
					Physics<font color="red">*</font>
				</h6>

				<div class="input-group mb-3">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-file-alt"></i>
							</span>
						</div>
						<input name="physics" class="form-control"
							placeholder="Enter the physics marks" type="text"
							value="<%=Datautility.getstringdata(dto.getphysics()) %>">
					</div>
					<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("physics", request)%>
					</font>
				</div>


				<h6 style="color: #20B2AA">
					Chimistry<font color="red">*</font>
				</h6>

				<div class="input-group mb-3">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-file-alt"></i>
							</span>
						</div>
						<input name="chemistry" class="form-control"
							placeholder="Enter the Chemistry marks" type="text"
							value="<%=Datautility.getstringdata(dto.getchemistry())%>">
					</div>
					<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("chemistry", request)%>
					</font>
				</div>

				<h6 style="color: #20B2AA">
					Math<font color="red">*</font>
				</h6>

				<div class="input-group mb-3">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-file-alt"></i>
							</span>
						</div>
						<input name="math" class="form-control"
							placeholder="Enter the Math marks" type="text"
							value="<%=Datautility.getstringdata(dto.getmath())%>">
					</div>
					<font color="red" class="ml-5"><%=Serviletutility.geterrormessage("math", request)%>
					</font>
				</div>


				<%
					if (id > 0) {
				%>
				<div class="form-group " align="center">
					<input type="submit" class="bg-success text-white" name="operation"
						value="<%=MarksheetCtl.OP_UPDATE%>"> <input type="submit"
						class="bg-warning text-white" name="operation"
						value="<%=MarksheetCtl.OP_CANCEL%>">
				</div>
				<!-- form-group// -->

				<%
					} else {
				%>
				<div class="form-group " align="center">
					<input type="submit" class="bg-success text-white" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>"> <input type="submit"
						class="bg-warning text-white" name="operation"
						value="<%=MarksheetCtl.OP_RESET%>">
				</div>
				<!-- form-group// -->
				<%
					}
				%>
			
		</div>
		</form>
	</div>

</body>
<%@ include file="Footer.jsp"%>
</html>