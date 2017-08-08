<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<!-- <link rel="stylesheet" href="styles.css"/> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Page Title -->
<title>Create Character</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body id="boo">
	<jsp:include page="../../partials/_nav.jsp"></jsp:include>

	<div class="container">
		<div class="page-header">
			<div id="title">
				<h2>Create Character</h2>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<form action="PlayerCreatesChar.do" method="POST">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<h4>Heraldry</h4>
						Name: <input type="text" name="name"
							value="Sir ${player.displayName} the Something"> <br />
						Image URL: <input type="url" maxlength="255" name="image" id="image"
							class="icon-menu"
							value="https://s-media-cache-ak0.pinimg.com/736x/aa/ba/e0/aabae0270c9635881205c87f3be59fc1--armor-concept-knight-concept-art.jpg">
						<br> <br> <input type="submit"
							class="btn btn-primary btn-md" role="button" value="Create!">
					</div>
					<div class="col-sm-6 col-md-4 col-lg-3">
						<h4>Base Stats</h4>
						Health: <input type="number" name="health" min="80" max="120"
							value="120" /> <br /> Energy: <input type="number"
							name="energy" min="100" max="100" value="100" /> <br /> Power:
						<input type="number" name="power" min="10" max="20" value="15" />
						<br /> Critical: <input type="number" name="critical" min="10"
							max="20" value="15" /> <br /> <br>
					</div>
					<div class="col-sm-6 col-md-4 col-lg-3">
						<h4>Resistances</h4>
						Physical: <input type="number" name="physicalR" min="10" max="20"
							value="15" /> <br /> Fire <input type="number" name="fireR"
							min="10" max="20" value="15" /> <br /> Frost: <input
							type="number" name="frostR" min="10" max="20" value="15" /> <br />
						Lightning <input type="number" name="lightningR" min="10" max="20"
							value="15" /> <br /> Blood: <input type="number" name="bloodR"
							min="10" max="20" value="10" />
					</div>
				</form>
				<!-- Page Content -->
				<!-- in: playerInfo -->
				<!-- out: playerInfo -->


			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>