<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registering for an appointment...</title>
<style type="text/css">
table, th, tr, td {
	border: 2px solid black;
}
</style>
</head>
<body>
	<h1>Select a Physician</h1>
	<form
		action="${pageContext.request.contextPath}/patient/registerappointment.ppl"
		method="post">
		<table>
			<c:forEach var="physician" items="${physicianLists}">
				<tr>
					<td><input type="radio" name="physician"
						value=<c:out value = "${physician.id}" /> required="required">
					<c:out
							value="${physician.firstName} ${physician.lastName} - ${physician.id} - ${physician.department}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br /> <input type="submit" value="Register" />

	</form>
	
	<a href = "${pageContext.request.contextPath}/patient/logout.ppl">Logout</a>
	<a href = "${pageContext.request.contextPath}/patient/welcome.ppl">Go Back</a>
</body>
</html>