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
<title>Create Character</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
<jsp:include page="../../partials/_resources.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Create Character</h2>
		</div>
		<div class="container">
		<form action = "main.jsp" method = "POST">
         Name: <input type = "text" name = "name">
         <br />
         Health starts at 100 each point adds 10: <input type = "number" name = "last_name" />
         <br />
         Energy starts at 100 each point adds 10: <input type = "number" name = "energy" />
         <br />
         Power: <input type = "number" name = "power" />
         <br />
         Critical: <input type = "number" name = "critical" />
         <br />
         Physical Resistance: <input type = "number" name = "physicalR" />
         <br />
         Fire Resistance: <input type = "number" name = "fire_r" />
         <br />
         Frost Resistance: <input type = "number" name = "frost_r" />
         <br />
         Lightning Resistance: <input type = "number" name = "lightning_r" />
         <br />
         Blood Resistance: <input type = "number" name = "blood_r" />
    	
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