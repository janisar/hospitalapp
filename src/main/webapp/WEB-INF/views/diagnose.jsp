<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class = "jumbotron">
	<c:choose >
	<c:when test ="${empty symptomList}">
		<h2>Good news, you are all fine!</h2>
	</c:when>
		<c:otherwise>
			<h2>Seems like you have these diseases: </h2>
 			<c:forEach items="${symptomList}" var="symptom" varStatus="status">
				<p id ="separate"><c:out value="${symptom}"></c:out>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>