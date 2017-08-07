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
<title>Character Info</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
<h1>Player Characters</h1>
<hr>
	<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	
	<c:forEach items="${characters}" var="GameCharacter">

		<ul>
			<li>${GameCharacter.Player.name} ${GameCharacter.name}</li>

		</ul>
	</c:forEach>
	<div class="container">
		<div class="page-header">
			<h2>Character Info</h2>
		</div>
		<div class="container">
			<!-- Page Content -->
			<!-- in: playerInfo, questCompletion, levelUp, itemShop, viewInventory -->
			<!-- out: playerInfo, questStart, levelUp, itemShop, viewInventory -->




			<!--  <c:choose>
        <c:when test="${! empty allballer}">
            <ol>
                <c:forEach items="${allballer}" var="s">
                    			<ul>
				<li>${s.name}</li>
				<li>${s.team}</li>
				<li>${s.position}</li>
				<li>${s.ppg} points per game</li>
				<li>${s.rpg} rebounds per game</li>
				<li>${s.apg} assists per game</li>
				<li>${s.fieldgoalpercentage} Field Goal %</li>
				<li>${s.salary} dollars annually</li>
			</ul>
                 
                    <form action="PlayerDeleted.do" method="POST">
                               <input type="hidden" name="name" value="${s.name}" /> 
                               <input type="hidden" name="team" value="${s.team}" />
                               <input type="hidden" name="position" value="${s.position}" /> 
                               <input type="hidden" name="ppg" value="${s.ppg}" /> 
                               <input type="hidden" name="rpg" value="${s.rpg}" /> 
                               <input type="hidden" name="apg" value="${s.apg}" /> 
                               <input type="hidden" name="fieldgoalpercentage" value="${s.fieldgoalpercentage}" /> 
                               <input type="hidden" name="salary" value="${s.salary}" /> 
                               <input type="submit" value="RemovePlayer" class="submit"/><br><br>
                    </form>
                </c:forEach>
            </ol>
        </c:when>
        <c:otherwise>
        <p>No Player found</p>
        </c:otherwise>
        </c:choose>     
		 -->

		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>