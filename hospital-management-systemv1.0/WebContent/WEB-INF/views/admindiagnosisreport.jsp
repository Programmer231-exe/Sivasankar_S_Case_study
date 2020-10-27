<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient History</title>
</head>
<style type="text/css">
table, th, tr, td {
	border: 2px solid black;
}
</style>
<body>
	<header>
		<h1>The Patient History</h1>
	</header>
	<table>
		<tr>
			<th>Patient Name</th>
			<th>Symptoms</th>
			<th>Date of Diagnosis</th>
			<th>Is Follow Up Required</th>
			<th>DateOfFollowUp</th>
			<th>Bill Amount</th>
			<th>Mode of Payment</th>
			<th>Diagnosed By</th>
			<th>Status
		</tr>
		<c:forEach var="diagnosis" items="${diagnosisLists}">
			<tr>
				<td>${diagnosis.patientId.firstName}
					${diagnosis.patientId.lastName}</td>
				<td><c:forEach var="symptom" items="${diagnosis.symptoms}">
						<c:out value="${symptom}" />
					</c:forEach></td>
				<td>${diagnosis.getDateOfDiagnosis()}</td>
				<td>${diagnosis.isFollowUpRequired}</td>
				<td>${diagnosis.dateOfFollowUp}</td>
				<td>${diagnosis.billAmount}</td>
				<td>${diagnosis.modeOfPayment}</td>
				<td>${diagnosis.physician.firstName }
					${diagnosis.physician.lastName}</td>
				<td>${diagnosis.status}</td>
			</tr>
		</c:forEach>
	</table>

	<a href="${pageContext.request.contextPath}/admin/logout.ppl">Logout</a>
	<a href="${pageContext.request.contextPath}/admin/welcome.ppl">Go
		Back</a>
</body>
</html>