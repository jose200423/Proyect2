<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<title>Hola</title>
</head>
<body>
	<form action="personcontrollerservlet" method="post">
		<div class="input-group mb-3">
			<span class="input-group-text">@</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="name"
					name="name" placeholder="name"> <label
					for="name">name</label>
			</div>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text">@</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="cc"
					name="cc" placeholder="cc"> <label
					for="cc">cc</label>
			</div>
			<input type="date" value="2023-09-28" id="date" name="date"/>
		</div>
		<div class="input-group mb-3"> 
			<span class="input-group-text">@</span>
			<div class="form-floating">
				<input type="text" class="form-control" id="city"
					name="city" placeholder="city"> <label
					for="city">city</label>
			</div>
		</div>
		 <input type="submit" value="Submit">
	</form>
	
	

	<form action="usercontrollerservlet" method="get">

		<input type="submit" value="Show All Users">
	</form>

	

</body>
</html>
