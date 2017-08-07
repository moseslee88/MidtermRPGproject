<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<link rel="stylesheet" href="styles.css"/>
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
			<form action="PlayerCreatesChar.do" method="POST">
				Name: <input type="text" name="name" value="name"> <br /> 
				Health starts at 100 each point adds 10: <input type="number" name="health"
					min="0" value="0" /> <br /> Energy starts at 100 each point adds 10: <input
					type="number" name="energy" min="0" value="0" /> <br /> Power: <input
					type="number" name="power" min="0" value="0" /> <br /> Critical: <input
					type="number" name="critical" min="0" value="0" /> <br /> Physical
				Resistance: <input type="number" name="physicalR" min="0" value="0" /> <br />
				Fire Resistance: <input type="number" min="0" name="fireR" value="0" /> <br />
				Frost Resistance: <input type="number" min="0" name="frostR" value="0" />
				<br /> Lightning Resistance: <input type="number"
					name="lightningR" min="0" value="0" /> <br /> Blood Resistance: <input
					type="number" name="bloodR" value="0" />				
         <br>
        Image: <select name="image" id="image" class="icon-menu">

<option value="">Select an Image</option>
<option value="../../images/Knight.jpg">Knight</option>
<option value="../../images/Mage.jpg">Mage</option>
<option value="../../images/BattleMonster.jpg">Battle Monster</option>
<option value="../../images/PrisonA.jpg">Prison A</option>


				</select> <br> 
				
				<input type="submit"
					value="Create Character">

			</form>
			<!-- Page Content -->
			<!-- in: playerInfo -->
			<!-- out: playerInfo -->



		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>