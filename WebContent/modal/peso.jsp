<!-- Modal Peso -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade " id="modal-peso" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel " aria-hidden="true">
	<section
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable   ">
		<article class="modal-content">
			<header class="modal-header ">
				<h5 class="modal-title" id="tituloModal">Adicionar Peso</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</header>
			<div class="modal-body">
				<section class="d-flex justify-content-center fw-bold">

					<!--  Formulario do modal -->
					<form action="dashboard" method="post">
						<input type="hidden" value="cadastrarPeso" name="acao">

						<!-- Peso -->
						<article class="col-md-12 pb-3  ">
							<label for="sistolica" placeholder="Kg" class="form-label">Peso</label> 
							<input type="text" id="peso"
								name="peso"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								required>
						</article>
						
						
						<!-- Data peso -->
						<article class="col-md-12 pb-3  ">
							<label for="sistolica" class="form-label">Data do Peso</label> 
							<input type="text" id="peso"
								name="pesoData"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								required>
						</article>


						<!-- Usuario -->
						<article class="col-md-12 pb-3  ">
							<label for="pressao" class="form-label">Usuario </label><br>
							<select name="usuario" id="id-usuario-alimentacao"
								class="form-control">
								<option value="0">Selecione</option>

								<c:forEach items="${usuarios}" var="c">
									<option value="${c.idUsuario }">${c.nome}</option>
								</c:forEach>


							</select>
						</article>


						<footer class="modal-footer">
							<button type="reset" value="reset" class="btn botao-login">Limpar</button>
							<input type="submit" class="btn botao-login" value="Cadastar">
						</footer>

					</form>

				</section>
			</div>
		</article>
	</section>
</div>