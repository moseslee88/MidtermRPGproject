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
<title>Login</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body id="boo">
	<jsp:include page="../../partials/_nav.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Login</h2>
		</div>
		<div class="container">
			<!-- Page Content -->
			<!-- in: home, index -->
			<!-- out: admin, playerInfo -->
			<div class="row">
				<div class="col-sm-3 col-md-3 col-lg-3"></div>
				<div class="col-sm-6 col-md-6 col-lg-6">
					<div class="container-fluid">
						<form action="AuthenticationRoute.do" method="POST">
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input
									type="email" name="email" class="form-control"
									id="exampleInputEmail1" placeholder="Email">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password" name="password">

							</div>
							<input type="submit" class="btn btn-primary btn-sm" role="button"
								value="Log In!" />
						</form>
					</div>
				</div>
				<div class="col-sm-3 col-md-3 col-lg-3"></div>

			</div>
		</div>
		<br> <br> <br>
</body>
</html>