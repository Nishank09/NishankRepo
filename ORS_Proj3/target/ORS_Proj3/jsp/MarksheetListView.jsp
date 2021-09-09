<%@page import="in.co.rays.proj3.model.MarksheetModelHBI"%>
<%@page import="in.co.rays.proj3.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.proj3.dto.MarksheetDTO"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="java.util.Iterator"%>
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
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.proj3.dto.MarksheetDTO" />
	<div>
		<%@include file="Testheader.jsp"%>
	</div>
	<div class="container-fluid">
		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

						<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">

			<%
				List list = Serviletutility.getlist(request);
			System.out.println("List="+list.size());
				List studentlist = (List) request.getAttribute("studentlist");

				if (list.size() == 0) {
			%>
			
			<div class="text-center">
				<td><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_BACK%>"></td>
					</div>
				<%
					} else {
				%>
				<h3 class="text-center default-text text-light py-3">Marksheet
					List:</h3>
				<br>
				
				<%
				if (!Serviletutility.getsuccessmessage(request).equals("")) {
			%>
			
			<center><div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong><font color="green"> <%=Serviletutility.getsuccessmessage(request)%></font>
			</div></center>
			<%
				}
			%>

			<%
				if (!Serviletutility.geterrormessage(request).equals("")) {
			%>
			<center>
				<div class="alert alert-danger alert-dismissible fade show">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>Error!</strong><font color="red"><%=Serviletutility.geterrormessage(request)%></font>
				</div>
			</center>

			<%
				}
			%>
				
				
				
				<div class="row justify-content-center">
					<div class="col-sm-2"></div>
					<label class="form-check-label text-light" for="check2" style="width: 50px">
						Student: </label>
					<div class="col-sm-2"><%= Htmlutility.getlist("studentId", String.valueOf(dto.getStudentid()), studentlist)%>
					</div>

					<label class="form-check-label text-light" for="check2" style="width: 50px">
						RollNo: </label>
					<div class="col-sm-2">
						<input placeholder="Roll No" type="text" id="defaultForm-email"
							name="rollNo" class="border"
							value="<%=Serviletutility.getparameter("rollNo", request)%>">
					</div>
					<div class="col-sm-2">
						<input class="bg-success text-white" style="font-size: 17px"
							type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_SEARCH%>"> <input
							class="bg-warning text-white" style="font-size: 17px"
							type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_RESET%>">
					</div>
					<div class="col-sm-2"></div>
				</div>
				<br>

				<div class="table-responsive ">
					<table class="table table-striped" width="100%">
						<thead class="thead-dark">
							<tr>
								<th class="text-center"><input type="checkbox"
									id="select_all" name="select"> Select All</th>
								<th>S.NO</th>
								<th>RollNo</th>
								<th>Name</th>
								<th>Physics</th>
								<th>Chemistry</th>
								<th>Maths</th>
								<th>Total</th>
								<th>Percentage(%)</th>
								<th>Grade</th>
								<th>Result</th>
								<th>Edit</th>
							</tr>
						</thead>
						<%

								int pageno = Serviletutility.getpageno(request);
								int pagesize = Serviletutility.getpagesize(request);
								int index = ((pageno - 1) * pagesize) + 1;
								int nextPageSize=Datautility.getInt("nextlistsize");
								Iterator<MarksheetDTO> it = list.iterator();
								while (it.hasNext()) {
									dto = it.next();
									String grade = null;
									String result = "<font color='green'>Pass</font>";
									long p = Datautility.getLong(dto.getphysics());
									long c = Datautility.getLong(dto.getchemistry());
									long m = Datautility.getLong(dto.getmath());
									long total = (p + c + m);
									float percentage = total / 3;

									percentage = Math.round(percentage);
									int avg = (int) (total / 3);
									if (avg >= 80 && !(p <= 32 || c <= 32 || m <= 32)) {
										grade = "A+";
									} else if (avg > 60 && avg <= 80 && !(p <= 32 || c <= 32 || m <= 32)) {
										grade = "A";
									} else if (avg > 40 && avg <= 60 && !(p <= 32 || c <= 32 || m <= 32)) {
										grade = "B";
									} else if (avg < 40 && !(p <= 32 || c <= 32 || m <= 32)) {

										grade = "C";
									} else {
										grade = "D";
										result = "<font color='red'>Fail</font>";
									}
						%>
						<tbody>
							<tr class="<%=((index) % 2 == 0) ? "table-info" : "table-danger"%>">
								<td class="text-center"><input type="checkbox"
									class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
								<td class=""><%=index++%></td>
								<td><%=dto.getrollno()%></td>
								<td><%=dto.getname()%></td>
								<td><%=Datautility.getLong(dto.getphysics()) < 33 ? dto.getphysics() + "<font color='red'>*</font>" : dto.getphysics()%></td>
								<td><%=Datautility.getLong(dto.getchemistry()) < 33 ? dto.getchemistry() + "<font color='red'>*</font>"
							: dto.getchemistry()%></td>
								<td><%=Datautility.getLong(dto.getmath()) < 33 ? dto.getmath() + "<font color='red'>*</font>" : dto.getmath()%></td>
								<td><%=total%></td>
								<td><%=percentage%></td>
								<td><%=grade%></td>
								<td><%=result%></td>
								<td><a class="text-dark"
									href="MarksheetCtl?id=<%=dto.getId()%>">Edit</a></td>
							</tr>


						</tbody>
						<%
							}
						%>
						
					</table>
					</div>
					<div class= "table-responsive">
					<table width="100%">
					<tr>
					<tr>
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_PREVIOUS%>"  <%=pageno > 1 ? "" : "disabled"%>></td>
		  <td > <input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_NEW%>"></td>
				<td align="right"><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_DELETE%>"></td>	
							<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_NEXT%>" <%=list.size()>=pagesize ? "":"disabled"%>>  
					</tr>
								</table>
			</div>
					
			<input type="hidden" name="pageno" value="<%=pageno%>"> 
			<input type="hidden" name="pagesize" value="<%=pagesize%>">
					<%
						}
					%>
					</form>
				</div>
				<br>
<%@include file="Footer.jsp" %>
</body>
</html>