<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Ver Alcoholicos</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style2.css">
</head>

<body>

	<div class="main">
		<div class="container">
			<div class="signup-content">
				<div class="signup-img">
					<img src="images/signup-img.jpg" alt="">
				</div>
				<div class="signup-form">
					<form method="POST" action="psychologistcontrollerservlet.jsp"
						class="register-form" id="register-form">
						<h2>Alcoholicos (Miembros) encontrados:</h2>

					</form>
					<section>
						<div class="form-group">
							<textarea name="Message" rows="20" cols="97" id="Message"
								class="form-control input-message wow fadeInUp"
								placeholder="Message" required></textarea>
						</div>
					</section>
					<section>
						<div>
							<input type="button" onclick="history.back()" name="Go Back"
								value="Go Back">
						</div>
					</section>

				</div>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
</body>

</html>
