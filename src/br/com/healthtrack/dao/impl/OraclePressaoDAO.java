package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.healthtrack.bean.Pressao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.PressaoDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OraclePressaoDAO implements PressaoDAO {
	
	private Connection conexao;

	@Override
	public void cadastrar(Pressao pressao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_PRESSAO (ID_PRESSAO, PR_DIASTOLICA, PR_SISTOLICA, DT_PRESSAO, T_USUARIO_ID_USUARIO)"
					+ " VALUES (SQ_T_PRESSAO.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			
			
			stmt.setInt(1,  pressao.getDiastolica());	
			stmt.setInt(2,  pressao.getSistolica());	
			java.sql.Date data = new java.sql.Date(pressao.getData().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, pressao.getUsuario().getIdUsuario());
			stmt.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Pressao pressao) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "UPDATE T_PRESSAO SET PR_DIASTOLICA = ?, PR_SISTOLICA = ?, DT_PRESSAO = ?, T_USUARIO_ID_USUARIO = ? WHERE ID_PRESSAO = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setInt(1,  pressao.getDiastolica());	
			stmt.setInt(2,  pressao.getSistolica());	
			java.sql.Date data = new java.sql.Date(pressao.getData().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, pressao.getUsuario().getIdUsuario());
			stmt.setInt(5, pressao.getIdPressao());
			
			stmt.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void remover(int codigo) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "DELETE FROM T_PRESSAO WHERE ID_PRESSAO = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1,  codigo);
			
			stmt.executeUpdate(); 
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new DBException("Erro ao deletar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Pressao buscar(int id) {

		Calendar data = Calendar.getInstance();
		Pressao pressao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM T_PRESSAO WHERE ID_PRESSAO = ?");
			
			stmt.setInt(1,  id);
			rs = stmt.executeQuery(); 
			
			if (rs.next()) {
				int idPressao = rs.getInt("ID_PRESSAO");
				int pressaoDiastolica = rs.getInt("PR_DIASTOLICA");
				int pressaoSistolica = rs.getInt("PR_SISTOLICA");
				
				java.sql.Date dataMedida = rs.getDate("DT_PRESSAO");
				Calendar dataPressao = Calendar.getInstance();
				dataPressao.setTimeInMillis(dataMedida.getTime());
				
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String nome = rs.getString("NM_USUARIO");
				String sexo = rs.getString("SEX_USUARIO");
				String email = rs.getString("DS_EMAIL");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date dataNasc = rs.getDate("DT_NASCIMENTO");
				Calendar nascimento = Calendar.getInstance();
				nascimento.setTimeInMillis(dataNasc.getTime());
				float altura = rs.getFloat("NR_ALTURA");
				
				Usuario usuario = new Usuario(idUsuario, nome, sexo, email, senha, nascimento, altura);
				
				
				
				pressao = new Pressao(idPressao, pressaoDiastolica, pressaoSistolica, data);
				pressao.setUsuario(usuario);
			}			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pressao;
	}


	@Override
	public List<Pressao> listar() {
		

		Calendar data = Calendar.getInstance();
		List<Pressao> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM T_PRESSAO WHERE T_USUARIO_ID_USUARIO = 1 "
					+ "ORDER BY DT_PRESSAO DESC FETCH FIRST 1 ROWS ONLY");
		
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				int idPressao = rs.getInt("ID_PRESSAO");
				int pressaoDiastolica = rs.getInt("PR_DIASTOLICA");
				int pressaoSistolica = rs.getInt("PR_SISTOLICA");
				
				java.sql.Date dataMedida = rs.getDate("DT_PRESSAO");
				Calendar dataPressao = Calendar.getInstance();
				dataPressao.setTimeInMillis(dataMedida.getTime());
	
				int cdUsuario = rs.getInt("T_USUARIO_ID_USUARIO");
				Usuario usuario = new Usuario();

				Pressao pressao = new Pressao(idPressao, pressaoDiastolica, pressaoSistolica, data);
				usuario.setIdUsuario(cdUsuario);
				pressao.setUsuario(usuario);
				lista.add(pressao);
			}		
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}



}