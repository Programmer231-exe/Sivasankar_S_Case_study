<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Diagnosis Report</title>
<style type="text/css">
table, th, tr, td {
	border: 2px solid black;
}
</style>
</head>
<body>
<h1>Hi ${user.firstName}</h1>
<p>Your Diagnosis Report</p>
<table >

<tr><th>Diagnosis Id</th><th>Patient Name</th><th>Physician Name</th><th>Symptoms</th><th>Is Follow UP Required</th><th>Date of Follow Up</th><th>Date of Diagnosis</th><th>Bill Amount</th><th>Mode of Payment</th><th>Status</tr>

<c:forEach var = "diagnosis" items = "${diagnosisList}" >
<tr><td>${diagnosis.diagnosisId}</td>
<td>${diagnosis.patientId.firstName} ${diagnosis.patientId.lastName}</td>
<td>${diagnosis.physician.firstName} ${diagnosis.physician.lastName}</td>
<td><c:forEach var = "symptom" items = "${diagnosis.symptoms}">
	<c:out value  ="${symptom}" />
</c:forEach></td>
<td>${diagnosis.isFollowUpRequired}</td>
<td>${diagnosis.dateOfFollowUp}</td>
<td>${diagnosis.dateOfDiagnosis}</td>
<td>${diagnosis.billAmount}</td>
<td>${diagnosis.modeOfPayment}</td>
<td>${diagnosis.status}</td></tr>
</c:forEach>
</table>
<a href = "${pageContext.request.contextPath}/patient/welcome.ppl">Go Back</a>
<a href = "${pageContext.request.contextPath}/patient/logout.ppl">Logout</a>
</body>
</html>