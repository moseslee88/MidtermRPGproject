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
<title>Quest Start</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
	<div class="container">
		<div class="page-header">
			<h2>Quest Start</h2>
		</div>
		<div class="container">
			<!-- Page Content -->
			<!-- in: characterInfo -->
			<!-- out: stageStart -->
			<div class="jumbotron">
				<h2>${currentQuest.name}</h2>
				<p>
					${currentQuest.intro} <a class="btn btn-primary btn-lg" role="button"
						href="GameplayStageStart.do" >Continue!</a>
				</p>
			</div>


		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>