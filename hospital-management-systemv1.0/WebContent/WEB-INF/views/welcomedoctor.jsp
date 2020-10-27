<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Doctor</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<style type = "text/css">
	header{
	text-align : center;}
	.container{
		text-align : center;
		width : 50%;
		margin-left : 10%;
		margin : auto;
		}
	.visible{
		display : block;
	}
	.invisible{
	display : none;
	}
	form{
	margin-left : 40%;}
	
	#error{
	font-weight : bold;
	color : red;
	text-align : center;}
	table,tr,th,td{
	border : 2px solid black;
	}
</style>
</head>
<body>
<header><h1>Your Unattended Patient Details: </h1></header>
<div id = "error">${message}</div>
<div class = "container">
<form action = "${pageContext.request.contextPath}/physician/treat.ppl" method = "post" >
	<table >
	<tr><th>Patient Details</th></tr>
		<tr><td>Patient Id - Patient Name</td></tr>
	<c:forEach var = "patient" items = "${diagnosislist}" >
		<tr><td><input type = "radio" name = "diagnosisId" value = "${patient.diagnosisId}" />"${patient.patientId.id}- ${patient.patientId.firstName} ${patient.patientId.lastName}" </td></tr>
	</c:forEach>
	<tr id = "button"><th><input type = "submit" value = "Update Report" /></th></tr>
	</table>	
</form>

<a href = "${pageContext.request.contextPath}/physician/logout.ppl">Logout</a>
</div>

</body>
</html>