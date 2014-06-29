<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Interactive Hospital ++</title>	
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>
<body>
	<div id="background">
		<img src="/hospital/resources/hospital.jpeg" class="stretch" alt="" />
	</div>
	<ul class="nav nav-pills">
		<li><a href="<c:url value="/"/>">Interactive Hospital ++</a></li>
 		<li><a href="<c:url value="/"/>">Home</a></li>
  		<li><a href="<c:url value="patient"/>">Check your symptoms</a></li>
  		<li class="active"><a href ="<c:url value="diagnose"/>">Let us Diagnose you</a></li>
	</ul>
	<div class = "jumbotron">
		<p>
		<c:choose>
			<c:when test="${empty diagnose}">
				<h4 id = "question-nr">Question #<c:out value="${questionNR}"/></h4>
				<h3 id = "question-text">Do you feel like you have a <c:out value = "${symptom}"/>?</h3>
				<p>
				<form:form id="form" method="post" modelAttribute ="patientAnswer" cssClass="cleanform" 
						action="/hospital/diagnosing" accept-charset="UTF-8">
					<form:hidden path="symptom" value ="${symptom}"/>
					<div id = "radiogroup">
		  				<form:radiobutton path="answer" value ="1" id = "r1"/> <label class = "radiobtn" id ="r-yes">YES</label>
		  				<form:radiobutton path="answer" value ="0" id = "r2"/> <label class = "radiobtn" id = "r-no">NO</label>
	  				</div>
					<button type="submit" class = "btn btn-success">Answer</button>
				</form:form>
			</c:when>
			<c:otherwise>
				<h3>Congratulation, you have got a <b><c:out value="${diagnose}"/></b></h3>
				<div id ="symptom-list">
					<h3>You may feel all these symptoms</h3>
					<c:forEach items="${symptomList}" var="symptom" varStatus="status">
						<h4><c:out value="${symptom}"/></h4>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<script>
		$(document).ready(function () {
			$("#r-yes").click(function () { 
				$("#r1").prop('checked', true);
			});
			$("#r-no").click(function () { 
				$("#r2").prop('checked', true);
			});
		});
	</script>
</body>
</html>