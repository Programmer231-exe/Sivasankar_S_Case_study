<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<style type = "text/css">
	.error{
		color : red;
		text-align : center;
		font-weight : bold;
	}
</style>
</head>
<body>
	<div class = "error">${message}</div>
	
	<div class = "error">${error.getMessage()}</div>
</body>
</html>