<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <link rel="stylesheet" href="styles.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Page Title -->
<title>Create Account</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body id="boo">
<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			           <h2>Create Account</h2>
		</div>
		<div class="container">
<!-- Page Content -->
<!-- in: home, index, login -->
<!-- out: login   --
		<form action = "main.jsp" method = "POST">    -->	
		
		<form action = "SubmitandGoToLogin.do" method = "POST">  
         Display Name: <input type = "text" name = displayName>
         <br />
         Email: <input type = "text" name = "email" />
         <br />
         Password: <input type = "text" name = "password" /><br>
         Confirm password: <input type = "text" name = "conPassword" />

         <input type = "submit" value = "Submit" />
      </form>
      
    
		
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>