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
		<nav class="main-nav-panel">
			<p class="left-nav">You are logged in as: 
			<security:authentication property="principal.username" />
			<security:authentication property="principal.authorities" /></p>
			
			
			<p class="right-nav">
				<form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="Logout" class="link-button right-nav" />
				</form:form>
			</p>
		</nav>
	</div>
	<div class="clearfix"></div>
	
	<div class="panel-container">
	
		<h1 class="panel-header">Welcome in student panel on WRSS website</h1>
	
		<p><a href="${pageContext.request.contextPath}/panel/rajdSignForm">Sign Up for a trip</a></p>
		
		<h5>People signed by you:</h5>
		
		<table>
			<tr>
				<th>Imie</th>
				<th>Nazwisko</th>
				<th>Email</th>
				<th>Numer indexu</th>
				<th>Koszulka</th>
				<th>Transport</th>
			</tr>
			<c:forEach var="rajd" items="${mySign}">
				<tr>
					<td>${rajd.firstName}</td>
					<td>${rajd.lastName}</td>
					<td>${rajd.email}</td>
					<td>${rajd.indexNumber}</td>
					<td>${rajd.shirtSize}</td>
					<td>${rajd.transportOption}</td>
				</tr>
			</c:forEach>
			
		</table>
		
		
	</div>
	
</body>
</html>