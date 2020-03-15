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
				<a href="${pageContext.request.contextPath}/panel/">Panel</a>
				<form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="Logout" class="link-button right-nav" />
				</form:form>
			</p>

		</nav>
	</div>
	
	<div class="clearfix"></div> 
	
	<div class="summer-container">
	
		<div class="text-center">
			<h2 class="summer-header">Summer Trip - Admin Panel</h2>
		</div>
		
	<input type="button" value="Change Paid Status" 
		onclick="window.location.href='changePaidStatus'; return false;" class="click-button" />	
		
	<h3 class="summer-registered">All Registered people:</h3>
	
	<table class="element-center">
		<tr>
			<th>Imie</th>
			<th>Nazwisko</th>
			<th>Email</th>
			<th>Numer indexu</th>
			<th>Koszulka</th>
			<th>Transport</th>
			<th>Paid</th>
			<th>Action</th>
		</tr>
		<c:forEach var="trip" items="${summerTripList}">
		
		<c:url var="update" value="updateEntryAdmin">
			<c:param name="id" value="${trip.id}"/>
		</c:url>
		
		<c:url var="delete" value="deleteEntryAdmin">
			<c:param name="id" value="${trip.id}"/>
		</c:url>
		
		<tr>
			<td>${trip.firstName}</td>
			<td>${trip.lastName}</td>
			<td>${trip.email}</td>
			<td>${trip.indexNumber}</td>
			<td>${trip.shirtSize}</td>
			<td>${trip.transportOption}</td>
			<td>${trip.paid.paid}</td>
			<td>
				<a href="${update}">Update</a> |
				<a href="${delete}"
				   onclick="if (!(confirm('Are you sure?'))) return false"> Delete</a>
			</td>
			
		</tr>
		</c:forEach>
		
	</table>
		
	<h5>${shirtSizes}</h5>
	
	</div>
	
	<footer>
		<small>&copy; 2020. Radoslaw Kalina</small>
	</footer>
</body>
</html>