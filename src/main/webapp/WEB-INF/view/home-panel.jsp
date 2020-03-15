<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>WRSS WZ</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

	<div class="nav-container">
		<nav class="main-nav">
			
			<p class="left-nav">
				<security:authentication property="principal.authorities" />
				<security:authentication property="principal.username" />
			</p>

			<p class="right-nav">
				<a href="${pageContext.request.contextPath}/">Homepage</a>
				<form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="Logout" class="link-button right-nav" />
				</form:form>
			</p>

		</nav>
	</div>
	
	<div class="clearfix"></div> 
	
	<div class="panel-container">
	
		<h2 class="panel-header">Welcome in student panel on WRSS website</h2>
		
		<div class="buttons-container">
			<input type="button" value="Winter Trip (Not Available)" 
				onclick="window.location.href='${pageContext.request.contextPath}/panel/'; return false;" class="panel-button" />
			
			<input type="button" value="Students Prom (Not Available)" 
				onclick="window.location.href='${pageContext.request.contextPath}/panel/'; return false;" class="panel-button" />
			
			<input type="button" value="Summer Trip" 
				onclick="window.location.href='summerTrip'; return false;" 
				class="panel-button" />
		</div>
		<security:authorize access="hasAnyRole('SUPER_ADMIN', 'ADMIN')">
		<div class="buttons-container">
			<div class="clearfix"></div>
			<input type="button" value="Admin Panel (Not&nbsp;Available)" 
				onclick="window.location.href='${pageContext.request.contextPath}/panel/'; return false;" class="panel-button" />
			
			<input type="button" value="Admin Panel (Not&nbsp;Available)" 
				onclick="window.location.href='${pageContext.request.contextPath}/panel/'; return false;" class="panel-button" />
			
			<input type="button" value="Summer Trip Admin Panel" 
				onclick="window.location.href='${pageContext.request.contextPath}/panel/admin/summerTrip'; return false;" 
				class="panel-button" />
		</div>
		</security:authorize>
	</div>
	
	<footer>
		<small>&copy; 2020. Radoslaw Kalina</small>
	</footer>
	
</body>
</html>