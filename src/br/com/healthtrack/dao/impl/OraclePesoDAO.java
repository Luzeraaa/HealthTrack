package br.com.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.PesoDAO;
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.singleton.ConnectionManager;

public class OraclePesoDAO implements PesoDAO {
	
	private Connection conexao;

	@Override
	public void cadastrar(Peso peso) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_PESO (ID_PESO, DT_PESO, DS_PESO, T_USUARIO_ID_USUARIO) VALUES (SQ_T_PESO.NEXTVAL, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			
			java.sql.Date data = new java.sql.Date(peso.getData().getTimeInMillis());
			stmt.setDate(1, data);
			stmt.setDouble(2,  peso.getPesoInicial());	
			stmt.setDouble(3,  peso.getUsuario().getIdUsuario());	
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
	public void atualizar(Peso peso) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "UPDATE T_PESO SET DT_PESO = ?, DS_PESO = ?, T_USUARIO_ID_USUARIO = ? WHERE ID_PESO = ?";
			stmt = conexao.prepareStatement(sql);

			java.sql.Date data = new java.sql.Date(peso.getData().getTimeInMillis());
			stmt.setDate(1, data);
			stmt.setDouble(2,  peso.getPesoInicial());
			stmt.setInt(3,  peso.getUsuario().getIdUsuario());
			stmt.setInt(4,  peso.getIdPeso());
			
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
			
			String sql = "DELETE FROM T_PESO WHERE ID_PESO = ?";
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
	public Peso buscar(int id) {

		Calendar data = Calendar.getInstance();
		Peso peso = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM T_PESO WHERE ID_PESO = ?");
			
			stmt.setInt(1,  id);
			rs = stmt.executeQuery(); 
			
			if (rs.next()) {
				int idPeso = rs.getInt("ID_PESO");
				double dsPeso = rs.getDouble("DS_PESO");
				java.util.Date javaDate = rs.getTimestamp("DT_PESO");
				data.setTime(javaDate);
				int idUsuario = rs.getInt("T_USUARIO_ID_USUARIO");

				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(idUsuario);
				
	
				peso = new Peso(idPeso, dsPeso, data);
				peso.setUsuario(usuario);
				
			}			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peso;
	}


	@Override
	public List<Peso> listar() {
		
		Calendar data = Calendar.getInstance();
		Peso peso = null;
		List<Peso> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			stmt = conexao.prepareStatement("SELECT * FROM T_PESO");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int idPeso = rs.getInt("ID_PESO");
				double dsPeso = rs.getDouble("DS_PESO");
				java.util.Date javaDate = rs.getTimestamp("DT_PESO");
				data.setTime(javaDate);
				int idUsuario = rs.getInt("T_USUARIO_ID_USUARIO");
				
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(idUsuario);
				
					
				peso = new Peso(idPeso, dsPeso, data);
				peso.setUsuario(usuario);
				lista.add(peso);
			}			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	
	
	@Override
	public double getPesoInicial(){

		Peso peso = new Peso();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		 try {
			 
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT * FROM T_PESO WHERE T_USUARIO_ID_USUARIO = 1 ORDER BY DT_PESO ASC FETCH FIRST 1 ROWS ONLY");
			rs = stmt.executeQuery();

			while(rs.next()) {
				double pesoInicial = rs.getDouble("DS_PESO");
				
				peso.setPesoInicial(pesoInicial);
				
			}

		
		} catch (SQLException e) {
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
		return peso.getPesoInicial();
	}
	
	
	@Override
	public double getPesoFinal(){

		Peso peso = new Peso();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		 try {
			 
			conexao = ConnectionManager.getInstance().getConnection();

			stmt = conexao.prepareStatement("SELECT * FROM T_PESO WHERE T_USUARIO_ID_USUARIO = 1 ORDER BY DT_PESO DESC FETCH FIRST 1 ROWS ONLY");
			rs = stmt.executeQuery();

			while(rs.next()) {
				double pesoAtual = rs.getDouble("DS_PESO");
				
				peso.setPesoAtual(pesoAtual);
				
			}

		
		} catch (SQLException e) {
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
		return peso.getPesoAtual();
	}
	
	
	
	
	
}