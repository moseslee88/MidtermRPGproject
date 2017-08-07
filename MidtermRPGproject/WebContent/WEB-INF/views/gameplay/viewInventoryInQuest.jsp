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
				<div class="col-sm-6 col-md-6">
				
					<c:choose>
						<c:when test="${not empty gameCharacters}">
							<h3>Select</h3>
							<form action="ViewBattleGear.do">
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
						<c:when test="${not empty inventory}">
							<c:choose>
								<c:when test="${not empty weapons}">
									<h3>Choose Your Weapon</h3>
									<form action="SetBattleGear.do">
										<select name="id">
											<c:forEach var="weapon" items="${weapons}">
												<option value="${weapon.id}">${weapon.name}</option>
											</c:forEach>

										</select>
										<button type="submit" value="Submit">Take Weapon</button>
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
										<select name="id">
											<c:forEach var="armor" items="${armor}">
												<option value="${armor.id}">${armor.name}</option>
											</c:forEach>
										</select>
										<button type="submit" value="Submit">Use Armor</button>
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
										<select name="id">
											<c:forEach var="edible" items="${edibles}">
												<option value="${edible.id}">${edible.name}</option>
											</c:forEach>
										</select>
									</form>
								</c:when>

								<c:otherwise>
									<h3>${noEdibles}</h3>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<h3>${noItems}</h3>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${beforeStats != null}">
					<div class="col-sm-6 col-md-6">
						<div class="row">
						<h3>${beforeStats.name}</h3>					
						<p>Level: ${beforeStats.level}</p>
						<p>Health: ${beforeStats.health}</p>
						<p>Energy: ${beforeStats.energy}</p>
						<p>Power: ${beforeStats.power}</p>
						<p>Critical: ${beforeStats.critical}</p>
						<p>Physical Resistance: ${beforeStats.physicalR}</p>
						<p>Fire Resistance: ${beforeStats.fireR}</p>
						<p>Lightning Resistance: ${beforeStats.lightningR}</p>
						<p>Blood Resistance: ${beforeStats.bloodR}</p>
						<p>Frost Resistance: ${beforeStats.frostR}</p>
						
						</div>
					</div>
					</c:when>
					</c:choose>
					
					<c:choose>
					<c:when test="${afterStats != null}">
					${afterStats.health}
					${afterStats.energy}
					${afterStats.power}
					${afterStats.critical}
					${afterStats.physicalR}
					</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>