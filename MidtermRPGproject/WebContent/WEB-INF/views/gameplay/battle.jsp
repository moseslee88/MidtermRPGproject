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
							<a class="btn btn-primary btn-lg" role="button" href="/WEB-INF/views/player/playerInfo.jsp" >Continue!</a>
						</p>
					</div>
				</c:when>

				<c:otherwise>
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="thumbnail">
								<div class="media">
									<div class="media-body">
										<h2 class="media-heading">${currentCharacter.name}</h2>
										Power: ${currentCharacter.power} <br>
										<c:if test="${attackEnemy != null}">
											<div class="alert alert-danger" role="alert">
												${enemyCharacter.name} used ${attackEnemy}</div>
										</c:if>
									</div>
									<div class="media-right">
										<img class="media-object"
											src="<%=request.getContextPath()%>/images/${currentCharacter.name}.jpg"
											height="160em">
									</div>
								</div>

								<div class="caption" position="absolute">
									Health: ${currentCharacter.hp}
									<div class="progress">
										<c:choose>
											<c:when
												test="${currentCharacter.hp == currentCharacter.health}">
												<div class="progress-bar progress-bar-success"
													style="width: 100%"></div>
											</c:when>
											<c:otherwise>
												<div class="progress-bar progress-bar-success"
													style="width: ${newHealthCurrent}%"></div>
												<div class="progress-bar progress-bar-danger"
													style="width: ${oldHealthCurrent}%"></div>
											</c:otherwise>
										</c:choose>
									</div>
									Energy: ${currentCharacter.stamina}
									<div class="progress">
										<div class="progress-bar progress-bar-info"
											style="width: ${newEnergyCurrent}%"></div>
									</div>
									<p>
									<c:choose>
									<c:when test="${not empty currentAbilities}">
									<form action="GameplayBattleLoop.do">
									<c:forEach var="ability" items="${currentAbilities}">
										<input type="submit" class="btn btn-primary"
											role="button" name="characterAbility" value="${ability.name}"/>
									</c:forEach>
											<input type="submit" class="btn btn-primary"
											role="button" name="characterAbility" value="Defend"/>
									</form>
									</c:when>
									<c:otherwise>
									<form action="GameplayBattleLoop.do">
									<input type="submit" class="btn btn-primary"
											role="button" name="characterAbility" value="Attack!"/>
											<input type="submit" class="btn btn-primary"
											role="button" name="characterAbility" value="Defend"/>
									</form>
									</c:otherwise>
									</c:choose>
									</p>
								</div>
							</div>
						</div>


						<div class="col-sm-6 col-md-6">
							<div class="thumbnail">
								<div class="media">
									<div class="media-body">
										<h2 class="media-heading">${enemyCharacter.name}</h2>
										Power: ${enemyCharacter.power} <br>
										<c:if test="${attackCurrent != null}">
											<div class="alert alert-info" role="alert">You used:
												${attackCurrent}</div>
										</c:if>
									</div>
									<div class="media-right">
										<img class="media-object"
											src="<%=request.getContextPath()%>/images/${enemyCharacter.name}.jpg"
											height="160em">
									</div>
								</div>

								<div class="caption">
									Health: ${enemyCharacter.hp}
									<div class="progress">
										<div class="progress-bar progress-bar-success"
											style="width: ${newHealthEnemy}%"></div>
										<div class="progress-bar progress-bar-danger"
											style="width: ${oldHealthEnemy}%"></div>
									</div>
									Energy: ${enemyCharacter.stamina}
									<div class="progress">
										<div class="progress-bar progress-bar-info"
											style="width: ${newEnergyEnemy}%"></div>
									</div>

								</div>
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