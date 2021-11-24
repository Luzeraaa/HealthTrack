package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.CategoriaAtividade;
import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.dao.CategoriaAtividadeDAO;
import br.com.healthtrack.dao.PesoDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AtividadeDAO atividadeDao;
	private CategoriaAtividadeDAO categoriaDao;
	private PesoDAO pesoDAO;
	



	private UsuarioDAO usuarioDao;

	public DashboardServlet() {
		atividadeDao = DAOFactory.getAtividadeDAO();
		categoriaDao = DAOFactory.getCategoriaDAO();
		pesoDAO = DAOFactory.getPesoDAO();
		
		usuarioDao = DAOFactory.getUsuarioDAO();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		switch (acao) {

		case "abrir-form-cadastro-atividade":
			abrirFormCadastroAtividade(request, response);
			break;
		
		case "abrir-form-cadastro-peso":
			abrirFormCadastroPeso(request, response);
			break;

		case "listar":
			listarUsuario(request, response);
			break;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {

		case "cadastrar":
			cadastrarAtividade(request, response);
			break;
			
		case "cadastrarPeso":
			cadastrarPeso(request, response);
			break;
		}
	}

	
	
	private void abrirFormCadastroAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		listarUsuario(request, response);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
	
	private void abrirFormCadastroPeso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		listarUsuario(request, response);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<CategoriaAtividade> lista = categoriaDao.listar();
		request.setAttribute("categorias", lista);
	}

	private void listarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> lista = usuarioDao.listar();
		request.setAttribute("usuarios", lista);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	

	
	// Cadastrar Atividade
	//
	//
	//
	//
	//
	private void cadastrarAtividade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int duracao = Integer.parseInt(request.getParameter("duracao"));
			double distancia = Double.parseDouble(request.getParameter("distancia"));
			int caloriasPerdidas = Integer.parseInt(request.getParameter("caloriasPerdidas"));

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataExercicio = Calendar.getInstance();
			dataExercicio.setTime(format.parse(request.getParameter("dataExercicio")));

			String nivel = request.getParameter("nivel");
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			int codigoUsuario = Integer.parseInt(request.getParameter("usuario"));

			CategoriaAtividade categoria = new CategoriaAtividade();
			categoria.setIdCategoria(codigoCategoria);

			Usuario usuario = new Usuario();
			usuario.setIdUsuario(codigoUsuario);

			Atividade atividade = new Atividade(0, duracao, caloriasPerdidas, dataExercicio, nivel, distancia);
			atividade.setCategoria(categoria);
			atividade.setUsuario(usuario);

			atividadeDao.cadastrar(atividade);

			request.setAttribute("msg", "Cadastrado com sucesso!");

		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao se conectar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}

		abrirFormCadastroAtividade(request, response);
	}
	
	
	
	
	//Cadastrar Peso
	//
	//
	//
	//
	
	
	private void cadastrarPeso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			double pesoAtual = Double.parseDouble(request.getParameter("peso"));
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataPeso = Calendar.getInstance();
			dataPeso.setTime(format.parse(request.getParameter("pesoData")));
			
			int codigoUsuario = Integer.parseInt(request.getParameter("usuario"));
			
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(codigoUsuario);
			
			
			Peso peso = new Peso(0, pesoAtual, dataPeso);
			peso.setUsuario(usuario);
			pesoDAO.cadastrar(peso);
			
			
			request.setAttribute("msg", "Cadastrado com sucesso!");
			
		}catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao se conectar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}
		
		
		abrirFormCadastroPeso(request, response);
	}

	
	
}
