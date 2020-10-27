<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to HMS</title>
<style>
	header{
	text-align : center;
	}
	#message{
	font-weight : bold;
	color : red;}
</style>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
</head>
<body>
<header><h1>Welcome To HMS Hospital Services</h1></header>
<p>Hi ${user.firstName} ${user.lastName}</p>

<div id = "message">${message}</div>
<div>
<a href = "${pageContext.request.contextPath}/patient/bookanappointment.ppl">Book An Appointment</a>
<a href = "${pageContext.request.contextPath}/patient/resetpassword.ppl">Reset Password</a>
<a href = "${pageContext.request.contextPath}/patient/history.ppl">My Diagnosis</a>
<a href = "${pageContext.request.contextPath}/patient/logout.ppl">Logout</a>
</div>
</body>
</html>