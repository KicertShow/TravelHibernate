<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>

<body>
	<h1>This is practice Spring </h1>
	<form action="test" method="get">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="userName"></td>
				<td><input type="text" name="userpwd"></td>
				<td>${errors.name}</td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	
	</form>
</body>
</html>