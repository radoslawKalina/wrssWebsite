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
	<h2>Login Form</h2>

	<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">

		<c:if test="${param.error != null}">
			<i class="error">Invalid email or password</i>
		</c:if>

		<p>Email: </p>
		<p><input type="text" name="email"/></p>
		
		<p>Password: </p>
		<p><input type="text" name="password"/></p>

		<input type="submit" value="Login" />
	
	
	</form:form>
	
	<p><a href="${pageContext.request.contextPath}/">Back to homepage</a></p>
	
</div>
</body>
</html>