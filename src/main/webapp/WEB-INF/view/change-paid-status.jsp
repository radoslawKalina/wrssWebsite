<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
	<div class="form-container">
		<div class="text-center">
			<h2 class="summer-header">Change Paid Status Form</h2>
		</div>

		<form:form action="processPaidStatusChange" modelAttribute="${paidForm}">

			<table class="element-center table-paid-status">
				<tr>
					<th>Imie</th>
					<th>Nazwisko</th>
					<th>Paid</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${paidForm.paidList}" var="paid" varStatus="status">
				<input type="hidden" name="paidList[${status.index}].id" value="${paid.id}"/>
				<input type="hidden" name="paidList[${status.index}].summerTrip.id" value="${paid.summerTrip.id}"/>
				
				<tr>
					<td>${paid.summerTrip.firstName}</td>
					<td>${paid.summerTrip.lastName}</td>
					<td>${paid.paid}</td>
					
					<c:if test="${paid.paid == 'NO'}">
					<td>NO:<input type="radio" name="paidList[${status.index}].paid" 
					value="${paid.paid}" ${paid.paid == 'NO' ? 'checked' : ''} />
						YES:<input type="radio" name="paidList[${status.index}].paid" value="YES"/>
					</td>
					</c:if>
					<c:if test="${paid.paid == 'YES'}">
					<td>NO:<input type="radio" name="paidList[${status.index}].paid" value="NO"/>
						YES:<input type="radio" name="paidList[${status.index}].paid" 
						value="${paid.paid}" ${paid.paid == 'YES' ? 'checked' : ''} />
					</td>
					</c:if>
				</tr>
				</c:forEach>	
			</table>
	
			<input type="submit" value="Save" class="click-button change-paid-button"/>
			
			<div class="clearfix"></div> 
	
		</form:form>
	</div>
	</div>
	
	
	<footer>
		<small>&copy; 2020. Radoslaw Kalina</small>
	</footer>
</body>
</html>