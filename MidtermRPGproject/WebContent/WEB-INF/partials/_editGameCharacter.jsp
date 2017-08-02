<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<form action="ChangeName.do">
	<select name="oldCharName">
		<c:forEach items="${characters}" var="GameCharacter">
			<option value="${GameCharacter.name}">${GameCharacter.name}</option>
	</select>
	</c:forEach>
	Enter new Name: <input type="text" name="name"> <br /> 
	<input type="submit" value="Submit">
</form>