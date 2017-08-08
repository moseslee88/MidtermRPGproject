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
	<hr>
	<div class="container">
		<div class="page-header">
			<h2>Character Info</h2>
		</div>
		<div class="container">
			Name:  ${GameCharacter.name}
			Health:  ${GameCharacter.health}
			Energy:  ${GameCharacter.energy}
			Power:  ${GameCharacter.power}

		                    <form action="CharacterDetails.do" method="POST">
					<input type="submit" value="Use Character" class="submit" /><br>
					<br>
				</form>


		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>