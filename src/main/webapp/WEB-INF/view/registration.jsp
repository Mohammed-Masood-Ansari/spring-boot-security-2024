<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4 style="color: maroon;" align="center">Welcome-To-User-Registration-Page</h4>
	<br>
	<div align="center">
		<form:form action="userRegister" method="post"
			modelAttribute="registeration">
			<table>
				<tr>
					<td>UserName:</td>
					<td><form:input path="userName" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:input path="userPassword" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="register" /></td>
				</tr>
			</table>
		</form:form>

	</div>

</body>
</html>