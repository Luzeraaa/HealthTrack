<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="pt">

<head>
<!-- Clock -->

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="stylesheet.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />



<meta charset="ISO-8859-1">
<link rel="shortcut icon" href="resources/imagens/logoHealthTrack.png"
	type="image/x-icon">
<link rel="canonical"
	href=" https://getbootstrap.com/docs/5.0/examples/dashboard/">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
	rel="stylesheet">
<!-- Bootstrap CSS -->
<%@include file="bootstrap/css.jsp"%>

<link rel="stylesheet" type="text/css" href="resources/style.css" />

<title>Dashboard - Health Track</title>
</head>

<body>




	<!-- navbar -->
	<%@ include file="menu.jsp"%>


	<c:if test="${not empty msg}">
		<div class="mt-5 alert alert-success justify-content-center">${msg}</div>
	</c:if>

	<c:if test="${not empty erro }">

		<div class="mt-5 alert alert-danger">${erro}</div>

	</c:if>


	<main class="margin-top-dashboard">


		<!-- Cronometro  -->
		<section id="tempoAtivo"
			class="d-flex align-items-center flex-column bd-highlight mb-3">
			<h1 class="mb-auto p-2 bd-highlight">Tempo ativo</h1>
			<figure class="p-2 bd-highlight">
				<img src="resources/imagens/cronometro.png" class="timer" />
			</figure>
		</section>


		<!-- Cards de interação com usuário -->
		<%@include file="cardsDashboard.jsp"%>


	</main>

	<footer class="bg-dark text-light text-center"> Health Track
		Inc. © </footer>



	<%@include file="modal/peso.jsp"%>
	<%@include file="modal/atividade.jsp"%>







	<!-- Scripts do bootstrap -->

	<%@include file="bootstrap/js.jsp"%>



</body>

</html>