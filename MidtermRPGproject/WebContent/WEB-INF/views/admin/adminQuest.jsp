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
          <title>Admin: Quest/Stage</title>

          <jsp:include page="../../partials/_resources.jsp"></jsp:include>
        </head>


        <!-- Page Body -->

        <body>
          <jsp:include page="../../partials/_nav.jsp"></jsp:include>
          <div class="container">
            <div class="page-header">
              <h2>Admin: Quest/Stage</h2>
            </div>
            <div class="container">
              <!-- Page Content -->
              <!-- in: admin -->
              <!-- out: admin -->
              <c:choose>
                <c:when test="${not empty quests}">
                  <h3>Select</h3>
                  <form action="AdminGetQuest.do">
                    <select name="id">
							<c:forEach var="qq" items="${quests}">
								<option value="${qq.id}">${qq.id}: ${qq.name}</option>
							</c:forEach>
						</select>
                    <button type="submit" value="Submit">Change!</button>
                  </form>
                  <br>
                  <br>
                  <h3>New Quest</h3>
                  <form action="AdminNewQuest.do">
                    Name: <input type="text" name="name"> <br> Short Description: <input type="text" name="description"> <br> Intro: <input type="text" name="intro"> <br> Conclusion: <input type="text" name="conclusion"> <br>
                    <button type="submit" value="Submit">Create!</button>
                  </form>
                </c:when>
                <c:otherwise>
                  <h3>Edit Quest</h3>
                  <form action="AdminEditQuest.do">
                    ID: ${quest.id}<input type="hidden" value="${quest.id}" name="id">
                    <br> Name: <input type="text" name="name" value="${quest.name}"> <br> Short Description: <input type="text" name="description" value="${quest.description}">
                    <br> Intro: <input type="text" name="intro" value="${quest.intro}"> <br> Conclusion: <input type="text" name="conclusion" value="${quest.conclusion}">
                    <br>
                    <button type="submit" value="Submit">Change!</button>
                  </form>
                  <br>
                  <br>
                  <h4>Edit Stages</h4>
                  <c:if test="${not empty quest.stages}">
                    <c:forEach var="stage" items="${quest.stages}">
                      <form action="AdminEditStage.do">
                        <input type="hidden" value="${quest.id}" name="questId"> ID: ${stage.id}<input type="hidden" value="${stage.id}" name="id"> <br> Name: <input type="text" name="name" value="${stage.name}"> <br> Intro: <input type="text" name="intro"
                          value="${stage.intro}"> <br> Conclusion: <input type="text" name="conclusion" value="${stage.conclusion}"> <br> Enemy: <select name="gameCharacter">
									<c:forEach var="gameChar" items="${gameCharacters}">
										<option value="${gameChar.id}">${gameChar.id}: ${gameChar.name}</option>
									</c:forEach>
								</select>
                        <button type="submit" value="Submit">Change!</button>
                      </form>
                      <form action="AdminDeleteStage.do">
                        <input type="hidden" value="${stage.id}" name="id"> <input type="hidden" value="${quest.id}" name="questId">
                        <button type="submit" value="Submit">Delete!</button>
                      </form>
                    </c:forEach>
                  </c:if>
                  <h4>Add Stage</h4>
                  <form action="AdminNewStage.do">
                    <input type="hidden" value="${quest.id}" name="questId"> Name: <input type="text" name="name" value="New Stage"> <br> Intro: <input type="text" name="intro" value="Stage Intro Text">
                    <br> Conclusion: <input type="text" name="conclusion" value="Stage Conclusion Text"> <br> Enemy: <select name="gameCharacterId">
							<c:forEach var="gameChar" items="${gameCharacters}">
								<option value="${gameChar.id}">${gameChar.id}: ${gameChar.name}</option>
							</c:forEach>
						</select>
                    <button type="submit" value="Submit">Add Stage!</button>
                  </form>
                  <br>

                  <br>
                  <h3>Delete Quest</h3>
                  <form action="AdminDeleteQuest.do">
                    ${quest.id}: ${quest.name}<input type="hidden" value="${quest.id}" name="id"> <br>
                    <button type="submit" value="Submit">Delete!</button>
                </c:otherwise>
              </c:choose>

            </div>
          </div>
          <br>
          <br>
          <br>
        </body>

        </html>
