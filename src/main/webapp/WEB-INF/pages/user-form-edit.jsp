<%@page import="java.util.List"%>
<%@page import="backend.hotel.model.hotel.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% Hotel hotel = (Hotel)request.getAttribute("Hotel"); %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"/>	
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"/></script>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<caption>
					<h2> EDIT</h2>
				</caption>
				
					
				<form:form  modelAttribute="hotel" method="POST"  enctype="multipart/form-data">
					
					<fieldset class="form-group">
						<form:label path="ID">ID</form:label>
						<form:input path="ID" value="<%=hotel.getId()%>" readonly  placeholder="" maxlength="50" class="form-control"/>
					</fieldset>	
				
					<fieldset class="form-group">
						<form:label path="hotel_name">飯店名稱</form:label>
						 <form:input path="hotel_name" placeholder="" maxlength="50" class="form-control"/>
					</fieldset>
				
					<fieldset class="form-group">
						<form:label path="price">價格</form:label>
						<form:input path="price"  placeholder="" maxlength="50" class="form-control" onkeypress="return my_key(event)" />
					</fieldset>
					
					

					<fieldset class="form-group">
						<form:label path="boss_name">業主名稱</form:label>
						 <form:input path="boss_name" placeholder="" maxlength="50" class="form-control"/>
					</fieldset>

					<fieldset class="form-group">
						<form:label path="phone">電話</form:label>
						 <form:input path="phone" placeholder="" maxlength="50" class="form-control" onkeypress="return my_key(event)"/>
					</fieldset>
					
					<fieldset class="form-group">
					<form:label path="productImage">照片</form:label>
						 <form:input path="productImage" type='file'/>
					</fieldset>
				
					<td>狀態</td>
					<fieldset class="form-group">
					
					  <form:select path="status" class="form-select" aria-label="Default select example">
						<form:option value="營業中">營業中</form:option>
						<form:option value="未營業">未業中</form:option>
	   	  		
	   	  			  </form:select>
					</fieldset>	
					<!-- -------------------------------------------------------------以下修改 -->



					<td>房型</td>
					<fieldset class="form-group">
					
					  <form:select path="roomtype" class="form-select" aria-label="Default select example" >
						<form:option value="營業中">單人房</form:option>
						<form:option value="未營業">雙人房</form:option>
	   	  		
	   	  			  </form:select>
	   	  			 </fieldset>	
					<!-- -------------------------------------------------------------以下修改 -->
					<button type="submit" class="btn btn-success">Save</button>
				
				</form:form>

			</div>
		</div>
	</div>
</body>
	<script>
						function my_key(e) {
							var key;
							if (window.event) {
								key = e.keyCode;
							} else if (e.which) {
								key = e.which;
							} else {
								return true;
							}
							if (8 == key || 46 == key) {//8:backspace 46:delete (倒退鍵和刪除鍵也允許作用)
								return true;
							}
							var keychar = String.fromCharCode(key);
							var reg = /\d/;
							return reg.test(keychar);
						}
					</script>
</html>
