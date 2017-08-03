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
<title>Admin: Player</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Admin: Player</h2>
		</div>
		<div class="container">
<!-- Page Content -->
<!-- in: admin -->
<!-- out: admin -->	
<c:choose>
				<c:when test="${not empty players}">
				<h3>Select</h3>
					<form action="AdminGetPlayer.do" >
						<select name="id">
							<c:forEach var="player" items="${players}">
								<option value="${player.id}">${player.id}: ${player.displayName} </option>
							</c:forEach>
						</select>
						<button type="submit" value="Submit">Change!</button>
					</form>
					<br>
					<br>
					<h3>New</h3>
					<form action="AdminNewPlayer.do" >
					User Type ID: <input type="text" name="integerUserTypeId"> <br>
					Email: <input type="text" name="email"> <br>
					Password: <input type="text" name="password"> <br>
					Display Name: <input type="text" name="displayName"> <br>
						<button type="submit" value="Submit">Create!</button>
					</form>
				</c:when>
				<c:otherwise>
				<h3>Edit</h3>
					<form action="AdminEditPlayer.do">
						ID: ${player.id}<input type="hidden" value="${player.id}" name="id">
						<br>
						New User Type:<input type="text" name="userType" value="${player.userType}">
						New Email:<input type="text" name="email" value="${player.email}">
						New Password:<input type="text" name="password" value="${player.password}">
						New Display Name:<input type="text" name="displayName" value="${player.displayName}">
						<br>
						<button type="submit" value="Submit">Change!</button>
					</form>
					<br>
					<br>
					<h3>Delete</h3>
					<form action="AdminDeletePlayer.do">
						${player.id}: ${player.displayName}<input type="hidden" value="${player.id}" name="id">
						<br>
						<button type="submit" value="Submit">Delete!</button>
				</c:otherwise>
			</c:choose>
		
		
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>