<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Physician</title>
<style>

	header{
	text-align : center;
	
	}
	body{
		font-weight : bold;
	}
	table,tr,td{
	padding : 0.7em;
	}
	.container{
		
		margin : auto;
		width : 50%;
	}
	
	input { 
	width : 100%;
	}
	
	.radio-btn{
		width : 10%;
	}
	
	#error{
		width : 100%;
		text-align : center;
		color : red;
		font-weight : bold;
	}
	.error {
		color : red;
		font-weight : bold;
	}

</style>
</head>
<body>
<header>
<h1>Physician Registration Form</h1>
</header>
<div class = "container">
<table>
	<form:form modelAttribute = "physician" method = "post" action = "${pageContext.request.contextPath}/admin/updatephysician.ppl">
		<tr><td><label>FirstName:</label><td><form:input path = "firstName" type = "text" />
							<form:errors path = "firstName" cssClass = "error"/></td></tr>
		<tr><td><label>LastName</label></td><td>								
		<form:input path = "lastName" type = "text" />
								<form:errors path = "lastName" cssClass = "error"/></td></tr>
		<tr><td><label>Department</label></td><td><form:select path = "department" >
			<form:option value = "Cardiologist" label = "Cardiologist Department" />
			<form:option value = "Dentist" label = "Dentist Department" />
			<form:option value = "Dermatologist" label = "Dermatologist Department" />
			<form:option value = "Pediatrician" label = "Pediatrician Department" />
			<form:option value = "Ayurvedic" label = "Ayurvedic Department" />
			<form:option value = "General" label = "General" />
		</form:select>
		</td></tr>
		<tr><td><label>Educational Qualification</label></td><td>
		<form:select path = "educationQualification" >
			<form:option value = "MBBS-Bachelog of Medicine,Bachelor of Surgery" label = "MBBS-Bachelog of Medicine,Bachelor of Surgery" />
			<form:option value = "BDS-Bachelor of Dental Surgery" label = "BDS-Bachelor of Dental Surgery" />
			<form:option value = "BAMS - Bachelof or Ayurvedic Medicine and Surgery" label = "BAMS - Bachelof or Ayurvedic Medicine and Surgery" />
			<form:option value = "Pediatrician" label = "Pediatrician Department" />
			<form:option value = "MD-Cardiologist" label = "MD-Cargiologist" />
			<form:option value = "MD-Dermatologist" labe = "MD-Dermatologist" />
		</form:select></td></tr>
		<tr><td><label>State</label></td><td><form:select path = "state" >
		<form:option value = "IN" label = "IN" />
		<form:option value = "OUT" label = "OUT" />
		
		</form:select></td></tr>
		
		<tr><td><label>Insurance Plan</label></td><td>
		<form:radiobutton class = "radio-btn" path = "insurancePlan" value = "XYZ plan" />XYZ plan
		<form:radiobutton class = "radio-btn" path = "insurancePlan" value = "ABC plan" />ABC plan</td></tr>
		<tr><td>Years of Experience</td><td><form:input path="yearsOfExperience" /></td></tr>
		<tr><td><input type = "reset" value  = "Reset" /></td><td>
		<input type = "submit" value = "Register" /></td></tr>
		
	</form:form>
	</table>
</div>
<div id = "error">${message}</div>

<a href = "${pageContext.request.contextPath}/admin/welcome.ppl">Go Back</a>
</body>
</html>