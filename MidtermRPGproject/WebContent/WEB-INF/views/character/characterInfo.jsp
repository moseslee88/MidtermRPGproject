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
	<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Character Info</h2>
		</div>
		<div class="container">
			<div class="row">

				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="thumbnail text-center">
						<img class="media-object"
							src="https://s-media-cache-ak0.pinimg.com/736x/aa/ba/e0/aabae0270c9635881205c87f3be59fc1--armor-concept-knight-concept-art.jpg"
							width="60%">
						<div class="caption text-center">
							<h3>${currentCharacter.name}</h3>
							<br>Health: ${currentCharacter.health} <br>Energy:
							${currentCharacter.energy} <br>Power:
							${currentCharacter.power}<br> <br>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="thumbnail text-center">
						<form action="GoOnAQuest.do" method="GET">
							<select name="questId">
								<c:forEach var="quest" items="${questList}">
									<option value="${quest.id}">${quest.name}</option>
								</c:forEach>

							</select> <br> <br> <input type="submit"
								class="btn btn-primary btn-md" role="button" value="submit" />
						</form>
					</div>
				</div>

			</div>
		</div>


	</div>

	<br>
	<br>
	<br>
</body>
</html>