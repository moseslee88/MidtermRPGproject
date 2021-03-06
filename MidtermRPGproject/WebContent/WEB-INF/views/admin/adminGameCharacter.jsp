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
<title>Admin: Character</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
	<jsp:include page="../../partials/_nav.jsp"></jsp:include>

	<div class="container">
		<div class="page-header">
			<h2>Admin: Character</h2>
		</div>
		<div class="container">
			<!-- Page Content -->
			<!-- in: admin -->
			<!-- out: admin -->
			<c:choose>
				<c:when test="${not empty gameCharacters}">
				<h3>Select</h3>
					<form action="AdminGetGameCharacter.do" >
						<select name="id">
							<c:forEach var="gameChar" items="${gameCharacters}">
								<option value="${gameChar.id}">${gameChar.id}: ${gameChar.name}</option>
							</c:forEach>
						</select>
						<button type="submit" value="Submit">Change!</button>
					</form>
					<br>
					<br>
					<h3>New</h3>
					<form action="AdminNewGameCharacter.do" >
					Name: <input type="text" name="name"> <br>
					Level: <input type="number" name="level" max="100" min="0" value="1"> <br>
					Health: <input type="number" name="health" max="10000" min="0" value="100"> <br>
					Power: <input type="number" name="power" max="100" min="0" value="15"> <br>
					Critical: <input type="number" name="critical" max="100" min="0" value="15"> <br>
					Fire Resistance: <input type="number" name="fireR" max="100" min="0" value="10"> <br>
					Frost Resistance: <input type="number" name="fireR" max="100" min="0" value="10"> <br>
					Lightning Resistance: <input type="number" name="fireR" max="100" min="0" value="10"> <br>
					Blood Resistance: <input type="number" name="fireR" max="100" min="0" value="10"> <br>
						<button type="submit" value="Submit">Create!</button>
					</form>
				</c:when>
				<c:otherwise>
				<h3>Edit</h3>
					<form action="AdminEditGameCharacter.do">
						ID: ${gameCharacter.id}<input type="hidden" value="${gameCharacter.id}" name="id">
						<br>
						New Name:<input type="text" name="name" placeholder="${gameCharacter.name}">
						<br>
						<button type="submit" value="Submit">Change!</button>
					</form>
					<br>
					<br>
					<h3>Delete</h3>
					<form action="AdminDeleteGameCharacter.do">
						${gameCharacter.id}: ${gameCharacter.name}<input type="hidden" value="${gameCharacter.id}" name="id">
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