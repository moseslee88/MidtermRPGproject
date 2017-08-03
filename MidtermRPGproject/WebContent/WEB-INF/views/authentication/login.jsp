<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Page Title -->
<title>Login</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body id = "boo">
<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Login</h2>
		</div>
		<div class="container">
<!-- Page Content -->
<!-- in: home, index -->
<!-- out: admin, playerInfo -->	
		
						<form action = "AuthenticationRoute.do" method = "POST">
         Email: <input type = "text" name = "email" />
         <br />
         Password: <input type = "text" name = "password" />
         Confirm password: <input type = "text" name = "conPassword" />

         <input type = "submit" value = "Submit" />
      </form>
		
		<!--  <a href="AuthenticationRoute.do">AuthenticationRoute.do</a><br/>  
		<a href="AuthenticationRoute.do">AuthenticationRoute.do</a><br/>   -->
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>