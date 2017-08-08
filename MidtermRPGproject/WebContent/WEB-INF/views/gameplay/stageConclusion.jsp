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
<title>Stage Conclusion</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
	<div class="container">
		<div class="page-header">
			<h2>Stage Conclusion</h2>
		</div>
		<div class="container">
			<!-- Page Content -->
			<!-- in: stageStart, viewInventoryInQuest -->
			<!-- out: stageStart, viewInventoryInQuest, characterInfo, questConclusion -->
			<div class="jumbotron">
				<h2>${currentStage.name}</h2>
				<p>${currentStage.conclusion}</p>
				<p>And you received the ${reward.name} for your efforts (in pilfering
					pockets).</p>
				<p>
					<a class="btn btn-primary btn-lg" role="button"
						href="GameplayEndOfStage.do">Moving on...</a>
				</p>

			</div>


		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>