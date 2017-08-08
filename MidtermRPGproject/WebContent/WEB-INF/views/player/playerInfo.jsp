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
<title>Player Information</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
<jsp:include page="../../partials/_nav.jsp"></jsp:include>
<jsp:include page="../../partials/_resources.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Player Information</h2>
		</div>
		<div class="container">


	  <h5>${player.displayName}</h5>
	  <h5>${player.email}</h5><br><br>
	  <ul>
			<c:forEach items="${gameC}" var="s">

	       <tr>
        
                <td>${s.name}</td>
                <td>${s.hp} HP/${s.health}</td>
                <td>${s.energy} energy</td>
                <td>${s.power} power</td>
                <td>${s.stamina} stamina</td>
                <td>${s.level} level</td>
            </tr><br>         
						                     <form action="ChooseACharacter.do" method="GET">
						                     <input type="hidden" name="gameCharacterId" value="${s.id }">gameCharacter ID: ${s.id }<br>
						                     <input type="submit" value="Select This Character!">

                                      </form><br><br>
			</c:forEach>
			</ul>
			 <!--Button goes here for create new character -->
			<form action="PlayerCreateForm.do" method="GET">
				<input type="submit" name="Create a New Character!" value="Create a new Character!">
			</form>
			
	
<a href="ContentCreationRoute.do">ContentCreationRoute.do</a><br/>
<a href="PlayerRoute.do">PlayerRoute.do</a><br/>
<a href="CharacterRoute.do">CharacterRoute.do</a><br/>
<a href="GameplayRoute.do">GameplayRoute.do</a><br/>
<a href="BattleGear.do">BattleGear.do</a><br/>
			
			
		
			
			
	
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>