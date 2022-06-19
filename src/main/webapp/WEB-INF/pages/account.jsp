<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
</head>
<body>
	<h1>This is Test </h1>
	<form action="accountAction" method=POST>
		<tr>
			<td>Name</td>
			<td><input type="text" name="username"></td>
			<td><input type="text" name="userpwd"></td><br>
			<td>${errors.msg}</td>
			

		</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
	
	
	</form>
</body>
</html>