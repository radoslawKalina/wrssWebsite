<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>WRSS WZ</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="form-container">
	<h2>Registration Form</h2>

	<form:form action="processRegistration" modelAttribute="userValidation">
	
		<p>First name: </p>
		<p><form:errors path="firstName" cssClass="error" /></p>
		<p><form:input path="firstName"/></p>
		
		<p>Last name: </p>
		<p><form:errors path="lastName" cssClass="error" /></p>
		<p><form:input path="lastName"/></p>
		
		<p>Email: </p>
		<p><form:errors path="email" cssClass="error" /></p>
		<p><form:input path="email"/></p>
		
		<p>Password: </p>
		<p><form:errors path="password" cssClass="error" /></p>
		<p><form:password path="password"/></p>
		
		<p>Confirm password: </p> 
		<p><form:errors path="confirmPassword" cssClass="error" /></p>
		<p><form:password path="confirmPassword"/></p>
	
		<input type="submit" value="Register" />
	
	
	</form:form>
	
	<p><a href="${pageContext.request.contextPath}/">Back to homepage</a></p>
</div>
</body>
</html>