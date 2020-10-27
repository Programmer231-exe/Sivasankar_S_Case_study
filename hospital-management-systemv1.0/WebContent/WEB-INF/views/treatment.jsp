<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Treatment</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<style type = "text/css">
	header{
	text-align : center;
	}
	
	.container{
		width : 100%;
		margin : auto;
	}
	table{
	margin-left :20%;
	}
	
	
</style>
</head>
<body>
<header><h1>Fill the Details with high most attention</h1></header>
<div class = "container">
<form:form modelAttribute = "diagnosis" action = "${pageContext.request.contextPath}/physician/updateDiagnosisReport.ppl">
<table >
<tr><td>Diagnosis Id:</td><td><form:input path = "diagnosisId" type = "text" /></td></tr>
<tr><td>Patient Id : </td><td><form:input path = "patientId.id" type = "text"/></td></tr>
<tr><td>Physician Id : </td><td><form:input path = "physician.id" type = "text"/></td></tr>
<tr><td>Symptoms : </td><td><form:input path = "symptoms" type = "text" /></td></tr>
							  
<tr><td>Is Follow Up Required : </td><td><form:select path = "isFollowUpRequired" required = "required">
	<form:option value = "y" label = "yes"/>
	<form:option value = "n" label = "no" />
</form:select>
</td></tr>
<tr><td>Date of Diagnosis : </td><td><form:input path = "dateOfDiagnosis" type = "text" pattern = "[0-9]{4}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}" placeholder = "yyyy/MM/dd" required = "required"/></td>
<tr><td>Follow Up Date:  </td><td><form:input path = "dateOfFollowUp" type = "text" pattern = "[0-9]{4}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}" placeholder = "yyyy/MM/dd" required = "required"/></td>
<tr><td>Bill Amount : </td><td><form:input path = "billAmount" type = "text" required = "required"/></td>
<tr><td>Mode of Payment : </td><td><form:input path = "modeOfPayment" type = "text" required = "required"/></td>
<tr><td colspan = "2"><input type = "submit" value = "Submit" /></td></tr>
</table>
</form:form>
</div>
<div id = "error">${message}</div>
<div><p>If follow up not required please enter the date of diagnosis in date of followup</p></div>
<a href = "${pageContext.request.contextPath}/physician/welcome.ppl">Go Back</a>
<a href = "${pageContext.request.contextPath}/physician/logout.ppl">Logout</a>
</body>
</html>