<!-- Modal Atividade -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="modal fade " id="modal-atividade" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel " aria-hidden="true">
	<section
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<article class="modal-content">

			<header class="modal-header ">
				<h5 class="modal-title" id="tituloModal">Adicionar exercício</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</header>

			<div class="modal-body">
				<section class="d-flex justify-content-center fw-bold">

					<!-- Formulario do modal -->
					<form action="dashboard" method="post">
						<input type="hidden" value="cadastrar" name="acao">


						<!-- Tipo de exercicio -->
						<article class="col-md-12 pb-3  ">
							<label for="nivelExercicio" class="form-label">Tipo de
								Exercício</label><br> <select name="categoria" id="id-categoria-exercicio"
								class="form-control">
								<option value="0">Selecione</option>

								<c:forEach items="${categorias}" var="c">
									<option value="${c.idCategoria }">${c.categoria}</option>
								</c:forEach>


							</select>
						</article>

						<!-- Duração -->
						<article class="col-md-12 pb-3  ">
							<label for="duracaoExercicio" class="form-label">Duração</label>
							<input type="text" id="duracaoExercicio" name="duracao"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								required>
						</article>

						<!-- Distância -->
						<article class="col-md-12 pb-3  ">
							<label for="distanciaExercicio" class="form-label">Distância</label>
							<input placeholder="Km" type="text" id="distanciaExercicio"
								name="distancia"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								aria-describedby="emailHelp" required>
						</article>

						<!-- Calorias -->
						<article class="col-md-12 pb-3  ">
							<label for="distanciaExercicio" class="form-label">Calorias
								Perdidas</label> <input placeholder="KCal" type="text"
								id="distanciaExercicio" name="caloriasPerdidas"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								aria-describedby="emailHelp" required>
						</article>



						<!-- Data exercicio -->
						<article class="col-md-12 pb-3  ">
							<label for="dataExercicio" class="form-label">Data do
								exercicio</label> <input placeholder="dd/MM/yyyy" type="text" id="dataExercicio"
								name="dataExercicio"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								required>
						</article>



						<!-- Nivel -->
						<article class="col-md-12 pb-3  ">
							<label for="distanciaExercicio" class="form-label">Nível
								<br> Baixo - Moderado - Alto
							</label> <input type="text" id="distanciaExercicio"
								name="nivel"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								aria-describedby="emailHelp" required>
						</article>
						
						<!-- Usuario -->
						<article class="col-md-12 pb-3  ">
							<label for="nivelExercicio" class="form-label">Usuario </label><br>
							<select name="usuario" id="id-usuario-exercicio" class="form-control">
								<option value="0">Selecione</option>

								<c:forEach items="${usuarios}" var="c">
									<option value="${c.idUsuario }">${c.nome}</option>
								</c:forEach>


							</select>
						</article>


						<footer class="modal-footer">
							<button type="reset" value="reset" class="btn botao-login">Limpar</button>
							<input type="submit" class="btn botao-login" value="Cadastrar">


						</footer>

					</form>
				</section>
			</div>
		</article>
	</section>

</div>
