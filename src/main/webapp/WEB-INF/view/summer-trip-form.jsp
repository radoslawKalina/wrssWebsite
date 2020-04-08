<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>WRSS WZ</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="form-container">
	<h2>Summer Trip Register Form</h2>

	<c:if test="${param.id != null}">
		<c:url var="action" value="processSummerTripForm">
			<c:param name="update" value="${param.id}"/>
		</c:url>
	</c:if>
	
	<c:if test="${param.id == null}">
		<c:url var="action" value="processSummerTripForm">
			<c:param name="update" value="0"/>
		</c:url>
	</c:if>

    <form:form action="${action}" modelAttribute="summerTripRequestModel">
		
		<c:if test="${param.error == 1}">
			<i class=error>You try to use incorrect data. Try again!</i>
		</c:if>
			
		<p>First name: </p>
		<p><form:errors path="firstName" cssClass="error" /></p>
		<p><form:input path="firstName"/></p>
		
		<p>Last name: </p>
		<p><form:errors path="lastName" cssClass="error" /></p>
		<p><form:input path="lastName"/></p>
		
		<p>Email: </p>
		<p><form:errors path="email" cssClass="error" /></p>
		<p><form:input path="email"/></p>
		
		<p>Index Number: </p>
		<p><form:errors path="indexNumber" cssClass="error" /></p>
		<p><form:input path="indexNumber"/></p>
		
		<p>T-Shirt Size: </p> 
		<p><form:errors path="shirtSize" cssClass="error" /></p>
		<p>
			<form:select path="shirtSize">
				<form:options items="${summerTripRequestModel.shirtSizes}"/>
			</form:select>
		</p>
		  
		<p>Transport Option: </p> 
		<p><form:errors path="transportOption" cssClass="error" /></p>
		<p>	
			Bus: <form:radiobutton path="transportOption" value="bus"/>
			Car: <form:radiobutton path="transportOption" value="car"/>	
		</p>
	
		<input type="submit" value="Sign in" />
	
	
	</form:form> 
	
	<p><a href="${pageContext.request.contextPath}/panel/summerTrip">Back to Panel</a></p>
</div>
</body>
</html>