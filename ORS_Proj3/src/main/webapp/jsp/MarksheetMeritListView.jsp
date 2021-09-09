<%@page import="in.co.rays.proj3.controller.JasperCtl"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.dto.MarksheetDTO"%>
<%@page import="in.co.rays.proj3.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.proj3.controller.MarksheetMeritListCtl"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body background="/Project_3/jsp/p3.png">
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.MarksheetDTO" />
<div>
<%@include file="Testheader.jsp" %>
</div>

<div class="container-fluid">


<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">


<% List list = Serviletutility.getlist(request);

System.out.println(list);
 if (list.size()==0){ %>
 
	
        <div class="text-center">
			<td><input class="button" type="submit"  name="operation" value="<%=MarksheetListCtl.OP_BACK%>"></td>
			</div>
			<%}else{ %>
	 <h3 class="text-center default-text text-light py-3"> Marksheet Merit List:</h3>
	
	  
	   <div class="col-12 d-flex justify-content-center">
					<a class="btn btn-info" href="<%=ORSView.JASPER_CTL%>" target="blank">Click Here to
						Print MeritList</a>
						<% System.out.println("hi i am marksheet merit list view");%>
				</div>
	  <br>
	 
	  
      <div class="table-responsive " >
      <table class="table table-striped" width="100%">
      <thead class="thead-dark">
      <tr >
      <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
                   <th>S.NO</th>
					<th>Roll No</th>
					<th>Name</th>
					<th>Maths</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Total</th>
					<th>Percentage%</th>
				   
      </tr>
      </thead>
<%
	                int pageNo = Serviletutility.getpageno(request);
                    int pageSize = Serviletutility.getpagesize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                   
                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                       dto = it.next();
               %>
				
               <tbody>
				<tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
				    <td><%=dto.getrollno()%></td>
					<td><%=dto.getname()%></td>
					<td><%=dto.getmath()%></td>
					<td><%=dto.getphysics()%></td>
					<td><%=dto.getchemistry()%></td>
	                <td><%=(Datautility.getLong(dto.getchemistry())+Datautility.getLong(dto.getmath())+Datautility.getLong(dto.getphysics()))%></td>
                    <td><%=(Datautility.getLong(dto.getchemistry())+Datautility.getLong(dto.getmath())+Datautility.getLong(dto.getphysics()))/3+"%"%></td>
					

				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			<div class="table-responsive " >
			<table width="100%">
			<tr>
			<td >
			<div class="text-center">
			<input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
			</div>
			</tr>
						
			</table>
			</div>
					
			<input type="hidden" name="pageno" value="<%=pageNo%>"> 
			<input type="hidden" name="pagesize" value="<%=pageSize%>">
						
			<%}%>

 <br><br> 
</form>
</div>
<%@include file="Footer.jsp" %>
</body>
</html>