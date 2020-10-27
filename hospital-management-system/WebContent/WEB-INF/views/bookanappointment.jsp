<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Requesting Services...</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
</head>
<body>
<form action = "${pageContext.request.contextPath}/patient/bookanappointment.ppl" method = "post">
	<label>Department</label><select name = "department" >
			<option value = "Cardiologist" label = "Cardiologist Department" />
			<option value = "Dentist" label = "Dentist Department" />
			<option value = "Dermatologist" label = "Dermatologist Department" />
			<option value = "Pediatrician" label = "Pediatrician Department" />
			<option value = "Ayurvedic" label = "Ayurvedic Department" />
			<option value = "General" label = "General" />
		</select>
		
	<input type = "submit" value = "Submit">
</form>
</body>
</html>