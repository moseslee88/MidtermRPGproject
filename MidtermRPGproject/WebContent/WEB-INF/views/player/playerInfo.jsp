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
			<h2>${player.displayName}&#9<small>PLAYER INFORMATION</small>
			</h2>

		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-md-6 col-lg-4">
					<div class="thumbnail text-center">
						<img class="media-object"
							src="https://s-media-cache-ak0.pinimg.com/736x/aa/ba/e0/aabae0270c9635881205c87f3be59fc1--armor-concept-knight-concept-art.jpg"
							width="60%">
						<div class="caption text-center">
							<h3>New Character</h3>
							<br>
							<!--Button goes here for create new character -->
							<form action="PlayerCreateForm.do" method="GET">
								<input type="submit" class="btn btn-primary btn-md"
									role="button" name="Create a New Character!"
									value="Create!">
							</form>
						</div>
					</div>
				</div>
				<c:forEach items="${gameC}" var="s">
					<div class="col-sm-12 col-md-6 col-lg-4">
						<div class="thumbnail text-center">
							<img class="media-object"
								src="${s.image}"
								width="60%">
							<div class="caption text-center">
								<h3>${s.name}</h3>
								<br>Health: ${s.health} <br>Energy: ${s.energy} <br>Power:
								${s.power}<br>
								<br>
								<form action="ChooseACharacter.do" method="GET" >
									<input type="hidden" name="gameCharacterId" value="${s.id }">
									<input type="submit" class="btn btn-default btn-sm"
										role="button" value="Select!">
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>