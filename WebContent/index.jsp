<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="pt">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
	rel="stylesheet">
<!-- Bootstrap CSS -->
<%@include file="bootstrap/css.jsp"%>

<link rel="stylesheet" type="text/css" href="resources/style.css" />


<title>Health Track</title>
</head>

<body>

	<c:if test="${not empty msg}">
		<div class="alert alert-success">${msg }</div>
	</c:if>

	<c:if test="${not empty erro }">

		<div class="alert alert-danger">${erro}</div>

	</c:if>
	<main class="container-fluid">
		<br>
		
		<!-- Logo health Track -->
		<section class="row justify-content-evenly ">
			<section class="col-sm-9 col-md-6 text-center">
				<figure>
					<img src="resources/imagens/logoHealthTrack.png"
						class="img-fluid img-logo " alt="logo Health Track">
				</figure>

				<h1 class="texto-especial">Health Track</h1>
			</section>

			<!-- Formulario de login -->
			<div class=" pt-5 col-sm-12 col-md-4  ">
				<form method="post" action="usuario">
					<div class="mb-4 col-sm-12 col-md-8  ">
						<label for="login"  class="form-labe fw-bold">Usuário</label> <input placeholder="usuario@teste.com"
							type="email"
							class="form-control box-shadow-none  border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login "
							required id="emailLogin" aria-describedby="emailHelp">

					</div>

					<div class="mb-4 col-sm-12 col-md-8">
						<label for="senha" class="form-label fw-bold ">Senha</label> <input  placeholder="teste123"
							type="password"
							class="form-control box-shadow-none border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login"
							required id="senhaLogin">
					</div>

					<span class="mb-3 form-check "> <input type="checkbox"
						class="form-check-input" id="conectado"> <label
						class="form-check-label " for="exampleCheck1">Manter
							conectado</label>
					</span>
					<p>
						Não possui uma conta?<a href="#" class="text-decoration-none"
							data-bs-toggle="modal" data-bs-target="#modal-cadastro">
							Clique aqui</a>
					</p>

					<div class="d-flex justify-content-center col-sm-4 col-md-8">
						<a href="dashboard?acao=abrir-form-cadastro-atividade"><button id="botaoLogin" type="button"
								class="btn  botao-login box-shadow-none">Entrar</button></a>
					</div>

				</form>
			</div>
			<!-- Fim do Formulario -->
		</section>
	</main>

	<!-- Modal -->
	<div class="modal fade " id="modal-cadastro" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel " aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
			<section class="modal-content">
				<header class="modal-header">
					<h5 class="modal-title" id="tituloModal">Cadastro</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</header>
				<main class="modal-body">
					<section class="d-flex justify-content-center fw-bold">
					
					
						<form action="usuario" method="post">
							<input type="hidden" value="cadastrar" name="acao">



							<article class="col-md-12 pb-3  ">
								<label for="usuario" class="form-label">Nome de usuário</label>
								<div class="input-group">
									<input type="text" id="validarUsuario" name="nome"
										class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none  "
										required>
								</div>
							</article>

							<article class="col-md-12 pb-3  ">
								<label for="email" class="form-label">E-mail</label> <input
									type="text" id="emailCadastro" name="email"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									required>
								<div id="emailHelp" class="form-text">Nunca
									compartilharemos seu e-mail com ninguém.</div>
							</article>

							<article class="col-md-12 pb-3  ">
								<label for="senhaCadastro" class="form-label">Senha</label> <input
									type="password" id="senhaCadastro" name="senha"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none "
									aria-describedby="passwordHelpBlock" required>
							</article>
							
							<article class="col-md-12 pb-3  ">
								<label for="sexo" class="form-label">sexo
								</label> <input type="text" id="sexo" name="sexo"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									data-tipo="dataNascimento" required>
							</article>


							<article class="col-md-12 pb-3  ">
								<label for="nascimento" class="form-label">Data de
									Nascimento</label> <input type="text" id="nascimento" name="nascimento"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									data-tipo="dataNascimento" required>
							</article>

							<article class="col-md-12 pb-3  ">
								<label for="altura" class="form-label">Altura</label> <input
									type="text" id="altura" name="altura"
									class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login box-shadow-none"
									data-tipo="dataNascimento" required>
							</article>

							<footer class="modal-footer">
								<button type="reset" value="reset" class="btn botao-login">Limpar</button>
								<input type="submit" class="btn botao-login" value="Cadastrar">
								

							</footer>
						</form>


					</section>

				</main>

			</section>

		</div>

	</div>



	<%@include file="bootstrap/js.jsp"%>

</body>

</html>











