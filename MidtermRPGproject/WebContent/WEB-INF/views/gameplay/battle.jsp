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
<title>Battle!</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
	<div class="container">
		<div class="page-header">
			<h2>Stage</h2>
		</div>
		<div class="container">
			<!-- Page Content -->
			<!-- in: stageStart -->
			<!-- out: stageConclusion -->
			<c:choose>
				<c:when test="${winner != null}">
					<div class="jumbotron">
						<h1>${winner.name} is victorious!</h1>
						<p>
							<a class="btn btn-primary btn-lg" href="#" role="button">Continue!</a>
						</p>
					</div>
				</c:when>

				<c:otherwise>
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="thumbnail">
								<c:if test="${attackEnemy != null}">
									<div class="alert alert-danger" role="alert">You were hit
										by: ${attackEnemy}</div>
								</c:if>
								<img src="../../images/defaultImage.jpg"
									alt="../../images/defaultImage.jpg">
								<div class="caption">
										Health:
									<div class="progress">
										<div class="progress-bar progress-bar-success"
											style="${newHealthCurrent}"></div>
										<div class="progress-bar progress-bar-danger"
											style="${oldHealthCurrent}"></div>
									</div>
										Energy:
									<div class="progress">
										<div class="progress-bar progress-bar-info"
											style="${newEnergyCurrent}"></div>
									</div>
									<h3>${currentCharacter.name}</h3>
									<p>Power: ${currentCharacter.power}</p>
									<p>
										<a href="GameplayBattleLoop.do" class="btn btn-primary"
											role="button">Attack!</a>
									</p>
								</div>
							</div>
						</div>
					</div>


					<div class="col-sm-6 col-md-6">
						<div class="thumbnail">
							<c:if test="${attackCurrent != null}">
								<div class="alert alert-info" role="alert">You used:
									${attackCurrent}</div>
							</c:if>
							<img src="${enemyCharacter.image}"
								alt="../../images/defaultImage.jpg">
							<div class="caption">
								<div class="progress">
									Health:
									<div class="progress-bar progress-bar-success"
										style="${newHealthEnemy}"></div>
									<div class="progress-bar progress-bar-danger"
										style="${oldHealthEnemy}"></div>
								</div>
								<div class="progress">
									Energy:
									<div class="progress-bar progress-bar-info"
										style="${newEnergyEnemy}"></div>
								</div>
								<h3>${enemyCharacter.name}</h3>
								<p>Power: ${enemyCharacter.power}</p>
								<p>
									<!-- 					<a href="GameplayBattleLoop.do" class="btn btn-primary" role="button">Attack!</a>
 -->
								</p>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

	</div>



	<br>
	<br>
	<br>
</body>
</html>