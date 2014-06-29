<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<title>Interactive Hospital ++</title>	
	<link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>
<body>
<div id="background">
	<img src="resources/hospital.jpeg" class="stretch" alt="" />
</div>
<ul class="nav nav-pills">
<li><a href="<c:url value="/"/>">Interactive Hospital ++</a></li>
  <li class="active"><a href="#home">Home</a></li>
  <li><a href="<c:url value="patient"/>">Check your symptoms</a></li>
  <li><a href ="<c:url value="diagnose"/>">Let us Diagnose you</a></li>
</ul>
<div class="jumbotron">
	<c:choose>
		<c:when test="${isActive}">
		
			<h3>We are the best hospital online! <br/>We have <c:out value="${uniqueSymptoms}"/> unique symptoms in our database.</h3>
			<h2>Top Three Diseases</h2>
			<div class = "center">
				<c:forEach items="${topDiseases}" var="disease" varStatus="status">
		  			<h4> ${status.index + 1}.  - <c:out value="${disease}"/> <span class="badge"><c:out value="${diseaseInfo[status.index]}"/></span></h4>
				</c:forEach>
				<p>
			</div>
			<h2>Top Three Symptoms</h2>
			<div id = "top-symptom" class = "center">
				<c:forEach items="${topSymptoms}" var="symptom" varStatus="status">
		  			<h4>${status.index + 1}.  - <c:out value="${symptom}"/> <span class="badge"><c:out value="${symptomInfo[status.index]}"/></span></h4>
				</c:forEach>
				<p>
			</div>
			<form method = "post" id = "data-form" action="${pageContext.request.contextPath}/data">
				<input type="text" name = "file" class="form-control"/>
				<input type = "submit" class = "btn btn-default" value = "set database">
			</form>
		</c:when>
		<c:otherwise>
			<h4 class ="error-info">Unfortunately there is no data! Please select database</h4>
			<form method = "post" id = "data-form" action="${pageContext.request.contextPath}/data">
				<input type="text" name ="file" class="form-control">
				<input type = "submit" class = "btn btn-default" value = "set database">
				<label>File must be a csv type</label>
			</form>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>