<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To HMS</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/static/css/index.css" />
</head>
<body>
<header>
<h1>HMS Hospital Management System</h1>
</header>
<div class = "container" >
<div class = "error"><p>${message}</p></div>
<div class = "btn-container">

	<a class = "btn user" href = "${pageContext.request.contextPath}/patient/login">Welcome People</a>
    <a class = "btn doc" href = "${pageContext.request.contextPath}/physician/login">Welcome Doctor</a>
	<a class = "btn admin" href = "${pageContext.request.contextPath}/admin/">For Administration Purpose Only</a>
</div>

</div>
<footer>
    <div><p>Mini Project &copy; 2020</p></div>
</footer>

</body>
</html>