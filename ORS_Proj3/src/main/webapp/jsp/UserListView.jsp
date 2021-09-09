<%@page import="in.co.rays.proj3.model.ModelFactory"%>
<%@page import="in.co.rays.proj3.model.UserModelINT"%>
<%@page import="in.co.rays.proj3.model.RoleModelHBI"%>
<%@page import="in.co.rays.proj3.util.Htmlutility"%>
<%@page import="in.co.rays.proj3.util.Datautility"%>
<%@page import="in.co.rays.proj3.util.Serviletutility"%>
<%@page import="in.co.rays.proj3.controller.UserListCtl" %>
<%@page import="java.util.Iterator"%>
<%@page import= "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<title>UserListView</title>
<style  >
body {
	background-image: url("/ORS_Proj3/img/forstudentview.jpg");
	background-size: cover;
	background-position: center;
} 
.divider-text {
    position: relative;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 15px;
}
.divider-text span {
    padding: 7px;
    font-size: 12px;
    position: relative;   
    z-index: 2;
}
.divider-text:after {
    content: "";
    position: absolute;
    width: 100%;
    border-bottom: 1px solid #ddd;
    top: 55%;
    left: 0;
    z-index: 1;
}

.btn-facebook {
    background-color: #405D9D;
    color: #fff;
}
.btn-twitter {
    background-color: #42AEEC;
    color: #fff;
}
</style>
</head>
<body>
<jsp:useBean id="dto" scope="request" class="in.co.rays.proj3.dto.UserDTO" />
<div>
<%@include file="Testheader.jsp" %>
</div>
<div class="container-fluid">
<form action="<%=ORSView.USER_LIST_CTL %>" method="post">  



                    
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=Datautility.getTimestamp(dto.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=Datautility.getTimestamp(dto.getModifiedDateTime())%>">

<%          
            List list= Serviletutility.getlist(request); 
            List roleList=(List)request.getAttribute("roleList");
            List userList=(List)request.getAttribute("userList");
            
            
            
            
            
            
         if(list.size()==0){%>
         
            <div class="text-center" style="">
            <td><input type="submit" name="operation" value="<%=UserListCtl.OP_BACK%>"> </td>
          <% } else{ %>          
            <h3 class="text-center default-text py-3 text-light"> User List:</h3>
            
            
               <%if(!Serviletutility.getsuccessmessage(request).equals("")){ %>
  <center><div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert" >&times;</button>
    <strong>Success!</strong><font color="green"> <%=Serviletutility.getsuccessmessage(request) %></font>
    </div></center>
    <%} %>                            
	
	<%if(!Serviletutility.geterrormessage(request).equals("")){ %> 
  <center><div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font color="red"><%=Serviletutility.geterrormessage(request) %></font>
  </div></center>
   <%} %> 

            
            <div class="row">
            	  <div class="col-sm-2 "></div>&ensp;&ensp;
            	  	  <label class="form-check-label text-light" for="check2" style = "width:50px">
            	  Name:
            	       </label>&ensp;
            	  <div class="col-sm-2 "><input placeholder="firstName" type="text" id="defaultForm-email"  name="firstname" class="border" style = "width:215px"></div>&ensp;
            	       
            	       <label class="form-check-label text-light" for="check2" style = "width:50px">
            	  Email:
            	       </label>&ensp;
            	  <div class="col-sm-2 "><input placeholder="email" type="text" id="defaultForm-email"  name="email" class="border" style = "width:215px"></div>&ensp;
            	  
            	  
	                    <label class="form-check-label text-light" for="check2" style = "width:50px">
                  Role:
                        </label>
                  <div class="col-sm-2"><%=Htmlutility.getlist("roleid", String.valueOf(dto.getRoleid()), roleList) %></div>     
                  
	   <div class="col-sm-2">
	  <input   class="bg-success text-white"style="font-size: 17px" type="submit"  name="operation" value="<%=UserListCtl.OP_SEARCH%>">
	  <input  class="bg-warning text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=UserListCtl.OP_RESET%>">
	  </div>  
	   <div class="col-sm-2"></div>
	   </div>
	   <br>
	         <div class="table-responsive " >
	         
	         
      <table class="table table-striped" width="100%">
      <thead class="thead-dark">
      <tr >
      <th class="text-center"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.NO</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login Id</th>
					<th>Gender</th>
					<th>DOB</th>
					<th>Mobile No</th>
					<th>Role</th>
					<th>Edit</th>
      </tr>
      </thead>
	         <%int pageno= Serviletutility.getpageno(request);
	           int pagesize= Serviletutility.getpagesize(request);
	           int index = ((pageno-1)*pagesize)+1;
	           Iterator<UserDTO> it= list.iterator();
	           while(it.hasNext()){
	        	   dto= it.next();
	        	   RoleModelHBI rmodel = new RoleModelHBI();
	        	   RoleDTO rdto= rmodel.findByPK(dto.getRoleid());
	           
	           
	           %>
               <tbody>
				<tr class="table-danger">
				<%if(rdto.getId() == RoleDTO.Admin){ %>
				<td class="text-center"><input type="checkbox" class="" name="ids" class="case" value="" <%="disabled" %>></td>
				
				<% }else{%>
				<td class="text-center"><input type="checkbox" class="checkbox" name="ids" class="case" value="<%=dto.getId() %>"<%-- <%if(rdto.getId() == RoleDTO.Admin){ %>
                 <%="disabled" %><%} %> --%>></td>
                 <%} %>
				
                    <td><%=index++ %></td>
					<td><%=dto.getfirstname()%></td>
					<td><%=dto.getlastname()%></td>
					<td><%=dto.getLogin()%></td>
					<td><%=Datautility.getstring(dto.getGender())%></td>
					<td><%=Datautility.getDateString(dto.getDob())%></td>
					<td><%=Datautility.getstring(dto.getMobileno())%></td>
					<td><%=rdto.getName() %></td>
					<%if(rdto.getId() == RoleDTO.Admin){ %>
					<td><a class="text-dark disabled">Edit</a></td>
					<%}else{ %>
					<td><a class="text-dark" href="UserCtl?id=<%=dto.getId()%>">Edit</a></td>
					<% }%>
				</tr>
				</tbody>
				<%} %>
				</table>
				<div class="table-responsive " >
			<table width="100%">
			<tr>
			<% UserModelINT model = ModelFactory.getInstance().getUserModel(); %>
			<td><input  class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=UserListCtl.OP_PREVIOUS%>" <%=pageno > 1 ? "" : "disabled"%>></td>
			<td><input  class="bg-success text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=UserListCtl.OP_NEW%>"></td>
			<td align="right"><input  class="bg-danger text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=UserListCtl.OP_DELETE%>"></td>
			<td align="right"><input   class="bg-primary text-white" style="font-size: 17px" type="submit"  name="operation" value="<%=UserListCtl.OP_NEXT%>" <%=list.size()>=pagesize ? "":"disabled" %>>
			</td>
			</tr>
			</table>
			</div>
			
			<input type="hidden" name="pageno" value="<%=pageno%>"> 
			<input type="hidden" name="pagesize" value="<%=pagesize%>"> 
           <%}%> 
          
        </div>
</form>
<br>
</div>
<%@include file="Footer.jsp" %> 
</body> 
</html>