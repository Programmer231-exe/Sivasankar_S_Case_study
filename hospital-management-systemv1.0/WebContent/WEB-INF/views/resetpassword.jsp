<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<title>Resetting password....</title>
<style type = "text/css">

#form{
	width : 50%;
	margin : auto;
}
header{
text-align : center;
}
#error{
	color : red;
	font-weight : bold;
}

</style>
</head>
<body>
<header><h1>Resetting password............</h1></header>
<div id = "form">
<form action = "${pageContext.request.contextPath}/patient/resetpassword.ppl" method = "post" onsubmit="return check()">
	<p><label>Enter your username : </label><input type = "text" name = "username" required = "required"/></p>
	<p><label>New Password : </label><input id = "password" type = "password" name = "newpassword" required = "required"/></p>
	<p><label>Confirm Password : </label><input id= "confirmpassword" type = "password" name = "confirmpassword" /></p>
	<input type = "submit" value = "Submit" />
 </form>
 </div>
 <div id = "error"></div>
 <a href = "${pageContext.request.contextPath}/patient/logout.ppl">Logout</a>
  <a href = "${pageContext.request.contextPath}/patient/welcome.ppl">Welcome</a>
 
 <script type = "text/javascript">
 	function check(){
 		let result = false;
 		console.log("This is working");
 		if(document.getElementById("password").value === document.getElementById("confirmpassword").value)
 		{
 			result = true;
 		}else{
 			document.getElementById("error").innerHTML = "<p>New password and confirm password does not match</p>";
 		}
 		return result;
 	}
 </script>
</body>
</html>