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
<title>Admin: Item</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Admin: Item</h2>
		</div>
		<div class="container">
<!-- Page Content -->
<!-- in: admin -->
<!-- out: admin -->	
		<c:choose>
				<c:when test="${not empty items}">
				<h3>Select</h3>
					<form action="AdminGetItem.do" >
						<select name="id">
							<c:forEach var="item" items="${items}">
								<option value="${item.id}">${item.id}: ${item.name}</option>
							</c:forEach>
						</select>
						<button type="submit" value="Submit">Change!</button>
					</form>
					<br>
					<br>
					<h3>New</h3>
					<form action="AdminNewItem.do" >
					Name: <input type="text" name="name"> <br>
					Item Level: <input type="text" name="itemLevel" max="100" min="0"> <br>
					Value: <input type="text" name="value" max="10000" min="0"> <br>
					
					Type: <select name="typeOfItem">
						 	 <option value="weapon">Weapon</option>
						  	 <option value="armor">Armor</option>
						  	 <option value="edible">Edible</option>
						  	 <option value="trash">Trash</option>
						</select>
						<br>
					
					Element: <select name="element">
						    	 	<option value="physical">Physical</option>
						     	<option value="fire">Fire</option>
					        		<option value="frost">Frost</option>
						 		<option value="lightning">Lightning</option>
						 		<option value="blood">Blood</option>
						 		<option value="dark">Dark</option>
						     </select> <br><br>
		
						<button type="submit" value="Submit">Create!</button>
					</form>
				</c:when>
				<c:otherwise>
				<h3>Edit</h3>
					<form action="AdminEditItem.do">
						ID: ${item.id}<input type="hidden" value="${item.id}" name="id"><br>
						New Name:<input type="text" name="name" placeholder="${item.name}"><br>
						New Item Level: <input type="text" name="itemLevel" placeholder="${item.itemLevel}"><br>
						New Value: <input type="text" name="value" placeholder="${item.value}"><br>
						New Type: <select name="typeOfItem">
						 			 <option value="weapon">Weapon</option>
						  			 <option value="armor">Armor</option>
						  			 <option value="edible">Edible</option>
						  	 		<option value="trash">Trash</option>
								</select>
								<br>
						New Element: <select name="element">
						    	 			<option value="physical">Physical</option>
						     			<option value="fire">Fire</option>
					        				<option value="frost">Frost</option>
						 				<option value="lightning">Lightning</option>
						 				<option value="blood">Blood</option>
						 				<option value="dark">Dark</option>
						     		</select> <br><br>
						
						<button type="submit" value="Submit">Change!</button>
					</form>
					<br>
					<br>
					<h3>Delete</h3>
					<form action="AdminDeleteItem.do">
						${item.id}: ${item.name}<input type="hidden" value="${item.id}" name="id">
						<br>
						<button type="submit" value="Submit">Delete!</button>
					</form>
				</c:otherwise>
			</c:choose>
		
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>