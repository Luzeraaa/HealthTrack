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
import br.com.healthtrack.bean.CategoriaRefeicao;
import br.com.healthtrack.bean.Pressao;
import br.com.healthtrack.bean.Refeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.dao.CategoriaRefeicaoDAO;
import br.com.healthtrack.dao.PesoDAO;
import br.com.healthtrack.dao.PressaoDAO;
import br.com.healthtrack.dao.RefeicaoDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;


@WebServlet("/saude")
public class SaudeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	private RefeicaoDAO refeicaoDao;
	private CategoriaRefeicaoDAO categoriaRefeicaoDao;
	private PressaoDAO pressaoDAO;
	private AtividadeDAO atividadeDAO;
	private PesoDAO pesoDAO;
	private UsuarioDAO usuarioDao;
	
	
	
    public SaudeServlet() {
    	refeicaoDao = DAOFactory.getRefeicaoDAO();
    	categoriaRefeicaoDao = DAOFactory.getCategoriaRefeicaoDAO();
		pressaoDAO = DAOFactory.getPressaoDAO();
    	atividadeDAO = DAOFactory.getAtividadeDAO();
		pesoDAO = DAOFactory.getPesoDAO();
    	usuarioDao = DAOFactory.getUsuarioDAO();
    	
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String acao = request.getParameter("acao");
        	
        	switch(acao) {
     
        		
        	case "abrir-form-cadastro-refeicao":
        		abrirFormCadastroRefeicao(request, response);
        		
        		break;
        		
        	case "abrir-form-cadastro-pressao":
        		
        		abrirFormCadastroPressao(request, response);
        		
        		break;
        	
        	}
    		
    	}
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String acao = request.getParameter("acao");

    	switch(acao) {
    	
    	case "cadastrarRefeicao":
    		cadastrarRefeicaoSaude(request, response);
    		break;
    		
    	case "cadastrarPressao":
    		cadastrarPressaoSaude(request, response);
    		break;
    	}
	}

	
	
	private void abrirFormCadastroRefeicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		atualizarValores(request, response);		
		request.getRequestDispatcher("saude.jsp").forward(request, response);
	}
	

	private void abrirFormCadastroPressao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		atualizarValores(request, response);
		request.getRequestDispatcher("saude.jsp").forward(request, response);
	}

	

	
	private void atualizarValores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Pressao> lista = pressaoDAO.listar();
		request.setAttribute("pressao", lista);
		
		List<Usuario>  usuario = usuarioDao.listar();
		request.setAttribute("usuarios", usuario);
		
		List<CategoriaRefeicao> alimentos = categoriaRefeicaoDao.listar();
		request.setAttribute("alimentos", alimentos);
		
		
		request.setAttribute("caloriaGasta", atividadeDAO.listarCaloriasGastas());
		request.setAttribute("caloriaIngerida", refeicaoDao.listarCaloriasIngeridas());
		
		request.setAttribute("pesoInicial", pesoDAO.getPesoInicial());
		request.setAttribute("pesoFinal", pesoDAO.getPesoFinal());
		
		request.setAttribute("distanciaPercorrida", atividadeDAO.getDistanciaPercorrida());
		request.setAttribute("categoriaPraticada", atividadeDAO.getCategoriaPraticada());
		
		
		
		request.getRequestDispatcher("saude.jsp").forward(request, response);

	}

	
	
	
	
	
	
	private void cadastrarRefeicaoSaude(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			String refeicao = request.getParameter("refeicao");
			double calorias = Double.parseDouble(request.getParameter("calorias"));

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataRefeicao = Calendar.getInstance();
			dataRefeicao.setTime(format.parse(request.getParameter("dataAlimentacao")));
	
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			int codigoUsuario = Integer.parseInt(request.getParameter("usuario"));
			
			
			CategoriaRefeicao categoria = new CategoriaRefeicao();
			categoria.setIdCategoria(codigoCategoria);
			
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(codigoUsuario);
			
			
			Refeicao refeicaoFeita = new Refeicao(0,refeicao,calorias, dataRefeicao);
			refeicaoFeita.setIdCategoriaRefeicao(categoria);
			refeicaoFeita.setUsuario(usuario);
			
			refeicaoDao.cadastrar(refeicaoFeita);

			
			request.setAttribute("msgSaude", "Produto Cadastrado com sucesso!");
			
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erroSaude", "Erro ao se conectar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erroSaude", "Por favor, valide os dados.");
			System.out.println();
		}

		abrirFormCadastroRefeicao(request, response);
	}
	
	

	private void cadastrarPressaoSaude(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			
			int diastolica = Integer.parseInt(request.getParameter("diastolica"));
			int sistolica = Integer.parseInt(request.getParameter("sistolica"));

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataPressao = Calendar.getInstance();
			dataPressao.setTime(format.parse(request.getParameter("dataPressao")));
	
			int codigoUsuario = Integer.parseInt(request.getParameter("usuario"));
		
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(codigoUsuario);
			
			
			Pressao pressao = new Pressao(0, diastolica, sistolica, dataPressao);
			pressao.setUsuario(usuario);
			pressaoDAO.cadastrar(pressao);


			request.setAttribute("msgSaude", "Cadastrado com sucesso!");
			
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erroSaude", "Erro ao se conectar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erroSaude", "Por favor, valide os dados.");
		}

		abrirFormCadastroPressao(request, response);
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	

}



















