<%@page import="in.co.rays.proj3.controller.LoginCtl"%>
<%@page import="in.co.rays.proj3.dto.RoleDTO"%>
<%@page import="in.co.rays.proj3.dto.UserDTO"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

 -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- fontawesome Library -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"

	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 
 
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 


<script>
 $(document).ready(function(){
	    $('#select_all').on('click',function(){
	        if(this.checked){
	            $('.checkbox').each(function(){
	                this.checked = true;
	            });
	        }else{
	             $('.checkbox').each(function(){
	                this.checked = false;
	            });
	        }
	    });
	    
	    $('.checkbox').on('click',function()
	    {
	        if($('.checkbox:checked').length == $('.checkbox').length){
	            $('#select_all').prop('checked',true);
	        }else{
	            $('#select_all').prop('checked',false);
	        }
	    });
	});
 
 function disable() {
	  document.getElementById("ids").disabled = true;
	}
 

	var d = new Date(90,0,1);
	$(function() {
		$( "#datepicker" ).datepicker({
	                defaultDate:d, //set the default date to Jan 1st 1990
			changeMonth: true,
			changeYear: true,
	        yearRange: '1980:2020', //set the range of years
	        dateFormat: 'dd-mm-yy' //set the format of the date
		});
	});
 
  </script>



</head>
</head>
<body>
           <%
		UserDTO userDto = (UserDTO) session.getAttribute("user");

		boolean userLoggedIn = userDto != null;

		String welcomeMsg = "Hi,";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userDto.getfirstname() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";
		}
	%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
<a href="<%=ORSView.WELCOME_CTL%>">
<div>
      <img  src="/ORS_Proj3/img/customLogo.png" class=" bg-light"height="50px" >
</div></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav  ml-auto">
    
      <%
            if (userLoggedIn) {
        %>
          <%
if (userDto.getRoleid() == 2) {
%>
<li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-user'></i> User
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="<%=ORSView.USER_CTL%>"> <i class="fas fa-user-plus"></i>Add User</a>
          <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fas fa-users"></i> User List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-file-alt' ></i> Marksheet
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Get Marksheet</a>
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fas fa-user-tag"></i>Marksheet MeritList</a>
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>"><i class="fas fa-user-tag"></i>Add Marksheet</a>
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fas fa-list-ol"></i>Marksheet List</a>
        </div>
      </li>
      
      
      <%
}else if(userDto.getRoleid()==1){
%>
 <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-user'></i> User
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.USER_CTL%>"> <i class="fas fa-user-plus"></i>Add User</a>
          <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fas fa-users"></i> User List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-file-alt' ></i> Marksheet
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"style="top: 128% ">
<!--           <a class="dropdown-item" href="#">Get Marksheet</a>
 -->          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fas fa-user-tag"></i>Marksheet MeritList</a>
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>"><i class="fas fa-file-alt"></i>Add Marksheet</a>
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fas fa-list-ol"></i>Marksheet List</a>
        </div>
      </li>
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-university'></i> College
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"><i class="fa fa-university"></i>Add College</a>
          <a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fas fa-list-ol"></i>College List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-user-circle'></i>  Role
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i class="fas fa-user-circle"></i>Add Role</a>
          <a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i class="fas fa-list-ol"></i>Role List</a>
        </div>
      </li>
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class='fas fa-user-graduate'></i>  Student
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"><i class="fas fa-user-graduate"></i>Add Student</a>
          <a class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fas fa-users"></i>Student List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fa fa-book'></i> Course
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>"><i class="fas fa-book"></i>Add Course</a>
          <a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fas fa-list-ol"></i>Course List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           <i class='fas fa-book'></i> Subject
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i class="fas fa-book"></i>Add Subject</a>
          <a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class="fas fa-list-ol "></i>Subject List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-user-tie'></i> Faculty
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>"><i class="fas fa-user-tie"></i>Add Faculty</a>
          <a class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fas fa-users"></i>Faculty List</a>
        </div>
      </li>
      <li class="nav-item dropdown ">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class='fas fa-clock'></i> TimeTable
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="top: 128% ">
          <a class="dropdown-item" href="<%=ORSView.TIME_TABLE_CTL%>"><i class="fas fa-clock"></i>Add TimeTable</a>
          <a class="dropdown-item" href="<%=ORSView.TIME_TABLE_LIST_CTL%>"><i class="fas fa-list-ol "></i>TimeTable List</a>
        </div>
      </li>
      
       
  <%
            }
            }
        %>
       <li class="nav-item dropdown " >
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<i class="fas fa-user-circle"></i>
          <%=welcomeMsg%>
        </a>

          <%
if(userLoggedIn){
      %>
       <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink " style="top: 128% ; left: 30px">
       
          <a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fa fa-user-secret" aria-hidden="true"></i>My Profile</a>
          <a class="dropdown-item" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fa fa-key"></i>Change Password</a>
          <a class="dropdown-item" href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank"><i class="fas fa-file-alt "></i>Java Doc</a>
          <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fas fa-sign-out-alt"></i>Logout</a>
       </div>
          <%
						} else {
					%> 
           <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink " style="top: 128% ; left: -58px">
       
          <a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fas fa-sign-in-alt"></i>login</a>
          <a class="dropdown-item" href="<%=ORSView.USER_REGISTRATION_CTL%>"><i class="fa fa-registered"></i>User Registration</a>
          <a class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class="fa fa-key"></i>Forget Password</a>
        </div>
      </li>
      
      <%} %>
   
      </ul>
</div>
</nav>
</body>
</html>