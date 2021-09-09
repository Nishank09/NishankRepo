<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@page import="java.util.Calendar"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"

	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
	
	<style type="text/css">


#footer {

      height:-8px;
   /*  clear: both; */
    position: fixed;
    
    
    /* height: 70px; */
    width:100%;
    bottom:0;
  /*   margin-top: 240px; */
  text-align: center;
   background-color: light ; 
}
</style>
</head>
<body>
<%
     Calendar c = Calendar.getInstance();
%>
<div id="footer">
<center>
<b style="color:#ffffff">&copy; Copyrights RAYS Technologies <%=c.getWeekYear() %></b>
</center>
</div>
</body>
</html>