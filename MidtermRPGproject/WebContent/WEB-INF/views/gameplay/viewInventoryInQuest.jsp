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
			<div class="row">
				<div class="col-sm-8 col-md-8 col-lg-8">
					
					<c:choose>
						<c:when test="${not empty inventory}">	
							
							<c:choose>
								<c:when test="${not empty weapons}">
									<h3>Choose Your Weapon</h3>
									<form action="SetBattleGear.do">
										<select name="weaponId">
											<c:forEach var="weapon" items="${weapons}">
												<option value="${weapon.id}">${weapon.name}</option>
											</c:forEach>

										</select>
										<button type="submit" class="btn btn-primary btn-sm" role="button" value="Submit">Take Weapon</button>
									</form>
								</c:when>
								<c:otherwise>
									<h3>${unarmedWarning}</h3>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${not empty armor}">
								
									<h3>Choose Your Armor</h3>
									<form action="SetBattleGear.do">
										<select name="weaponId">
											<c:forEach var="armor" items="${armor}">
												<option value="${armor.id}">${armor.name}</option>
											</c:forEach>
										</select>
										<button type="submit" class="btn btn-primary btn-sm" role="button" value="Submit">Use Armor</button>
									</form>
								</c:when>
								<c:otherwise>
									<h3>${noArmorWarning}</h3>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${not empty edibles}">
								
									<h3>Pick Your Poison</h3>
									<form action="SetBattleGear.do">
										<select name="weaponId">
											<c:forEach var="edible" items="${edibles}">
												<option value="${edible.id}">${edible.name}</option>
											</c:forEach>
										</select>
												<button type="submit" class="btn btn-primary btn-sm" role="button" value="Submit">Consume</button>
									</form>
								</c:when>

								<c:otherwise>
									<h3></h3>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<h3>${noItems}</h3>
						</c:otherwise>
					</c:choose>
					<form action="GameplayStartBattle.do">
						<input type="Submit" class="btn btn-primary btn-md" role="button"
							value="Fight!" />
					</form>

				</div>
				<c:if test="${(currentCharacter != null)}">
						<div class="col-sm-4 col-md-4">
							<h3>${currentCharacter.name}</h3>
							<h4>Stats</h4>
							<p></p>
							<p>Health: ${currentCharacter.health}</p>
							<p>Energy: ${currentCharacter.energy}</p>
							<p>Power: ${currentCharacter.power}</p>
							<p>Critical: ${currentCharacter.critical}</p>
							<p>Physical Resistance: ${currentCharacter.physicalR}</p>
							<p>Fire Resistance: ${currentCharacter.fireR}</p>
							<p>Lightning Resistance: ${currentCharacter.lightningR}</p>
							<p>Blood Resistance: ${currentCharacter.bloodR}</p>
							<p>Frost Resistance: ${currentCharacter.frostR}</p>
						</div>
					</c:if>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>