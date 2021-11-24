<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="pt">

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

	<c:if test="${not empty msg}">
		<div class="mt-5 alert alert-success justify-content-center">${msg}</div>
	</c:if>

	<c:if test="${not empty erro }">

		<div class="mt-5 alert alert-danger">${erro}</div>

	</c:if>
	<main class="container">
		<figure>
			<img alt="perfil" src="resources/imagens/perfil.png"
				class="mt-5 pt-5 rounded mx-auto d-block img-perfil">
		</figure>
		<h1 class="text-center">Giulia Vargas</h1>

		<section class="d-flex justify-content-center mt-5 ">
			<form action="perfil" method="post">
							<input type="hidden" value="editar" name="acao">



							<article class="col-md-12 pb-3  ">
								<label for="usuario" class="form-label"></label>
								<div class="input-group">
									<input type="text" id="validarUsuario" value="${usuario.nome}" name="nome"
										class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none  "
										required>
								</div>
							</article>

							<article class="col-md-12 pb-3  ">
								<label for="email" class="form-label"></label> <input
									type="text" id="emailCadastro" name="email" value="${usuario.email}"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									required>
							</article>

							<article class="col-md-12 pb-3  ">
								<label for="senhaCadastro" class="form-label"></label> <input
									type="password" id="senhaCadastro" name="senha" value="${usuario.senha}"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none "
									aria-describedby="passwordHelpBlock" required>
							</article>
							
							<article class="col-md-12 pb-3  ">
								<label for="sexo" class="form-label">
								</label> <input type="text" id="sexo" name="sexo" value="${usuario.sexo}"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									data-tipo="dataNascimento" required>
							</article>


							<article class="col-md-12 pb-3  ">
								<label for="nascimento" class="form-label"></label> <input type="text" id="nascimento"
								 name="nascimento" value="<fmt:formatDate value="${usuario.dataNascimento.time}" pattern="dd/MM/yyyy"/>"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									data-tipo="dataNascimento" required>
							</article>

							<article class="col-md-12 pb-3  ">
								<label for="altura" class="form-label"></label> <input value="${usuario.altura}"
									type="text" id="altura" name="altura"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									data-tipo="dataNascimento" required>
							</article>

							<footer class="modal-footer">
								<input type="submit" class="btn botao-login" value="Editar">
								

							</footer>
						</form>

		</section>



	</main>



	<%@include file="bootstrap/js.jsp"%>

</body>

</html>