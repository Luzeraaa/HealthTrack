<%@ page import="br.com.healthtrack.bean.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="pt">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>
<meta charset="ISO-8859-1">
<link rel="shortcut icon" href="resources/imagens/logoHealthTrack.png"
	type="image/x-icon">
<link rel=" canonical "
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

<title>Perfil - Health Track</title>
</head>

<body>
	<!-- navbar -->
	<%@include file="menu.jsp"%>

	<c:if test="${not empty msgSaude}">
		<div class="mt-5 alert alert-success justify-content-center">${msgSaude}</div>
	</c:if>

	<c:if test="${not empty erroSaude }">

		<div class="mt-5 alert alert-danger">${erroSaude}</div>

	</c:if>


	<main class="container-fluid mt-4 ">


		<div class="row botao-login rounded-3 shadow ">
			<div class="col">

				<img class="img-perfil" src="resources/imagens/perfil.png">

			</div>
			<div class="col-6">

				<h1 class="mt-3">Lucas Mendes da Silva Conceição</h1>
				<br> <br> <br>
				<h2>
					Status: <span class="bg-success text-light pe-2 ps-2 rounded-3">
						Saudável</span>
				</h2>


			</div>
		</div>

		<br>
		<div class="row justify-content-center mt-5">

			<div class="col-md-3 mb-3">
				<div class="card-border ">
					<ul class="list-group list-group-flush text-center ">
						<li class="list-group-item fw-bold">Peso Inicial <br>
							<h3 class="text-danger">
							
							${pesoFinal}
							
							</h3>
						</li>


						<li class="list-group-item fw-bold">Peso Atual <br>
							<h3 class="text-success">${pesoInicial }</h3>
						</li>

					</ul>
				</div>
			</div>

			<div class="col-md-3 mb-3 ">
				<div class="card-border">
					<ul class="list-group list-group-flush text-center">
						<li class="list-group-item fw-bold">Pressão Sistólica <br>
							<h3 class="text-danger">

								<c:forEach items="${pressao}" var="c">
								${c.diastolica}
									
								</c:forEach>

							</h3>
						</li>
						<li class="list-group-item fw-bold">Pressão Diastólica <br>
							<h3 class="text-success">
								<c:forEach items="${pressao}" var="c">
									${c.sistolica}
									
								</c:forEach>

							</h3>
						</li>
					</ul>
				</div>
			</div>

			<div class="col-md-3 mb-3">
				<div class="card-border">
					<ul class="list-group list-group-flush text-center">
						<li class="list-group-item fw-bold ">Distancia Percorrida <br>
							<h3 class="text-success">${distanciaPercorrida }</h3>
						</li>


						<li class="list-group-item fw-bold">Modalidade Mais Praticada
							<br>
							<h3 class="text-success">${categoriaPraticada }</h3>
						</li>

					</ul>
				</div>
			</div>

			<div class="col-md-3 mb-3">
				<div class=" card-border ">
					<ul class="list-group list-group-flush text-center">
						<li class="list-group-item fw-bold">Total Calorias Ingeridas
							<br>
							<h3 class="text-danger">
							${caloriaIngerida}
							
							</h3>
						</li>
						<li class="list-group-item fw-bold">Total Calorias Perdidas <br>
							<h3 class="text-success">
							
									${caloriaGasta}
									
							</h3>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<%@include file="cardsSaude.jsp"%>



	</main>

	<footer class="bg-dark text-light text-center fixed-bottom">
		Health Track Inc. © </footer>





	<%@include file="modal/refeicao.jsp"%>
	<%@include file="modal/pressao.jsp"%>
	<%@include file="bootstrap/js.jsp"%>

	<!-- Scripts do bootstrap -->

	<%@include file="bootstrap/js.jsp"%>

</body>

</html>