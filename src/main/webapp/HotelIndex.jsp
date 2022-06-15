<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Hotel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

			<link rel="stylesheet"
				href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
			<link rel="stylesheet"
				href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
			
			<link rel="stylesheet"
				href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
			<link rel="stylesheet"
				href="//cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css">
			<link rel="stylesheet"
				href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
</head>
<body>

 <%@include file = "jspf/sidebar.jspf"%>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			

			<ul class="navbar-nav">
				<li></li>
				
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">Hotel Manager</h3>
			<hr>
			<div class="container text-left">
<!-- 			<button type="button" class="btn btn-sm btn-danger"  onclick="new1()"><i class="bi bi-person-plus-fill"></i>新增</button> -->
				
				<form action="Hotel_ServletNew" name="new" method="POST">
					<input type ="hidden" value="new" name="showNewForm">
					<input type ="submit" class="btn btn-success" 
					value="new Hotel">
				</form>
				
<!-- 		    <button type="button" class="btn btn-sm btn-danger"  onclick="query()"><i class="bi bi-search"></i>查詢</button> -->
		   
				<form action="Hotel_Servlet" method="POST">
					<input type="hidden" name="action" value="query">
					<input type="submit"  class="btn btn-success"  
					value="query">
				</form>
			
			</div>
			<br>
			<table class="table table-bordered" id="Research">
				<thead>
					<tr>
						<th>ID</th>
						<th>飯店名稱</th>
						<th>價格</th>
						<th>業主名稱</th>
						<th>電話</th> 
						<th>狀態</th> 
						<th>房型</th> 
						<th>照片</th> 
						<th>刪除</th>
						<th>編輯</th>
						
						
					</tr>
				</thead>
				
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="hotel" items="${Hotel}">

						<tr>
							<td><c:out value="${hotel.id}" /></td>
							<td><c:out value="${hotel.hotel_name}" /></td>
							<td><c:out value="${hotel.price}" /></td>
							<td><c:out value="${hotel.boss_name}" /></td>
							<td><c:out value="${hotel.phone}" /></td>
							<td><c:out value="${hotel.status}" /></td>
							<td><c:out value="${hotel.roomtype}" /></td>
							
							
							<td><img src="/travelWebFinalVersion/Picture?id=${hotel.id}" width="100px"></td>
							
							
							<td>
<%-- 							  <button  class="btn btn-sm btn-danger" type="button"  onclick="bom('${hotel.id}');" id="delete1" ><i class="bi bi-trash-fill"></i>刪除</button> --%>
							 
							<form action="Hotel_ServletDelete" method="POST" name="delete">
								<input type="hidden" name="action" value="delete">
								<input type="hidden" name="id" value="<c:out value='${hotel.id}' />">
								<input type="submit" class="btn btn-sm btn-danger" value="刪除">
							</form>
						
							</td>
							
							<td>
<%-- 							<button  class="btn btn-success" type="button"  onclick="edit('${hotel.id}');" id="edit" ><i class="bi bi-credit-card-2-front"></i>編輯</button> --%>
							 
							<form action="Hotel_Servlet" method="POST" name="edit">
							<input type="hidden" name="action" value="edit">
							<input type="hidden" name="id" value="<c:out value='${hotel.id}' />">
							<input type="submit" class="btn btn-success" value="修改">
							
							</form>
							</td>
							
					
							
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>	
<script type="text/javascript" src="//cdn.datatables.net/1.12.0/js/jquery.dataTables.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	
</body>
</html>