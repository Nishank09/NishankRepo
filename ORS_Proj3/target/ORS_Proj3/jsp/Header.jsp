<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="in.co.rays.proj3.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

  <meta charset="utf-8">
    <!--Font Awesome  -->
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <!--Bootstrap   -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <style type="text/css">
  <!--Bootstrap End  -->

  
<!-- datepicker files -->
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  #dcolor {
	font-size: 110%;
	color: #ffffff;
}
.btn {
	height: 35px;
	width: 90px;
	background: green;
	box-shadow: 0 0 1px #ccc;
	-webkit-transition: all 0.5s ease-in-out;
	border: 0px;
	color: #fff;
	border-color: none;
}
.btn-success:hover {
	-webkit-transform: scale(1.1);
	background: Yellow;
}
.navbar-inverse{
border-color: black;
color: red;
 background-color: rgba(28, 35, 49, 0.6);
}
.dropdown-menu {
	background-color: #dedfe2;
}
.dropdown-menu {
	background-color: #dedfe2;
}

</style>

 <script type="text/javascript">
	$(function() {
		$(".dropdown").hover(function() {
			$('.dropdown-menu', this).stop(true, true).fadeIn("fast");
			$(this).toggleClass('open');
		}, function() {
			$('.dropdown-menu', this).stop(true, true).fadeOut("fast");
			$(this).toggleClass('open');
		});
	});
	
	$(function(){
		$(".dropdown").click(function(){
			$(".dropdown-menu",this).toggle();
		});
	});
	
	
</script> 
</head>
<body>
<nav class="navbar-inverse .navbar-brand{color:#9d9d9d}" >
<div class="container-fluid">
<img src="/ORS_Proj3/img/customLogo.png" align="left" height="45px" width="120px">


<% UserDTO userdto = (UserDTO)session.getAttribute("user"); %>
<ul>
       <%if(userdto==null){ %>

            <h3 align="Right"> <a href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
|
                               <a href="<%=ORSView.LOGIN_CTL%>">Login</a></h3>

       <% }else {%>
                  <%if(userdto != null){ %>
                   <h3 align="right"> Hello,<%=userdto.getfirstname() %>(<%=session.getAttribute("role") %>)
</ul>                                      <%}%></h3>
<ul >
<li><a href="<%=ORSView.MY_PROFILE_CTL%>">Edit Profile</a></li>
<li><a href="<%=ORSView.JAVA_DOC_VIEW%>">Java Document</a></li>
<li><a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
<li><a href="<%=ORSView.LOGIN_CTL%>">Login</a></li>
</ul>
         <% }%>
</ul>
<%
UserDTO user1=(UserDTO) session.getAttribute("user");
if(user1 != null){ 
if(userdto.getRoleid()==1){%>
<ul class="nav navbar-nav" style="font-style: italic">	
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
              <li class=""><a class="" id=""  href="<%=ORSView.USER_CTL%>">
                  <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add User</font></a>
              </li>
              <li><a href="<%=ORSView.USER_LIST_CTL%>">
                  <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">User List</font></a>
              </li>
          </ul>    
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Role<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
              <li class=""><a class="" id=""  href="<%=ORSView.ROLE_CTL%>">
                  <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Role</font></a>
              </li>
              <li><a href="<%=ORSView.ROLE_LIST_CTL%>">
                  <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Role List</font></a>
              </li>
          </ul>    
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Student<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
              <li class=""><a class="" id="" href="<%=ORSView.STUDENT_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add Student</font></a>
              </li>
              <li><a href="<%=ORSView.STUDENT_LIST_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Student List</font></a>
              </li>
           </ul>   
       </li>     
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">College<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
            <li class=""><a class="" id="" href="<%=ORSView.COLLEGE_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add College</font></a>
            </li>
            <li><a href="<%=ORSView.COLLEGE_LIST_CTL%>">
                <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">College List</font></a>
            </li>
          </ul>  
       </li> 
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Marksheet<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
            <li class=""><a class="" id="" href="<%=ORSView.MARKSHEET_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add Marksheet</font></a>
            </li>
            <li>
             <a href="<%=ORSView.GET_MARKSHEET_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Get Marksheet</font></a>
            </li>
            <li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Get Marksheet List</font></a>
            </li>
            <li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Get Marksheet List</font></a>
            </li> 
            <li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Get Marksheet Merrit List</font></a>
            </li>
          </ul>  
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Faculty<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
            <li class=""><a class="" id="" href="<%=ORSView.FACULTY_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add Faculty</font></a>
            </li>
            <li><a href="<%=ORSView.FACULTY_LIST_CTL%>">
                <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Faculty List</font></a>
            </li>
          </ul>  
       </li> 
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Course<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
            <li class=""><a class="" id="" href="<%=ORSView.COURSE_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add Course</font></a>
            </li>
            <li><a href="<%=ORSView.COURSE_LIST_CTL%>">
                <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Course List</font></a>
            </li>
          </ul>  
       </li>        
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Subject<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
            <li class=""><a class="" id="" href="<%=ORSView.SUBJECT_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add Subject</font></a>
            </li>
            <li><a href="<%=ORSView.SUBJECT_LIST_CTL%>">
                <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Subject List</font></a>
            </li>
          </ul>  
       </li>  
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">TimeTable<span class="caret"></span></a>
          <ul class="dropdown-menu" style="background-color: wheat">
            <li class=""><a class="" id="" href="<%=ORSView.TIME_TABLE_CTL%>">
                 <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">Add TimeTable</font></a>
            </li>
            <li><a href="<%=ORSView.TIME_TABLE_LIST_CTL%>">
                <span class="glyphicon glyphicon-user" style="color: #990000"></span><font style="font-family: cursive;">TimeTable List</font></a>
            </li>
          </ul>  
       </li>        
</ul>
<%}%> 
<%if(user1.getRoleid()==3){ %>
<ul>
<li>Course</li>

<li><a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a></li>
<li>Subject</li>

<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a></li>
<li>TimeTable</li>

<li><a href="<%=ORSView.TIME_TABLE_LIST_CTL%>">TimeTable List</a></li>
</ul>

                       <% }%>
<%if(user1.getRoleid()==5){ %>
<ul>
<li>Marksheet</li>

<li><a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</a></li>
<li><a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Get Marksheet List</a></li>
<li><a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Get Marksheet Merit List</a></li>
<li>Course</li>

<li><a href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a></li>
<li>Subject</li>

<li><a href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a></li>
</ul>

                         <%} %>                       
<% }%>

</div>
</nav>
</body>
</html>