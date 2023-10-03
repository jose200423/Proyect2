<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Alcoholics anonymous</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- CSS Files -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="fonts/icon-7-stroke/css/pe-icon-7-stroke.css"
	rel="stylesheet">
<link href="css/animate.css" rel="stylesheet" media="screen">
<link href="css/owl.theme.css" rel="stylesheet">
<link href="css/owl.carousel.css" rel="stylesheet">
<!-- Colors -->
<link href="css/css-index.css" rel="stylesheet" media="screen">
<!-- Google Fonts -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic" />
</head>
<body data-spy="scroll" data-target="#navbar-scroll">
	<div id="preloader"></div>
	<div id="top"></div>
	<div class="fullscreen landing parallax"
		style="background-image: url('images/fondo.jpg');"
		data-img-width="2000" data-img-height="1333" data-diff="100">
		<div class="overlay">
			<div class="container">
				<div class="row">
					<div class="col-md-7">
						<div class="logo wow fadeInDown">
							<a href=""><img src="images/Alcoholics anonymous-2.png"
								data-img-width="2000" data-img-height="1333" alt="logo"></a>
						</div>
						<h1 class="wow fadeInLeft">¿Tiene un problema con el alcohol?
							Hay una solución.</h1>
						<div class="landing-text wow fadeInUp">
							<p>Alcoholics anonymous ofrece un programa sencillo que
								funciona.</p>
						</div>
						<div class="head-btn wow fadeInLeft">
							<a href="#intro" class="btn-primary">About</a>
						</div>
					</div>
					<div class="col-md-5">
						<div class="signup-header wow fadeInUp">
							<h3 class="form-title text-center">BIENVENIDO</h3>
							<form class="form-header" action="usercontrollerservlet" method="POST">
								<input type="hidden" name="u" value="503bdae81fde8612ff4944435">
								<input type="hidden" name="id" value="bfdba52708">
								<div class="form-group">
									<input class="form-control input-lg" name="name" id="name"
										type="text" placeholder="name" required>
								</div>
								<div class="form-group">
									<input class="form-control input-lg" name="password"
										id="password" type="password" placeholder="password" required>
								</div>
								<div class="form-group last">
									<input type="submit" class="btn btn-warning btn-block btn-lg"
										value="LOGIN">
								</div>
								<p></p>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="menu">
		<div class="navbar-wrapper navbar-light bg-light" role="navigation">
			<div class="container">
				<button type="button" class="navbar-toggler" data-toggle="collapse"
					data-target=".navbar-backyard">
					<span class="sr-only">Toggle navigation</span> &#x2630;
				</button>
				<a class="navbar-brand site-name" href="#top"><img
					src="images/Alcoholics anonymous-2.png" alt="logo"></a>
				<div id="navbar-scroll"
					class="collapse navbar-collapse navbar-backyard ml-auto">
					<ul class="nav navbar-nav">
						<li><a href="#intro" class="nav-link">About</a></li>
						<li><a href="#nosotros" class="nav-link">Sobre Nostros</a></li>
						<li><a href="#vinculate" class="nav-link">Vinculate</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="intro">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 intro-pic wow slideInLeft">
					<img src="images/about.jpg" alt="image" class="img-fluid">
				</div>
				<div class="col-lg-6 wow slideInRight">
					<h2>¿Qué es Alcoholics Anonymous?</h2>
					<p>Alcoholics Anonymous es una comunidad de personas que se
						reúnen para resolver su problema con la bebida. Asistir a
						reuniones de Alcoholics Anonymous no cuesta nada. No hay ningún
						requisito de edad o nivel de educación para participar. Cualquiera
						que quiera hacer algo para resolver su problema con la bebida
						puede hacerse miembro. El propósito primordial de Alcoholics
						Anonymous es ayudar a los alcohólicos a lograr la sobriedad.</p>
					<div class="btn-section">
						<a href="#nosotros" class="btn-default">Conoce mas</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="nosotros">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 wow fadeInLeft">
					<h2>¿Quiénes son miembros de Alcoholics Anonymous?</h2>
					<p>
						Somos personas que hemos <span class="highlight">descubierto
							y admitido</span> que no podemos controlar el alcohol. Hemos aprendido
						que tenemos que vivir sin él para vivir vidas normales y felices.
						No estamos en contra del alcohol y no queremos reformar el mundo.
						No estamos aliados con ningún grupo, causa u organización
						religiosa. <span class="highlight">Recibimos a nuevos
							miembros, pero no los reclutamos.</span> No imponemos nuestra
						experiencia con los problemas con la bebida a otros, <span
							class="highlight">pero la compartimos</span> cuando nos piden que
						lo hagamos. Sabemos que nuestra propia sobriedad depende de la
						conexión con otros alcohólicos.
					</p>
				</div>
				<div class="col-lg-6 feature-2-pic wow fadeInRight">
					<img src="images/reunion.jpg" class="img-fluid">
				</div>
			</div>
		</div>
	</div>
	<div id="vinculate">
		<div class="action fullscreen parallax"
			style="background-image: url('images/fondo.jpg');"
			data-img-width="2000" data-img-height="1333" data-diff="100">
			<div class="overlay">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 mr-auto ml-auto col-sm-12 text-lg-center">
							<h2 class="wow fadeInRight">Alcoholics anonymous</h2>
							<p class="download-text wow fadeInLeft">Si en repetidas
								ocasiones bebe más de lo que planea o quiere beber, puede que
								sea alcohólico. Sólo usted puede decidirlo.</p>
							<div class="download-cta wow fadeInLeft">
								<a href="#intro" class="btn-secondary">Vinculate</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer id="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 mr-auto ml-auto">
					<div class="text-center wow fadeInUp" style="font-size: 14px;">Copyright
						Alcoholics anonymous 2023</div>
					<div class="text-center wow fadeInUp" style="font-size: 14px;">Universidad
						El Bosque</div>
					<a href="#" class="scrollToTop"><i class="pe-7s-up-arrow pe-va"></i></a>
				</div>
			</div>
		</div>
	</footer>
	<!-- JavaScript Files -->
	<script src="js/jquery.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/custom.js"></script>
	<script>
		new WOW().init();
	</script>
</body>
</html>

