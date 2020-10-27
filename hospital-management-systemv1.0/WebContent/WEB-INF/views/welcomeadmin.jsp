<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Administrator....</title>
<link rel = "shorcut icon" href = "https://image.shutterstock.com/image-vector/medical-icon-trendy-flat-style-600w-675381097.jpg" />
<link rel = "stylesheet" href = "${pageContext.request.contextPath}/static/css/welcomeadmin.css"/>
<style type = "text/css">
	#error{
		width : 100%;
		text-align : center;
		font-weight : bold;
		color : red;
	}
</style>
</head>
<body>
<header>
    <h1>Welcome Administrator......</h1>
    </header>
    <div class = "container" >
    <div class = "error"><p>${message}</p></div>
    <div class = "btn-container">
        <a class = "btn" href = "${pageContext.request.contextPath}/admin/registerphysician.ppl">Register Physician</a>
        <a class = "btn" href = "${pageContext.request.contextPath}/admin/enrollpeople.ppl">Enroll People</a>
        <a class = "btn" href = "${pageContext.request.contextPath}/admin/searchphysician.ppl">Search Physician</a>  
        <a class = "btn" href = "${pageContext.request.contextPath}/admin/getDiagnosisReportForAdmin.ppl">Patient History</a></div>
        <a class = "btn" href = "${pageContext.request.contextPath}/admin/logout.ppl">Logout</a>  </div>
    
  
    <div class = "result">
        <p>Generated Login Details</p>
        <p>${loginDetails.username}</p>
        <p>${loginDetails.password}</p>
    
    </div>
    <footer>
        <div><p>Mini Project &copy; 2020</p></div>
    </footer>
    </body>
</html>