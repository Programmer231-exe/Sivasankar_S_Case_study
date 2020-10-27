<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enroll Patient</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<style>

	header{
	text-align : center;
	
	}
	body{
		font-weight : bold;
	}
	table,tr,td{
	padding : 1em;
	}
	.container{
		
		margin : auto;
		width : 50%;
	}
	
	input { 
		width : 300px;
		margin : auto;
	}
	
	.radio-btn{
		width : 50px;
	}
	
	.error{
	font-weight : bold;
	color : red;
	}
	
	#error{
		text-align  : center;
		color : red;
		font-weight : bold;
	}

</style>
</head>
<body>
<header><h1>Enroll Patient</h1></header>
<div class = "container">
<table>
<form:form modelAttribute = "patient" action = "${pageContext.request.contextPath}/admin/enrollpeople.ppl" method = "post">
<tr><td><label>First Name :</label></td><td><form:input path = "firstName" type = "text" required = "required"/>
											<form:errors path = "firstName" cssClass = "error"/></td></tr>
<tr><td><label>Last Name  :</label></td><td><form:input path = "lastName" type = "text" required = "required"/>
											<form:errors path = "lastName" cssClass = "error"/></td></tr>
<tr><td><label>Date of Birth :</label></td><td><form:input path = "dateOfBirth" type = "text" pattern = "[0-9]{4}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}" placeholder = "yyyy/MM/dd" required = "required"/></td></tr>
<tr><td><label>Email Address :</label></td><td><form:input path = "email" type = "email" required = "required"/></td></tr>
<tr><td><label>Contact Number: </label></td><td><form:input path = "contactNumber" type = "text" pattern = "[0-9]{10}" required = "required"/>
								<form:radiobutton class = "radio-btn" path = "insurancePlan" value = "ABC plan" label = "ABC plan"/></td></tr>
<tr><td><label>Insurance Plan</label></td><td><form:radiobutton class = "radio-btn" path = "insurancePlan" value = "XYZ plan" label = "XYZ plan"/></td></tr>

<tr><td><label>State </label></td><td>
		<form:select path = "state" >
			<form:option value = "idle" label = "idle" />
			<form:option value = "undergoing treatment" label = "undergoing treatment" />
			<form:option value = "diagnosed" label = "Discharged" />
			<form:option value = "followback" label = "Follow Back" />
		</form:select></td></tr>
				
<tr><td><input type = "reset" value = "RESET"/></td><td><input type = "submit" value = "SUBMIT" /></td></tr>

</form:form>
</table>

<div id = "error">${message}</div>

<a href = "${pageContext.request.contextPath}/admin/welcome.ppl" >Go Back</a>
</div>
</body>
</html>