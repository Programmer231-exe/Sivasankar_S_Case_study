<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Physician</title>
<style>
	.invisible{
	display : none;
	}
	
	.visible{
	display : block;
	}
</style>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
</head>
<body>
<header>
	<h1>Search Physician</h1>
</header>
<form method = "post" action = "${pageContext.request.contextPath}/admin/searchphysician.ppl">
<div>
<label>Search By</label>
<select id = "searchby" name = "searchby">
	<option value = "state" label="State of the Physician" />
	<option value = "department" label = "Department of the Physician" />
	<option value = "insurancePlan" label = "Insurance Plan of the physician" />
</select>
</div>
<br />
<div id = "state-by" class = "invisible visible">
	<label>Select State:</label><select name = "searchword">
		<option value = "IN" label = "IN"/>
		<option value = "OUT" label = "OUT"/>
	</select>
</div>

<div id = "department-by" class = "invisible">
	<label>Select Department:</label><select name = "searchword">
		<option value = "Cardiologist" label = "Cardiologist Department" />
			<option value = "Dentist" label = "Dentist Department" />
			<option value = "Dermatologist" label = "Dermatologist Department" />
			<option value = "Pediatrician" label = "Pediatrician Department" />
			<option value = "Ayurvedic" label = "Ayurvedic Department" />
			<option value = "General" label = "General" />

	</select>
</div>

<div id = "plan-by" class = "invisible">
	<label>Select Insurance Plan:</label><select name = "searchword">
		<option value = "XYZ plan" label = "XYZ plan"/>
		<option value = "ABC plan" label = "ABC plan" />
	</select>
</div>
<br />
<input type = "submit" value = "Search"/>
<input type = "reset" value = "Reset" />

</form>
<br />
<br />

<div id = "result">
<table border = 1>
<tr><th>Physician Name </th><th>Department</th><th>Years of Experience</th><th>Insurance Plan</th><th>State</th><th>Educational Qualification</th></tr>
<c:forEach var = "physician" items = "${physicianLists}">
	<tr><td><c:out value = "${physician.firstName}  ${physician.lastName}"/></td><td>${physician.department}</td><td>${physician.yearsOfExperience}</td><td>${physician.insurancePlan}</td><td>${physician.state}</td><td>${physician.educationQualification}</td></tr>
</c:forEach>

</table>

<a class = "btn" href = "${pageContext.request.contextPath}/admin/logout.ppl">Logout</a>
<br />
<a href = "${pageContext.request.contextPath}/admin/welcome.ppl" >Welcome</a>
</div>
<script type = "text/javascript">
	let searchby = document.getElementById("searchby");
	searchby.addEventListener("change",performsome);

    function performsome(){
        console.log(this.value);
        if(this.value === 'state'){
            document.getElementById("state-by").classList.add("visible");
            document.getElementById("department-by").classList.remove("visible");
            document.getElementById("plan-by").classList.remove("visible");
        }

        if(this.value === 'department'){
            document.getElementById("state-by").classList.remove("visible");
            document.getElementById("department-by").classList.add("visible");
            document.getElementById("plan-by").classList.remove("visible");
        }

        if(this.value === 'insurancePlan'){
            document.getElementById("state-by").classList.remove("visible");
            document.getElementById("department-by").classList.remove("visible");
            document.getElementById("plan-by").classList.add("visible");
        }
    }

</script>

</body>
</html>