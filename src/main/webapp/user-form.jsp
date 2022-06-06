<%@page import="java.util.List"%>
<%@page import="model.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
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
					<h2>
					 <% 
					 Hotel hotel = (Hotel)request.getAttribute("Hotel"); 
					
					
					
						out.print("Add New Hotel ");
						
					
					%>
					<!-- 
						<c:if test="${hotel != null}">
            			Edit Hotel   edit
            		</c:if>
						<c:if test="${hotel == null}">
            			Add New Hotel insert
            		</c:if>
            		
            		 -->
					</h2>
				</caption>
				
				
				
			
					
					<form action="Hotel_Servlet" method="post" enctype="multipart/form-data">
					<input type="hidden" name="action" value="insert">
				
			
				<!-- 
				if (hotel != null) {
					<input type="hidden" name="id"
								value="<c:out value='${hotel.id}' />" />
				 } 
				 -->
			
				
						
			
				 
				<script>
function my_key(e){
    var key;
    if(window.event) {
        key = e.keyCode;
    }else if(e.which) {
        key = e.which;
    } else {
        return true;
    }
    if(8==key || 46==key){//8:backspace 46:delete (倒退鍵和刪除鍵也允許作用)
        return true;
    }
    var keychar = String.fromCharCode(key);
    var reg = /\d/;
    return reg.test(keychar);
}
  </script>
  
  			
  
  				<fieldset class="form-group">
					 <input type="hidden"  
						value="<c:out value='${hotel.id}' />" class="form-control"
						name="id"  placeholder="type something">
				</fieldset>	
							

				<fieldset class="form-group">
					<label>飯店名稱</label> <input type="text"
						value="<c:out value='${hotel.hotel_name}' />" class="form-control"
						name="hotel_name" placeholder="" maxlength=50>
				</fieldset>

				<fieldset class="form-group">
					<label>價格</label> <input type="text"
						value="<c:out value='${hotel.price}' />" class="form-control"
						name="price" onkeypress="return my_key(event)">
				</fieldset>
				
				<fieldset class="form-group">
					<label>業主名稱</label> <input type="text"
						value="<c:out value='${hotel.boss_name}' />" class="form-control"
						name="boss_name">
				</fieldset>
				
				<fieldset class="form-group">
					<label>電話</label> <input type="text"
						value="<c:out value='${hotel.phone}' />" class="form-control"
						name="phone" onkeypress="return my_key(event)">
				</fieldset>
				<fieldset class="form-group">
					<label>照片</label> <input type="file" 
						value="" class="form-control" 
						name="picture">
				</fieldset>
				<!-- 
				<fieldset class="form-group">
					<label>status</label> <input type="text"
						value="<c:out value='${hotel.status}' />" class="form-control"
						name="status">
				</fieldset>
				 -->
				<!-- 
				<fieldset class="form-group">
					<label>roomtype</label> <input type="text"
						value="<c:out value='${hotel.roomtype}'/>" class="form-control"
						name="roomtype">
				</fieldset>
				 -->
				<!-- -------------------------------------------------------------以下修改 -->
				<label>狀態</label>
				<fieldset class="form-group">
				<select class="form-select" aria-label="Default select example" name="status">
				  <option  value="營業中" >營業中</option>
				  <option  value="未營業">未營業</option>
				</select>
				</fieldset>
				<!-- -------------------------------------------------------------以下修改 -->
				
				
				
				<label>房型選擇</label>
				<fieldset class="form-group">
				<select class="form-select" aria-label="Default select example" name="roomtype">
				  <option  value="單人房" >單人房</option>
				  <option  value="雙人房">雙人房</option>
				</select>
				</fieldset>
				<!-- -------------------------------------------------------------以下修改 -->
				<button type="submit" class="btn btn-success">Save</button>
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>
