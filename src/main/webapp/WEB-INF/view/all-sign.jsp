<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>WRSS WZ</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

	<div class="panel-container">
	
		<h5>All people signed:</h5>
		
		<table>
			<tr>
				<th>Imie</th>
				<th>Nazwisko</th>
				<th>Email</th>
				<th>Numer indexu</th>
				<th>Koszulka</th>
				<th>Transport</th>
			</tr>
			<c:forEach var="rajd" items="${allRajd}">
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