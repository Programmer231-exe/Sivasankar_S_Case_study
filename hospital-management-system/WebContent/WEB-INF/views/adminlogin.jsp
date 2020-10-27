<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To Administration Page</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/static/css/loginpage.css" />
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/static/css/adminlogin.css" />
</head>
<body>
<header>
    <h1>Welcome to HMS Hospital Login Page</h1>
</header>
<div class = "container">
    <h4>Admin Login Page</h4>
    <div ><img src = "https://image.shutterstock.com/image-vector/user-icon-vector-600w-393536320.jpg" alt = "icon" width="200px" height="200px"/></div>
	<form action = "${pageContext.request.contextPath}/admin/validate" method = "post" onsubmit = "return check()">
	<input class = "field" id = "username" name  = "username" placeholder = "Username" type = "text" />
	<input class = "field" id = "password" name  = "password" placeholder = "Password" type = "password" />
    <input class = "btn" type = "submit" value = "Submit" />
    <div id = "message">${message}</div>
</form>
</div>


<footer>
	<a href = "${pageContext.request.contextPath}/">Go to index Page</a>
    <div id = "note"><p>&copy;2020</p></div>
</footer>
<script type = "text/javascript" src = "${pageContext.request.contextPath}/static/js/login.js" defer ></script>

</body>
</html>