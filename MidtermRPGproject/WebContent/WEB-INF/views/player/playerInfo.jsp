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
<title>Player Information</title>

<jsp:include page="../../partials/_resources.jsp"></jsp:include>
</head>


<!-- Page Body -->
<body>
<jsp:include page="../../partials/_nav.jsp"></jsp:include>
<jsp:include page="../../partials/_resources.jsp"></jsp:include>
	<div class="container">
		<div class="page-header">
			<h2>Player Information</h2>
		</div>
		<div class="container">
<!-- Page Content -->
<!-- in: login -->
<!-- out: characterInfo, friendList, createQuest, report, createCharacter -->	
		
		<h5>${players }</h5>
<c:forEach items="${players}" var="s">
                    			<ul>
				<li>${s.email}</li>
				<li>${s.displayName}</li>
				<li>${s.userType}</li>
				<li>${s.gameCharacters}</li>
			</ul>
			
			</c:forEach>
			
			
			
			
		
		<!--  
		private String email;

	private String password;

	@Column(name = "display_name")  //either admin or user
	private String displayName;

	@OneToMany(mappedBy = "friend")
	private List<Friend> friends;

	@ManyToOne
	@JoinColumn(name = "user_type_id")  //1 for admin, 2 for player
	private UserType userType;

	@ManyToMany
	@JoinTable(name = "player_quest", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "quest_id"))
	private List<Quest> quests;

	@OneToMany(mappedBy = "player")
	private List<GameCharacter> gameCharacters;    -->
		
		
		
		</div>
	</div>
	<br>
	<br>
	<br>
</body>
</html>