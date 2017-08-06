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
<title>Inventory</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
	<div class="container">
		<div class="page-header">
			<h2>Inventory</h2>
		</div>
		<div class="container">
<!-- Page Content -->
<!-- in: stageConclusion -->
<!-- out: stageConclusion -->
<c:choose>
	<c:when test="${not empty gameCharacters}">
	<h3>Select</h3>
					<form action="SetBattleGear.do" >
						<select name="id">
							<c:forEach var="character" items="${gameCharacters}">
								<option value="${character.id}">${character.id}: ${character.name}  ${character.image }</option>
							</c:forEach>
						</select>
						<button type="submit" value="Submit">Submit</button>
						</form>
		</c:when>
		</c:choose>
		<c:choose>
		<c:when test="${not empty inventoryList}">
			<h3>Select Your Gear</h3>
			<c:forEach items = "${inventoryList}"  var = "item">
         <c:out value = "${item.name}"/>
         <c:out value = "${item.itemLevel}"/>
         <c:out value = "${item.value}"/>
         <c:out value = "${item.typeOfItem}"/>
         <c:out value = "${item.element}"/>
      </c:forEach>
		</c:when>
		<c:otherwise>
		No Items In Inventory
		</c:otherwise>
		</c:choose>
		
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>