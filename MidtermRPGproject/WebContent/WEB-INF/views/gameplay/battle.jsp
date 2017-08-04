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
<title>Stage</title>

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
				<c:when test="${winner is not null}">
					<div class="jumbotron">
						<h1>${winner.name}isvictorious!</h1>
						<p>
							<a class="btn btn-primary btn-lg" href="#" role="button">Continue!</a>
						</p>
					</div>
				</c:when>

				<c:otherwise>
					<div class="row">
						<div class="col-sm-6 col-md-6">
							<div class="thumbnail">
								<img src="${currentCharacter.image}" alt="../../images/defaultImage.jpg">
								<div class="caption">
								
									<h3>${currentCharacter.name}</h3>
									<p>${currentCharacter.power}</p>
									<p>
										<a href="#" class="btn btn-primary" role="button">Button</a> <a
											href="#" class="btn btn-default" role="button">Button</a>
									</p>
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