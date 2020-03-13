<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>WRSS WZ</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>

	<div class="nav-container">
	<nav class="main-nav">

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<p class="left-nav"><security:authentication property="principal.username" /></p>
			<p class="right-nav">
				<a href="${pageContext.request.contextPath}/panel/">Panel</a>
				<form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="Logout" class="link-button right-nav" />
				</form:form>
			</p>
		</c:if>
	
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<p class="left-nav">You are logged out</p>
			<p class="right-nav">
				<a href="${pageContext.request.contextPath}/showLoginForm">Login</a>
				<a href="${pageContext.request.contextPath}/registration">Registration</a>
			</p>
		</c:if>
	</nav>
	</div>
	
	  <div class="clearfix"></div> 
	
	<div class="container">
		<h2 class="main-header">WRSS WZ</h2>
	</div>
	
	<footer>
		<small>&copy; 2020. Radoslaw Kalina</small>
	</footer>
</body>
</html>
