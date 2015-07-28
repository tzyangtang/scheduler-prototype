<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduler Status</title>
</head>
<body>
	<form action="status" method="post">
		<table>
			<tr>
				<th>Job Name</th>
				<th>Group</th>
				<th>Next Fire Time</th>
			</tr>
			<c:forEach var="job" items="${jobs}">
				<tr>
					<td>${job.jobName}</td>
					<td>${job.groupName}</td>
					<td>${job.nextFireTime}</td>
					<td><input type="submit" name="${job.jobName}" value="Run Now" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>