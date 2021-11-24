<!-- Modal Alimentacao -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade " id="modal-alimentacao"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel " aria-hidden="true">
	<section
		class="modal-dialog modal-dialog-centered modal-dialog-scrollable   ">
		<article class="modal-content">
			<header class="modal-header ">
				<h5 class="modal-title" id="tituloModal">Adicionar Alimentação</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</header>
			<div class="modal-body">
				<section class="d-flex justify-content-center fw-bold">

					<!-- Formulario do modal -->
					<form action="saude" method="post">
						<input type="hidden" value="cadastrarRefeicao" name="acao">



						<!-- Categoria alimento -->
						<article class="col-md-12 pb-3  ">
							<label for="nivelExercicio" class="form-label">Alimentação </label>
							<select class="form-control" name="categoria">
								<option value="0">Selecione</option>

								<c:forEach items="${alimentos}" var="c">
									<option value="${c.idCategoria }">${c.categoria}</option>
								</c:forEach>


							</select>
						</article>

						<!-- Descrição da alimentação -->
						<article class="col-md-12 pb-3  ">
							<label for="duracaoExercicio" class="form-label">Refeição
								Realizada realizada</label> <input type="text" id="alimentacaoRealizada"
								name="refeicao"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								required>
						</article>

						<!-- Calorias -->
						<article class="col-md-12 pb-3  ">
							<label for="distanciaExercicio" class="form-label">Calorias
								aproximadas (Apenas números e vírgula)</label> <input type="text"
								id="calorias" name="calorias"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								aria-describedby="emailHelp" required>
						</article>

						<!-- Data alimentação -->
						<article class="col-md-12 pb-3  ">
							<label for="dataExercicio" class="form-label">Data da
								alimentação </label> <input type="text" id="dataExercicio"
								name="dataAlimentacao"
								class="form-control border-bottom-1 border-2 border-top-0 border-end-0 border-start-0 rounded-0 input-login shadow-none"
								required>
						</article>

						<!-- Usuario -->
						<article class="col-md-12 pb-3  ">
							<label for="pressao" class="form-label">Usuario </label><br>
							<select name="usuario" id="id-usuario-alimentacao" class="form-control">
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