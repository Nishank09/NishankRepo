<%@page import="in.co.rays.proj3.controller.RoleListCtl"%>
<%@page import="in.co.rays.proj3.dto.RoleDTO"%>
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
		class="in.co.rays.proj3.dto.RoleDTO" />
	<div>
		<%@include file="Testheader.jsp"%>
	</div>
	<div class="container-fluid">
		<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">

		
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
				List rolelist = (List) request.getAttribute("rolelist");

				if (list.size() == 0) {
					
				
			%>
						<div class="text-center">
				<td><input type="button" name="operation"
					value="<%=RoleListCtl.OP_BACK%>>"></td>
					</div>
					<%}else{ %>
               <h3 class="text-center default-text text-light py-3"> Role List:</h3>
               	  <%
				if (!Serviletutility.getsuccessmessage(request).equals("")) {
			%>
			<center>
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong><font color="green"> <%=Serviletutility.getsuccessmessage(request)%></font>
			</div>
			</center>
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
	  <div class="row justify-content-center "  >
	  <div class="col-sm-2"></div>
	  <label class="form-check-label" for="check2" style = "width:50px">
       Role:
       </label>
       <div class="col-sm-2"> <%=Htmlutility.getlist("roleid", String.valueOf(dto.getId()), rolelist)%></div>
       	  <div class="col-sm-2">
	  <input   class="bg-success text-white"style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_SEARCH%>">
	  <input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_RESET%>">
	  </div> 
	  <div class="col-sm-2"></div>
	  </div>
	  <br>
	  <div class="table-responsive " >
      <table class="table table-striped" width="100%">
      <thead class="thead-dark">
      <tr >
      <th class="text-center"><input type="checkbox" id="select_all" name="select"> Select All</th>
                   <th>S.NO</th>
					<th>Name</th>
					<th>Description</th>					
					<th>Edit</th>
      </tr>
      </thead>
           <%
	                int pageno = Serviletutility.getpageno(request);
                    int pagesize = Serviletutility.getpagesize(request);
                    int index = ((pageno - 1) * pagesize) + 1;
/*                     int nextPageSize = DataUtility.getInt
 *//*                    		 (request.getAttribute("nextListSize").toString());
 */                   
                    Iterator<RoleDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<tr   class="<%=((index)%2==0)?"table-info":"table-danger"%>">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><%=index++%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getDiscription()%></td>
					
					
					<td><a class="text-dark" href="RoleCtl?id=<%=dto.getId()%>">Edit</a></td>
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
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_PREVIOUS%>" <%=pageno > 1 ? "" : "disabled"%>></td>
			<td><input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_NEW%>"></td>
			<td><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_DELETE%>"></td>
			
			<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=RoleListCtl.OP_NEXT%>" <%=list.size()>=pagesize ? "":"disabled"%>>
			</td>
			
			</tr>			
			
			</table>
			</div>			
					
			<input type="hidden" name="pageno" value="<%=pageno%>"> 
			<input type="hidden" name="pagesize" value="<%=pagesize%>">
			
						
                
       <%} %>
       <br>
<%@include file="Footer.jsp" %>
</body>
</html>