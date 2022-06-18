<%@page import="model.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath(); %>

<html>
<head>
<title>User Management Application</title>
<logic:forward name="/"/>
	 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">  
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" >
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
>
	 <link rel="stylesheet" href="//cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
</head>
<body>
  <%@include file = "/WEB-INF/resource/jspf/sidebar.jspf"%>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.google.com." class="navbar-brand"> google
					 </a>
			</div>

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
				
				<form action="ShowNewForm" name="new" method="POST">
					<input type ="hidden" value="new" name="showNewForm">
					<input type ="submit" class="btn btn-success" 
					value="new Hotel">
				</form>
				
<!-- 		    <button type="button" class="btn btn-sm btn-danger"  onclick="query()"><i class="bi bi-search"></i>查詢</button> -->
		   
				<form action="Hotel.Query" method="POST">
					<input type="hidden" name="query" value="query">
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
							 
							<form action="Hotel.Delete" method="POST" name="delete">
<!-- 							<input type="hidden" name="action" value="delete"> -->
								<input type="hidden" name="DeleteId" value="<c:out value='${hotel.id}' />">
								<input type="submit" class="btn btn-sm btn-danger" value="刪除">
							</form>
						
							</td>
							
							<td>
<%-- 							<button  class="btn btn-success" type="button"  onclick="edit('${hotel.id}');" id="edit" ><i class="bi bi-credit-card-2-front"></i>編輯</button> --%>
							 
							<form action="shoeEditForm" method="POST" name="edit">
							<input type="hidden" name="showEditForm" value="edit">
							<input type="hidden" name="UpdateId" value="<c:out value='${hotel.id}' />">
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
<script>

// $(document).ready( function () {
//     $('#Research').DataTable();
// } );
// function new1(){
<%-- 	 window.location.href ="<%=path%>/Hotel_Servlet?action=new" --%>
// 	}
// // 	this is can't code  "new" >>to new"
// function edit(id){
<%-- 	 window.location.href ="<%=path%>/Hotel_Servlet?action=edit&id="+id --%>
// 	}
// function query(){
<%-- 	 window.location.href ="<%=path%>/Hotel_Servlet?action=" --%>
// 	}
	



// function bom(id){
// 	const swalWithBootstrapButtons = Swal.mixin({
// 		  customClass: {
// 		    confirmButton: 'btn btn-success',
// 		    cancelButton: 'btn btn-danger'
// 		  },
// 		  buttonsStyling: false
// 		})

// 		swalWithBootstrapButtons.fire({
// 		  title: 'Are you sure?',
// 		  text: "You won't be able to revert this!",
// 		  icon: 'warning',
// 		  showCancelButton: true,
// 		  confirmButtonText: 'Yes, delete it!',
// 		  cancelButtonText: 'No, cancel!',
// 		  reverseButtons: true
// 		}).then((result) => {
// 		  if (result.isConfirmed) {
// 		    swalWithBootstrapButtons.fire(
// 		      'Deleted!',
// 		      'Your file has been deleted.',
// 		      'success'
// 		    )
<%-- 		    window.location.href ="<%=path%>/Hotel_Servlet?action=delete&id="+id --%>
// 		  } else if (
// 		    /* Read more about handling dismissals below */
// 		    result.dismiss === Swal.DismissReason.cancel
// 		  ) {
// 		    swalWithBootstrapButtons.fire(
// 		      'Cancelled',
// 		      'Your imaginary file is safe :)',
// 		      'error'
// 		    )
// 		  }
// 		})
// 	}






</script>	
	
</body>
</html>
