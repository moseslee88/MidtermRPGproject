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
<body>
	<jsp:include page="../../partials/_nav.jsp"></jsp:include>

	<div class="container">
		<div class="page-header">
			<h2>Create Character</h2>
		</div>
		<div class="container">
			<form action="PlayerCreatesChar.do" method="POST">
				Name: <input type="text" name="name"> <br /> Health starts
				at 100 each point adds 10: <input type="number" name="health"
					value="0" /> <br /> Energy starts at 100 each point adds 10: <input
					type="number" name="energy" value="0" /> <br /> Power: <input
					type="number" name="power" value="0" /> <br /> Critical: <input
					type="number" name="critical" value="0" /> <br /> Physical
				Resistance: <input type="number" name="physicalR" value="0" /> <br />
				Fire Resistance: <input type="number" name="fire_r" value="0" /> <br />
				Frost Resistance: <input type="number" name="frost_r" value="0" />
				<br /> Lightning Resistance: <input type="number"
					name="lightning_r" value="0" /> <br /> Blood Resistance: <input
					type="number" name="blood_r" value="0" />				
         <br>
        Image: <select name="image" id="images" class="icon-menu">

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