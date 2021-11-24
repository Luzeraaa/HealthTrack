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

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class PerfilServlet
 */
@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDao;

	public PerfilServlet() {
		usuarioDao = DAOFactory.getUsuarioDAO();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		switch (acao) {
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
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
		case "editar":
			editar(request, response);
			break;
		}

	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = usuarioDao.buscar(1);
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("perfil.jsp").forward(request, response);
	}

	private void listarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> lista = usuarioDao.listar();
		request.setAttribute("usuarios", lista);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	
	


	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String nome = request.getParameter("nome");
			String sexo = request.getParameter("sexo");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dtNascimento = Calendar.getInstance();
			dtNascimento.setTime(format.parse(request.getParameter("nascimento")));
			double altura = Double.parseDouble(request.getParameter("altura"));
			

			Usuario usuario = new Usuario(0, nome, sexo, email, senha, dtNascimento, altura);
			usuarioDao.atualizar(usuario);
	
			request.setAttribute("msg", "Usuario atualizado com sucesso!");
			
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao se conectar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados.");
		}

		listarUsuario(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
